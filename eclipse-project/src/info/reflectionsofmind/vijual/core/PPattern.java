package info.reflectionsofmind.vijual.core;

import info.reflectionsofmind.vijual.core.type.IType;
import info.reflectionsofmind.vijual.core.util.Types;
import info.reflectionsofmind.vijual.core.value.IConstructor;
import info.reflectionsofmind.vijual.core.value.IValue;

public class PPattern implements IPattern
{
	private final IConstructor<? extends IType> constructor;
	private final IType[] arguments;
	private final IType matchingType;

	public PPattern(IConstructor<? extends IType> constructor, IType... arguments)
	{
		this.constructor = constructor;
		this.arguments = arguments;
		
		this.matchingType = constructor.getConstructedType();
	}
	
	@Override
	public IType getMatchingType()
	{
		return null;
	}
	
	@Override
	public boolean matches(IValue value)
	{
		final IConstructor<? extends IType> constructor = value.getConstructor();
		
		if (constructor != this.constructor)
			return false;
		
		final ILazy[] argumentValues = value.getArguments();
		for (int i = 0, size = Math.max(arguments.length, argumentValues.length); i < size; i++)
			Types.solve(arguments[i], argumentValues[i].getType());

		return true;
	}

	public IConstructor<? extends IType> getConstructor()
	{
		return this.constructor;
	}

	public IType[] getArgumentTypes()
	{
		return this.arguments;
	}
}
