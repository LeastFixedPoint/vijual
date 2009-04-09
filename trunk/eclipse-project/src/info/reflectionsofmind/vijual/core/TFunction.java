package info.reflectionsofmind.vijual.core;

import java.util.List;

import info.reflectionsofmind.vijual.core.tuple.TTuple;

public final class TFunction extends TTuple
{
	public TFunction(IType arg, IType res)
	{
		super(arg, res);
	}
	
	@Override
	public List<IType> getConstructors()
	{
		throw new UnsupportedOperationException("Functions cannot be constructed.");
	}
	
	@Override
	public TTuple recreate(IType... typeArgs)
	{
		return new TFunction(typeArgs[0], typeArgs[1]);
	}

	public IType getArgType()
	{
		return getComponentTypes()[0];
	}

	public IType getResType()
	{
		return getComponentTypes()[1];
	}
	
	@Override
	public String toString()
	{
		return "(" + getArgType() + " -> " + getResType() + ")";
	}
}
