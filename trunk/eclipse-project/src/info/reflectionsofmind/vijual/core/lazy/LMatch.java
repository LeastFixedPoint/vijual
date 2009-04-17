package info.reflectionsofmind.vijual.core.lazy;

import info.reflectionsofmind.util.Sets;
import info.reflectionsofmind.vijual.core.exception.EvaluationException;
import info.reflectionsofmind.vijual.core.exception.TypingException;
import info.reflectionsofmind.vijual.core.pattern.IPattern;
import info.reflectionsofmind.vijual.core.type.ITypeDefined;
import info.reflectionsofmind.vijual.core.util.Types;
import info.reflectionsofmind.vijual.core.value.IValue;
import info.reflectionsofmind.vijual.library.type.function.TFunction;

import java.util.Set;

public class LMatch implements ILazy
{
	private final IPattern pattern;
	private final ILazy ifMatched;
	private final ILazy otherwise;
	private final ITypeDefined type;

	public LMatch(final IPattern pattern, final ILazy ifMatched, final ILazy otherwise)
	{
		ITypeDefined type = ifMatched.getType();

		for (int i = pattern.getPatternVariables().size() - 1; i >= 0; i--)
		{
			if (type instanceof TFunction)
				type = ((TFunction) type).getResType();
			else
				throw new RuntimeException("Type " + type + " is not a function; there's more variables in pattern (" // 
						+ pattern.getPatternVariables().size() + ") than arguments in match case (" // 
						+ (pattern.getPatternVariables().size() - i - 1) + ").");
		}

		try
		{
			this.type = (ITypeDefined) Types.applySubstitutions(Types.solve(type, ((TFunction) otherwise.getType()).getResType()), type);
		}
		catch (final TypingException exception)
		{
			throw new RuntimeException("Match cases' result types are different: " + type + " != " + ((TFunction) otherwise.getType()).getResType() + ".", exception);
		}

		this.pattern = pattern;
		this.ifMatched = ifMatched;
		this.otherwise = otherwise;
	}

	@Override
	public Set<LVariable> getVariables()
	{
		return Sets.union(this.otherwise.getVariables(),//
				Sets.subtract(this.ifMatched.getVariables(), this.pattern.getPatternVariables()));
	}

	@Override
	public ILazy substitute(final LVariable variable, final ILazy expression)
	{
		return new LMatch( //
				this.pattern.substitute(variable, expression), // 
				this.ifMatched.substitute(variable, expression), //
				this.otherwise.substitute(variable, expression));
	}

	@Override
	public IValue evaluate() throws EvaluationException, TypingException
	{
		return null;
	}

	@Override
	public ITypeDefined getType()
	{
		return this.type;
	}

	@Override
	public String toString()
	{
		return "[match: " + this.pattern + " ? " + this.ifMatched + " : " + this.otherwise + "]";
	}
}
