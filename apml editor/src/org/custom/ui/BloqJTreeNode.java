package org.custom.ui;

import org.w3c.dom.Node;

import javax.swing.tree.DefaultMutableTreeNode;

public class BloqJTreeNode extends DefaultMutableTreeNode
{
	Integer my_index;

	public BloqJTreeNode(String name)
	{
		super(name, false);
	}

	public BloqJTreeNode(Node node)
	{
		super(node, false);
	}

	public BloqJTreeNode(Node node, Integer child_index)
	{
		super(node, false);

		this.my_index = child_index;
	}

	public BloqJTreeNode(Node node, Boolean allowsChildren)
	{
		super(node, allowsChildren);
	}

	public BloqJTreeNode(String object, Boolean allowsChildren)
	{
		super(object, allowsChildren);
	}

	@Override
	public String toString()
	{
		return ((Node) this.getUserObject()).getNodeName();
	}
}

