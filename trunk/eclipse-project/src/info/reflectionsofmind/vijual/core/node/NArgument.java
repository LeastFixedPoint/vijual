package info.reflectionsofmind.vijual.core.node;

import info.reflectionsofmind.vijual.core.ILazy;
import info.reflectionsofmind.vijual.core.exception.EvaluationException;
import info.reflectionsofmind.vijual.core.exception.TypingException;
import info.reflectionsofmind.vijual.core.type.IType;

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
