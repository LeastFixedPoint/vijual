package info.reflectionsofmind.vijual.library.type;

import info.reflectionsofmind.vijual.library.type.template.TFunction1;


public final class TMaybeConstructor extends TFunction1
{
	public static final TMaybeConstructor INSTANCE = new TMaybeConstructor();
	
	private TMaybeConstructor()
	{
		super("Maybe");
	}
}
