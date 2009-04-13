package info.reflectionsofmind.vijual.core.lazy;

public abstract class FFunction implements IFunction
{
	private final TFunction type;

	public FFunction(TFunction type)
	{
		this.type = type;
	}

	@Override
	public TFunction getType()
	{
		return this.type;
	}

	public ILazy toLazy()
	{
		return new LValue(this);
	}

	@Override
	public IConstructor<? extends IType> getConstructor()
	{
		throw new RuntimeException("Functions don't have constructors.");
	}

	@Override
	public String toString()
	{
		return "[function: " + getType() + "]";
	}
}
