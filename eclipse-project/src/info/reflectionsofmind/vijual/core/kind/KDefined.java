package info.reflectionsofmind.vijual.core.kind;

public final class KDefined implements IKind
{
	public static final KDefined INSTANCE = new KDefined();
	
	private KDefined()
	{
		
	}
	
	@Override
	public String toString()
	{
		return "*";
	}
}
