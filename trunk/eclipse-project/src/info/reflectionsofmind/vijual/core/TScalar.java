package info.reflectionsofmind.vijual.core;

public abstract class TScalar implements IType
{
	@Override
	public IType substitute(TVariable variable, IType substitution)
	{
		return this;
	}
}
