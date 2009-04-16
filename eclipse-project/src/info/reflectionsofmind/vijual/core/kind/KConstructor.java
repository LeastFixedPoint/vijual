package info.reflectionsofmind.vijual.core.kind;

public final class KConstructor<TArgKind extends IKind, TResKind extends IKind> implements IKind
{
	public static final KConstructor<KDefined, KDefined> INSTANCE2 = // 
	new KConstructor<KDefined, KDefined>(KDefined.INSTANCE, KDefined.INSTANCE);

	public static final KConstructor<KDefined, KConstructor<KDefined, KDefined>> INSTANCE3 = //
	new KConstructor<KDefined, KConstructor<KDefined, KDefined>>(KDefined.INSTANCE, KConstructor.INSTANCE2);

	private final TArgKind argumentKind;
	private final TResKind resultKind;

	public KConstructor(final TArgKind argumentKind, final TResKind resultKind)
	{
		this.argumentKind = argumentKind;
		this.resultKind = resultKind;
	}

	public TArgKind getArgumentKind()
	{
		return this.argumentKind;
	}

	public TResKind getResultKind()
	{
		return this.resultKind;
	}

	@Override
	public boolean equals(final Object obj)
	{
		return obj instanceof KConstructor && ((KConstructor<?, ?>) obj).argumentKind.equals(this.argumentKind) //
				&& ((KConstructor<?, ?>) obj).resultKind.equals(this.resultKind);
	}
	
	@Override
	public String toString()
	{
		return "[" + this.argumentKind + " -> " + this.resultKind + "]";
	}
}
