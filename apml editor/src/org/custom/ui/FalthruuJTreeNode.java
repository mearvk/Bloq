package org.custom.ui;

import org.w3c.dom.Node;

import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;

public class FalthruuJTreeNode extends DefaultMutableTreeNode
{
	public Integer my_index;

	public File fileRef;

	public FalthruuJTreeNode(String name)
	{
		super(name, false);
	}

	public FalthruuJTreeNode(String filename, File fileRef)
	{
		this.userObject = filename;

		this.fileRef = fileRef;
	}

	public FalthruuJTreeNode(Node node)
	{
		super(node, false);
	}

	public FalthruuJTreeNode(Node node, Integer child_index)
	{
		super(node, false);

		this.my_index = child_index;
	}

	public FalthruuJTreeNode(Node node, Boolean allowsChildren)
	{
		super(node, allowsChildren);
	}

	public FalthruuJTreeNode(String object, Boolean allowsChildren)
	{
		super(object, allowsChildren);
	}

	@Override
	public String toString()
	{
		if (this.userObject instanceof String)
			return (String) this.userObject;

		return ((Node) this.getUserObject()).getNodeName();
	}

	public File getFileRef()
	{
		return this.fileRef;
	}
}

