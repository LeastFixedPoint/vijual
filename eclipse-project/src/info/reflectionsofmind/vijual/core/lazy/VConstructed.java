package info.reflectionsofmind.vijual.core.lazy;

import info.reflectionsofmind.vijual.core.lazy.util.Types;

import java.util.List;

public class VConstructed<TType extends IType, TConstructor extends IConstructor<TType>> implements IValue
{
	private final TConstructor constructor;
	private final List<ILazy> arguments;

	public VConstructed(TConstructor constructor, List<ILazy> arguments)
	{
		this.constructor = constructor;
		this.arguments = arguments;

		if (constructor.getArgumentTypes())

		for (int i = 0; i < constructor.getArgumentTypes().length; i++)
			Types.solve(constructor.getArgumentTypes()[i], arguments.get(i).getType());
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

	public List<ILazy> getArguments()
	{
		return this.arguments;
	}
}
