package info.reflectionsofmind.vijual.library.data.list;

import info.reflectionsofmind.vijual.core.lazy.IConstructor;
import info.reflectionsofmind.vijual.core.lazy.IType;
import info.reflectionsofmind.vijual.core.lazy.TComposite;

import java.util.Arrays;
import java.util.List;

public final class TList extends TComposite
{
	@SuppressWarnings("unchecked")
	private static final List<? extends IConstructor<TList>> CONSTRUCTORS = Arrays.asList(CEmpty.INSTANCE, CPrepend.INSTANCE);

	public TList(IType type)
	{
		super(type);
	}
	
	@Override
	public List<? extends IConstructor<TList>> getConstructors()
	{
		return CONSTRUCTORS;
	}
	
	@Override
	public TComposite recreate(IType... typeArgs)
	{
		return new TList(typeArgs[0]);
	}
	
	public IType getType()
	{
		return getComponentTypes()[0];
	}
	
	@Override
	public String toString()
	{
		return TList.class.getSimpleName() + "[" + getType() + "]";
	}
}
