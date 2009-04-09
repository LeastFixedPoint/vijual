package info.reflectionsofmind.vijual.library.function;

import info.reflectionsofmind.vijual.core.IFunction;
import info.reflectionsofmind.vijual.core.ILazy;
import info.reflectionsofmind.vijual.core.LValue;
import info.reflectionsofmind.vijual.core.TFunction;
import info.reflectionsofmind.vijual.core.TVariable;
import info.reflectionsofmind.vijual.core.exception.EvaluationException;
import info.reflectionsofmind.vijual.core.exception.TypingException;
import info.reflectionsofmind.vijual.core.util.DerivedFunction;
import info.reflectionsofmind.vijual.library.data.list.TList;
import info.reflectionsofmind.vijual.library.data.list.VList;

public final class Prepend implements IFunction
{
	private static final TVariable a = new TVariable("a[" + Prepend.class.getName() + "]");
	private static final TFunction TYPE = new TFunction(a, new TFunction(new TList(a), new TList(a)));
	public static final Prepend INSTANCE = new Prepend();

	@Override
	public ILazy apply(final ILazy head) throws EvaluationException, TypingException
	{
		return new DerivedFunction(this)
		{
			@Override
			public ILazy apply(final ILazy tail) throws EvaluationException, TypingException
			{
				return new LValue(new VList(head, tail));
			}
		}.toLazy();
	}

	@Override
	public TFunction getType()
	{
		return TYPE;
	}
}
