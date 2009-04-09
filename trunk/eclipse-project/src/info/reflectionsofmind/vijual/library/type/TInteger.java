package info.reflectionsofmind.vijual.library.type;

import info.reflectionsofmind.vijual.core.TScalar;

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
