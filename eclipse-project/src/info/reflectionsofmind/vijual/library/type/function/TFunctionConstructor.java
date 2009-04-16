package info.reflectionsofmind.vijual.library.type.function;

import info.reflectionsofmind.vijual.core.kind.KConstructor;
import info.reflectionsofmind.vijual.core.kind.KDefined;
import info.reflectionsofmind.vijual.core.type.IType;
import info.reflectionsofmind.vijual.core.type.ITypeConstructed;
import info.reflectionsofmind.vijual.core.type.ITypeConstructorConstructed;
import info.reflectionsofmind.vijual.core.type.ITypeDefined;
import info.reflectionsofmind.vijual.core.type.ITypeDefinedConstructed;
import info.reflectionsofmind.vijual.core.type.TConstructor;
import info.reflectionsofmind.vijual.core.type.TDefined;
import info.reflectionsofmind.vijual.core.util.Types;

public final class TFunctionConstructor extends TConstructor<KDefined, KConstructor<KDefined, KDefined>>
{
	public static final TFunctionConstructor INSTANCE = new TFunctionConstructor();

	private TFunctionConstructor()
	{
		super("Function", KConstructor.INSTANCE3);
	}

	@SuppressWarnings("unchecked")
	public ITypeDefinedConstructed<KDefined> apply(ITypeDefined a, ITypeDefined b)
	{
		return (ITypeDefinedConstructed<KDefined>) ((ITypeConstructorConstructed<KDefined, KDefined, KDefined>) INSTANCE.apply(a)).apply(b);
	}
	
	@Override
	public ITypeDefinedConstructed<?> wrapAfterDefined(ITypeDefinedConstructed<?> type)
	{
		if (Types.getRootConstructor(type) != TFunctionConstructor.INSTANCE) throw new RuntimeException();

		final IType<?> resType = type.getArgument();
		final IType<?> argType = ((ITypeConstructed<?, ?>) type.getConstructor()).getArgument();

		return new TFunction((TDefined) resType, (TDefined) argType);
	}
}
