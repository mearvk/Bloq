package org.listeners;

import apml.system.bodi.Bodi;
import org.custom.ui.ApmlJTreeNode;
import org.custom.ui.AttributeFolderJTreeNode;
import org.custom.ui.BodiJTreeNode;
import org.widgets.*;

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

public class ApmlJTreeOnClickListener implements TreeSelectionListener, MouseListener
{
	public JTree_Apml_000 jtree;

	public File selected;

	public DefaultMutableTreeNode selectednode;

	//

	public ApmlJTreeOnClickListener(JTree_Apml_000 jtree)
	{
		this.jtree = jtree;
	}

	//

	@Override
	public void mouseClicked(MouseEvent e)
	{
		Component component;

		ApmlJTreeNode apmlnode;

		RSTextPane_Apml_000 rstextpane_apml_000;

		//

		if (this.jtree.getLastSelectedPathComponent() instanceof AttributeFolderJTreeNode || (!(this.jtree.getLastSelectedPathComponent() instanceof ApmlJTreeNode)))
			return;

		//

		apmlnode = (ApmlJTreeNode) this.jtree.getLastSelectedPathComponent();

		//

		File fileRef = apmlnode.getFileRef();

		if (fileRef == null)
		{
			System.err.println("File was null; returning.");

			return;
		}

		if (this.jtree.getLastSelectedPathComponent() instanceof ApmlJTreeNode)
		{
			//JOptionPane.showMessageDialog(null, "Oddly, the bodi file itself wasn't passed to the listener onload function.");

			System.err.println("Class is right actually: " + this.jtree.getLastSelectedPathComponent().getClass());
		}
		else
		{
			System.err.println("Class is strangely actually: " + this.jtree.getLastSelectedPathComponent().getClass());
		}

		APMLGui apmlgui = (APMLGui) Bodi.context("editor").pull("//editor/ui/apmlgui");

		//

		rstextpane_apml_000 = (RSTextPane_Apml_000) Bodi.context("editor").pull("//editor/ui/rstextpane_apml_000");

		try
		{
			BufferedReader reader = new BufferedReader(new FileReader(fileRef));

			String line = "";

			String text = "";

			while ((line = reader.readLine()) != null)
			{
				text += line + "\n";
			}

			rstextpane_apml_000.setText(text);
		}
		catch (Exception exception)
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
