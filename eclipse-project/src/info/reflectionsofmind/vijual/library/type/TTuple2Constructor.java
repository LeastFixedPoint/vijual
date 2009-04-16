package info.reflectionsofmind.vijual.library.type;

import info.reflectionsofmind.vijual.core.kind.KConstructor;
import info.reflectionsofmind.vijual.core.kind.KDefined;
import info.reflectionsofmind.vijual.core.type.ITypeConstructorConstructed;
import info.reflectionsofmind.vijual.core.type.IType;
import info.reflectionsofmind.vijual.core.type.TConstructor;

public final class TTuple2Constructor extends TConstructor<KDefined, KConstructor<KDefined, KDefined>>
{
	public static final TTuple2Constructor INSTANCE = new TTuple2Constructor();

	private TTuple2Constructor()
	{
		super("Tuple2", KConstructor.INSTANCE3);
	}

	@SuppressWarnings("unchecked")
	public IType<KDefined> apply(IType<KDefined> a, IType<KDefined> b)
	{
		return ((ITypeConstructorConstructed<KDefined, KDefined, KDefined>) apply(a)).apply(b);
	}
}
