package info.reflectionsofmind.vijual.library.function;

import info.reflectionsofmind.vijual.core.lazy.FFunction;
import info.reflectionsofmind.vijual.core.lazy.ILazy;
import info.reflectionsofmind.vijual.core.lazy.IValue;
import info.reflectionsofmind.vijual.core.lazy.LApply;
import info.reflectionsofmind.vijual.core.lazy.TFunction;
import info.reflectionsofmind.vijual.core.lazy.TVariable;
import info.reflectionsofmind.vijual.core.lazy.VConstructed;
import info.reflectionsofmind.vijual.core.lazy.exception.EvaluationException;
import info.reflectionsofmind.vijual.core.lazy.exception.TypingException;
import info.reflectionsofmind.vijual.core.lazy.util.DerivedFunction;
import info.reflectionsofmind.vijual.library.data.list.CEmpty;
import info.reflectionsofmind.vijual.library.data.list.CPrepend;
import info.reflectionsofmind.vijual.library.data.list.TList;

public final class Map extends FFunction
{
	private static final TVariable a = new TVariable(Map.class.getSimpleName() + ".a");
	private static final TFunction TYPE = new TFunction(new TFunction(a, a), new TFunction(new TList(a), new TList(a)));
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
					final VConstructed<TList, CPrepend> prepend = (VConstructed<TList, CPrepend>) evList;

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
