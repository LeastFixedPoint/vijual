package info.reflectionsofmind.vijual.core;

public class TScalar implements IType
{
	@Override
	public IType substitute(TVariable variable, IType substitution)
	{
		return this;
	}
}
