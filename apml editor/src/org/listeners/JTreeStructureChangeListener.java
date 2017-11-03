package org.listeners;

import apml.system.bodi.Bodi;
import org.events.TreeStructureUpdatedEvent;
import org.system.UserInterfaceProcessor;

import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

public class JTreeStructureChangeListener implements TreeSelectionListener, TreeModelListener
{

	public JTreeStructureChangeListener()
	{
		//bodi

		Bodi.context("system").put("//ui/listeners/JTreeStructureChangeListener", this);
	}

	@Override
	public void treeNodesChanged(TreeModelEvent e)
	{
		//
	}

	@Override
	public void treeNodesInserted(TreeModelEvent e)
	{
		//
	}

	@Override
	public void treeNodesRemoved(TreeModelEvent e)
	{
		//
	}

	@Override
	public void treeStructureChanged(TreeModelEvent e)
	{
		//
	}

	@Override
	public void valueChanged(TreeSelectionEvent e)
	{
		//

		DefaultMutableTreeNode source_node;

		source_node = (DefaultMutableTreeNode) e.getSource();

		UserInterfaceProcessor processor;

		processor = (UserInterfaceProcessor) Bodi.context("system").pull("//events/system/{id}/uiprocessor");

		processor.update(new TreeStructureUpdatedEvent(e, e.hashCode(), "tree_structure_updated_event"));
	}
}