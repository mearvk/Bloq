package org.custom.ui;

import org.w3c.dom.Node;

import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;

public class BloqJTreeNode extends DefaultMutableTreeNode
{
	public Integer my_index;

	public File fileRef;

	public BloqJTreeNode(String filename, File fileRef)
	{
		this.userObject = filename;

		this.fileRef = fileRef;
	}

	public BloqJTreeNode(String name)
	{
		super(name, false);
	}

	public BloqJTreeNode(Node node, File fileRef)
	{
		super(node, false);

		this.fileRef = fileRef;
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
		if (this.userObject instanceof String)
			return (String) this.userObject;

		return ((Node) this.getUserObject()).getNodeName();
	}

	public File getFileRef()
	{
		return this.fileRef;
	}
}

