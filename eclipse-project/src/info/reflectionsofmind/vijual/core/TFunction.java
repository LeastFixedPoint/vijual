package info.reflectionsofmind.vijual.core;

import java.util.List;


public final class TFunction extends TComposite
{
	public TFunction(IType arg, IType res)
	{
		super(arg, res);
	}
	
	@Override
	public List<? extends IConstructor<TFunction>> getConstructors()
	{
		throw new UnsupportedOperationException("Functions cannot be constructed.");
	}
	
	@Override
	public TComposite recreate(IType... typeArgs)
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
