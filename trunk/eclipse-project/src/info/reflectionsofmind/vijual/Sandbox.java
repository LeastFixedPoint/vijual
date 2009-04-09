package info.reflectionsofmind.vijual;

import static info.reflectionsofmind.vijual.Sandbox.Direction.*;

import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import org.apache.batik.dom.svg.SVGDOMImplementation;
import org.apache.batik.swing.JSVGCanvas;
import org.apache.batik.swing.gvt.AbstractPanInteractor;
import org.w3c.dom.Element;
import org.w3c.dom.svg.SVGDocument;

public class Sandbox extends JFrame
{
	private final JSVGCanvas svgCanvas = new JSVGCanvas();

	public static void main(final String[] args) throws Exception
	{
		new Sandbox().setVisible(true);

		// final NValue v2 = new NValue(new VInteger(2));
		// final NValue v3 = new NValue(new VInteger(3));
		//		
		// final NApply plus1 = new NApply(new NValue(IntSum.INSTANCE), v2); // -> "2+"
		// final NApply plus2 = new NApply(plus1, v3); // -> "2+3"
		// final NApply prepend1 = new NApply(new NValue(Prepend.INSTANCE), plus2); // -> "2+3 :"
		// final NApply prepend2 = new NApply(prepend1, new NValue(new VNil())); // -> "2+3 : nil"
		// final NApply prepend3 = new NApply(new NValue(Prepend.INSTANCE), v2); // -> "2 :"
		// final NApply prepend4 = new NApply(prepend3, prepend2); // -> "2 : 2+3 : nil"
		// final NApply map1 = new NApply(new NValue(Map.INSTANCE), plus1); // -> "map (2+)"
		// final NApply map2 = new NApply(map1, prepend4);
		//
		// System.out.println(map2.evaluate());
	}

	public Sandbox()
	{
		super("Vijual");

		getContentPane().add(this.svgCanvas);

		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent event)
			{
				System.exit(0);
			}
		});

		final SVGDocument document = (SVGDocument) SVGDOMImplementation.getDOMImplementation().createDocument( //
				SVGDOMImplementation.SVG_NAMESPACE_URI, "svg", null);

		this.svgCanvas.setSVGDocument(document);

		document.getRootElement().appendChild(createLink(document, 0, 1, R, 1, 1, R));
		document.getRootElement().appendChild(createLink(document, 1, 1, R, 2, 2, D));
		document.getRootElement().appendChild(createLink(document, 1, 0, D, 1, 1, D));

		document.getRootElement().appendChild(createValue(document, 0, 1, R));
		document.getRootElement().appendChild(createApply(document, 1, 0, D));
		document.getRootElement().appendChild(createApply(document, 1, 1, R));
		document.getRootElement().appendChild(createApply(document, 2, 2, D));

//
//		final Element link = document.createElementNS(SVGDOMImplementation.SVG_NAMESPACE_URI, "path");
//		link.setAttribute("d", "M 200,200 L 290,200 L 290,197 L 300,200 L 290,203 L 290,200");
//		link.setAttribute("stroke", "#000000");
//		link.setAttribute("stroke-width", "3");
//		link.setAttribute("stroke-linejoin", "miter");
//		link.setAttribute("transform", "translate(-90 -200) rotate(90 200 200)");
//
//		document.getRootElement().appendChild(link);

		AbstractPanInteractor shift = new AbstractPanInteractor()
		{
			@Override
			public boolean startInteraction(InputEvent ie)
			{
				int mods = ie.getModifiers();
				return ie.getID() == MouseEvent.MOUSE_PRESSED && (mods & InputEvent.BUTTON2_MASK) != 0;
			}
		};

		this.svgCanvas.getInteractors().add(shift);

		setSize(400, 400);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		// add(new JButton(new AbstractAction("Change")
		// {
		// @Override
		// public void actionPerformed(ActionEvent arg0)
		// {
		// apply.setAttribute("fill", "#ff0000");
		// apply.setAttribute("transform", "translate(-100 -100)");
		// svgCanvas.setDocument(document);
		// }
		// }), BorderLayout.SOUTH);
	}

	public enum Direction
	{
		U, R, D, L
	}

	public int t(int c)
	{
		return 100 + 100 * c;
	}

	public Element createLink(SVGDocument document, int xs, int ys, Direction ds, int xe, int ye, Direction de)
	{
		final Element link = document.createElementNS(SVGDOMImplementation.SVG_NAMESPACE_URI, "path");

		if (ds == D && de == D && ye > ys)
		{
			link.setAttribute("d", "M " + t(xs) + "," + t(ys) + " " + //
					"L " + t(xs) + "," + (t(ys) + t(ye)) / 2 + " " + //
					"L " + t(xe) + "," + (t(ys) + t(ye)) / 2 + " " + //
					"L " + t(xe) + "," + t(ye) + "");
		}
		else if (ds == R && de == R && xe > xs)
		{
			link.setAttribute("d", "M " + t(xs) + "," + t(ys) + " " + //
					"L " + (t(xs) + t(xe)) / 2 + "," + t(ys) + " " + //
					"L " + (t(xs) + t(xe)) / 2 + "," + t(ye) + " " + //
					"L " + t(xe) + "," + t(ye) + "");
		}
		else if (ds == D && de == R && xe > xs && ye > ys)
		{
			link.setAttribute("d", "M " + t(xs) + "," + t(ys) + " " + //
					"L " + t(xs) + "," + t(ye) + " " + //
					"L " + t(xe) + "," + t(ye) + "");
		}
		else if (ds == R && de == D && xe > xs && ye > ys)
		{
			link.setAttribute("d", "M " + t(xs) + "," + t(ys) + " " + //
					"L " + t(xe) + "," + t(ys) + " " + //
					"L " + t(xe) + "," + t(ye) + "");
		}
		else
		{
			throw new RuntimeException();
		}

		link.setAttribute("fill", "none");
		link.setAttribute("stroke", "#000000");
		link.setAttribute("stroke-width", "5");
		link.setAttribute("stroke-linejoin", "round");

		return link;
	}

	public Element createApply(SVGDocument document, int x, int y, Direction d)
	{
		final Element apply = document.createElementNS(SVGDOMImplementation.SVG_NAMESPACE_URI, "polygon");

		apply.setAttribute("points", "90,90   100,80   110,90   110,120   100,110   90,120   90,90");
		apply.setAttribute("stroke", "#000000");
		apply.setAttribute("fill", "#ffff00");
		apply.setAttribute("stroke-width", "5");
		apply.setAttribute("stroke-linejoin", "round");
		apply.setAttribute("transform",  //
				"rotate(" + d.ordinal() * 90 + " " + (100 + x * 100) + " " + (100 + y * 100) + ")" + //
				"translate(" + x * 100 + " " + y * 100 + ") ");

		return apply;
	}

	public Element createValue(SVGDocument document, int x, int y, Direction d)
	{
		final Element value = document.createElementNS(SVGDOMImplementation.SVG_NAMESPACE_URI, "polygon");

		value.setAttribute("points", "90,90   100,80   110,90   110,120   90,120   90,90");
		value.setAttribute("stroke", "#000000");
		value.setAttribute("fill", "#bbbbbb");
		value.setAttribute("stroke-width", "5");
		value.setAttribute("stroke-linejoin", "round");
		value.setAttribute("transform",  // 
				"rotate(" + d.ordinal() * 90 + " " + (100 + x * 100) + " " + (100 + y * 100) + ")" + //
				"translate(" + x * 100 + " " + y * 100 + ") ");

		return value;
	}

	public void change()
	{

	}
}
