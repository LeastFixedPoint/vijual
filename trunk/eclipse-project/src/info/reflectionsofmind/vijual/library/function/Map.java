package info.reflectionsofmind.vijual.library.function;

import info.reflectionsofmind.vijual.core.lazy.IFunction;
import info.reflectionsofmind.vijual.core.lazy.ILazy;
import info.reflectionsofmind.vijual.core.lazy.IValue;
import info.reflectionsofmind.vijual.core.lazy.LApply;
import info.reflectionsofmind.vijual.core.lazy.LValue;
import info.reflectionsofmind.vijual.core.lazy.TFunction;
import info.reflectionsofmind.vijual.core.lazy.TVariable;
import info.reflectionsofmind.vijual.core.lazy.exception.EvaluationException;
import info.reflectionsofmind.vijual.core.lazy.exception.TypingException;
import info.reflectionsofmind.vijual.core.lazy.util.DerivedFunction;
import info.reflectionsofmind.vijual.library.data.list.TList;
import info.reflectionsofmind.vijual.library.data.list.VList;
import info.reflectionsofmind.vijual.library.data.list.VEmpty;

public final class Map implements IFunction
{
	private static final TVariable a = new TVariable(Map.class.getSimpleName() + ".a");
	private static final TFunction TYPE = new TFunction(new TFunction(a, a), new TFunction(new TList(a), new TList(a)));
	public static final Map INSTANCE = new Map();

	private Map()
	{
	}

	@Override
	public ILazy apply(final ILazy f) throws EvaluationException, TypingException
	{
		return new DerivedFunction(this)
		{
			@Override
			public ILazy apply(ILazy list) throws EvaluationException, TypingException
			{
				final IValue evList = list.evaluate();

				if (evList instanceof VList)
				{
					final ILazy head = ((VList) evList).getHead();
					final ILazy newHead = new LApply(f, head);

					final LApply newTail = new LApply( //
							new LApply(new LValue(Map.INSTANCE), f), // 
							((VList) evList).getTail());
					
					return new LValue(new VList(newHead, newTail));
				}
				else if (evList instanceof VEmpty)
				{
					return new LValue(evList);
				}
				else
				{
					throw new EvaluationException();
				}
			}
		}.toLazy();
	}

	@Override
	public TFunction getType()
	{
		return TYPE;
	}
}
