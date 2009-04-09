package info.reflectionsofmind.vijual.core.tuple;

import java.util.Arrays;

import info.reflectionsofmind.vijual.core.CConstructor;
import info.reflectionsofmind.vijual.core.ILazy;
import info.reflectionsofmind.vijual.core.IType;
import info.reflectionsofmind.vijual.core.LValue;

public class CTuple extends CConstructor
{
	public CTuple(IType... componentTypes)
	{
		super(new TTuple(componentTypes), Arrays.asList(componentTypes));
	}
	
	@Override
	public ILazy construct(ILazy... args)
	{
		return new LValue(new VTuple(args));
	}
}
