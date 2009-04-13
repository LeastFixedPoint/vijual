package info.reflectionsofmind.vijual.core.lazy.tuple;

import info.reflectionsofmind.vijual.core.lazy.CConstructor;
import info.reflectionsofmind.vijual.core.lazy.IType;

public final class CTuple extends CConstructor<TTuple>
{
	public CTuple(IType... componentTypes)
	{
		super(new TTuple(componentTypes), componentTypes);
	}
}
