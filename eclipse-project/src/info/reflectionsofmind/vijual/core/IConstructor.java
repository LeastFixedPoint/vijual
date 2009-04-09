package info.reflectionsofmind.vijual.core;

import info.reflectionsofmind.vijual.core.exception.TypingException;


public interface IConstructor<TType extends IType> extends IValue
{
	IType[] getArgumentTypes();
	TType getConstructedType();
	ILazy construct(ILazy... args) throws TypingException;
}
