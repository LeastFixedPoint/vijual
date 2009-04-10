package info.reflectionsofmind.vijual.library.data.list;

import info.reflectionsofmind.vijual.core.lazy.CConstructor;
import info.reflectionsofmind.vijual.core.lazy.ILazy;
import info.reflectionsofmind.vijual.core.lazy.LValue;
import info.reflectionsofmind.vijual.core.lazy.TVariable;
import info.reflectionsofmind.vijual.core.lazy.exception.TypingException;

public class CPrepend extends CConstructor<TList>
{
	private static final TVariable a = new TVariable("a", CPrepend.class);
	public static final CPrepend INSTANCE = new CPrepend();
	
	private CPrepend()
	{
		super(new TList(a), a, new TList(a));
	}
	
	@Override
	public ILazy construct(ILazy... args) throws TypingException
	{
		return new LValue(new VList(args[0], args[1]));
	}
}
