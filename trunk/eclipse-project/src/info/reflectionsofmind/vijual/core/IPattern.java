package info.reflectionsofmind.vijual.core;

import info.reflectionsofmind.vijual.core.type.IType;
import info.reflectionsofmind.vijual.core.value.IConstructor;
import info.reflectionsofmind.vijual.core.value.IValue;

public interface IPattern
{
	IType getMatchingType();
	IConstructor<? extends IType> getConstructor();
	IType[] getArgumentTypes();
	boolean matches(IValue value);
}
