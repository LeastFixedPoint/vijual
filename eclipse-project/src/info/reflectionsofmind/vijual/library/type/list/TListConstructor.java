package info.reflectionsofmind.vijual.library.type.list;

import info.reflectionsofmind.vijual.library.type.template.TFunction1;


public final class TListConstructor extends TFunction1
{
	public static final TListConstructor INSTANCE = new TListConstructor();
	
	private TListConstructor()
	{
		super("List");
	}
}
