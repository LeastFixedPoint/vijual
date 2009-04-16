package info.reflectionsofmind.vijual.core.expression;

import info.reflectionsofmind.vijual.core.ILazy;
import info.reflectionsofmind.vijual.core.type.IType;

public abstract class Expression
{
	public abstract ILazy toLazy();
	public abstract IType<?> getType();
	public abstract Expression substitute(EVariable variable, Expression expression);
}
