package info.reflectionsofmind.vijual.library.data.list;

import info.reflectionsofmind.vijual.core.CConstructor;
import info.reflectionsofmind.vijual.core.ILazy;
import info.reflectionsofmind.vijual.core.LValue;
import info.reflectionsofmind.vijual.core.TVariable;

public class CEmpty extends CConstructor<TList>
{
	private static final TList TYPE = new TList(new TVariable("a", CEmpty.class));

	public CEmpty()
	{
		super(TYPE);
	}
	
	@Override
	public ILazy construct(ILazy... args)
	{
		return new LValue(VEmpty.INSTANCE);
	}
}
