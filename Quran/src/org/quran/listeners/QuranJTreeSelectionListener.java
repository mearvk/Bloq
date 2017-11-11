package org.quran.listeners;

import apml.system.bodi.Bodi;
import org.custom.ui.BodiJTreeNode;
import org.quran.widgets.JTree_Quran_000;
import org.quran.widgets.QuranJTreeNode;
import org.quran.widgets.RSTextPane_Quran_000;
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
		/*
		Component component;

		QuranJTreeNode clickednode;

		//BodiJTreeNode bodinode;

		RSTextPane_Quran_000 rstextpane_quran_000;

		//

		clickednode = (QuranJTreeNode)	this.jtree.getComponentAt(e.getX(),e.getY()); //this.jtree.getLastSelectedPathComponent();

		//

		APMLGui apmlgui = (APMLGui) Bodi.context("editor").pull("//editor/ui/apmlgui");

		//

		rstextpane_quran_000 = (RSTextPane_Quran_000)Bodi.context("editor").pull("//editor/ui/rstextpane_quran_000");

		try
		{
			String line = "";

			String text = "";

			rstextpane_quran_000.setText("Quran: "+clickednode._toString());
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
		*/
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
		Component component;

		QuranJTreeNode clickednode;

		//BodiJTreeNode bodinode;

		RSTextPane_Quran_000 rstextpane_quran_000;

		//

		clickednode = (QuranJTreeNode) this.jtree.getLastSelectedPathComponent();

		//

		APMLGui apmlgui = (APMLGui) Bodi.context("editor").pull("//editor/ui/apmlgui");

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