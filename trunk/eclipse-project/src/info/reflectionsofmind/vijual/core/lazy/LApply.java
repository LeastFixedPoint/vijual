package info.reflectionsofmind.vijual.core.lazy;

import java.util.Set;

import info.reflectionsofmind.util.Sets;
import info.reflectionsofmind.vijual.core.type.ITypeDefined;
import info.reflectionsofmind.vijual.core.util.Types;
import info.reflectionsofmind.vijual.core.value.IFunction;
import info.reflectionsofmind.vijual.core.value.IValue;
import info.reflectionsofmind.vijual.library.type.function.TFunction;

public final class LApply implements ILazy
{
	private final ITypeDefined type;
	private final ILazy function;
	private final ILazy argument;

	public LApply(final ILazy function, final ILazy argument)
	{
		this.type = Types.resolve((TFunction) function.getType(), argument.getType());

		this.function = function;
		this.argument = argument;
	}
	
	@Override
	public Set<LVariable> getVariables()
	{
		return Sets.union(this.function.getVariables(), this.argument.getVariables());
	}

	@Override
	public IValue evaluate()
	{
		return ((IFunction) this.function.evaluate()).apply(this.argument).evaluate();
	}

	@Override
	public ILazy substitute(final LVariable variable, final ILazy expression)
	{
		final ILazy newFunction = this.function.substitute(variable, expression);
		final ILazy newArgument = this.argument.substitute(variable, expression);

		if (this.function == newFunction && this.argument == newArgument)
		{
			return this;
		}
		else
		{
			return new LApply(newFunction, newArgument);
		}
	}

	@Override
	public ITypeDefined getType()
	{
		return this.type;
	}

	public ILazy getFunction()
	{
		return this.function;
	}

	public ILazy getArgument()
	{
		return this.argument;
	}

	@Override
	public String toString()
	{
		return "[lazy: " + getType().toString() + "]";
	}
}
