package org.widgets;

import apml.system.bodi.Bodi;

import javax.swing.*;
import java.awt.*;

public class JScrollPane_000 extends JScrollPane
{
	public Component parent;

	public String bodi = "//editor/ui/jscrollpane_000";

	public JScrollPane_000(Component parent)
	{
		// setters

		this.setBackground(new Color(0xff,0xff,0xff));

		this.setForeground(Color.BLACK);

		this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		// instantiation

		// hierarchy

		// listeners

		// bodi

		Bodi.context("editor").put(this.bodi, this);

		//devolvement

		this.parent = parent;

		this.setVisible(true);
	}

	@Override
	public Dimension getPreferredSize()
	{
		return new Dimension((int) (parent.getWidth() * 1.0), 50);
	}
}
