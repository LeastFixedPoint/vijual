package info.reflectionsofmind.vijual.library.type.list;

import info.reflectionsofmind.vijual.core.kind.KDefined;
import info.reflectionsofmind.vijual.core.type.ITypeDefined;
import info.reflectionsofmind.vijual.core.type.TDefinedConstructed;
import info.reflectionsofmind.vijual.core.type.TDefinedVariable;
import info.reflectionsofmind.vijual.core.value.VConstructor;
import info.reflectionsofmind.vijual.core.value.Value;

public final class TList extends TDefinedConstructed<KDefined>
{
	public TList(final ITypeDefined type)
	{
		super(TListConstructor.INSTANCE, type);
	}

	public ITypeDefined getElementType()
	{
		return (ITypeDefined) super.getArgument();
	}
	
	public static Value newEmpty()
	{
		return new Value("List.Empty", new TList(new TDefinedVariable("a")));
	}
	
	public static Value newPrepend()
	{
		final TDefinedVariable a = new TDefinedVariable("a");
		return new VConstructor("List.Prepend", new TList(a), a, new TList(a));
	}
}
