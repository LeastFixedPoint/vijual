package info.reflectionsofmind.vijual.core.expression;

import info.reflectionsofmind.util.Lists;
import info.reflectionsofmind.vijual.core.lazy.FFunction;
import info.reflectionsofmind.vijual.core.lazy.IFunction;
import info.reflectionsofmind.vijual.core.lazy.ILazy;
import info.reflectionsofmind.vijual.core.lazy.IType;
import info.reflectionsofmind.vijual.core.lazy.LApply;
import info.reflectionsofmind.vijual.core.lazy.LValue;
import info.reflectionsofmind.vijual.core.lazy.TFunction;
import info.reflectionsofmind.vijual.core.lazy.exception.EvaluationException;
import info.reflectionsofmind.vijual.core.lazy.exception.TypingException;
import info.reflectionsofmind.vijual.core.lazy.util.Types;

import java.util.List;

public final class EApplication extends Expression
{
	private final List<EVariable> variables;
	private final Expression function;
	private final Expression argument;
	private final IType type;

	public EApplication(final Expression function, final Expression argument)
	{
		this.function = function;
		this.argument = argument;
		this.variables = Lists.concat(this.function.getVariables(), this.argument.getVariables());

		IType type = Types.resolve((TFunction) function.getType(), argument.getType());
		
		for (int i = this.variables.size() - 1; i >= 0; --i)
			type = new TFunction(this.variables.get(i).getType(), type);

		this.type = type;
	}

	@Override
	public ILazy toLazy()
	{
		if (this.variables.isEmpty()) return new LApply(this.function.toLazy(), this.argument.toLazy());

		return new LValue(new FFunction((TFunction) EApplication.this.getType())
		{
			@Override
			public ILazy apply(ILazy lazy) throws EvaluationException, TypingException
			{
				return EApplication.this.substitute(getVariables().get(0), new EConstant(lazy)).toLazy();
			}
		});
	}

	@Override
	public Expression substitute(EVariable variable, Expression expression)
	{
		if (getVariables().isEmpty()) return this;

		return new EApplication(//
				getFunction().substitute(variable, expression), //
				getArgument().substitute(variable, expression));
	}

	@Override
	public List<EVariable> getVariables()
	{
		return this.variables;
	}

	@Override
	public IType getType()
	{
		return this.type;
	}

	public Expression getFunction()
	{
		return this.function;
	}

	public Expression getArgument()
	{
		return this.argument;
	}

	@Override
	public String toString()
	{
		return "(" + getFunction().toString() + " " + getArgument().toString() + ")";
	}
}
