package info.reflectionsofmind.vijual.core.util;

import info.reflectionsofmind.vijual.core.lazy.ILazy;
import info.reflectionsofmind.vijual.core.lazy.LApply;

public final class Values
{
	public Values()
	{
		throw new UnsupportedOperationException("Utility class");
	}

	public static ILazy getRootValue(ILazy value)
	{
		return value instanceof LApply ? getRootValue((LApply) value) : value;
	}
}
