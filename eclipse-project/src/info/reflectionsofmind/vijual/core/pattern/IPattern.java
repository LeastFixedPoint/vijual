package info.reflectionsofmind.vijual.core.pattern;

import info.reflectionsofmind.vijual.core.lazy.ILazy;
import info.reflectionsofmind.vijual.core.lazy.LVariable;
import info.reflectionsofmind.vijual.core.type.ITypeDefined;

import java.util.List;
import java.util.Map;

public interface IPattern
{
	Map<LVariable, ILazy> match(ILazy value);
	ITypeDefined getMatchingType();
	IPattern substitute(LVariable variable, ILazy expression);
	List<LVariable> getPatternVariables();
}
