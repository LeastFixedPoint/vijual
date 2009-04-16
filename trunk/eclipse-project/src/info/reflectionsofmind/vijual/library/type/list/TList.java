package info.reflectionsofmind.vijual.library.type.list;

import info.reflectionsofmind.vijual.core.kind.KDefined;
import info.reflectionsofmind.vijual.core.type.ITypeDefined;
import info.reflectionsofmind.vijual.core.type.TDefinedConstructed;
import info.reflectionsofmind.vijual.core.type.TDefinedVariable;
import info.reflectionsofmind.vijual.core.value.VConstructor;
import info.reflectionsofmind.vijual.core.value.Value;

public final class TList extends TDefinedConstructed<KDefined>
{
	public static final Value EMPTY = new Value(new TList(new TDefinedVariable("a")));

	private static final TDefinedVariable b = new TDefinedVariable("b");
	public static final Value PREPEND = new VConstructor(new TList(b), b, new TList(b));

	public TList(final ITypeDefined type)
	{
		super(TListConstructor.INSTANCE, type);
	}

	public ITypeDefined getElementType()
	{
		return (ITypeDefined) super.getArgument();
	}
}
