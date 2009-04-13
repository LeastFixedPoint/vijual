package info.reflectionsofmind.vijual.core.lazy;

import java.util.Arrays;

import info.reflectionsofmind.vijual.core.lazy.util.Types;

public class VConstructed<TType extends IType, TConstructor extends IConstructor<TType>> implements IValue
{
	private final TConstructor constructor;
	private final ILazy[] arguments;

	public VConstructed(TConstructor constructor, ILazy... arguments)
	{
		this.constructor = constructor;
		this.arguments = arguments;

		for (int i = 0; i < constructor.getArgumentTypes().length; i++)
			Types.solve(constructor.getArgumentTypes()[i], arguments[i].getType());
	}

	@Override
	public IConstructor<? extends IType> getConstructor()
	{
		return this.constructor;
	}

	@Override
	public IType getType()
	{
		return this.constructor.getConstructedType();
	}

	public ILazy[] getArguments()
	{
		return this.arguments;
	}
	
	public ILazy toLazy()
	{
		return new LValue(this);
	}
	
	@Override
	public String toString()
	{
		return "[" + getConstructor().toString() + ": " + Arrays.toString(getArguments()) + "]";
	}
}
