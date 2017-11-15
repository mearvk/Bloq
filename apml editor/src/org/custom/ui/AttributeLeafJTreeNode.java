package org.custom.ui;

import org.w3c.dom.Node;

import javax.swing.tree.DefaultMutableTreeNode;

public class AttributeLeafJTreeNode extends DefaultMutableTreeNode
{
	Integer my_index;

	public AttributeLeafJTreeNode(String nodename)
	{
		super(nodename);

	}

	public AttributeLeafJTreeNode(Node node)
	{
		super(node, false);
	}

	public AttributeLeafJTreeNode(Node node, Integer child_index)
	{
		super(node, false);

		this.my_index = child_index;
	}

	public AttributeLeafJTreeNode(Node node, Boolean allowsChildren)
	{
		super(node, allowsChildren);
	}

	public AttributeLeafJTreeNode(String object, Boolean allowsChildren)
	{
		super(object, allowsChildren);
	}
}
