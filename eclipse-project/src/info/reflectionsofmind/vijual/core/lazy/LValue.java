package info.reflectionsofmind.vijual.core.lazy;

import info.reflectionsofmind.vijual.core.exception.EvaluationException;
import info.reflectionsofmind.vijual.core.exception.TypingException;
import info.reflectionsofmind.vijual.core.type.ITypeDefined;
import info.reflectionsofmind.vijual.core.value.IValue;

import java.util.Collections;
import java.util.Set;

public final class LValue implements ILazy
{
	private final IValue value;

	public LValue(IValue value)
	{
		this.value = value;
	}
	
	@Override
	public Set<LVariable> getVariables()
	{
		return Collections.emptySet();
	}
	
	@Override
	public IValue evaluate() throws EvaluationException, TypingException
	{
		return this.value;
	}
	
	@Override
	public ILazy substitute(LVariable variable, ILazy expression)
	{
		return this;
	}
	
	@Override
	public ITypeDefined getType()
	{
		return this.value.getType();
	}
	
	@Override
	public String toString()
	{
		return "[lazy: " + this.value.getType() + "]";
	}
}
