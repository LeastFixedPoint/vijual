package info.reflectionsofmind.vijual.library.type.integer;

import info.reflectionsofmind.vijual.core.value.VPrimitive;

public final class Vinteger extends VPrimitive
{
	private final int value;

	public Vinteger(int value)
	{
		super(String.valueOf(value), TInteger.INSTANCE);
		this.value = value;
	}

	public int getValue()
	{
		return this.value;
	}
}
