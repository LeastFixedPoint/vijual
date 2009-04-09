package info.reflectionsofmind.vijual.core;

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
	
	@Override
	public IType substitute(TVariable variable, IType substitution)
	{
		return this == variable ? substitution : this;
	}
	
	@Override
	public String toString()
	{
		return "?" + this.id;
	}
}
