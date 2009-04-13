package info.reflectionsofmind.vijual.library.function;

import info.reflectionsofmind.vijual.core.lazy.FFunction;
import info.reflectionsofmind.vijual.core.lazy.ILazy;
import info.reflectionsofmind.vijual.core.lazy.TFunction;
import info.reflectionsofmind.vijual.core.lazy.exception.EvaluationException;
import info.reflectionsofmind.vijual.core.lazy.exception.TypingException;
import info.reflectionsofmind.vijual.core.lazy.util.DerivedFunction;
import info.reflectionsofmind.vijual.library.data.integer.CInteger;
import info.reflectionsofmind.vijual.library.data.integer.TInteger;

public final class IntSum extends FFunction
{
	private final static TFunction TYPE = new TFunction(TInteger.INSTANCE, new TFunction(TInteger.INSTANCE, TInteger.INSTANCE));
	public final static IntSum INSTANCE = new IntSum();
	
	private IntSum()
	{
		super(TYPE);
	}

	@Override
	public ILazy apply(final ILazy lazy1)
	{
		return new DerivedFunction(this)
		{
			@Override
			public ILazy apply(final ILazy lazy2) throws EvaluationException, TypingException
			{
				final int i1 = ((CInteger)lazy1.evaluate().getConstructor()).getValue();
				final int i2 = ((CInteger)lazy2.evaluate().getConstructor()).getValue();
				
				return new CInteger(i1 + i2).construct();
			}
		}.toLazy();
	}
}
