package org.listeners;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class JTreeEditorLeftClickMouseListener implements MouseListener
{
	JTree tree;

	public JTreeEditorLeftClickMouseListener(JTree tree)
	{
		this.tree = tree;
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		if(e.getClickCount()==1 && SwingUtilities.isLeftMouseButton(e))
		{
			e.consume();
		}
		else return;

		//

		System.out.println("Left mouse button click detected!");
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
