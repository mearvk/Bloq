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

	public Apmlbasesystem monitor;

	//
	public RTextScrollPane_000(Component parent)
	{
		//setters

		this.setLineNumbersEnabled(true);

		this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

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

		this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		this.revalidate();

		//bodi

		Bodi.context("editor").put(this.bodi, this);

		//devolvement

		this.parent = parent;

		this.monitor = monitor;
	}


	@Override
	public Dimension getPreferredSize()
	{
		Dimension simple;

		simple = new Dimension(this.parent.getWidth(), this.parent.getHeight());

		return simple;
	}
}
