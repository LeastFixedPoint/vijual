package info.reflectionsofmind.vijual.core.expression;

import info.reflectionsofmind.vijual.core.lazy.FFunction;
import info.reflectionsofmind.vijual.core.lazy.IConstructor;
import info.reflectionsofmind.vijual.core.lazy.ILazy;
import info.reflectionsofmind.vijual.core.lazy.IType;
import info.reflectionsofmind.vijual.core.lazy.TFunction;
import info.reflectionsofmind.vijual.core.lazy.exception.EvaluationException;
import info.reflectionsofmind.vijual.core.lazy.exception.TypingException;

import java.util.List;

public final class EMatcher<TType extends IType> extends Expression
{
	private final IConstructor<TType> constructor;
	private final Expression expression;
	private final Expression otherwise;
	private final IType resultType;
	private final TFunction type;

	public EMatcher(IConstructor<TType> constructor, Expression expression, Expression otherwise)
	{
		final List<EVariable> variables = expression.getVariables();
		final IType[] types = constructor.getArgumentTypes();

		if (variables.size() < types.length) throw new TypingException("Expression does not have enough parameters to accomodate constructor's arguments.");

		for (int i = 0; i < types.length; i++)
			if (!variables.get(i).getType().equals(types[i])) throw new TypingException("Cannot match types " + variables.get(i).getType() + " and " + types[i]);

		if (otherwise.getVariables().isEmpty()) throw new TypingException("Otherwise expression must take matching value as parameter.");
		if (otherwise.getVariables().get(0).getType() != constructor.getConstructedType()) throw new TypingException("Otherwise expression's parameter must match types with matching value.");

		this.constructor = constructor;
		this.expression = expression;
		this.otherwise = otherwise;

		this.type = new TFunction(this.constructor.getConstructedType(), this.resultType);
	}

	@Override
	public TFunction getType()
	{
		return this.type;
	}

	@Override
	public ILazy toLazy()
	{
		return new FFunction(getType())
		{
			@Override
			public ILazy apply(ILazy lazy) throws EvaluationException, TypingException
			{

				return null;
			}
		}.toLazy();
	}

	@Override
	public Expression substitute(EVariable variable, Expression expression)
	{
		return new EMatcher<TType>(this.constructor, // 
				this.expression.substitute(variable, expression), //
				this.otherwise.substitute(variable, expression));
	}
}