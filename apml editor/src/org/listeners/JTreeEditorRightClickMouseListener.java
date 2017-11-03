package org.listeners;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class JTreeEditorRightClickMouseListener implements MouseListener
{
	JTree tree;

	public JTreeEditorRightClickMouseListener(JTree tree)
	{
		this.tree = tree;
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		if (e.getClickCount() == 1 && SwingUtilities.isRightMouseButton(e))
		{
			e.consume();
		}
		else
			return;

		//

		System.out.println("Right mouse button click detected!");
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
