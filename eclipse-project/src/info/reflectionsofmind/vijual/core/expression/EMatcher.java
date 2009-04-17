package info.reflectionsofmind.vijual.core.expression;

import info.reflectionsofmind.vijual.core.exception.EvaluationException;
import info.reflectionsofmind.vijual.core.exception.TypingException;
import info.reflectionsofmind.vijual.core.lazy.ILazy;
import info.reflectionsofmind.vijual.core.lazy.LApply;
import info.reflectionsofmind.vijual.core.pattern.IPattern;
import info.reflectionsofmind.vijual.core.type.IType;
import info.reflectionsofmind.vijual.core.util.Types;
import info.reflectionsofmind.vijual.core.value.IValue;
import info.reflectionsofmind.vijual.core.value.VFunction;
import info.reflectionsofmind.vijual.library.type.function.TFunctionConstructor;

public final class EMatcher extends Expression
{
	private final IPattern pattern;
	private final Expression expression;
	private final Expression otherwise;
	private final IType resultType;
	private final TFunctionConstructor type;

	public EMatcher(final IPattern pattern, final Expression expression, final Expression otherwise)
	{
		IType expressionOutputType = expression.getType();
		for (final IType patternArgumentType : pattern.getArgumentTypes())
			expressionOutputType = Types.resolve((TFunctionConstructor) expressionOutputType, patternArgumentType);

		final TFunctionConstructor otherwiseType = (TFunctionConstructor) otherwise.getType();

		if (otherwiseType.getArgType() != pattern.getMatchingType()) throw new RuntimeException( //
				"Pattern and otherwise case argument types " + otherwiseType.getArgType() // 
						+ " and " + pattern.getMatchingType() + " must match.");

		if (!expressionOutputType.equals(otherwiseType.getResType())) throw new RuntimeException( //
				"Match and otherwise cases' result types " + expressionOutputType // 
						+ " and " + otherwiseType.getResType() + " must match.");

		this.resultType = otherwiseType.getResType();

		this.pattern = pattern;
		this.expression = expression;
		this.otherwise = otherwise;

		this.type = new TFunctionConstructor(this.pattern.getMatchingType(), this.resultType);
	}

	@Override
	public TFunctionConstructor getType()
	{
		return this.type;
	}

	@Override
	public ILazy toLazy()
	{
		return new VFunction(getType())
		{
			@Override
			public ILazy apply(final ILazy lazy) throws EvaluationException, TypingException
			{
				final IValue value = lazy.evaluate();

				if (EMatcher.this.pattern.matches(value))
				{
					ILazy result = EMatcher.this.expression.toLazy();
					for (int i = value.getArguments().length - 1; i >= 0; i++)
						result = new LApply(result, value.getArguments()[i]);
					return result;
				}
				else
				{
					return new LApply(EMatcher.this.otherwise.toLazy(), lazy);
				}
			}
		}.toLazy();
	}

	@Override
	public Expression substitute(final EVariable variable, final Expression expression)
	{
		return new EMatcher(this.pattern, // 
				this.expression.substitute(variable, expression), //
				this.otherwise.substitute(variable, expression));
	}
}