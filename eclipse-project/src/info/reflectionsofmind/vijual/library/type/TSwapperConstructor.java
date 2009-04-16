package info.reflectionsofmind.vijual.library.type;

import info.reflectionsofmind.vijual.core.kind.KConstructor;
import info.reflectionsofmind.vijual.core.kind.KDefined;
import info.reflectionsofmind.vijual.core.type.TConstructor;

public final class TSwapperConstructor extends TConstructor< //
		KConstructor<KDefined, KConstructor<KDefined, KDefined>>, // 
		KConstructor<KDefined, KConstructor<KDefined, KDefined>>>
{
	public static final TSwapperConstructor INSTANCE = new TSwapperConstructor();
	
	private TSwapperConstructor()
	{
		super("Swapper", new KConstructor< //
				KConstructor<KDefined, KConstructor<KDefined, KDefined>>, //  
				KConstructor<KDefined, KConstructor<KDefined, KDefined>>>(//
						KConstructor.INSTANCE3, KConstructor.INSTANCE3));
	}
}
