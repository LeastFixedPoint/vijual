package info.reflectionsofmind.vijual.library.type.function;

import info.reflectionsofmind.vijual.core.kind.KDefined;
import info.reflectionsofmind.vijual.core.type.ITypeConstructor;
import info.reflectionsofmind.vijual.core.type.ITypeDefined;
import info.reflectionsofmind.vijual.core.type.TDefinedConstructed;

public final class TFunction extends TDefinedConstructed<KDefined>
{
	private final ITypeDefined argType;
	private final ITypeDefined resType;

	@SuppressWarnings("unchecked")
	public TFunction(ITypeDefined argType, ITypeDefined resType)
	{
		super((ITypeConstructor<KDefined, KDefined>) TFunctionConstructor.INSTANCE.apply(argType), resType);

		this.argType = argType;
		this.resType = resType;
	}

	public ITypeDefined getArgType()
	{
		return this.argType;
	}

	public ITypeDefined getResType()
	{
		return this.resType;
	}
	
	public String toString()
	{
		return "[" + getArgType() + " -> " + getResType() + "]";
	}
}
