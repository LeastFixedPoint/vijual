package info.reflectionsofmind.vijual.core.lazy;

import info.reflectionsofmind.vijual.core.lazy.exception.EvaluationException;
import info.reflectionsofmind.vijual.core.lazy.exception.TypingException;

public interface ILazy
{
	IValue evaluate() throws EvaluationException, TypingException;
	IType getType();
}
