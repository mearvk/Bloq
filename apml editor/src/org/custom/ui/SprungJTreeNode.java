package org.custom.ui;

import org.w3c.dom.Node;

import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;

public class SprungJTreeNode extends DefaultMutableTreeNode
{
	public Integer my_index;

	public File fileRef;

	public SprungJTreeNode(String name)
	{
		super(name, false);
	}

	public SprungJTreeNode(Node node)
	{
		super(node, false);
	}

	public SprungJTreeNode(Node node, Integer child_index)
	{
		super(node, false);

		this.my_index = child_index;
	}

	public SprungJTreeNode(Node node, Boolean allowsChildren)
	{
		super(node, allowsChildren);
	}

	public SprungJTreeNode(String object, Boolean allowsChildren)
	{
		super(object, allowsChildren);
	}

	@Override
	public String toString()
	{
		return ((Node) this.getUserObject()).getNodeName();
	}

	public File getFileRef()
	{
		return this.fileRef;
	}
}

