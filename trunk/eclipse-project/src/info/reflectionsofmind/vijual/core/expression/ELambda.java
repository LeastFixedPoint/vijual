package info.reflectionsofmind.vijual.core.expression;

import java.util.Arrays;

import info.reflectionsofmind.vijual.core.ILazy;
import info.reflectionsofmind.vijual.core.exception.EvaluationException;
import info.reflectionsofmind.vijual.core.exception.TypingException;
import info.reflectionsofmind.vijual.core.value.VFunction;
import info.reflectionsofmind.vijual.library.type.function.TFunctionConstructor;

public final class ELambda extends Expression
{
	private final EVariable variable;
	private final Expression expression;

	public ELambda(EVariable variable, Expression expression)
	{
		this.variable = variable;
		this.expression = expression;

		if (expression.getType() == null)
			throw new RuntimeException();
	}

	@Override
	public Expression substitute(EVariable variable, Expression expression)
	{
		if (variable == this.variable) throw new RuntimeException("Cannot substitute lambda-expression's argument variable.");

		final Expression newExpression = this.expression.substitute(variable, expression);
		return newExpression == this.expression ? this : new ELambda(this.variable, newExpression);
	}

	@Override
	public TFunctionConstructor getType()
	{
		return new TFunctionConstructor(this.variable.getType(), this.expression.getType());
	}

	@Override
	public ILazy toLazy()
	{
		return new VFunction(getType())
		{
			@Override
			public ILazy apply(ILazy lazy) throws EvaluationException, TypingException
			{
				return expression.substitute(variable, new EConstant(lazy)).toLazy();
			}
		}.toLazy();
	}
}
