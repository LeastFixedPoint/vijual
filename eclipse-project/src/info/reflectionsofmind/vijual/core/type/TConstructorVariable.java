package info.reflectionsofmind.vijual.core.type;

import info.reflectionsofmind.vijual.core.kind.IKind;
import info.reflectionsofmind.vijual.core.kind.KConstructor;

public final class TConstructorVariable<KArg extends IKind, KRes extends IKind> // 
		extends TVariable<KConstructor<KArg, KRes>> implements ITypeConstructor<KArg, KRes>
{
	public TConstructorVariable(final String name, final KArg argKind, final KRes resKind)
	{
		super("$" + name, new KConstructor<KArg, KRes>(argKind, resKind));
	}

	@Override
	public ITypeConstructed<KArg, KRes> apply(IType<KArg> typeArgument)
	{
		throw new RuntimeException("Type constructor variables cannot be applied to arguments.");
	}
	
	@Override
	public ITypeDefinedConstructed<?> wrapAfterDefined(ITypeDefinedConstructed<?> type)
	{
		throw new RuntimeException("Type constructor variables cannot wrap constructed types.");
	}
}
