package info.reflectionsofmind.vijual.core;

import java.util.List;

public interface IType
{
	List<IType> getConstructors();
	IType substitute(TVariable variable, IType substitution);
}
