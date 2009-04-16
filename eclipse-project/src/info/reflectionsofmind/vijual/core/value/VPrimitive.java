package info.reflectionsofmind.vijual.core.value;

import info.reflectionsofmind.vijual.library.type.template.TPrimitive;

public abstract class VPrimitive extends Value implements IDefined
{
	public VPrimitive(TPrimitive type)
	{
		super(type);
	}

	@Override
	public TPrimitive getType()
	{
		return (TPrimitive) super.getType();
	}
}
