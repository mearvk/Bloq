package org.listeners;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class JTreeEditorDoubleClickMouseListener implements MouseListener
{
	JTree tree;

	public JTreeEditorDoubleClickMouseListener(JTree tree)
	{
		this.tree = tree;
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		if(e.getClickCount()>=2)
		{
			e.consume();
		}
		else return;

		//

		System.out.println("Double mouse button click detected!");
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
