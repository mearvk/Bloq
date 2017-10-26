package org.custom.ui;

import org.w3c.dom.Node;

import javax.swing.tree.DefaultMutableTreeNode;

public class ApmlJTreeNode extends DefaultMutableTreeNode
{
	Integer my_index;

	public ApmlJTreeNode(Node node)
	{
		super(node, false);
	}

	public ApmlJTreeNode(Node node, Integer child_index)
	{
		super(node, false);

		this.my_index = child_index;
	}

	public ApmlJTreeNode(Node node, Boolean allowsChildren)
	{
		super(node, allowsChildren);
	}

	public ApmlJTreeNode(String object, Boolean allowsChildren)
	{
		super(object, allowsChildren);
	}

	@Override
	public String toString()
	{
		return ((Node)this.getUserObject()).getNodeName();
	}
}
