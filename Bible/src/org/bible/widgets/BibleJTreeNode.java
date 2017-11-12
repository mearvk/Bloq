package org.bible.widgets;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.swing.tree.DefaultMutableTreeNode;

public class BibleJTreeNode extends DefaultMutableTreeNode
{
	Integer my_index;

	public BibleJTreeNode(Node node)
	{
		super(node, false);
	}

	public BibleJTreeNode(Node node, Integer child_index)
	{
		super(node, false);

		this.my_index = child_index;
	}

	public BibleJTreeNode(Node node, Boolean allowsChildren)
	{
		super(node, allowsChildren);
	}

	public BibleJTreeNode(String object, Boolean allowsChildren)
	{
		super(object, allowsChildren);
	}

	@Override
	public String toString()
	{
		Node node;

		node = (Node) this.getUserObject();

		if (node.getNodeType() == Node.ELEMENT_NODE)
		{
			if (node.getNodeName().contains("b"))
			{
				return "Book: " + ((Element) node).getAttribute("n");
			}

			if (node.getNodeName().contains("v"))
			{
				return "Verse: " + ((Element) node).getAttribute("n");
			}

			if (node.getNodeName().contains("c"))
			{
				return "Chapter: " + ((Element) node).getAttribute("n");
			}

			return node.getNodeName();
		}

		if (node.getNodeType() == Node.TEXT_NODE)
		{
			return node.getTextContent();
		}

		return node.getTextContent().substring(0, 12) + "...";
	}

	public String _toString()
	{
		Node node;

		node = (Node) this.getUserObject();

		return node.getTextContent();
	}
}