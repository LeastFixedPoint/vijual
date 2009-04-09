package info.reflectionsofmind.vijual.library.data.integer;

import java.util.List;

import info.reflectionsofmind.vijual.core.IConstructor;
import info.reflectionsofmind.vijual.core.IType;
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
