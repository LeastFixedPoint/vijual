package info.reflectionsofmind.vijual.core.value;

import info.reflectionsofmind.vijual.core.ILazy;

public interface IConstructed extends IValue
{
	IConstructor getConstructor();
	ILazy getArgument();
}
