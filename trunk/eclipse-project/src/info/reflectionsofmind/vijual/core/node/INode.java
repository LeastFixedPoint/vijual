package info.reflectionsofmind.vijual.core.node;

import info.reflectionsofmind.vijual.core.lazy.ILazy;
import info.reflectionsofmind.vijual.core.lazy.IType;
import info.reflectionsofmind.vijual.core.lazy.exception.EvaluationException;
import info.reflectionsofmind.vijual.core.lazy.exception.TypingException;

public interface INode
{
	IType getType() throws TypingException;
	ILazy evaluate() throws EvaluationException, TypingException;
}
