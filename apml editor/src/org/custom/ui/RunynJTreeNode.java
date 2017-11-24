package org.custom.ui;

import org.w3c.dom.Node;

import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;

public class RunynJTreeNode extends DefaultMutableTreeNode
{
	public Integer my_index;

	public File fileRef;

	public RunynJTreeNode(String name)
	{
		super(name, false);
	}

	public RunynJTreeNode(Node node)
	{
		super(node, false);
	}

	public RunynJTreeNode(Node node, Integer child_index)
	{
		super(node, false);

		this.my_index = child_index;
	}

	public RunynJTreeNode(Node node, Boolean allowsChildren)
	{
		super(node, allowsChildren);
	}

	public RunynJTreeNode(String object, Boolean allowsChildren)
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

