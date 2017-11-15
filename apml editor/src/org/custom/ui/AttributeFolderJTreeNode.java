package org.custom.ui;

import org.w3c.dom.Node;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

public class AttributeFolderJTreeNode extends DefaultMutableTreeNode
{
	Integer my_index;

	public AttributeFolderJTreeNode(String nodename)
	{
		super(nodename);
	}

	public AttributeFolderJTreeNode(Node node)
	{
		super(node, false);
	}

	public AttributeFolderJTreeNode(Node node, Integer child_index)
	{
		super(node, false);

		this.my_index = child_index;
	}

	public AttributeFolderJTreeNode(Node node, Boolean allowsChildren)
	{
		super(node, allowsChildren);
	}

	public AttributeFolderJTreeNode(String object, Boolean allowsChildren)
	{
		super(object, allowsChildren);
	}
}
