package info.reflectionsofmind.vijual.library.data.integer;

import info.reflectionsofmind.vijual.core.lazy.TScalar;

public final class TInteger extends TScalar
{
	public static final TInteger INSTANCE = new TInteger();

	private TInteger()
	{

	}
	
	@Override
	public String toString()
	{
		return TInteger.class.getSimpleName();
	}
}
