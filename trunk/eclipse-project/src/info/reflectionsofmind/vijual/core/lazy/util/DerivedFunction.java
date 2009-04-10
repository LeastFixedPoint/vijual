package info.reflectionsofmind.vijual.core.lazy.util;

import info.reflectionsofmind.vijual.core.lazy.IFunction;
import info.reflectionsofmind.vijual.core.lazy.ILazy;
import info.reflectionsofmind.vijual.core.lazy.LValue;
import info.reflectionsofmind.vijual.core.lazy.TFunction;

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
