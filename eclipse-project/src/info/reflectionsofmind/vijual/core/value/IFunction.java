package info.reflectionsofmind.vijual.core.value;

import info.reflectionsofmind.vijual.core.ILazy;
import info.reflectionsofmind.vijual.core.exception.EvaluationException;
import info.reflectionsofmind.vijual.core.exception.TypingException;
import info.reflectionsofmind.vijual.library.type.function.TFunction;

public interface IFunction extends IValue
{
	TFunction getType();
	ILazy apply(ILazy lazy) throws EvaluationException, TypingException;
}
