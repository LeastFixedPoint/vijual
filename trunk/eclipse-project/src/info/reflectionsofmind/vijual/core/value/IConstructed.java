package info.reflectionsofmind.vijual.core.value;

import info.reflectionsofmind.vijual.core.lazy.ILazy;

public interface IConstructed extends IValue
{
	IConstructor getConstructor();
	ILazy getArgument();
}
