package info.reflectionsofmind.vijual.library.type.integer;

import info.reflectionsofmind.vijual.library.type.template.TPrimitive;

public final class TInteger extends TPrimitive
{
	public static final TInteger INSTANCE = new TInteger();
	
	private TInteger()
	{
		super("Integer");
	}
}
