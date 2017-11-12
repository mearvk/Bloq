package org.listeners;

import apml.system.bodi.Bodi;
import org.custom.ui.BodiJTreeNode;
import org.widgets.APMLGui;
import org.widgets.JTree_Bodi_000;
import org.widgets.RSTextPane_Bodi_000;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class BodiJTreeSelectionListener implements TreeSelectionListener, MouseListener
{
	public JTree_Bodi_000 jtree;

	public DefaultMutableTreeNode selectednode;

	//

	public BodiJTreeSelectionListener(JTree_Bodi_000 jtree)
	{
		this.jtree = jtree;
	}

	//

	@Override
	public void mouseClicked(MouseEvent e)
	{
		Component component;

		DefaultMutableTreeNode clickednode;

		BodiJTreeNode bodinode;

		RSTextPane_Bodi_000 rstextpane_bodi_000;

		//

		clickednode = (DefaultMutableTreeNode)	this.jtree.getLastSelectedPathComponent();

		bodinode = (BodiJTreeNode)(DefaultMutableTreeNode) this.jtree.getLastSelectedPathComponent();

		//

		File fileRef = bodinode.getFileRef();

		if(fileRef==null) return;

		APMLGui apmlgui = (APMLGui)Bodi.context("editor").pull("//editor/ui/apmlgui");

		//

		rstextpane_bodi_000 = (RSTextPane_Bodi_000)Bodi.context("editor").pull("//editor/ui/rstextpane_bodi_000");

		try
		{
			BufferedReader reader = new BufferedReader(new FileReader(fileRef));

			String line = "";

			String text = "";

			while((line=reader.readLine())!=null)
			{
				text += line + "\n";
			}

			rstextpane_bodi_000.setText(text);
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
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

	}
}
