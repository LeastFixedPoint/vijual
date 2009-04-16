package info.reflectionsofmind.vijual.core.type;

import info.reflectionsofmind.vijual.core.kind.IKind;
import info.reflectionsofmind.vijual.core.kind.KDefined;

public interface ITypeDefinedConstructed<KArg extends IKind> extends ITypeDefined, ITypeConstructed<KArg, KDefined>
{

}
