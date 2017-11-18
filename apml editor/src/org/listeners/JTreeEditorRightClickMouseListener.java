package org.listeners;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
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
		//

		if (e.getClickCount() == 1 && SwingUtilities.isRightMouseButton(e))
		{
			//e.consume();
		}
		else
			return;

		//

		int row;

		TreePath path;

		//

		//row = tree.getClosestRowForLocation(e.getX(), e.getY());

		//tree.setSelectionRow(row);

		path = tree.getClosestPathForLocation(e.getX(), e.getY());

		tree.startEditingAtPath(path);

		//

		JPopupMenu popupMenu = new JPopupMenu();

		popupMenu.add("Edit");

		popupMenu.show(e.getComponent(), e.getX(), e.getY());

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
