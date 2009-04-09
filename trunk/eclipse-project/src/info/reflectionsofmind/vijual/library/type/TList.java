package info.reflectionsofmind.vijual.library.type;

import java.util.List;

import info.reflectionsofmind.vijual.core.IType;
import info.reflectionsofmind.vijual.core.TFunction;
import info.reflectionsofmind.vijual.core.TVariable;
import info.reflectionsofmind.vijual.core.tuple.TTuple;

public final class TList extends TTuple
{
	public TList(IType type)
	{
		super(type);
	}
	
	@Override
	public List<TFunction> getConstructors()
	{
		final TVariable a = new TVariable("a", TList.class);
		
		IType empty = new TList(a);
		
		IType prepend = new TFunction(new TTuple(a, new TList(a)), new TList(a));
		
		return null;
	}
	
	@Override
	public TTuple recreate(IType... typeArgs)
	{
		return new TList(typeArgs[0]);
	}
	
	public IType getType()
	{
		return getComponentTypes()[0];
	}
	
	@Override
	public String toString()
	{
		return TList.class.getSimpleName() + "[" + getType() + "]";
	}
}
