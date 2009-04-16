package info.reflectionsofmind.vijual.core.expression;

import java.util.HashMap;
import java.util.Map;

public class Context
{
	private final Map<String, Expression> expressions = new HashMap<String, Expression>();
	
	public Expression getByName(String name)
	{
		return this.expressions.get(name);
	}
	
	public void addExpression(String name, Expression expression)
	{
		this.expressions.put(name, expression);
	}
}
