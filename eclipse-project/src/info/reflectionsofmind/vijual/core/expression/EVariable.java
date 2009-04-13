package info.reflectionsofmind.vijual.core.expression;

import info.reflectionsofmind.vijual.core.lazy.ILazy;
import info.reflectionsofmind.vijual.core.lazy.IType;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

public final class EVariable extends Expression
{
	private final static Map<EVariable, String> names = new WeakHashMap<EVariable, String>();
	private static String newName = "a";

	private final IType type;

	public EVariable(IType type)
	{
		this.type = type;

		names.put(this, newName);
		newName = "" + (char) (newName.charAt(0) + 1);
	}

	@Override
	public Expression substitute(EVariable variable, Expression expression)
	{
		return expression;
	}

	@Override
	public List<EVariable> getVariables()
	{
		return Collections.singletonList(this);
	}

	@Override
	public ILazy toLazy()
	{
		throw new RuntimeException("Cannot directly evaluate variables");
	}

	@Override
	public IType getType()
	{
		return this.type;
	}

	@Override
	public String toString()
	{
		return "[var(" + names.get(this) + "): " + getType() + "]";
	}
}
