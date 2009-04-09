package info.reflectionsofmind.vijual.node;

import info.reflectionsofmind.vijual.core.ILazy;
import info.reflectionsofmind.vijual.core.IType;
import info.reflectionsofmind.vijual.core.exception.EvaluationException;
import info.reflectionsofmind.vijual.core.exception.TypingException;

import java.util.ArrayList;
import java.util.List;

public class Schema implements INode
{
	private final List<INode> nodes = new ArrayList<INode>();
	private final List<NValue> argNodes = new ArrayList<NValue>();
	private INode resultNode;
	
	public Schema()
	{
	}
	
	public void addNode(INode node)
	{
		this.nodes.add(node);
	}
	
	public void addArgument(NValue node)
	{
		this.argNodes.add(node);
	}
	
	public void setResultNode(INode resultNode)
	{
		this.resultNode = resultNode;
	}
	
	@Override
	public ILazy evaluate() throws EvaluationException, TypingException
	{
		
	}
	
	@Override
	public IType getType() throws TypingException
	{
		return this.resultNode.getType();
	}
}
