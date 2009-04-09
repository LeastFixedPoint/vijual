package info.reflectionsofmind.vijual.library.data.integer;

import info.reflectionsofmind.vijual.core.IType;
import info.reflectionsofmind.vijual.core.IValue;

public final class VInteger implements IValue
{
	private final int value;

	public VInteger(int value)
	{
		this.value = value;
	}
	
	public int getValue()
	{
		return this.value;
	}

	@Override
	public IType getType()
	{
		return TInteger.INSTANCE;
	}
	
	@Override
	public String toString()
	{
		return "[value/integer: " + getValue() + "]";
	}
}
