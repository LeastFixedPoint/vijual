package info.reflectionsofmind.vijual.library.value;

import info.reflectionsofmind.vijual.core.IType;
import info.reflectionsofmind.vijual.core.IValue;
import info.reflectionsofmind.vijual.core.TVariable;
import info.reflectionsofmind.vijual.library.type.TList;

public final class VNil implements IValue
{
	private final IType type = new TList(new TVariable(VNil.class.getSimpleName() + ".a"));

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
