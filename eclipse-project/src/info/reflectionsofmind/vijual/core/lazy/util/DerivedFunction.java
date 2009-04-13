package info.reflectionsofmind.vijual.core.lazy.util;

import info.reflectionsofmind.vijual.core.lazy.FFunction;
import info.reflectionsofmind.vijual.core.lazy.IFunction;
import info.reflectionsofmind.vijual.core.lazy.TFunction;

public abstract class DerivedFunction extends FFunction
{
	public DerivedFunction(IFunction originalFunction)
	{
		super((TFunction) originalFunction.getType().getResType());
	}
}
