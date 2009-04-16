package info.reflectionsofmind.vijual.core.type;

import info.reflectionsofmind.util.Sets;
import info.reflectionsofmind.vijual.core.kind.IKind;
import info.reflectionsofmind.vijual.core.kind.KConstructor;

public final class TConstructorConstructed<KArg extends IKind, KArg1 extends IKind, KRes extends IKind> // 
		extends TConstructor<KArg1, KRes> //
		implements ITypeConstructorConstructed<KArg, KArg1, KRes>, ITypeConstructor<KArg1, KRes>
{
	private final IType<KArg> argument;
	private final ITypeConstructor<KArg, KConstructor<KArg1, KRes>> constructor;

	public TConstructorConstructed(ITypeConstructor<KArg, KConstructor<KArg1, KRes>> constructor, // 
			IType<KArg> argument)
	{
		super("[" + constructor + " " + argument + "]", //
				constructor.getKind().getResultKind(), // 
				Sets.union(argument.getTypeVariables(), constructor.getTypeVariables()));

		this.argument = argument;
		this.constructor = constructor;
	}

	@Override
	public IType<KArg> getArgument()
	{
		return this.argument;
	}

	@Override
	public ITypeConstructor<KArg, KConstructor<KArg1, KRes>> getConstructor()
	{
		return this.constructor;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <KVar extends IKind> IType<KConstructor<KArg1, KRes>> substitute(TVariable<KVar> variable, IType<KVar> substitution)
	{
		return new TConstructorConstructed( //
				(ITypeConstructor) this.constructor.substitute(variable, substitution), //
				this.argument.substitute(variable, substitution));
	}
	
	@Override
	public ITypeDefinedConstructed<?> wrapAfterDefined(ITypeDefinedConstructed<?> type)
	{
		return this.constructor.wrapAfterDefined(type);
	}
}
