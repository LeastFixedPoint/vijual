package info.reflectionsofmind.vijual.core.lazy;

import info.reflectionsofmind.util.Sets;
import info.reflectionsofmind.vijual.core.exception.EvaluationException;
import info.reflectionsofmind.vijual.core.exception.TypingException;
import info.reflectionsofmind.vijual.core.value.IValue;
import info.reflectionsofmind.vijual.core.value.VFunction;
import info.reflectionsofmind.vijual.library.type.function.TFunction;

import java.util.Set;

public class LLambda implements ILazy
{
	private final LVariable variable;
	private final ILazy expression;
	private final TFunction type;

	public LLambda(final LVariable variable, final ILazy expression)
	{
		this.variable = variable;
		this.expression = expression;
		this.type = new TFunction(variable.getType(), expression.getType());
	}
	
	@Override
	public Set<LVariable> getVariables()
	{
		return Sets.subtract(this.expression.getVariables(), this.variable);
	}

	@Override
	public IValue evaluate() throws EvaluationException, TypingException
	{
		return new VFunction("lambda", getType())
		{
			@Override
			public ILazy apply(final ILazy lazy) throws EvaluationException, TypingException
			{
				return LLambda.this.expression.substitute(LLambda.this.variable, lazy);
			}
		};
	}

	@Override
	public ILazy substitute(final LVariable variable, final ILazy expression)
	{
		if (variable == this.variable) throw new RuntimeException();
		final ILazy newExpression = this.expression.substitute(variable, expression);
		return newExpression == this.expression ? this : new LLambda(variable, expression);
	}

	@Override
	public TFunction getType()
	{
		return this.type;
	}
	
	@Override
	public String toString()
	{
		return "[lambda: " + this.variable + " -> " + this.expression + "]";
	}
}
