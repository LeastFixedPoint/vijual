package info.reflectionsofmind.vijual;

import info.reflectionsofmind.vijual.core.expression.EApplication;
import info.reflectionsofmind.vijual.core.expression.EConstant;
import info.reflectionsofmind.vijual.core.expression.EVariable;
import info.reflectionsofmind.vijual.core.expression.Expression;
import info.reflectionsofmind.vijual.core.lazy.LApply;
import info.reflectionsofmind.vijual.library.data.integer.CInteger;
import info.reflectionsofmind.vijual.library.data.integer.TInteger;
import info.reflectionsofmind.vijual.library.function.IntSum;

import javax.swing.JFrame;

public class Sandbox extends JFrame
{
	public static void main(final String[] args) throws Exception
	{
		final Expression expression = new EApplication( //
				new EApplication( //
						new EConstant(IntSum.INSTANCE), // 
						new EVariable(TInteger.INSTANCE)), // 
				new EConstant(new CInteger(2).construct()));

		System.out.println(expression);
		System.out.println(expression.getType());

		System.out.println(expression.toLazy());
		System.out.println(new LApply(expression.toLazy(), new CInteger(3).construct()));
		System.out.println(new LApply(expression.toLazy(), new CInteger(3).construct()).evaluate());

		if (true) return;
	}
}
