package info.reflectionsofmind.vijual;

import info.reflectionsofmind.vijual.library.data.integer.VInteger;
import info.reflectionsofmind.vijual.library.data.list.VEmpty;
import info.reflectionsofmind.vijual.library.function.IntSum;
import info.reflectionsofmind.vijual.library.function.Map;
import info.reflectionsofmind.vijual.library.function.Prepend;
import info.reflectionsofmind.vijual.node.NApply;
import info.reflectionsofmind.vijual.node.NValue;

import javax.swing.JFrame;

public class Sandbox extends JFrame
{
	public static void main(final String[] args) throws Exception
	{
		final NValue v2 = new NValue(new VInteger(2));
		final NValue v3 = new NValue(new VInteger(3));

		final NApply plus1 = new NApply(new NValue(IntSum.INSTANCE), v2); // -> "2+"
		final NApply plus2 = new NApply(plus1, v3); // -> "2+3"
		final NApply prepend1 = new NApply(new NValue(Prepend.INSTANCE), plus2); // -> "2+3 :"
		final NApply prepend2 = new NApply(prepend1, new NValue(new VEmpty())); // -> "2+3 : nil"
		final NApply prepend3 = new NApply(new NValue(Prepend.INSTANCE), v2); // -> "2 :"
		final NApply prepend4 = new NApply(prepend3, prepend2); // -> "2 : 2+3 : nil"
		final NApply map1 = new NApply(new NValue(Map.INSTANCE), plus1); // -> "map (2+)"
		final NApply map2 = new NApply(map1, prepend4);

		System.out.println(map2.evaluate());
	}
}
