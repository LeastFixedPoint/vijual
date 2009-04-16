package info.reflectionsofmind.vijual.library.function;

import info.reflectionsofmind.vijual.core.ILazy;
import info.reflectionsofmind.vijual.core.exception.EvaluationException;
import info.reflectionsofmind.vijual.core.exception.TypingException;
import info.reflectionsofmind.vijual.core.util.DerivedFunction;
import info.reflectionsofmind.vijual.core.value.VFunction;
import info.reflectionsofmind.vijual.library.type.function.TFunctionConstructor;
import info.reflectionsofmind.vijual.library.type.integer.CInteger;
import info.reflectionsofmind.vijual.library.type.integer.TInteger;

public final class IntSum extends VFunction
{
	private final static TFunctionConstructor TYPE = new TFunctionConstructor(TInteger.INSTANCE, new TFunctionConstructor(TInteger.INSTANCE, TInteger.INSTANCE));
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
