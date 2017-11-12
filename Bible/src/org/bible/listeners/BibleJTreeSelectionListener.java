package org.bible.listeners;

import apml.system.bodi.Bodi;
import org.bible.widgets.BibleJTreeNode;
import org.bible.widgets.JTree_Bible_000;
import org.bible.widgets.RSTextPane_Bible_000;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BibleJTreeSelectionListener implements TreeSelectionListener, MouseListener
{
	public JTree_Bible_000 jtree;

	public DefaultMutableTreeNode selectednode;

	//

	public BibleJTreeSelectionListener(JTree_Bible_000 jtree)
	{
		this.jtree = jtree;
	}

	//

	@Override
	public void mouseClicked(MouseEvent e)
	{

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

	@Override
	public void valueChanged(TreeSelectionEvent e)
	{
		BibleJTreeNode clickednode;

		RSTextPane_Bible_000 rstextpane_bible_000;

		//

		clickednode = (BibleJTreeNode) this.jtree.getLastSelectedPathComponent();

		//

		rstextpane_bible_000 = (RSTextPane_Bible_000) Bodi.context("editor").pull("//editor/ui/rstextpane_bible_000");

		try
		{
			String line = "";

			String text = "";

			rstextpane_bible_000.setText(clickednode._toString());
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
	}
}