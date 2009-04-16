package info.reflectionsofmind.vijual.core;

import info.reflectionsofmind.vijual.core.exception.EvaluationException;
import info.reflectionsofmind.vijual.core.exception.TypingException;
import info.reflectionsofmind.vijual.core.type.ITypeDefined;
import info.reflectionsofmind.vijual.core.value.IValue;

public interface ILazy
{
	IValue evaluate() throws EvaluationException, TypingException;
	ITypeDefined getType();
}
