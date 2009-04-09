package info.reflectionsofmind.vijual.core;

import java.util.List;

public abstract class TScalar implements IType
{
	@Override
	public IType substitute(TVariable variable, IType substitution)
	{
		return this;
	}

	@Override
	public List<? extends IConstructor<? extends IType>> getConstructors()
	{
		throw new UnsupportedOperationException("Values of scalar types cannot be constructed.");
	}
}
