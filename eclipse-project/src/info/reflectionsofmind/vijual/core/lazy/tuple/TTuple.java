package info.reflectionsofmind.vijual.core.lazy.tuple;

import java.util.Arrays;
import java.util.List;

import info.reflectionsofmind.vijual.core.lazy.IConstructor;
import info.reflectionsofmind.vijual.core.lazy.IType;
import info.reflectionsofmind.vijual.core.lazy.TComposite;

public final class TTuple extends TComposite
{
	private final List<? extends IConstructor<TTuple>> constructors;

	public TTuple(IType... componentTypes)
	{
		super(componentTypes);
		this.constructors = Arrays.asList(new CTuple(componentTypes));
	}
	
	@Override
	public TTuple recreate(IType... componentTypes)
	{
		return new TTuple(componentTypes);
	}
	
	@Override
	public List<? extends IConstructor<TTuple>> getConstructors()
	{
		return this.constructors;
	}
}
