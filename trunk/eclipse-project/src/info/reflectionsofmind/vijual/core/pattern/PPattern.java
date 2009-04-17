package info.reflectionsofmind.vijual.core.pattern;

import info.reflectionsofmind.vijual.core.lazy.ILazy;
import info.reflectionsofmind.vijual.core.lazy.LApply;
import info.reflectionsofmind.vijual.core.lazy.LValue;
import info.reflectionsofmind.vijual.core.lazy.LVariable;
import info.reflectionsofmind.vijual.core.type.ITypeDefined;
import info.reflectionsofmind.vijual.core.value.IConstructor;
import info.reflectionsofmind.vijual.core.value.IValue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class PPattern implements IPattern
{
	private final ILazy pattern;
	private final List<LVariable> variables;

	public PPattern(final ILazy pattern, LVariable... variables)
	{
		this.pattern = pattern;
		this.variables = Arrays.asList(variables);
	}

	@Override
	public ITypeDefined getMatchingType()
	{
		return this.pattern.getType();
	}

	@Override
	public Map<LVariable, ILazy> match(final ILazy value)
	{
		return new Matcher(value).match();
	}
	
	@Override
	public List<LVariable> getPatternVariables()
	{
		return this.variables;
	}

	@Override
	public IPattern substitute(final LVariable variable, final ILazy expression)
	{
		final LApply newPattern = (LApply) this.pattern.substitute(variable, expression);
		return newPattern == this.pattern ? this : new PPattern(newPattern);
	}

	public class Matcher
	{
		private final Map<LVariable, ILazy> map = new HashMap<LVariable, ILazy>();
		private final ILazy value;

		public Matcher(final ILazy value)
		{
			this.value = value;
		}

		public Map<LVariable, ILazy> match()
		{
			this.map.clear();
			return match(PPattern.this.pattern, this.value) ? this.map : null;
		}

		private boolean match(final ILazy pattern, final ILazy value)
		{
			if (pattern instanceof LVariable)
			{
				return matchVariable((LVariable) pattern, value);
			}
			else if (pattern instanceof LValue)
			{
				return matchValue((LValue) pattern, value);
			}
			else if (pattern instanceof LApply)
			{
				return matchApply((LApply) pattern, value);
			}
			else
			{
				return false;
			}
		}

		private boolean matchVariable(final LVariable pattern, final ILazy value)
		{
			this.map.put(pattern, value);
			return true;
		}

		private boolean matchValue(final LValue pattern, final ILazy value)
		{
			if (!(value instanceof LValue)) return false;
			final IValue evaluatedValue = value.evaluate();

			if (!(evaluatedValue instanceof IConstructor)) return false;
			final IConstructor valueConstructor = (IConstructor) evaluatedValue;

			final IConstructor patternConstructor = (IConstructor) pattern.evaluate();
			if (!valueConstructor.equals(patternConstructor)) return false;

			return true;
		}

		private boolean matchApply(final LApply pattern, final ILazy value)
		{
			if (!(value instanceof LApply)) return false;

			final ILazy valueArgument = ((LApply) value).getArgument();
			final ILazy valueFunction = ((LApply) value).getFunction();

			final ILazy patternArgument = pattern.getArgument();
			final ILazy patternFunction = pattern.getFunction();

			return match(valueFunction, patternFunction) && match(valueArgument, patternArgument);
		}
	}
	
	@Override
	public String toString()
	{
		return "[pattern: " + this.pattern + "]";
	}
}
