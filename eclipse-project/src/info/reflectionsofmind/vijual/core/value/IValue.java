package info.reflectionsofmind.vijual.core.value;

import info.reflectionsofmind.vijual.core.ILazy;
import info.reflectionsofmind.vijual.core.type.ITypeDefined;

public interface IValue
{
	ITypeDefined getType();
	ILazy toLazy();
}
