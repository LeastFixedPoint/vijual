package info.reflectionsofmind.vijual.core.util;

import info.reflectionsofmind.vijual.core.IFunction;
import info.reflectionsofmind.vijual.core.ILazy;
import info.reflectionsofmind.vijual.core.LValue;
import info.reflectionsofmind.vijual.core.TFunction;

public abstract class DerivedFunction implements IFunction
{
	private final IFunction originalFunction;

	public DerivedFunction(IFunction originalFunction)
	{
		this.originalFunction = originalFunction;
	}

	@Override
	public TFunction getType()
	{
		return (TFunction) this.originalFunction.getType().getResType();
	}
	
	public ILazy toLazy()
	{
		return new LValue(this);
	}
}
