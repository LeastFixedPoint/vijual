package info.reflectionsofmind.vijual.core.lazy.tuple;

import info.reflectionsofmind.vijual.core.lazy.CConstructor;
import info.reflectionsofmind.vijual.core.lazy.ILazy;
import info.reflectionsofmind.vijual.core.lazy.IType;
import info.reflectionsofmind.vijual.core.lazy.LValue;

public class CTuple extends CConstructor<TTuple>
{
	public CTuple(IType... componentTypes)
	{
		super(new TTuple(componentTypes), componentTypes);
	}
	
	@Override
	public ILazy construct(ILazy... args)
	{
		return new LValue(new VTuple(args));
	}
}
