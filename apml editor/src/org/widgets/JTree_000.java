package org.widgets;

import apml.system.Apmlbasesystem;
import apml.system.bodi.Bodi;
import apml.xpath.helpers.Xpathquick;
import org.custom.ui.ApmlJTreeNode;
import org.custom.ui.AttributeFolderJTreeNode;
import org.custom.ui.AttributeLeafJTreeNode;
import org.custom.ui.TranslucentJTreeCellRenderer;
import org.events.CloseApmlDocumentEvent;
import org.events.LoadApmlDocumentEvent;
import org.listeners.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;


/**
 * Software programmatically produced by Bloq implementation version 1.05 - Bodi Remote Version
 *
 * @author
 * @see
 * @since
 */
public class JTree_000 extends JTree
{
	public String bodi = "//editor/ui/jtree_000";

	public KeyEvent importref_001;
	public KeyStroke importref_002;
	public ActionEvent importref_003;
	public ImageIcon importref_004;
	public URL importref_005;
	public Color importref_006;
	public BorderLayout importref_007;
	public FlowLayout importref_008;
	public GridLayout importref_009;
	public Color importref_010;
	public EmptyBorder importref_011;
	public ChangeEvent importref_012;
	public Dimension importref_013;
	public Rectangle importref_014;
	public ImageIO importref_015;
	public File importref_016;

	public Component parent;
	public Apmlbasesystem system;

	public Integer selected_child_index;

	//

	public File last_selected_basedir = null;

	public File last_selected_apml_file = null;

	public File last_selected_manifest_file = null;

	public ByteArrayInputStream last_selected_manifest_file_bytes = null;

	public ByteArrayInputStream last_selected_apml_file_bytes = null;

	//

	/**
	 * @param parent : The tree AWT object.
	 */
	public JTree_000(Component parent)
	{
		// setters

		this.setBackground(new Color(223,223,223));

		this.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));

		this.setCellRenderer(new TranslucentJTreeCellRenderer());

		// instantiation

		this.init();

		// hierarchy

		// devolvement

		this.parent = parent;

		this.setVisible(true);

		// listeners

		// bodi

		Bodi.context("editor").put(this.bodi, this);
	}

	/**
	 * @param parent : The tree AWT object.
	 * @param system : The APML system object.
	 */
	public JTree_000(Component parent, Apmlbasesystem system)
	{
		// setters

		this.setBackground(new Color(223,223,223));

		this.setCellRenderer(new TranslucentJTreeCellRenderer());

		// instantiation

		this.init();

		// hierarchy

		// devolvement

		this.parent = parent;

		this.system = system;

		this.setVisible(true);

		// listeners

		// bodi

		Bodi.context("editor").put(this.bodi, this);
	}

	/**
	 *
	 */
	public void init()
	{
		DefaultMutableTreeNode root = ((DefaultMutableTreeNode) this.getModel().getRoot());

		DefaultTreeModel model = (DefaultTreeModel) this.getModel();

		Document document;

		Node node;

		//

		try
		{
			root.removeAllChildren();

			root.setUserObject("User Design Area");

			model.reload();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 *
	 */
	public void removenewlinetextnodes()
	{
		DefaultMutableTreeNode root = (DefaultMutableTreeNode) this.getModel().getRoot();

		DefaultTreeModel model = (DefaultTreeModel) this.getModel();

		Enumeration children = root.breadthFirstEnumeration();

		//

		DefaultMutableTreeNode current_child;

		Object current_object;

		Short current_type;

		ArrayList<DefaultMutableTreeNode> remove = new ArrayList<>(500);

		for (; children.hasMoreElements(); )
		{
			current_child = (DefaultMutableTreeNode) children.nextElement();

			current_object = current_child.getUserObject();

			current_type = current_object instanceof Node ? ((Node) current_object).getNodeType() : -1;

			//

			if (current_type == Node.TEXT_NODE)
			{
				remove.add(current_child);
			}
		}

		//

		for (int i = 0; i < remove.size(); i++)
		{
			model.removeNodeFromParent(remove.get(i));
		}

		//

		//model.reload();
	}

	/**
	 * @return
	 */
	public ByteArrayInputStream getlastselectedmanifestfilebytes()
	{
		this.last_selected_manifest_file_bytes.reset();

		byte[] bytes = new byte[this.last_selected_manifest_file_bytes.available()];

		try
		{
			this.last_selected_manifest_file_bytes.read(bytes);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		this.last_selected_manifest_file_bytes.reset();

		return new ByteArrayInputStream(bytes);
	}

	/**
	 * @param nodes
	 * @return
	 */
	public String getdocumenttype(NodeList nodes)
	{
		if (nodes.getLength() == 0)
		{
			System.err.println("Unable to proceed; doctype attribute not found.");

			return null;
		}
		else if (nodes.getLength() == 1)
		{
			Node doctypenode = nodes.item(0);

			if (doctypenode.getNodeType() == Node.ELEMENT_NODE)
			{
				Element doctype = (Element) nodes.item(0);

				return doctype.getAttributeNode("doctype").getNodeValue();
			}
			else
			{
				System.out.println("Unable to proceed; doctypenode is other type than Element.");

				return null;
			}
		}
		else
		{
			System.err.println("Unable to proceed; doctype attribute ambiguous.");

			return null;
		}
	}

	/**
	 * @param event
	 */
	public void removeallchildren(CloseApmlDocumentEvent event)
	{
		((DefaultMutableTreeNode) this.getModel().getRoot()).removeAllChildren();
	}

	/**
	 * @param model
	 * @param root
	 * @param parent
	 * @param child
	 * @param children
	 * @param depth
	 */
	private void rloadfromnodelist(DefaultTreeModel model, DefaultMutableTreeNode root /*root*/, DefaultMutableTreeNode parent /*packages*/, DefaultMutableTreeNode child /*project*/, NodeList children, Integer depth)
	{
		try
		{
			//if (child == null)
			//return;

			//

			if (parent == null)
				return;

			//

			//

			for (int i = 0; i < children.getLength(); i++)
			{
				//

				Node node = children.item(i);

				//

				ApmlJTreeNode apml_node;

				apml_node = new ApmlJTreeNode(node);

				apml_node.setAllowsChildren(true);

				//

				AttributeFolderJTreeNode attr_folder;

				attr_folder = new AttributeFolderJTreeNode("attributes");

				attr_folder.setAllowsChildren(true);

				//

				AttributeLeafJTreeNode attr_node;

				//

				if (node.getNodeType() == Node.ELEMENT_NODE)
				{
					for (int j = 0; j < node.getAttributes().getLength(); j++)
					{
						attr_node = new AttributeLeafJTreeNode(node.getAttributes().item(j).getNodeName() + " : " + node.getAttributes().item(j).getNodeValue());

						attr_node.setAllowsChildren(false);

						attr_folder.insert(attr_node, attr_folder.getChildCount() == 0 ? 0 : attr_folder.getChildCount() - 1);
					}
				}

				//

				int parent_insertion_index = parent.getChildCount() == 0 ? 0 : parent.getChildCount() - 1;

				int apml_insertion_index = apml_node.getChildCount() == 0 ? 0 : apml_node.getChildCount() - 1;

				//

				rloadfromnodelist(model, root, apml_node, apml_node, children.item(i).getChildNodes(), depth + 1);

				//

				model.insertNodeInto(apml_node, parent, parent_insertion_index);

				model.insertNodeInto(attr_folder, apml_node, apml_insertion_index);

				//
			}

			//

			model.reload();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}