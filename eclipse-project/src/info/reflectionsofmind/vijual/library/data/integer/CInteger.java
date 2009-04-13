package info.reflectionsofmind.vijual.library.data.integer;

import info.reflectionsofmind.vijual.core.lazy.CConstructor;

public final class CInteger extends CConstructor<TInteger>
{
	private final int value;
	
	public CInteger(int value)
	{
		super(TInteger.INSTANCE);
		this.value = value;
	}
	
	public int getValue()
	{
		return this.value;
	}
	
	@Override
	public String toString()
	{
		return getClass().getSimpleName() + "(" + this.value + ")";
	}
}
