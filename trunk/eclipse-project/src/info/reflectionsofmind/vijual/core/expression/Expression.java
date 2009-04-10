package info.reflectionsofmind.vijual.core.expression;

import info.reflectionsofmind.vijual.core.lazy.ILazy;
import info.reflectionsofmind.vijual.core.lazy.IType;

import java.util.List;

public abstract class Expression
{
	public abstract ILazy toLazy();
	public abstract IType getType();
	public abstract Expression substitute(EVariable variable, Expression expression);
	public abstract List<EVariable> getVariables();
}
