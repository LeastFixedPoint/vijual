package info.reflectionsofmind.vijual.core.node;

import info.reflectionsofmind.vijual.core.exception.EvaluationException;
import info.reflectionsofmind.vijual.core.exception.TypingException;
import info.reflectionsofmind.vijual.core.lazy.ILazy;
import info.reflectionsofmind.vijual.core.type.IType;

public interface INode
{
	IType getType() throws TypingException;
	ILazy evaluate() throws EvaluationException, TypingException;
}
