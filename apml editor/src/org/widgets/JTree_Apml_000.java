package org.widgets;

import apml.system.Apmlbasesystem;
import apml.system.bodi.Bodi;
import apml.xpath.helpers.Xpathquick;
import org.custom.ui.*;
import org.events.CloseApmlDocumentEvent;
import org.events.LoadApmlTreeEvent;
import org.events.ReloadApmlTreeEvent;
import org.listeners.*;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
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
public class JTree_Apml_000 extends JTree
{
	public String bodi = "//editor/ui/jtree_apml_000";

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

	/**
	 * @param parent : The tree AWT object.
	 */
	public JTree_Apml_000(Component parent)
	{
		// setters

		this.setBackground(new Color(223,223,223));

		this.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));

		//this.setCellRenderer(new TranslucentJTreeCellRenderer());

		this.setCellRenderer(new AttributeSensitiveJTreeCellRenderer());

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
	public JTree_Apml_000(Component parent, Apmlbasesystem system)
	{
		// setters

		this.setBackground(new Color(223,223,223));

		//this.setCellRenderer(new TranslucentJTreeCellRenderer());

		this.setCellRenderer(new AttributeSensitiveJTreeCellRenderer());

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

			root.setUserObject("APML Informatics");

			model.reload();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void update(CloseApmlDocumentEvent event)
	{
		((DefaultMutableTreeNode) this.getModel().getRoot()).removeAllChildren();
	}

	public void _update(ReloadApmlTreeEvent event)
	{
		ByteArrayInputStream bytes = event.getByteRef();

		Document document;

		DefaultTreeModel model;

		DefaultMutableTreeNode root;

		DefaultMutableTreeNode manifestnode;

		DefaultMutableTreeNode packagesnode;

		DefaultMutableTreeNode childnode;

		XPath xpath;

		NodeList nodes;

		//

		try
		{


			document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(bytes);

			xpath = XPathFactory.newInstance().newXPath();

			nodes = Xpathquick.evaluate(document, xpath, "/*");

			model = (DefaultTreeModel) this.getModel();

			root = (DefaultMutableTreeNode) model.getRoot();

			//

			((DefaultMutableTreeNode) model.getRoot()).removeAllChildren();

			//

			manifestnode = new DefaultMutableTreeNode("manifest", true);

			packagesnode = new DefaultMutableTreeNode("packages", true);

			//

			manifestnode.setAllowsChildren(true);

			packagesnode.setAllowsChildren(true);

			//

			childnode = new DefaultMutableTreeNode(nodes.item(0));

			childnode.setAllowsChildren(true);

			//

			model.insertNodeInto(manifestnode, root, 0);

			model.insertNodeInto(packagesnode, root, 1);

			//

			this.update(model, root, packagesnode, childnode, nodes, 0);

			//

			model.reload();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void update(LoadApmlTreeEvent event)
	{
		File file = event.getFileRef();

		Document document;

		DefaultTreeModel model;

		DefaultMutableTreeNode root;

		DefaultMutableTreeNode manifestnode;

		DefaultMutableTreeNode packagesnode;

		DefaultMutableTreeNode attributesnode;

		DefaultMutableTreeNode childnode;

		XPath xpath;

		NodeList nodes;

		//

		try
		{
			document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);

			xpath = XPathFactory.newInstance().newXPath();

			nodes = Xpathquick.evaluate(document, xpath, "/*");

			model = (DefaultTreeModel) this.getModel();

			root = (DefaultMutableTreeNode) model.getRoot();

			//

			manifestnode = new DefaultMutableTreeNode("manifest", true);

			packagesnode = new DefaultMutableTreeNode("packages", true);

			//

			manifestnode.setAllowsChildren(true);

			packagesnode.setAllowsChildren(true);

			//

			childnode = new ApmlJTreeNode(nodes.item(0));

			childnode.setAllowsChildren(true);

			//

			manifestnode.insert(new DefaultMutableTreeNode("manifest.xml"), manifestnode.getChildCount() == 0 ? 0 : manifestnode.getChildCount() - 1);

			//

			model.insertNodeInto(manifestnode, root, 0);

			model.insertNodeInto(packagesnode, root, 1);

			//

			this.update(model, root, packagesnode, childnode, nodes, 0);

			//

			//model.reload();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	//
	private void update(DefaultTreeModel model, DefaultMutableTreeNode root /*root*/, DefaultMutableTreeNode parent /*packages*/, DefaultMutableTreeNode child /*project*/, NodeList children, Integer depth)
	{
		try
		{
			if (child == null)
				return;

			//

			if (parent == null)
				return;

			//

			for (int i = 0; i < children.getLength(); i++)
			{
				//

				Node node = children.item(i);

				//

				DefaultMutableTreeNode element_node;

				element_node = new ApmlJTreeNode(node);

				element_node.setAllowsChildren(true);

				//

				AttributeFolderJTreeNode attr_element_folder;

				attr_element_folder = new AttributeFolderJTreeNode("attributes");

				attr_element_folder.setAllowsChildren(true);

				//

				AttributeLeafJTreeNode attr_element_node;

				//attr_element_node = new AttributeLeafJTreeNode("00");

				//attr_element_node.setAllowsChildren(true);

				//

				if (node != null && node.getNodeType() == Node.ELEMENT_NODE)
					for (int j = 0; j < node.getAttributes().getLength(); j++)
					{
						attr_element_node = new AttributeLeafJTreeNode(node.getAttributes().item(j).getNodeName() + " : " + node.getAttributes().item(j).getNodeValue());

						attr_element_node.setAllowsChildren(false);

						attr_element_folder.insert(attr_element_node, attr_element_folder.getChildCount() == 0 ? 0 : attr_element_folder.getChildCount() - 1);
					}

				//


				model.insertNodeInto(attr_element_folder, element_node, element_node.getChildCount() == 0 ? 0 : element_node.getChildCount() - 1);


				model.insertNodeInto(element_node, parent, parent.getChildCount() == 0 ? 0 : parent.getChildCount() - 1);

				//

				update(model, root, element_node, element_node, children.item(i).getChildNodes(), depth + 1);
			}

			//

			model.reload();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

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

		model.reload();
	}
}