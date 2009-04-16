package info.reflectionsofmind.vijual.core.type;

import info.reflectionsofmind.util.Sets;
import info.reflectionsofmind.vijual.core.kind.IKind;
import info.reflectionsofmind.vijual.core.kind.KDefined;

public class TDefinedConstructed<KArg extends IKind> extends TDefined implements ITypeDefinedConstructed<KArg>
{
	private final ITypeConstructor<KArg, KDefined> typeConstructor;
	private final IType<KArg> typeArgument;

	public TDefinedConstructed(ITypeConstructor<KArg, KDefined> typeConstructor, IType<KArg> typeArgument)
	{
		super("[" + typeConstructor + " " + typeArgument + "]", //
				Sets.union(typeConstructor.getTypeVariables(), typeArgument.getTypeVariables()));

		this.typeConstructor = typeConstructor;
		this.typeArgument = typeArgument;
	}

	@Override
	public IType<KArg> getArgument()
	{
		return this.typeArgument;
	}

	@Override
	public ITypeConstructor<KArg, KDefined> getConstructor()
	{
		return this.typeConstructor;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <KVar extends IKind> IType<KDefined> substitute(TVariable<KVar> variable, IType<KVar> substitution)
	{
		return this.typeConstructor.wrapAfterDefined(new TDefinedConstructed( //
				(ITypeConstructor) this.typeConstructor.substitute(variable, substitution), //
				this.typeArgument.substitute(variable, substitution)));
	}
}
