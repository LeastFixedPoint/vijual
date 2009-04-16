package info.reflectionsofmind.vijual.core.value;

import info.reflectionsofmind.util.Lists;
import info.reflectionsofmind.vijual.core.ILazy;
import info.reflectionsofmind.vijual.core.exception.EvaluationException;
import info.reflectionsofmind.vijual.core.exception.TypingException;
import info.reflectionsofmind.vijual.core.type.ITypeDefined;
import info.reflectionsofmind.vijual.core.util.Types;

public class VConstructor extends VFunction implements IConstructor
{
	private final ITypeDefined constructedType;
	private final ITypeDefined[] componentTypes;

	public VConstructor(final ITypeDefined constructedType, final ITypeDefined... componentTypes)
	{
		super(Types.constructFunction(constructedType, componentTypes));

		this.constructedType = constructedType;
		this.componentTypes = componentTypes;
	}

	@Override
	public final ILazy apply(final ILazy lazy) throws EvaluationException, TypingException
	{
		if (this.componentTypes.length == 1)
		{
			return new ConstructedDefined(lazy).toLazy();
		}
		else
		{
			return new ConstructedConstructor(lazy).toLazy();
		}
	}

	public final ITypeDefined getConstructedType()
	{
		return this.constructedType;
	}

	public final ITypeDefined[] getComponentTypes()
	{
		return this.componentTypes;
	}
	
	@Override
	public String toString()
	{
		return "[constructor: " + getType().getArgType() + " -> " + getType().getResType() + "]";
	}

	public final class ConstructedDefined extends Value implements IConstructed, IDefined
	{
		private final ILazy argument;

		public ConstructedDefined(final ILazy argument)
		{
			super(Types.resolve(VConstructor.this.getType(), argument.getType()));
			this.argument = argument;
		}

		public IConstructor getConstructor()
		{
			return VConstructor.this;
		}

		public ILazy getArgument()
		{
			return this.argument;
		}

		@Override
		public String toString()
		{
			return "[" + VConstructor.this + " " + this.argument + "]";
		}
	}

	public final class ConstructedConstructor extends VConstructor implements IConstructed
	{
		private final ILazy argument;

		public ConstructedConstructor(final ILazy argument)
		{
			super( //
					(ITypeDefined) Types.applySubstitutions(Types.solve( //
							VConstructor.this.getComponentTypes()[0], argument.getType()), // 
							VConstructor.this.getConstructedType()), // 
					Types.applySubstitutions(Types.solve( //
							VConstructor.this.getComponentTypes()[0], argument.getType()), // 
							Lists.subArrayFrom(1, VConstructor.this.getComponentTypes())));

			this.argument = argument;
		}

		public IConstructor getConstructor()
		{
			return VConstructor.this;
		}

		public ILazy getArgument()
		{
			return this.argument;
		}

		@Override
		public String toString()
		{
			return "[" + getConstructor() + " " + getArgument() + "]";
		}
	}
}
