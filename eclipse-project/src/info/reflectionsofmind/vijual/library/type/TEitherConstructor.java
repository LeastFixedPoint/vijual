package info.reflectionsofmind.vijual.library.type;

import info.reflectionsofmind.vijual.library.type.template.TFunction1;

public final class TEitherConstructor extends TFunction1
{
	public static final TEitherConstructor INSTANCE = new TEitherConstructor();

	private TEitherConstructor()
	{
		super("Either");
	}
}
