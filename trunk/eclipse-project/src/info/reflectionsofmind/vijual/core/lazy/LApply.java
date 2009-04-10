package info.reflectionsofmind.vijual.core.lazy;

import info.reflectionsofmind.vijual.core.lazy.util.Types;

public final class LApply implements ILazy
{
	private final IType type;
	private final ILazy function;
	private final ILazy argument;

	public LApply(final ILazy function, final ILazy argument)
	{
		this.type = Types.substitute(((TFunction) function.getType()).getResType(), //
				Types.solve(argument.getType(), ((TFunction) function.getType()).getArgType()));

		this.function = function;
		this.argument = argument;
	}

	@Override
	public IValue evaluate()
	{
		return ((IFunction) this.function.evaluate()).apply(this.argument).evaluate();
	}

	@Override
	public IType getType()
	{
		return this.type;
	}

	@Override
	public String toString()
	{
		return "[lazy: " + getType().toString() + "]";
	}
}
