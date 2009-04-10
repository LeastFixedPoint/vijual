package info.reflectionsofmind.vijual.core.lazy;

import java.util.List;

public final class TVariable implements IType
{
	private final String id;

	public TVariable()
	{
		this("");
	}
	
	public TVariable(String id)
	{
		this.id = id;
	}
	
	public TVariable(String id, Class<?> owner)
	{
		this.id = owner.getSimpleName() + "." + id;
	}
	
	@Override
	public IType substitute(TVariable variable, IType substitution)
	{
		return this == variable ? substitution : this;
	}
	
	@Override
	public List<? extends IConstructor<?>> getConstructors()
	{
		throw new UnsupportedOperationException("Type variables cannot be constructed: " + this);
	}
	
	@Override
	public String toString()
	{
		return "?" + this.id;
	}
}
