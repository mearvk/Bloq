package org.quran.widgets;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.swing.tree.DefaultMutableTreeNode;

public class QuranJTreeNode extends DefaultMutableTreeNode
{
	Integer my_index;

	public QuranJTreeNode(Node node)
	{
		super(node, false);
	}

	public QuranJTreeNode(Node node, Integer child_index)
	{
		super(node, false);

		this.my_index = child_index;
	}

	public QuranJTreeNode(Node node, Boolean allowsChildren)
	{
		super(node, allowsChildren);
	}

	public QuranJTreeNode(String object, Boolean allowsChildren)
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
			if (node.getNodeName().contains("Verse"))
			{
				return "Verse: " + ((Element) node).getAttribute("VerseID");
			}

			if (node.getNodeName().contains("Chapter"))
			{
				return "Chapter: " + ((Element) node).getAttribute("ChapterID");
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