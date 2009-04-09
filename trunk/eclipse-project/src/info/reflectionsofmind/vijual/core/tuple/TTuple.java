package info.reflectionsofmind.vijual.core.tuple;

import info.reflectionsofmind.vijual.core.IType;
import info.reflectionsofmind.vijual.core.TVariable;

import java.util.Arrays;
import java.util.List;

public class TTuple implements IType
{
	private final IType[] componentTypes;

	public TTuple(final IType... componentTypes)
	{
		this.componentTypes = componentTypes;
	}
	
	public TTuple recreate(IType... componentTypes)
	{
		return new TTuple(componentTypes);
	}
	
	@Override
	public List<IType> getConstructors()
	{
		return null;
	}

	@Override
	public IType substitute(final TVariable variable, final IType substitution)
	{
		final IType[] newArgs = new IType[getComponentTypes().length];

		for (int i = 0; i < newArgs.length; i++)
			newArgs[i] = getComponentTypes()[i].substitute(variable, substitution);

		return recreate(newArgs);
	}

	public IType[] getComponentTypes()
	{
		return this.componentTypes;
	}

	@Override
	public boolean equals(final Object obj)
	{
		if (obj.getClass() != this.getClass()) return false;
		final TTuple type = (TTuple) obj;
		return Arrays.deepEquals(getComponentTypes(), type.getComponentTypes());
	}
}
