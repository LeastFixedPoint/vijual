package info.reflectionsofmind.vijual.core.type;

import info.reflectionsofmind.vijual.core.kind.IKind;
import info.reflectionsofmind.vijual.core.kind.KConstructor;
import info.reflectionsofmind.vijual.core.kind.KDefined;

import java.util.Collections;
import java.util.Set;

public abstract class TConstructor<KArgument extends IKind, KResult extends IKind> //
		extends TType<KConstructor<KArgument, KResult>> //
		implements ITypeConstructor<KArgument, KResult>
{
	public TConstructor(String name, KConstructor<KArgument, KResult> kind)
	{
		this(name, kind, Collections.<TVariable<?>> emptySet());
	}
	
	public TConstructor(String name, KConstructor<KArgument, KResult> kind, Set<TVariable<?>> typeVariables)
	{
		super(name, kind, typeVariables);
	}

	@Override
	@SuppressWarnings("unchecked")
	public ITypeConstructed<KArgument, KResult> apply(IType<KArgument> typeArgument)
	{
		if (getKind().getResultKind() == KDefined.INSTANCE)
		{
			return (ITypeConstructed<KArgument, KResult>) wrapAfterDefined(new TDefinedConstructed<KArgument>( //
					(ITypeConstructor<KArgument, KDefined>) this, typeArgument));
		}
		else
		{
			final ITypeConstructor<KArgument, KConstructor<IKind, IKind>> constructor = // 
			(ITypeConstructor<KArgument, KConstructor<IKind, IKind>>) this;

			return (ITypeConstructed<KArgument, KResult>) new TConstructorConstructed<KArgument, IKind, IKind>( //
					constructor, typeArgument);
		}
	}

	@Override
	public <KVar extends IKind> IType<KConstructor<KArgument, KResult>> substitute(TVariable<KVar> variable, IType<KVar> substitution)
	{
		return this;
	}
	
	@Override
	public ITypeDefinedConstructed<?> wrapAfterDefined(ITypeDefinedConstructed<?> type)
	{
		return type;
	}
}
