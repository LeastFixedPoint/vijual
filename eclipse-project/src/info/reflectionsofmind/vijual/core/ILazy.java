package info.reflectionsofmind.vijual.core;

import info.reflectionsofmind.vijual.core.exception.EvaluationException;
import info.reflectionsofmind.vijual.core.exception.TypingException;

public interface ILazy
{
	IValue evaluate() throws EvaluationException, TypingException;
	IType getType();
}
