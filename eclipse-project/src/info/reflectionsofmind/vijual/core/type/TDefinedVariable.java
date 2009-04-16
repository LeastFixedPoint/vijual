package info.reflectionsofmind.vijual.core.type;

import info.reflectionsofmind.vijual.core.kind.KDefined;

public final class TDefinedVariable extends TVariable<KDefined> implements ITypeDefined
{
	public TDefinedVariable(final String name)
	{
		super("$" + name, KDefined.INSTANCE);
	}
}
