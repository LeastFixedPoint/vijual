package info.reflectionsofmind.vijual.core;

public abstract class CConstructor<TType extends IType> implements IConstructor<TType>
{
	private final IType[] argumentTypes;
	private final TType constructedType;
	private final IType type;

	public CConstructor(final TType constructedType, final IType... args)
	{
		this.argumentTypes = args;
		this.constructedType = constructedType;

		IType type = this.constructedType;

		for (int i = this.argumentTypes.length - 1; i > 0; --i)
			type = new TFunction(this.argumentTypes[i], type);

		this.type = type;
	}

	@Override
	public IType getType()
	{
		return this.type;
	}

	@Override
	public IType[] getArgumentTypes()
	{
		return this.argumentTypes;
	}

	public TType getConstructedType()
	{
		return this.constructedType;
	}
}
