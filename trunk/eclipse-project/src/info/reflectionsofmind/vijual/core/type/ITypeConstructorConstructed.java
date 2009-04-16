package info.reflectionsofmind.vijual.core.type;

import info.reflectionsofmind.vijual.core.kind.IKind;
import info.reflectionsofmind.vijual.core.kind.KConstructor;

public interface ITypeConstructorConstructed<KArg extends IKind, KArg1 extends IKind, KRes extends IKind> // 
		extends ITypeConstructor<KArg1, KRes>, ITypeConstructed<KArg, KConstructor<KArg1, KRes>>
{
	
}
