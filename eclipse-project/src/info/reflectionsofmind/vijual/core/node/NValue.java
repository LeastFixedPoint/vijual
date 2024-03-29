package info.reflectionsofmind.vijual.core.node;

import info.reflectionsofmind.vijual.core.exception.TypingException;
import info.reflectionsofmind.vijual.core.exception.ValueNotSetException;
import info.reflectionsofmind.vijual.core.lazy.ILazy;
import info.reflectionsofmind.vijual.core.lazy.LValue;
import info.reflectionsofmind.vijual.core.type.IType;
import info.reflectionsofmind.vijual.core.value.IValue;

public final class NValue implements INode
{
	private IValue value;
	
	public NValue(IValue value)
	{
		this.value = value;
	}
	
	public void setValue(IValue value)
	{
		this.value = value;
	}

	public IValue getValue()
	{
		return this.value;
	}

	@Override
	public ILazy evaluate() throws TypingException
	{
		getType();
		return new LValue(this.value);
	}
	
	@Override
	public IType getType() throws TypingException
	{
		if (this.value == null) throw new ValueNotSetException(this);
		
		return this.value.getType();
	}
}
