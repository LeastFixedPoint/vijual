package info.reflectionsofmind.vijual.core;

import java.util.Arrays;
import java.util.List;

import info.reflectionsofmind.vijual.core.exception.EvaluationException;
import info.reflectionsofmind.vijual.core.exception.TypingException;
import info.reflectionsofmind.vijual.core.util.DerivedFunction;

public final class CurriedFunction extends DerivedFunction
{
	private final IFunction function;
	private final List<ILazy> arguments;

	public CurriedFunction(IFunction function, List<ILazy> arguments)
	{
		super(function);

		this.function = function;
		this.arguments = arguments;
	}

	public CurriedFunction(IFunction function, ILazy argument)
	{
		this(function, Arrays.asList(argument));
	}

	@Override
	public ILazy apply(ILazy lazy) throws EvaluationException, TypingException
	{
		ILazy result = new LValue(this.function);
		
		for (ILazy argument : this.arguments)
			result = new LApply(result, argument);
		
		return new LApply(result, lazy);
	}
}
