package info.reflectionsofmind.util;

import java.util.AbstractList;
import java.util.List;

public final class Lists
{
	private Lists()
	{
		throw new UnsupportedOperationException("Utility class");
	}

	public static <T> List<T> concat(final List<T> left, final List<T> right)
	{
		return new AbstractList<T>()
		{
			@Override
			public T get(final int index)
			{
				return index < left.size() ? left.get(index) : right.get(index - left.size());
			}

			@Override
			public int size()
			{
				return left.size() + right.size();
			}
		};
	}
}
