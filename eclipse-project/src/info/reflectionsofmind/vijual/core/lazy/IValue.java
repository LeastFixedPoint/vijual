package info.reflectionsofmind.vijual.core.lazy;

public interface IValue
{
	IType getType();

	IConstructor<? extends IType> getConstructor();
}
