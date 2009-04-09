package info.reflectionsofmind.vijual.core.util;

import info.reflectionsofmind.vijual.core.IType;
import info.reflectionsofmind.vijual.core.TFunction;
import info.reflectionsofmind.vijual.core.TVariable;
import info.reflectionsofmind.vijual.core.exception.TypingException;
import info.reflectionsofmind.vijual.core.tuple.TTuple;

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
	
	public static IType resolve(final TFunction function, final IType argument) throws TypingException
	{
		return substitute(function.getResType(), solve(function.getArgType(), argument));
	}

	public static List<TypeEquation> solve(final IType left, final IType right) throws TypingException
	{
		final List<TypeEquation> solution = new ArrayList<TypeEquation>();

		Set<TypeEquation> eqs = refine(left, right);

		while (true)
		{
			final TypeEquation substitution = getSubstitution(eqs);
			if (substitution == null) break;

			final Set<TypeEquation> newEqs = new HashSet<TypeEquation>();

			for (final TypeEquation eq : eqs)
				newEqs.addAll(refine(substitute(eq.getVar(), substitution), substitute(eq.getType(), substitution)));

			solution.add(substitution);
			eqs = newEqs;

			if (!solvable(eqs)) throw new TypingException("Type equations are unsolvable: " + Arrays.toString(eqs.toArray()));
		}

		solution.addAll(eqs);

		return solution;
	}

	private static TypeEquation getSubstitution(final Set<TypeEquation> eqs)
	{
		for (final TypeEquation substitution : eqs)
		{
			int numberOfSubstitutions = 0;

			for (final TypeEquation equation : eqs)
				if (equation.getVar() == substitution.getVar() || contains(substitution.getVar(), equation.getType())) numberOfSubstitutions++;

			if (numberOfSubstitutions > 1) return substitution;
		}

		return null;
	}

	private static boolean solvable(final Set<TypeEquation> eqs)
	{
		for (final TypeEquation equation : eqs)
			if (!equation.solvable()) return false;

		return true;
	}

	public static IType substitute(IType type, final List<TypeEquation> subs)
	{
		for (final TypeEquation sub : subs)
			type = substitute(type, sub);
		
		return type;
	}

	private static IType substitute(final IType type, final TypeEquation substitution)
	{
		return type.substitute(substitution.getVar(), substitution.getType());
	}

	private static boolean contains(final TVariable var, final IType type)
	{
		if (type instanceof TVariable) return var == type;

		if (type instanceof TTuple)
		{
			for (final IType arg : ((TTuple) type).getComponentTypes())
			{
				if (arg == var) return true;
				if (arg instanceof TTuple && contains(var, arg)) return true;
			}
		}

		return false;
	}

	private static Set<TypeEquation> refine(final IType left, final IType right) throws TypingException
	{
		final Set<TypeEquation> eqs = new HashSet<TypeEquation>();

		if (left.equals(right)) return eqs;

		if (left instanceof TTuple && right instanceof TTuple)
		{
			if (left.getClass() != right.getClass()) throw new TypingException("Cannot refine types " + left + " and " + right);

			for (int i = 0; i < ((TTuple) left).getComponentTypes().length; i++)
			{
				final IType newL = ((TTuple) left).getComponentTypes()[i];
				final IType newR = ((TTuple) right).getComponentTypes()[i];

				eqs.addAll(refine(newL, newR));
			}
		}
		else if (left instanceof TVariable || right instanceof TVariable)
		{
			if (left instanceof TVariable) eqs.add(new TypeEquation((TVariable) left, right));
			if (right instanceof TVariable) eqs.add(new TypeEquation((TVariable) right, left));
		}
		else
		{
			throw new TypingException("Cannot refine types " + left + " and " + right);
		}

		return eqs;
	}

	public static final class TypeEquation
	{
		public final TVariable var;
		public final IType type;

		public TypeEquation(final TVariable var, final IType type)
		{
			this.var = var;
			this.type = type;
		}

		public boolean solvable()
		{
			if (getType() instanceof TTuple) return !Types.contains(getVar(), getType());

			return true;
		}

		public TVariable getVar()
		{
			return this.var;
		}

		public IType getType()
		{
			return this.type;
		}

		@Override
		public boolean equals(final Object obj)
		{
			if (!(obj instanceof TypeEquation)) return false;
			final TypeEquation eq = (TypeEquation) obj;
			return eq.getVar().equals(getVar()) && eq.getType().equals(getType());
		}

		@Override
		public String toString()
		{
			return getVar() + " = " + getType();
		}
	}
}
