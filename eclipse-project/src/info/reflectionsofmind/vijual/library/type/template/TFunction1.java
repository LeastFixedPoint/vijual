package info.reflectionsofmind.vijual.library.type.template;

import info.reflectionsofmind.vijual.core.kind.KConstructor;
import info.reflectionsofmind.vijual.core.kind.KDefined;
import info.reflectionsofmind.vijual.core.type.ITypeDefinedConstructed;
import info.reflectionsofmind.vijual.core.type.IType;
import info.reflectionsofmind.vijual.core.type.TConstructor;

public abstract class TFunction1 extends TConstructor<KDefined, KDefined>
{
	public TFunction1(String name)
	{
		super(name, KConstructor.INSTANCE2);
	}
	
	@Override
	public ITypeDefinedConstructed<KDefined> apply(IType<KDefined> typeArgument)
	{
		return (ITypeDefinedConstructed<KDefined>) super.apply(typeArgument);
	}
}
