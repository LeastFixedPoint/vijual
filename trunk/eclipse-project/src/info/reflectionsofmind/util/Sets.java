package info.reflectionsofmind.util;

import java.util.HashSet;
import java.util.Set;

public final class Sets
{
	private Sets()
	{
		throw new UnsupportedOperationException("Utility class");
	}

	public static <T> Set<T> union(final Set<T> left, final Set<T> right)
	{
		final Set<T> union = new HashSet<T>(left);
		union.addAll(right);
		return union;
	}
	
	public static <T> Set<T> subtract(final Set<T> set, final T element)
	{
		if (!set.contains(element)) throw new RuntimeException();

		final HashSet<T> result = new HashSet<T>(set);
		result.remove(element);
		return result;
	}
}
