package info.reflectionsofmind.vijual.library.data.list;

import info.reflectionsofmind.vijual.core.lazy.CConstructor;
import info.reflectionsofmind.vijual.core.lazy.TVariable;

public final class CPrepend extends CConstructor<TList>
{
	private static final TVariable a = new TVariable("a", CPrepend.class);
	public static final CPrepend INSTANCE = new CPrepend();
	
	private CPrepend()
	{
		super(new TList(a), a, new TList(a));
	}
}
