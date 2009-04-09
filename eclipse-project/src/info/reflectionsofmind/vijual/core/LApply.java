package info.reflectionsofmind.vijual.core;

import info.reflectionsofmind.vijual.core.exception.TypingException;
import info.reflectionsofmind.vijual.core.util.Types;

public final class LApply implements ILazy
{
	private final IType type;
	private final ILazy function;
	private final ILazy argument;

	public LApply(final ILazy function, final ILazy argument) throws TypingException
	{
		this.type = Types.substitute(((TFunction) function.getType()).getResType(), //
				Types.solve(argument.getType(), ((TFunction) function.getType()).getArgType()));

		this.function = function;
		this.argument = argument;
	}

	@Override
	public IValue evaluate() throws TypingException
	{
		return ((IFunction) this.function.evaluate()).apply(this.argument).evaluate();
	}

	@Override
	public IType getType() throws TypingException
	{
		return this.type;
	}

	@Override
	public String toString()
	{
		try
		{
			return evaluate().toString();
		}
		catch (final TypingException exception)
		{
			return "<error>";
		}
	}
}
