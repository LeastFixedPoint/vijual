package info.reflectionsofmind.vijual.core.value;

import info.reflectionsofmind.vijual.core.lazy.ILazy;
import info.reflectionsofmind.vijual.core.util.Types;
import info.reflectionsofmind.vijual.library.type.function.TFunction;

public abstract class VFunction extends Value implements IFunction
{
	public VFunction(String name, TFunction functionType)
	{
		super(name, functionType);
	}

	public VFunction(TFunction functionType)
	{
		this("[function: " + functionType.getArgType() + " -> " + functionType.getResType() + "]", functionType);
	}

	@Override
	public TFunction getType()
	{
		return (TFunction) super.getType();
	}

	public static abstract class Derived extends VFunction
	{
		public Derived(VFunction function, ILazy argument)
		{
			super((TFunction) Types.resolve(function.getType(), argument.getType()));
		}
	}
}
