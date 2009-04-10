package info.reflectionsofmind.vijual.core.lazy;


import java.util.Arrays;

public abstract class TComposite implements IType
{
	private final IType[] componentTypes;

	public TComposite(final IType... componentTypes)
	{
		this.componentTypes = componentTypes;
	}
	
	public abstract TComposite recreate(IType... componentTypes);
	
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
		final TComposite type = (TComposite) obj;
		return Arrays.deepEquals(getComponentTypes(), type.getComponentTypes());
	}
}
