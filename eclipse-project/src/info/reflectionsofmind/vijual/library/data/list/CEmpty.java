package info.reflectionsofmind.vijual.library.data.list;

import info.reflectionsofmind.vijual.core.lazy.CConstructor;
import info.reflectionsofmind.vijual.core.lazy.TVariable;

public final class CEmpty extends CConstructor<TList>
{
	public static final CEmpty INSTANCE = new CEmpty();

	private CEmpty()
	{
		super(new TList(new TVariable("a", CEmpty.class)));
	}
}
