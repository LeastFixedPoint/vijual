package info.reflectionsofmind.vijual.core.util;

import info.reflectionsofmind.vijual.core.exception.TypingException;
import info.reflectionsofmind.vijual.core.kind.IKind;
import info.reflectionsofmind.vijual.core.kind.KDefined;
import info.reflectionsofmind.vijual.core.type.IType;
import info.reflectionsofmind.vijual.core.type.ITypeConstructed;
import info.reflectionsofmind.vijual.core.type.ITypeConstructor;
import info.reflectionsofmind.vijual.core.type.ITypeConstructorConstructed;
import info.reflectionsofmind.vijual.core.type.ITypeDefined;
import info.reflectionsofmind.vijual.core.type.ITypeDefinedConstructed;
import info.reflectionsofmind.vijual.core.type.ITypeVariable;
import info.reflectionsofmind.vijual.core.type.TVariable;
import info.reflectionsofmind.vijual.library.type.function.TFunction;
import info.reflectionsofmind.vijual.library.type.function.TFunctionConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class Types
{
	private Types()
	{
		throw new UnsupportedOperationException();
	}

	// ============================================================================================
	// === TYPE DECOMPOSITION
	// ============================================================================================

	public static ITypeConstructor<?, ?> getRootConstructor(final ITypeConstructed<?, ?> type)
	{
		final ITypeConstructor<?, ?> constructor = type.getConstructor();
		return constructor instanceof ITypeConstructed ? getRootConstructor((ITypeConstructed<?, ?>) constructor) : constructor;
	}

	public static List<IType<?>> getTypeComponents(final ITypeConstructed<?, ?> constructedType)
	{
		final List<IType<?>> componentTypes = new ArrayList<IType<?>>();
		componentTypes.add(constructedType.getArgument());

		if (constructedType.getConstructor() instanceof ITypeConstructed<?, ?>) componentTypes.addAll(getTypeComponents(constructedType));

		return componentTypes;
	}

	public static TFunction constructFunction(final ITypeDefined resType, final ITypeDefined... argTypes)
	{
		TFunction tfunction = new TFunction(argTypes[argTypes.length - 1], resType);

		for (int i = argTypes.length - 2; i >= 0; i--)
			tfunction = new TFunction(argTypes[i], tfunction);

		return tfunction;
	}

	public static ITypeDefined getResType(final ITypeDefinedConstructed<KDefined> function)
	{
		if (getRootConstructor(function) != TFunctionConstructor.INSTANCE) throw new RuntimeException();
		return (ITypeDefined) function.getArgument();
	}

	@SuppressWarnings("unchecked")
	public static ITypeDefined getArgType(final ITypeDefinedConstructed<KDefined> function)
	{
		if (getRootConstructor(function) != TFunctionConstructor.INSTANCE) throw new RuntimeException();
		return (ITypeDefined) ((ITypeConstructorConstructed<KDefined, KDefined, KDefined>) function.getConstructor()).getArgument();
	}

	public static ITypeDefined resolve(final TFunction function, final ITypeDefined argument) throws TypingException
	{
		return (ITypeDefined) applySubstitutions(solve(getArgType(function), argument), getResType(function));
	}

	public static List<TypeEquation<?>> solve(final ITypeDefined left, final ITypeDefined right)
	{
		final List<TypeEquation<?>> solution = new ArrayList<TypeEquation<?>>();

		Set<TypeEquation<?>> eqs = unify(left, right);

		while (true)
		{
			final TypeEquation<?> substitution = getSubstitution(eqs);
			if (substitution == null) break;

			final Set<TypeEquation<?>> newEqs = new HashSet<TypeEquation<?>>();

			for (final TypeEquation<?> eq : eqs)
				newEqs.addAll(unify(applySubstitution(substitution, eq.getVar()), applySubstitution(substitution, eq.getType())));

			solution.add(substitution);
			eqs = newEqs;

			if (!solvable(eqs)) throw new TypingException("Type equations are unsolvable: " + Arrays.toString(eqs.toArray()));
		}

		solution.addAll(eqs);

		return solution;
	}

	private static TypeEquation<?> getSubstitution(final Set<TypeEquation<?>> eqs)
	{
		for (final TypeEquation<?> substitution : eqs)
		{
			int numberOfSubstitutions = 0;

			for (final TypeEquation<?> equation : eqs)
				if (equation.getVar() == substitution.getVar() || // 
						contains(equation.getType(), (ITypeVariable<?>) substitution.getVar())) numberOfSubstitutions++;

			if (numberOfSubstitutions > 1) return substitution;
		}

		return null;
	}

	private static boolean solvable(final Set<TypeEquation<?>> eqs)
	{
		for (final TypeEquation<?> equation : eqs)
			if (!equation.solvable()) return false;

		return true;
	}

	public static <KKind extends IKind> IType<KKind> applySubstitutions(final List<TypeEquation<?>> subs, final IType<KKind> type)
	{
		IType<KKind> result = type;

		for (final TypeEquation<?> substitution : subs)
			result = applySubstitution(substitution, result);

		return result;
	}

	public static ITypeDefined[] applySubstitutions(final List<TypeEquation<?>> subs, final ITypeDefined... types)
	{
		final ITypeDefined[] result = new ITypeDefined[types.length];

		for (int i = 0; i < types.length; i++)
		{
			result[i] = types[i];
			for (final TypeEquation<?> substitution : subs)
				result[i] = (ITypeDefined) applySubstitution(substitution, result[i]);
		}

		return result;
	}

	private static <KKind extends IKind, KSubst extends IKind> IType<KKind> applySubstitution( //
			final TypeEquation<KSubst> substitution, final IType<KKind> type)
	{
		return type.substitute(substitution.getVar(), substitution.getType());
	}

	private static boolean contains(final IType<?> type, final ITypeVariable<?> var)
	{
		return type.getTypeVariables().contains(var);
	}

	@SuppressWarnings("unchecked")
	private static Set<TypeEquation<?>> unify(final IType<?> left, final IType<?> right)
	{
		final Set<TypeEquation<?>> equations = new HashSet<TypeEquation<?>>();

		if (left.equals(right)) return equations;

		if (left instanceof ITypeConstructed && right instanceof ITypeConstructed)
		{
			if (getRootConstructor((ITypeConstructed<?, ?>) left) != getRootConstructor((ITypeConstructed<?, ?>) right)) //
				throw new TypingException("Cannot unify types " + left + " and " + right + ": different root constructors.");

			final List<IType<?>> leftComponents = getTypeComponents((ITypeConstructed<?, ?>) left);
			final List<IType<?>> rightComponents = getTypeComponents((ITypeConstructed<?, ?>) right);

			for (int i = 0; i < leftComponents.size(); i++)
				equations.addAll(unify(leftComponents.get(i), rightComponents.get(i)));
		}
		else if (left instanceof ITypeVariable || right instanceof ITypeVariable)
		{
			if (left instanceof TVariable) equations.add(new TypeEquation<IKind>((TVariable<IKind>) left, (IType<IKind>) right));
			if (right instanceof TVariable) equations.add(new TypeEquation<IKind>((TVariable<IKind>) right, (IType<IKind>) left));
		}
		else
		{
			throw new TypingException("Cannot refine types " + left + " and " + right);
		}

		return equations;
	}

	public static final class TypeEquation<KKind extends IKind>
	{
		public final TVariable<KKind> var;
		public final IType<KKind> type;

		public TypeEquation(final TVariable<KKind> var, final IType<KKind> type)
		{
			this.var = var;
			this.type = type;
		}

		public boolean solvable()
		{
			return !this.type.getTypeVariables().contains(this.var);
		}

		public TVariable<KKind> getVar()
		{
			return this.var;
		}

		public IType<KKind> getType()
		{
			return this.type;
		}

		@Override
		public boolean equals(final Object obj)
		{
			if (!(obj instanceof TypeEquation)) return false;
			final TypeEquation<?> eq = (TypeEquation<?>) obj;
			return eq.getVar().equals(getVar()) && eq.getType().equals(getType());
		}

		@Override
		public String toString()
		{
			return getVar() + " = " + getType();
		}
	}
}
