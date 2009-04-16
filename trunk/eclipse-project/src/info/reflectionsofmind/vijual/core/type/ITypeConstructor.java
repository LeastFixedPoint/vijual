package info.reflectionsofmind.vijual.core.type;

import info.reflectionsofmind.vijual.core.kind.IKind;
import info.reflectionsofmind.vijual.core.kind.KConstructor;

public interface ITypeConstructor<KArgument extends IKind, KResult extends IKind> extends IType<KConstructor<KArgument, KResult>>
{
	ITypeConstructed<KArgument, KResult> apply(IType<KArgument> typeArgument);

	ITypeDefinedConstructed<?> wrapAfterDefined(ITypeDefinedConstructed<?> type);
}
