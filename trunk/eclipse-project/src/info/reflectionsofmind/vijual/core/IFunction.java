package info.reflectionsofmind.vijual.core;

import info.reflectionsofmind.vijual.core.exception.EvaluationException;
import info.reflectionsofmind.vijual.core.exception.TypingException;

public interface IFunction extends IValue
{
	TFunction getType();
	ILazy apply(ILazy lazy) throws EvaluationException, TypingException;
}
