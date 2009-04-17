package info.reflectionsofmind.vijual;

import info.reflectionsofmind.vijual.core.kind.KConstructor;
import info.reflectionsofmind.vijual.core.kind.KDefined;
import info.reflectionsofmind.vijual.core.lazy.Context;
import info.reflectionsofmind.vijual.core.lazy.ILazy;
import info.reflectionsofmind.vijual.core.lazy.LApply;
import info.reflectionsofmind.vijual.core.lazy.LLambda;
import info.reflectionsofmind.vijual.core.lazy.LMatch;
import info.reflectionsofmind.vijual.core.lazy.LVariable;
import info.reflectionsofmind.vijual.core.pattern.IPattern;
import info.reflectionsofmind.vijual.core.pattern.PPattern;
import info.reflectionsofmind.vijual.core.type.IType;
import info.reflectionsofmind.vijual.core.type.ITypeConstructed;
import info.reflectionsofmind.vijual.core.type.ITypeConstructor;
import info.reflectionsofmind.vijual.core.type.ITypeDefined;
import info.reflectionsofmind.vijual.core.type.ITypeDefinedConstructed;
import info.reflectionsofmind.vijual.core.type.TDefinedVariable;
import info.reflectionsofmind.vijual.core.util.Types;
import info.reflectionsofmind.vijual.library.function.IntSum;
import info.reflectionsofmind.vijual.library.type.TSwapperConstructor;
import info.reflectionsofmind.vijual.library.type.function.TFunction;
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
		final TDefinedVariable a = new TDefinedVariable("ax");
		final TDefinedVariable b = new TDefinedVariable("bx");

		Context context = new Context();

		Context.LReference map = context.new LReference("map", new TFunction(new TFunction(a, b), new TFunction(new TList(a), new TList(b))));

		LVariable f = new LVariable("f", new TFunction(a, b));
		LVariable list = new LVariable("list", new TList(a));
		LVariable head = new LVariable("head", a);
		LVariable tail = new LVariable("tail", new TList(a));

		IPattern pattern = new PPattern(new LApply(new LApply(TList.newPrepend().toLazy(), head), tail), head, tail);

		System.out.println("Pattern: " + new LApply(new LApply(TList.newPrepend().toLazy(), head), tail).getType());

		ILazy ifMatched = new LLambda(head, new LLambda(tail, // 
				new LApply( //
						new LApply(TList.newPrepend().toLazy(), new LApply(f, head)), // Prepend (f head)
						new LApply(new LApply(map, f), tail)))); // (map f) tail

		System.out.println("IfMatched: " + ifMatched.getType());

		ILazy otherwise = new LLambda(new LVariable("_", new TList(a)), TList.newEmpty().toLazy());

		System.out.println("Otherwise: " + otherwise.getType());

		context.bind("map", new LLambda(f, new LLambda(list, new LMatch(pattern, ifMatched, otherwise))));

		System.out.println(context.resolve("map").getType());
	}
}
