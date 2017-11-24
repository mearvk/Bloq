package org.custom.ui;

import org.w3c.dom.Node;

import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;

public class BodiJTreeNode extends DefaultMutableTreeNode
{
	public Integer my_index;

	public File fileRef;

	//

	public BodiJTreeNode(String name)
	{
		super(name, false);
	}

	public BodiJTreeNode(String name, File fileRef)
	{
		super(name, false);

		this.fileRef = fileRef;
	}

	public BodiJTreeNode(Node node, File fileRef)
	{
		super(node, false);

		this.fileRef = fileRef;
	}

	public BodiJTreeNode(Node node, Integer child_index, File file)
	{
		super(node, false);

		this.my_index = child_index;

		this.fileRef = fileRef;
	}

	public BodiJTreeNode(Node node, Boolean allowsChildren, File file)
	{
		super(node, allowsChildren);

		this.fileRef = fileRef;
	}

	public BodiJTreeNode(String object, Boolean allowsChildren, File file)
	{
		super(object, allowsChildren);

		this.fileRef = fileRef;
	}

	public File getFileRef()
	{
		return this.fileRef;
	}

	@Override
	public String toString()
	{
		return ((Node) this.getUserObject()).getNodeName();
	}
}

