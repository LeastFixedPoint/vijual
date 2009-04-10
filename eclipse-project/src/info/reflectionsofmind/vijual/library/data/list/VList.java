package info.reflectionsofmind.vijual.library.data.list;

import info.reflectionsofmind.vijual.core.lazy.ILazy;
import info.reflectionsofmind.vijual.core.lazy.IType;
import info.reflectionsofmind.vijual.core.lazy.IValue;
import info.reflectionsofmind.vijual.core.lazy.exception.TypingException;
import info.reflectionsofmind.vijual.core.lazy.util.Types;

public class VList implements IValue
{
	private final TList type;
	private final ILazy head;
	private final ILazy tail;

	public VList(ILazy head, ILazy tail) throws TypingException
	{
		if (!(tail.getType() instanceof TList)) throw new TypingException("Tail's type " + tail.getType() + " is not a " + TList.class.getSimpleName() + ".");

		final IType headType = head.getType();
		final IType tailType = ((TList) tail.getType()).getType();

		this.type = new TList(Types.substitute(headType, Types.solve(headType, tailType)));

		this.head = head;
		this.tail = tail;
	}

	@Override
	public TList getType()
	{
		return this.type;
	}

	public ILazy getHead()
	{
		return this.head;
	}

	public ILazy getTail()
	{
		return this.tail;
	}

	@Override
	public String toString()
	{
		return this.head + ":" + this.tail;
	}
}
