package info.reflectionsofmind.vijual.core.value;

import info.reflectionsofmind.util.Lists;
import info.reflectionsofmind.vijual.core.exception.EvaluationException;
import info.reflectionsofmind.vijual.core.exception.TypingException;
import info.reflectionsofmind.vijual.core.lazy.ILazy;
import info.reflectionsofmind.vijual.core.type.ITypeDefined;
import info.reflectionsofmind.vijual.core.type.ITypeDefinedConstructed;
import info.reflectionsofmind.vijual.core.util.Types;

public class VConstructor extends VFunction implements IConstructor
{
	private final ITypeDefinedConstructed<?> constructedType;
	private final ITypeDefined[] componentTypes;

	public VConstructor(final String name, final ITypeDefinedConstructed<?> constructedType, final ITypeDefined... componentTypes)
	{
		super(name, Types.constructFunction(constructedType, componentTypes));

		this.constructedType = constructedType;
		this.componentTypes = componentTypes;
	}

	public VConstructor(final ITypeDefinedConstructed<?> constructedType, final ITypeDefined... componentTypes)
	{
		this( //
				"[constructor: " + // 
						Types.constructFunction(constructedType, componentTypes).getArgType() + " -> " + // 
						Types.constructFunction(constructedType, componentTypes).getResType() + "]", // 
				constructedType, componentTypes);
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

	public final class ConstructedDefined extends Value implements IConstructed, IDefined
	{
		private final ILazy argument;

		public ConstructedDefined(final ILazy argument)
		{
			super( //
					"[" + VConstructor.this + " " + argument + "]", //
					Types.resolve(VConstructor.this.getType(), argument.getType()));

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
	}

	public final class ConstructedConstructor extends VConstructor implements IConstructed
	{
		private final ILazy argument;

		public ConstructedConstructor(final ILazy argument)
		{
			super("[" + VConstructor.this + " " + argument + "]", //
					(ITypeDefinedConstructed<?>) Types.applySubstitutions(Types.solve( //
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
	}
}
