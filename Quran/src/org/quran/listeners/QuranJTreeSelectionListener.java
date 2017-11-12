package org.quran.listeners;

import apml.system.bodi.Bodi;
import org.quran.widgets.JTree_Quran_000;
import org.quran.widgets.QuranJTreeNode;
import org.quran.widgets.RSTextPane_Quran_000;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class QuranJTreeSelectionListener implements TreeSelectionListener, MouseListener
{
	public JTree_Quran_000 jtree;

	public DefaultMutableTreeNode selectednode;

	//

	public QuranJTreeSelectionListener(JTree_Quran_000 jtree)
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
		QuranJTreeNode clickednode;

		RSTextPane_Quran_000 rstextpane_quran_000;

		//

		clickednode = (QuranJTreeNode) this.jtree.getLastSelectedPathComponent();

		//

		rstextpane_quran_000 = (RSTextPane_Quran_000) Bodi.context("editor").pull("//editor/ui/rstextpane_quran_000");

		try
		{
			String line = "";

			String text = "";

			rstextpane_quran_000.setText(clickednode._toString());
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
	}
}