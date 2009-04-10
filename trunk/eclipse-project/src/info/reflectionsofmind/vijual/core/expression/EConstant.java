package info.reflectionsofmind.vijual.core.expression;

import info.reflectionsofmind.vijual.core.lazy.ILazy;
import info.reflectionsofmind.vijual.core.lazy.IType;
import info.reflectionsofmind.vijual.core.lazy.IValue;
import info.reflectionsofmind.vijual.core.lazy.LValue;

import java.util.Collections;
import java.util.List;

public final class EConstant extends Expression
{
	private final ILazy value;

	public EConstant(ILazy value)
	{
		this.value = value;
	}

	public EConstant(IValue value)
	{
		this(new LValue(value));
	}

	@Override
	public ILazy toLazy()
	{
		return this.value;
	}

	@Override
	public Expression substitute(EVariable variable, Expression expression)
	{
		return this;
	}

	@Override
	public List<EVariable> getVariables()
	{
		return Collections.emptyList();
	}

	@Override
	public IType getType()
	{
		return this.value.getType();
	}
	
	@Override
	public String toString()
	{
		return this.value.toString();
	}
}
