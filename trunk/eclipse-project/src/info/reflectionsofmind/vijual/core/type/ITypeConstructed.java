package info.reflectionsofmind.vijual.core.type;

import info.reflectionsofmind.vijual.core.kind.IKind;

public interface ITypeConstructed<KArg extends IKind, KKind extends IKind> extends IType<KKind>
{
	ITypeConstructor<KArg, KKind> getConstructor();
	IType<KArg> getArgument();
}
