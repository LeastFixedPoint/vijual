package info.reflectionsofmind.vijual.core.expression;

import info.reflectionsofmind.vijual.core.ILazy;
import info.reflectionsofmind.vijual.core.LValue;
import info.reflectionsofmind.vijual.core.type.IType;
import info.reflectionsofmind.vijual.core.value.IValue;

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
