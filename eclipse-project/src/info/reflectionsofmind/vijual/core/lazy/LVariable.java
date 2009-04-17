package info.reflectionsofmind.vijual.core.lazy;

import info.reflectionsofmind.vijual.core.exception.EvaluationException;
import info.reflectionsofmind.vijual.core.exception.TypingException;
import info.reflectionsofmind.vijual.core.type.ITypeDefined;
import info.reflectionsofmind.vijual.core.value.IValue;

import java.util.Collections;
import java.util.Set;

public final class LVariable implements ILazy
{
	private final ITypeDefined type;
	private final String name;

	public LVariable(String name, ITypeDefined type)
	{
		this.name = name;
		this.type = type;
	}
	
	@Override
	public Set<LVariable> getVariables()
	{
		return Collections.singleton(this);
	}

	@Override
	public IValue evaluate() throws EvaluationException, TypingException
	{
		throw new RuntimeException("Variables cannot be evaluated");
	}
	
	@Override
	public ILazy substitute(LVariable variable, ILazy expression)
	{
		return this == variable ? expression : this;
	}

	public ITypeDefined getType()
	{
		return this.type;
	}
	
	@Override
	public String toString()
	{
		return "[var(" + this.name + "): " + getType() + "]";
	}
}
