package info.reflectionsofmind.vijual.core.lazy;

import info.reflectionsofmind.vijual.core.exception.EvaluationException;
import info.reflectionsofmind.vijual.core.exception.TypingException;
import info.reflectionsofmind.vijual.core.type.ITypeDefined;
import info.reflectionsofmind.vijual.core.value.IValue;

import java.util.Set;

public interface ILazy
{
	ILazy substitute(LVariable variable, ILazy expression);
	IValue evaluate() throws EvaluationException, TypingException;
	ITypeDefined getType();
	Set<LVariable> getVariables();
}
