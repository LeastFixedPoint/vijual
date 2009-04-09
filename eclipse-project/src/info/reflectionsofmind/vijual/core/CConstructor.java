package info.reflectionsofmind.vijual.core;

import java.util.Arrays;

import info.reflectionsofmind.vijual.core.exception.EvaluationException;
import info.reflectionsofmind.vijual.core.exception.TypingException;

public abstract class CConstructor<TType extends IType> implements IConstructor<TType>
{
	private final IType[] argumentTypes;
	private final TType constructedType;
	private final IType type;

	public CConstructor(final TType constructedType, final IType... args)
	{
		this.argumentTypes = args;
		this.constructedType = constructedType;

		IType type = this.constructedType;

		for (int i = this.argumentTypes.length - 1; i > 0; --i)
			type = new TFunction(this.argumentTypes[i], type);

		this.type = type;
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

	public TType getConstructedType()
	{
		return this.constructedType;
	}
	
	public ILazy toLazy()
	{
		try
		{
			if (this.argumentTypes.length == 0) return construct();
		}
		catch (TypingException exception)
		{
			throw new RuntimeException("Caught typing error on nullary constructor!?", exception);
		}

		return new LValue(new IFunction()
		{
			@Override
			public ILazy apply(final ILazy lazy) throws EvaluationException, TypingException
			{
				return new CConstructor<TType>(getConstructedType(), Arrays.copyOfRange(getArgumentTypes(), 1, getArgumentTypes().length))
				{
					@Override
					public ILazy construct(ILazy... args) throws TypingException
					{
						final ILazy[] allArgs = new ILazy[args.length + 1];
						allArgs[0] = lazy;
						System.arraycopy(args, 0, allArgs, 1, args.length);
						
						return CConstructor.this.construct(allArgs);
					}
				}.toLazy();
			}

			@Override
			public TFunction getType()
			{
				return (TFunction) CConstructor.this.type;
			}
		});
	}
}
