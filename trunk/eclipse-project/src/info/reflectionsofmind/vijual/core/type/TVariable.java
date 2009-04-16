package info.reflectionsofmind.vijual.core.type;

import info.reflectionsofmind.vijual.core.kind.IKind;

import java.util.Collections;
import java.util.Set;

public abstract class TVariable<KKind extends IKind> extends TType<KKind> implements ITypeVariable<KKind>
{
	public TVariable(final String name, final KKind kind)
	{
		super(name, kind);
	}

	@Override
	@SuppressWarnings("unchecked")
	public final <KVar extends IKind> IType<KKind> substitute(final TVariable<KVar> variable, final IType<KVar> substitution)
	{
		return this == variable ? (IType<KKind>) substitution : this;
	}

	@Override
	public final Set<TVariable<?>> getTypeVariables()
	{
		return Collections.<TVariable<?>> singleton(this);
	}
}
