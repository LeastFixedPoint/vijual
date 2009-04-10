package info.reflectionsofmind.vijual.core.node;

import info.reflectionsofmind.vijual.core.lazy.ILazy;
import info.reflectionsofmind.vijual.core.lazy.IType;
import info.reflectionsofmind.vijual.core.lazy.exception.EvaluationException;
import info.reflectionsofmind.vijual.core.lazy.exception.TypingException;

public class NArgument implements INode
{
	private final IType type;

	public NArgument(IType type)
	{
		this.type = type;
	}

	@Override
	public ILazy evaluate() throws EvaluationException, TypingException
	{
		throw new UnsupportedOperationException("Argument nodes cannot be evaluated.");
	}

	@Override
	public IType getType() throws TypingException
	{
		return this.type;
	}
}
