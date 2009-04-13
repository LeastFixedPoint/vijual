package info.reflectionsofmind.vijual.core.lazy.tuple;

import info.reflectionsofmind.vijual.core.lazy.ILazy;
import info.reflectionsofmind.vijual.core.lazy.IType;
import info.reflectionsofmind.vijual.core.lazy.IValue;
import info.reflectionsofmind.vijual.core.lazy.TComposite;

import java.util.ArrayList;
import java.util.List;

public class VTuple implements IValue
{
	private final ILazy[] components;
	private final TComposite type;

	public VTuple(ILazy... components)
	{
		this.components = components;

		List<IType> componentTypes = new ArrayList<IType>();

		for (ILazy component : components)
			componentTypes.add(component.getType());

		this.type = new TTuple(componentTypes.toArray(new IType[] {}));
	}

	public ILazy[] getComponents()
	{
		return this.components;
	}

	@Override
	public IType getType()
	{
		return this.type;
	}
}