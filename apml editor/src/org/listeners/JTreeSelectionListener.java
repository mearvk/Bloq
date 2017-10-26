package org.listeners;

import apml.system.bodi.Bodi;
import org.widgets.RSTextPane_000;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

public class JTreeSelectionListener implements TreeSelectionListener
{
	public JTree tree;

	public JTreeSelectionListener(JTree tree)
	{
		this.tree = tree;
	}

	@Override
	public void valueChanged(TreeSelectionEvent event)
	{

		RSTextPane_000 textpane;

		textpane = (RSTextPane_000) Bodi.context("system").pull("//ui/system/rstextpane_000");

		//

		try
		{
			DefaultMutableTreeNode treenode;

			treenode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
		}
		catch(Exception e)
		{
			//
		}
	}
}