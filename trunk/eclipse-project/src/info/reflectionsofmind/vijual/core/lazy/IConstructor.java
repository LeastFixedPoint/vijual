package info.reflectionsofmind.vijual.core.lazy;

import info.reflectionsofmind.vijual.core.lazy.exception.TypingException;


public interface IConstructor<TType extends IType> extends IValue
{
	IType[] getArgumentTypes();
	TType getConstructedType();
	ILazy construct(ILazy... args) throws TypingException;
}
