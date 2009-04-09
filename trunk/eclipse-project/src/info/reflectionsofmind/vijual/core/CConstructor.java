package info.reflectionsofmind.vijual.core;

import info.reflectionsofmind.vijual.core.util.Types;

import java.util.List;

public abstract class CConstructor implements IConstructor
{
	private final IType[] argumentTypes;
	private final IType constructedType;
	private final IType type;

	public CConstructor(final IType type, final IType... args)
	{
		this.argumentTypes = args;
		this.constructedType = type;
		this.type = Types.curry(this.argumentTypes, this.constructedType);
	}

	@Override
	public IType getType()
	{
		return this.type;
	}

	@Override
	public IType[] getArgumentTypes()
	{
		return this.argumentTypes;
	}

	public IType getConstructedType()
	{
		return this.constructedType;
	}
}
