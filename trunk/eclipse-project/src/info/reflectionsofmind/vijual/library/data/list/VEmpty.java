package info.reflectionsofmind.vijual.library.data.list;

import info.reflectionsofmind.vijual.core.lazy.IType;
import info.reflectionsofmind.vijual.core.lazy.IValue;
import info.reflectionsofmind.vijual.core.lazy.TVariable;

public final class VEmpty implements IValue
{
	private final IType type = new TList(new TVariable("a", VEmpty.class));
	public static final VEmpty INSTANCE = new VEmpty();
	
	private VEmpty()
	{
	}

	@Override
	public IType getType()
	{
		return this.type;
	}

	@Override
	public String toString()
	{
		return "[<nil>]";
	}
}
