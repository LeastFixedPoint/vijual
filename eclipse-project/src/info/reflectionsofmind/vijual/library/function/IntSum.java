package info.reflectionsofmind.vijual.library.function;

import info.reflectionsofmind.vijual.core.exception.EvaluationException;
import info.reflectionsofmind.vijual.core.exception.TypingException;
import info.reflectionsofmind.vijual.core.lazy.ILazy;
import info.reflectionsofmind.vijual.core.value.VFunction;
import info.reflectionsofmind.vijual.library.type.function.TFunction;
import info.reflectionsofmind.vijual.library.type.integer.TInteger;
import info.reflectionsofmind.vijual.library.type.integer.Vinteger;

public final class IntSum extends VFunction
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
		return new Derived(this, lazy1)
		{
			@Override
			public ILazy apply(final ILazy lazy2) throws EvaluationException, TypingException
			{
				final int i1 = ((Vinteger)lazy1.evaluate()).getValue();
				final int i2 = ((Vinteger)lazy2.evaluate()).getValue();
				
				return new Vinteger(i1 + i2).toLazy();
			}
		}.toLazy();
	}
}
