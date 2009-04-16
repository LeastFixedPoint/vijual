package info.reflectionsofmind.vijual.core.type;

import info.reflectionsofmind.vijual.core.kind.IKind;

import java.util.Set;

public interface IType<KKind extends IKind>
{
	KKind getKind();
	Set<TVariable<?>> getTypeVariables();
	<KVar extends IKind> IType<KKind> substitute(TVariable<KVar> variable, IType<KVar> substitution);
	<KVar extends IKind> ITypeConstructor<KVar, KKind> toTypeFunction(TVariable<KVar> variable);
}
