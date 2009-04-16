package info.reflectionsofmind.vijual.library.function;

import info.reflectionsofmind.vijual.core.ILazy;
import info.reflectionsofmind.vijual.core.LApply;
import info.reflectionsofmind.vijual.core.VValue;
import info.reflectionsofmind.vijual.core.exception.EvaluationException;
import info.reflectionsofmind.vijual.core.exception.TypingException;
import info.reflectionsofmind.vijual.core.type.TVariable;
import info.reflectionsofmind.vijual.core.util.DerivedFunction;
import info.reflectionsofmind.vijual.core.value.IValue;
import info.reflectionsofmind.vijual.core.value.VFunction;
import info.reflectionsofmind.vijual.library.data.list.CEmpty;
import info.reflectionsofmind.vijual.library.data.list.CPrepend;
import info.reflectionsofmind.vijual.library.data.list.TList;
import info.reflectionsofmind.vijual.library.type.function.TFunctionConstructor;

public final class Map extends VFunction
{
	private static final TVariable a = new TVariable(Map.class.getSimpleName() + ".a");
	private static final TFunctionConstructor TYPE = new TFunctionConstructor(new TFunctionConstructor(a, a), new TFunctionConstructor(new TList(a), new TList(a)));
	public static final Map INSTANCE = new Map();

	private Map()
	{
		super(TYPE);
	}

	@Override
	public ILazy apply(final ILazy f) throws EvaluationException, TypingException
	{
		return new DerivedFunction(this)
		{
			@SuppressWarnings("unchecked")
			@Override
			public ILazy apply(ILazy list) throws EvaluationException, TypingException
			{
				final IValue evList = list.evaluate();

				if (evList.getConstructor() == CEmpty.INSTANCE)
				{
					return list;
				}
				else if (evList.getConstructor() == CPrepend.INSTANCE)
				{
					final VValue<TList, CPrepend> prepend = (VValue<TList, CPrepend>) evList;

					final ILazy newHead = new LApply(f, prepend.getArguments()[0]);
					final LApply newTail = new LApply(toLazy(), prepend.getArguments()[1]);

					return CPrepend.INSTANCE.construct(newHead, newTail);
				}
				else
				{
					throw new EvaluationException(evList + " is not a list! It is constructed by " + evList.getConstructor() + ".");
				}
			}
		}.toLazy();
	}
}
