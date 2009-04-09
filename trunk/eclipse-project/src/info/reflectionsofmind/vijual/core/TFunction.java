package info.reflectionsofmind.vijual.core;

public final class TFunction extends TComposite
{
	public TFunction(IType arg, IType res)
	{
		super(arg, res);
	}
	
	@Override
	public TComposite recreate(IType... typeArgs)
	{
		return new TFunction(typeArgs[0], typeArgs[1]);
	}

	public IType getArgType()
	{
		return getTypeArgs()[0];
	}

	public IType getResType()
	{
		return getTypeArgs()[1];
	}
	
	@Override
	public String toString()
	{
		return "(" + getArgType() + " -> " + getResType() + ")";
	}
}
