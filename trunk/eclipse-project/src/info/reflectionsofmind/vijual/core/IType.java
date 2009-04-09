package info.reflectionsofmind.vijual.core;

public interface IType
{
	IType substitute(TVariable variable, IType substitution);
}
