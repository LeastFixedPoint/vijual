package info.reflectionsofmind.vijual.core.lazy;

import info.reflectionsofmind.vijual.core.lazy.exception.EvaluationException;
import info.reflectionsofmind.vijual.core.lazy.exception.TypingException;

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
	public IType getType()
	{
		return this.value.getType();
	}
	
	@Override
	public String toString()
	{
		return "[lazy: " + this.value.getType() + "]";
	}
}
