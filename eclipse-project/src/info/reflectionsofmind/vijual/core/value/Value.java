package info.reflectionsofmind.vijual.core.value;

import info.reflectionsofmind.vijual.core.lazy.ILazy;
import info.reflectionsofmind.vijual.core.lazy.LValue;
import info.reflectionsofmind.vijual.core.type.ITypeDefined;

public class Value implements IValue
{
	private final ILazy lazy;
	private final ITypeDefined type;
	private final String name;
	
	public Value(final String name, final ITypeDefined type)
	{
		this.type = type;
		this.lazy = new LValue(this);
		this.name = name;
	}
	
	public final ILazy toLazy()
	{
		return this.lazy;
	}
	
	public ITypeDefined getType()
	{
		return this.type;
	}
	
	@Override
	public final String toString()
	{
		return this.name;
	}
}
