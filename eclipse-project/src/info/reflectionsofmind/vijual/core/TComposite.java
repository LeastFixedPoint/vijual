package info.reflectionsofmind.vijual.core;

import java.util.Arrays;

public abstract class TComposite implements IType
{
	private final IType[] typeArgs;

	public TComposite(final IType... typeArgs)
	{
		this.typeArgs = typeArgs;
	}

	public abstract TComposite recreate(IType... typeArgs);

	@Override
	public IType substitute(final TVariable variable, final IType substitution)
	{
		final IType[] newArgs = new IType[getTypeArgs().length];

		for (int i = 0; i < newArgs.length; i++)
			newArgs[i] = getTypeArgs()[i].substitute(variable, substitution);

		return recreate(newArgs);
	}

	public IType[] getTypeArgs()
	{
		return this.typeArgs;
	}

	@Override
	public boolean equals(final Object obj)
	{
		if (obj.getClass() != this.getClass()) return false;
		final TComposite type = (TComposite) obj;
		return Arrays.deepEquals(getTypeArgs(), type.getTypeArgs());
	}
}
