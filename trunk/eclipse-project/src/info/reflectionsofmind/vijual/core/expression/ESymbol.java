package info.reflectionsofmind.vijual.core.expression;

import info.reflectionsofmind.vijual.core.ILazy;
import info.reflectionsofmind.vijual.core.type.IType;

public final class ESymbol extends Expression
{
	private final Context context;
	private final String name;
	private final IType type;
	
	public ESymbol(Context context, String name, IType type)
	{
		this.context = context;
		this.name = name;
		this.type = type;
	}
	
	@Override
	public IType getType()
	{
		return this.type;
	}
	
	@Override
	public Expression substitute(EVariable variable, Expression expression)
	{
		return context.getByName(name).substitute(variable, expression);
	}
	
	@Override
	public ILazy toLazy()
	{
		return context.getByName(name).toLazy();
	}
}
