package info.reflectionsofmind.vijual.core.type;

import info.reflectionsofmind.vijual.core.kind.KDefined;

import java.util.Set;

public abstract class TDefined extends TType<KDefined> implements ITypeDefined
{
	public TDefined(String name)
	{
		super(name, KDefined.INSTANCE);
	}

	public TDefined(String name, Set<TVariable<?>> variables)
	{
		super(name, KDefined.INSTANCE, variables);
	}
}
