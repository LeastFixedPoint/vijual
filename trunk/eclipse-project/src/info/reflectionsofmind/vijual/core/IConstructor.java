package info.reflectionsofmind.vijual.core;

import java.util.List;

public interface IConstructor extends IValue
{
	IType[] getArgumentTypes();
	IType getConstructedType();
	ILazy construct(ILazy... args);
}
