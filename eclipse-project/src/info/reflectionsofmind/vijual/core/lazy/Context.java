package info.reflectionsofmind.vijual.core.lazy;

import info.reflectionsofmind.vijual.core.exception.EvaluationException;
import info.reflectionsofmind.vijual.core.exception.TypingException;
import info.reflectionsofmind.vijual.core.type.ITypeDefined;
import info.reflectionsofmind.vijual.core.value.IValue;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class Context implements IContext
{
	private final Map<String, ILazy> map = new HashMap<String, ILazy>();

	@Override
	public ILazy resolve(String name)
	{
		return this.map.get(name);
	}
	
	public void bind(String name, ILazy lazy)
	{
		this.map.put(name, lazy);
	}

	public final class LReference implements ILazy
	{
		private final ITypeDefined type;
		private final String name;

		public LReference(String name, ITypeDefined type)
		{
			this.name = name;
			this.type = type;
		}
		
		public ILazy resolve()
		{
			return Context.this.resolve(this.name);
		}
		
		@Override
		public Set<LVariable> getVariables()
		{
			return resolve().getVariables();
		}

		@Override
		public IValue evaluate() throws EvaluationException, TypingException
		{
			return resolve().evaluate();
		}
		
		@Override
		public ILazy substitute(LVariable variable, ILazy expression)
		{
			return resolve().substitute(variable, expression);
		}

		public ITypeDefined getType()
		{
			return resolve() == null ? this.type : resolve().getType();
		}
		
		@Override
		public String toString()
		{
			return "[var(" + this.name + "): " + getType() + "]";
		}
	}
}
