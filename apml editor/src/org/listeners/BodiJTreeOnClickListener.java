package org.listeners;

import apml.system.bodi.Bodi;
import org.custom.ui.ApmlJTreeNode;
import org.custom.ui.AttributeFolderJTreeNode;
import org.custom.ui.BodiJTreeNode;
import org.widgets.APMLGui;
import org.widgets.JTree_Bodi_000;
import org.widgets.RSTextPane_Bodi_000;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class BodiJTreeOnClickListener implements TreeSelectionListener, MouseListener
{
	public JTree_Bodi_000 jtree;

	public File selected;

	public DefaultMutableTreeNode selectednode;

	//

	public BodiJTreeOnClickListener(JTree_Bodi_000 jtree)
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

		if (this.jtree.getLastSelectedPathComponent() instanceof AttributeFolderJTreeNode || (!(this.jtree.getLastSelectedPathComponent() instanceof BodiJTreeNode)))
			return;

		//

		bodinode = (BodiJTreeNode) this.jtree.getLastSelectedPathComponent();

		//

		File fileRef = bodinode.getFileRef();

		if (fileRef == null)
		{
			System.err.println("File was null; returning.");

			return;
		}

		if (this.jtree.getLastSelectedPathComponent() instanceof BodiJTreeNode)
		{
			//JOptionPane.showMessageDialog(null, "Oddly, the bodi file itself wasn't passed to the listener onload function.");

			System.err.println("Class is right actually: " + this.jtree.getLastSelectedPathComponent().getClass());
		}
		else
		{
			System.err.println("Class is strangely actually: " + this.jtree.getLastSelectedPathComponent().getClass());
		}

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
