package info.reflectionsofmind.vijual.core.lazy;

import info.reflectionsofmind.vijual.core.lazy.exception.EvaluationException;
import info.reflectionsofmind.vijual.core.lazy.exception.TypingException;

public interface IFunction extends IValue
{
	TFunction getType();
	ILazy apply(ILazy lazy) throws EvaluationException, TypingException;
}
