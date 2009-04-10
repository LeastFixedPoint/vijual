package info.reflectionsofmind.vijual.core.lazy;

import java.util.List;

public interface IType
{
	List<? extends IConstructor<? extends IType>> getConstructors();
	IType substitute(TVariable variable, IType substitution);
}
