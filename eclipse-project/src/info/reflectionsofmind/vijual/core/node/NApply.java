package info.reflectionsofmind.vijual.core.node;

import info.reflectionsofmind.vijual.core.exception.ArgumentNotSetException;
import info.reflectionsofmind.vijual.core.exception.EvaluationException;
import info.reflectionsofmind.vijual.core.exception.FunctionNotSetException;
import info.reflectionsofmind.vijual.core.exception.NotFunctionTypeException;
import info.reflectionsofmind.vijual.core.exception.TypingException;
import info.reflectionsofmind.vijual.core.lazy.ILazy;
import info.reflectionsofmind.vijual.core.lazy.LApply;
import info.reflectionsofmind.vijual.core.type.IType;
import info.reflectionsofmind.vijual.core.util.Types;
import info.reflectionsofmind.vijual.library.type.function.TFunctionConstructor;

public class NApply implements INode
{
	private INode functionNode;
	private INode argumentNode;

	public NApply(final INode function, final INode argument)
	{
		this.functionNode = function;
		this.argumentNode = argument;
	}

	@Override
	public ILazy evaluate() throws EvaluationException, TypingException
	{
		getType(); // check types

		return new LApply(this.functionNode.evaluate(), this.argumentNode.evaluate());
	}

	public IType getType() throws TypingException
	{
		if (this.functionNode == null) throw new FunctionNotSetException(this);
		if (this.argumentNode == null) throw new ArgumentNotSetException(this);

		if (!(this.functionNode.getType() instanceof TFunctionConstructor)) throw new NotFunctionTypeException(this);

		final TFunctionConstructor funType = (TFunctionConstructor) this.functionNode.getType();
		final IType argType = this.argumentNode.getType();
		final IType resType = Types.applySubstitutions(funType.getResType(), Types.solve(argType, funType.getArgType()));

		return resType;
	}

	public INode getFunction()
	{
		return this.functionNode;
	}

	public void setFunction(final INode function)
	{
		this.functionNode = function;
	}

	public INode getArgument()
	{
		return this.argumentNode;
	}

	public void setArgument(final INode argument)
	{
		this.argumentNode = argument;
	}
}
