package info.reflectionsofmind.vijual.core;

import info.reflectionsofmind.vijual.core.type.ITypeDefined;
import info.reflectionsofmind.vijual.core.util.Types;
import info.reflectionsofmind.vijual.core.value.IFunction;
import info.reflectionsofmind.vijual.core.value.IValue;
import info.reflectionsofmind.vijual.library.type.function.TFunction;

public final class LApply implements ILazy
{
	private final ITypeDefined type;
	private final ILazy function;
	private final ILazy argument;

	public LApply(final ILazy function, final ILazy argument)
	{
		this.type = Types.resolve((TFunction) function.getType(), argument.getType());

		this.function = function;
		this.argument = argument;
	}

	@Override
	public IValue evaluate()
	{
		return ((IFunction) this.function.evaluate()).apply(this.argument).evaluate();
	}

	@Override
	public ITypeDefined getType()
	{
		return this.type;
	}

	@Override
	public String toString()
	{
		return "[lazy: " + getType().toString() + "]";
	}
}
