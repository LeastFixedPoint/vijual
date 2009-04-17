package info.reflectionsofmind.vijual.core.expression;

import info.reflectionsofmind.vijual.core.VValue;
import info.reflectionsofmind.vijual.core.lazy.ILazy;
import info.reflectionsofmind.vijual.core.lazy.LValue;
import info.reflectionsofmind.vijual.core.type.IType;
import info.reflectionsofmind.vijual.core.value.IConstructor;

public class EConstruct extends Expression
{
	private final IConstructor<? extends IType> constructor;
	private final Expression[] arguments;

	public EConstruct(final IConstructor<? extends IType> constructor, final Expression... arguments)
	{
		this.arguments = arguments;
		this.constructor = constructor;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ILazy toLazy()
	{
		final ILazy[] arguments = new ILazy[this.arguments.length];

		for (int i = 0; i < arguments.length; i++)
			arguments[i] = this.arguments[i].toLazy();

		return new LValue(new VValue<IType, IConstructor<IType>>((IConstructor<IType>) this.constructor, arguments));
	}

	@Override
	public Expression substitute(final EVariable variable, final Expression expression)
	{
		final Expression[] newArguments = new Expression[this.arguments.length];

		boolean changed = false;
		for (int i = 0; i < this.arguments.length; i++)
			// I'm gonna burn in hell for this trick, most def.
			changed = (newArguments[i] = this.arguments[i].substitute(variable, expression)) != this.arguments[i];

		return changed ? new EConstruct(this.constructor, newArguments) : this;
	}

	@Override
	public IType getType()
	{
		return constructor.getConstructedType().parameterize(arguments);
	}
}
