package info.reflectionsofmind.vijual.core.value;

import info.reflectionsofmind.vijual.core.ILazy;
import info.reflectionsofmind.vijual.core.LValue;
import info.reflectionsofmind.vijual.core.type.ITypeDefined;

public class Value implements IValue
{
	private final ILazy lazy;
	private final ITypeDefined type;
	
	public Value(final ITypeDefined type)
	{
		this.type = type;
		this.lazy = new LValue(this);
	}
	
	public final ILazy toLazy()
	{
		return this.lazy;
	}
	
	public ITypeDefined getType()
	{
		return this.type;
	}
}
