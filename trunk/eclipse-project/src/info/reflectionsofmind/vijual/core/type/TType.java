package info.reflectionsofmind.vijual.core.type;

import info.reflectionsofmind.util.Sets;
import info.reflectionsofmind.vijual.core.kind.IKind;
import info.reflectionsofmind.vijual.core.kind.KConstructor;

import java.util.Collections;
import java.util.Set;

public abstract class TType<KKind extends IKind> implements IType<KKind>
{
	private final KKind kind;
	private final Set<TVariable<?>> typeVariables;
	private final String name;

	public TType(String name, final KKind kind, final Set<TVariable<?>> typeVariables)
	{
		this.name = name;
		this.kind = kind;
		this.typeVariables = typeVariables;
	}

	public TType(String name, KKind kind)
	{
		this(name, kind, Collections.<TVariable<?>>emptySet());
	}

	public final KKind getKind()
	{
		return this.kind;
	}

	public Set<TVariable<?>> getTypeVariables()
	{
		return this.typeVariables;
	}

	@Override
	public final <KVar extends IKind> ITypeConstructor<KVar, KKind> toTypeFunction(final TVariable<KVar> variable)
	{
		if (!getTypeVariables().contains(variable)) throw new RuntimeException();

		return new TConstructor<KVar, KKind>("[" + variable + " -> " + toString() + "]", //
				new KConstructor<KVar, KKind>(variable.getKind(), getKind()), // 
				Sets.subtract(this.typeVariables, variable))
		{
			@Override
			@SuppressWarnings("unchecked")
			public ITypeConstructed<KVar, KKind> apply(IType<KVar> typeArgument)
			{
				return (ITypeConstructed<KVar, KKind>) TType.this.substitute(variable, typeArgument);
			}
			
			@Override
			public <KSubst extends IKind> IType<KConstructor<KVar, KKind>> substitute(final TVariable<KSubst> substVar, final IType<KSubst> substType)
			{
				return TType.this.substitute(substVar, substType).toTypeFunction(variable);
			}
		};
	}
	
	@Override
	public String toString()
	{
		return this.name;
	}
}
