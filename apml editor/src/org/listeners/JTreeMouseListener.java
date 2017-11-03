package org.listeners;

import apml.system.bodi.Bodi;
import org.helpers.LineNumberFinder;
import org.widgets.RSTextPane_000;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

//

public class JTreeMouseListener implements MouseListener
{
	public JTree tree;

	public JTreeMouseListener(JTree tree)
	{
		this.tree = tree;
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		DefaultMutableTreeNode selected;

		selected = (DefaultMutableTreeNode) this.tree.getLastSelectedPathComponent();

		//

		RSTextPane_000 textpane;

		textpane = (RSTextPane_000) Bodi.context("editor").pull("//editor/ui/rstextpane_000");

		//

		LineNumberFinder linenumberfinder;

		linenumberfinder = new LineNumberFinder();

		linenumberfinder.processByClick(selected, textpane, e);

		//

		textpane.setCaretPosition(linenumberfinder.charcount);
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
	}
}
