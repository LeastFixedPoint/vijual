package info.reflectionsofmind.vijual.core.value;

import info.reflectionsofmind.vijual.core.type.ITypeDefined;

public interface IConstructor extends IFunction
{
	ITypeDefined getConstructedType();
	ITypeDefined[] getComponentTypes();
}
