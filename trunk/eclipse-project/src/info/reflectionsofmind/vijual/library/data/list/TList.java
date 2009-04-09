package info.reflectionsofmind.vijual.library.data.list;

import java.util.Arrays;
import java.util.List;

import info.reflectionsofmind.vijual.core.IConstructor;
import info.reflectionsofmind.vijual.core.IType;
import info.reflectionsofmind.vijual.core.TComposite;
import info.reflectionsofmind.vijual.core.TFunction;
import info.reflectionsofmind.vijual.core.TVariable;

public final class TList extends TComposite
{
	@SuppressWarnings("unchecked")
	private static final List<? extends IConstructor<TList>> CONSTRUCTORS = Arrays.asList(new CEmpty(), new CPrepend());

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
