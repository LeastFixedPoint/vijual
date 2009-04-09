package info.reflectionsofmind.vijual.core;

import info.reflectionsofmind.vijual.core.exception.EvaluationException;
import info.reflectionsofmind.vijual.core.exception.TypingException;

public final class LValue implements ILazy
{
	private final IValue value;

	public LValue(IValue value)
	{
		this.value = value;
	}
	
	@Override
	public IValue evaluate() throws EvaluationException, TypingException
	{
		return this.value;
	}
	
	@Override
	public IType getType() throws TypingException
	{
		return this.value.getType();
	}
}
