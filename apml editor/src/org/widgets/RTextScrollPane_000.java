package org.widgets;

import apml.system.Apmlbasesystem;
import apml.system.bodi.Bodi;
import org.fife.ui.rtextarea.RTextScrollPane;

import java.awt.*;

public class RTextScrollPane_000 extends RTextScrollPane
{
	public String bodi = "//ui/editor/rtextscrollpane_000";

	public Component parent;

	//
	public RTextScrollPane_000(Component parent)
	{
		//setters

		//bodi

		Bodi.context("editor").put("//ui/editor/rtextscrollpane_000", this);

		//devolvement

		this.parent = parent;
	}

	//
	public RTextScrollPane_000(Component parent, Apmlbasesystem monitor)
	{
		//setters

		//bodi

		Bodi.context("editor").put("//ui/editor/rtextscrollpane_000", this);

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
