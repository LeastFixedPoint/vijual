package info.reflectionsofmind.vijual.library.data.list;

import info.reflectionsofmind.vijual.core.lazy.CConstructor;
import info.reflectionsofmind.vijual.core.lazy.ILazy;
import info.reflectionsofmind.vijual.core.lazy.LValue;
import info.reflectionsofmind.vijual.core.lazy.TVariable;

public class CEmpty extends CConstructor<TList>
{
	private static final TList TYPE = new TList(new TVariable("a", CEmpty.class));
	public static final CEmpty INSTANCE = new CEmpty();

	private CEmpty()
	{
		super(TYPE);
	}
	
	@Override
	public ILazy construct(ILazy... args)
	{
		return new LValue(VEmpty.INSTANCE);
	}
}
