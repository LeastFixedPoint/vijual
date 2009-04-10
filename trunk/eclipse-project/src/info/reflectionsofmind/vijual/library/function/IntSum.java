package info.reflectionsofmind.vijual.library.function;

import info.reflectionsofmind.vijual.core.lazy.IFunction;
import info.reflectionsofmind.vijual.core.lazy.ILazy;
import info.reflectionsofmind.vijual.core.lazy.LValue;
import info.reflectionsofmind.vijual.core.lazy.TFunction;
import info.reflectionsofmind.vijual.core.lazy.exception.EvaluationException;
import info.reflectionsofmind.vijual.core.lazy.exception.TypingException;
import info.reflectionsofmind.vijual.core.lazy.util.DerivedFunction;
import info.reflectionsofmind.vijual.library.data.integer.TInteger;
import info.reflectionsofmind.vijual.library.data.integer.VInteger;

public final class IntSum implements IFunction
{
	private final static TFunction TYPE = new TFunction(TInteger.INSTANCE, new TFunction(TInteger.INSTANCE, TInteger.INSTANCE));
	public final static IntSum INSTANCE = new IntSum();
	
	private IntSum()
	{
		
	}

	@Override
	public TFunction getType()
	{
		return TYPE;
	}

	@Override
	public ILazy apply(final ILazy lazy1)
	{
		return new DerivedFunction(this)
		{
			@Override
			public ILazy apply(final ILazy lazy2) throws EvaluationException, TypingException
			{
				return new LValue(new VInteger(((VInteger) lazy1.evaluate()).getValue() + ((VInteger) lazy2.evaluate()).getValue()));
			}
		}.toLazy();
	}
	
	@Override
	public String toString()
	{
		return IntSum.class.getSimpleName();
	}
}
