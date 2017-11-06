package org.widgets;

import apml.system.Apmlbasesystem;
import apml.system.bodi.Bodi;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.*;
import java.awt.*;

public class RTextScrollPane_000 extends RTextScrollPane
{
	public String bodi = "//editor/ui/rtextscrollpane_000";

	public Component parent;

	//
	public RTextScrollPane_000(Component parent)
	{
		//setters

		this.setLineNumbersEnabled(true);

		this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);



		this.getGutter().setPreferredSize(new Dimension(40,600));

		this.revalidate();

		//bodi

		Bodi.context("editor").put(this.bodi, this);

		//devolvement

		this.parent = parent;
	}

	//
	public RTextScrollPane_000(Component parent, Apmlbasesystem monitor)
	{
		//setters

		this.setLineNumbersEnabled(true);

		this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		this.revalidate();

		//bodi

		Bodi.context("editor").put(this.bodi, this);

		//devolvement

		this.parent = parent;
	}


	@Override
	public Dimension getPreferredSize()
	{
		Dimension simple;

		simple = new Dimension(this.parent.getWidth(), this.parent.getHeight());

		return simple;
	}
}
