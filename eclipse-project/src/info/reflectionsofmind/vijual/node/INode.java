package info.reflectionsofmind.vijual.node;

import info.reflectionsofmind.vijual.core.ILazy;
import info.reflectionsofmind.vijual.core.IType;
import info.reflectionsofmind.vijual.core.exception.EvaluationException;
import info.reflectionsofmind.vijual.core.exception.TypingException;

public interface INode
{
	IType getType() throws TypingException;
	ILazy evaluate() throws EvaluationException, TypingException;
}
