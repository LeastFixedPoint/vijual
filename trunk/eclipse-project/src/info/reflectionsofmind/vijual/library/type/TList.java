package info.reflectionsofmind.vijual.library.type;

import info.reflectionsofmind.vijual.core.IType;
import info.reflectionsofmind.vijual.core.TComposite;

public final class TList extends TComposite
{
	public TList(IType type)
	{
		super(type);
	}
	
	@Override
	public TComposite recreate(IType... typeArgs)
	{
		return new TList(typeArgs[0]);
	}
	
	public IType getType()
	{
		return getTypeArgs()[0];
	}
	
	@Override
	public String toString()
	{
		return TList.class.getSimpleName() + "[" + getType() + "]";
	}
}
