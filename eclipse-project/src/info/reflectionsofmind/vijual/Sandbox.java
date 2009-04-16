package info.reflectionsofmind.vijual;

import info.reflectionsofmind.vijual.core.ILazy;
import info.reflectionsofmind.vijual.core.LApply;
import info.reflectionsofmind.vijual.core.kind.KConstructor;
import info.reflectionsofmind.vijual.core.kind.KDefined;
import info.reflectionsofmind.vijual.core.type.IType;
import info.reflectionsofmind.vijual.core.type.ITypeConstructed;
import info.reflectionsofmind.vijual.core.type.ITypeConstructor;
import info.reflectionsofmind.vijual.core.type.ITypeDefined;
import info.reflectionsofmind.vijual.core.type.ITypeDefinedConstructed;
import info.reflectionsofmind.vijual.core.type.TDefinedVariable;
import info.reflectionsofmind.vijual.core.util.Types;
import info.reflectionsofmind.vijual.library.type.TSwapperConstructor;
import info.reflectionsofmind.vijual.library.type.function.TFunctionConstructor;
import info.reflectionsofmind.vijual.library.type.integer.Vinteger;
import info.reflectionsofmind.vijual.library.type.list.TList;
import info.reflectionsofmind.vijual.library.type.list.TListConstructor;

import javax.swing.JFrame;

public class Sandbox extends JFrame
{
	public static void print(final IType<?> type)
	{
		System.out.println(type + " :: " + type.getKind());
	}

	public static void main(final String[] args) throws Exception
	{
		final TDefinedVariable a = new TDefinedVariable("a");
		final TDefinedVariable b = new TDefinedVariable("b");

		final TSwapperConstructor tswapper = TSwapperConstructor.INSTANCE;
		final TListConstructor tlist = TListConstructor.INSTANCE;
		final TFunctionConstructor tfunction = TFunctionConstructor.INSTANCE;
		
		ILazy list = TList.EMPTY.toLazy();
		list = new LApply(new LApply(TList.PREPEND.toLazy(), new Vinteger(1).toLazy()), list);
		list = new LApply(new LApply(TList.PREPEND.toLazy(), new Vinteger(2).toLazy()), list);
		System.out.println(list.evaluate());
		
		System.exit(0);

		final ITypeDefinedConstructed<KDefined> t1 = tlist.apply(a);
		print(t1);

		final ITypeConstructed<KDefined, KConstructor<KDefined, KDefined>> t2 = tfunction.apply(a);
		print(t2);

		final ITypeDefinedConstructed<KDefined> t3 = tfunction.apply(a, b);
		print(t3);

		final ITypeDefinedConstructed<KDefined> t4 = tfunction.apply((ITypeDefined) t1, (ITypeDefined) tlist.apply(b));
		print(t4);

		final ITypeConstructed<KConstructor<KDefined, KConstructor<KDefined, KDefined>>, KConstructor<KDefined, KConstructor<KDefined, KDefined>>> t5 = tswapper.apply(tfunction);
		print(t5);

		final IType<KDefined> t6 = tfunction.apply((ITypeDefined) t1, (ITypeDefined) t1);
		print(t6);

		final ITypeConstructor<KDefined, KDefined> t7 = t6.toTypeFunction(a);
		print(t7);

		final ITypeDefinedConstructed<KDefined> t8 = (ITypeDefinedConstructed<KDefined>) t7.apply(tlist.apply(b));
		print(t8);

		System.out.println();
		System.out.println();
		System.out.println();

		System.out.println(Types.getRootConstructor(t8));
		System.out.println(Types.getArgType(t8));
		System.out.println(Types.getResType(t8));
	}
}
