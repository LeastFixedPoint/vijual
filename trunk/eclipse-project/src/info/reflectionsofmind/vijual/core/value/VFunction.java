package info.reflectionsofmind.vijual.core.value;

import info.reflectionsofmind.vijual.core.type.ITypeDefined;
import info.reflectionsofmind.vijual.library.type.function.TFunction;

public abstract class VFunction extends Value implements IFunction
{
	public VFunction(ITypeDefined argType, ITypeDefined resType)
	{
		this(new TFunction(argType, resType));
	}

	public VFunction(TFunction functionType)
	{
		super(functionType);
	}

	@Override
	public TFunction getType()
	{
		return (TFunction) super.getType();
	}

	@Override
	public String toString()
	{
		return "[function: " + getType().getArgType() + " -> " + getType().getResType() + "]";
	}
}
