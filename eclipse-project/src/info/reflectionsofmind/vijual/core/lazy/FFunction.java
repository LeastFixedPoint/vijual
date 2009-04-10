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
	
	@Override
	public String toString()
	{
		return "[function: " + getType() + "]";
	}
}
