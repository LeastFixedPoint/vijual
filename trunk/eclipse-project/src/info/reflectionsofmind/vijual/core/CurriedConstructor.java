package info.reflectionsofmind.vijual.core;

import info.reflectionsofmind.vijual.core.exception.TypingException;
import info.reflectionsofmind.vijual.core.util.Types;

import java.util.ArrayList;
import java.util.List;

public final class CurriedConstructor implements IConstructor
{
	private final IConstructor constructor;
	private final List<IType> argumentTypes = new ArrayList<IType>();
	private final ILazy[] arguments;
	private final IType type;

	public CurriedConstructor(IConstructor constructor, ILazy... arguments) throws TypingException
	{
		this.constructor = constructor;
		this.arguments = arguments;

		IType type = this.constructor.getType();

		for (ILazy argument : this.arguments)
			type = Types.resolve((TFunction) type, argument.getType());
		
		this.type = type;
		
		while (type instanceof TFunction)
		{
			this.argumentTypes.add(((TFunction)type).getArgType());
			type = ((TFunction)type).getResType();
		}
	}

	@Override
	public ILazy construct(ILazy... restArgs)
	{
		final ILazy[] allArgs = new ILazy[this.arguments.length + restArgs.length];

		for (int i = 0; i < allArgs.length; i++)
			allArgs[i] = (i < this.arguments.length) ? this.arguments[i] : restArgs[i - this.arguments.length];

		return this.constructor.construct(allArgs);
	}

	@Override
	public IType getType()
	{
		return this.type;
	}
	
	@Override
	public IType getConstructedType()
	{
		return this.constructor.getConstructedType();
	}
	
	@Override
	public List<IType> getArgumentTypes()
	{
		return this.argumentTypes;
	}
}
