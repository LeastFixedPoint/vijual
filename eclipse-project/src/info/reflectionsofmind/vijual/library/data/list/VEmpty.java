package info.reflectionsofmind.vijual.library.data.list;

import info.reflectionsofmind.vijual.core.IType;
import info.reflectionsofmind.vijual.core.IValue;
import info.reflectionsofmind.vijual.core.TVariable;

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
