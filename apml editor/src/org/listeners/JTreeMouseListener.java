package org.listeners;

import org.helpers.LineNumberFinder;

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

		LineNumberFinder linenumberfinder;

		linenumberfinder = new LineNumberFinder();

		//
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
