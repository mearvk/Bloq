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
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.tree.*;
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

	//

	public Component parent;
	public Apmlbasesystem system;

	//

	public Integer selected_child_index;

	//

	public File last_selected_manifest_file = null;

	public File last_selected_basedir = null;

	//

	/**
	 * @param parent : The tree AWT object.
	 */
	public JTree_Apml_000(Component parent)
	{
		// setters

		this.setBackground(new Color(213, 213, 213));

		this.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));

		this.setEditable(Boolean.TRUE);

		this.setCellRenderer(new AttributeSensitiveJTreeCellRenderer());

		this.setCellEditor(new DefaultTreeCellEditor(this, new AttributeSensitiveJTreeCellRenderer()));

		// instantiation

		this.init();

		// hierarchy



		// devolvement

		this.parent = parent;

		this.setVisible(true);

		// listeners

		this.addTreeSelectionListener(new ApmlJTreeOnClickListener(this));

		this.addMouseListener(new ApmlJTreeOnClickListener(this));

		this.addMouseListener(new JTreeEditorRightClickMouseListener(this));

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

		this.setBackground(new Color(213, 213, 213));

		this.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));

		this.setEditable(Boolean.TRUE);

		this.setCellRenderer(new AttributeSensitiveJTreeCellRenderer());

		this.setCellEditor(new DefaultTreeCellEditor(this, new AttributeSensitiveJTreeCellRenderer()));

		// instantiation

		this.init();

		// hierarchy

		// devolvement

		this.parent = parent;

		this.system = system;

		this.setVisible(true);

		// listeners

		this.addMouseListener(new JTreeEditorRightClickMouseListener(this));

		this.addTreeSelectionListener(new ApmlJTreeOnClickListener(this));

		this.addMouseListener(new ApmlJTreeOnClickListener(this));

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

	public void removeallchildren(CloseApmlDocumentEvent event)
	{
		((DefaultMutableTreeNode) this.getModel().getRoot()).removeAllChildren();
	}

	public void loadfromtextpane(ReloadApmlTreeEvent event)
	{
		ByteArrayInputStream bytes = event.getByteRef();

		Document document;

		Document manifestdocument;

		DefaultTreeModel model;

		DefaultMutableTreeNode root;

		ApmlJTreeNode apmlnode;

		DefaultMutableTreeNode manifestfoldernode;

		DefaultMutableTreeNode packagesnode;

		DefaultMutableTreeNode childnode;

		XPath xpath;

		NodeList nodes;

		//

		try
		{


			document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(bytes);

			manifestdocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(this.last_selected_manifest_file);

			xpath = XPathFactory.newInstance().newXPath();

			nodes = Xpathquick.evaluate(document, xpath, "/*");

			model = (DefaultTreeModel) this.getModel();

			root = (DefaultMutableTreeNode) model.getRoot();

			//

			root.removeAllChildren();

			model.reload();

			//

			apmlnode = new ApmlJTreeNode(document.createElement("apml"), true);

			manifestfoldernode = new DefaultMutableTreeNode("manifest", true);

			packagesnode = new DefaultMutableTreeNode("packages", true);

			//

			manifestfoldernode.setAllowsChildren(true);

			apmlnode.setAllowsChildren(true);

			packagesnode.setAllowsChildren(true);

			//

			childnode = new DefaultMutableTreeNode(nodes.item(0));

			childnode.setAllowsChildren(true);

			//

			model.insertNodeInto(manifestfoldernode, root, 0);

			model.insertNodeInto(apmlnode, root, 1);

			model.insertNodeInto(packagesnode, root, 2);

			//

			model.reload();

			//

			this.rloadfromnodelist(model, root, packagesnode, childnode, nodes, 0);

			//

			//

			ApmlJTreeNode manifestnode = new ApmlJTreeNode(this.last_selected_manifest_file.getName(), this.last_selected_manifest_file);

			model.insertNodeInto(manifestnode, manifestfoldernode, 0);

			//

			NodeList apmlxmldocumentnodelist;

			apmlxmldocumentnodelist = Xpathquick.evaluate(manifestdocument, xpath, "//document[@type='apml']");

			if (apmlxmldocumentnodelist.getLength() == 0)
			{
				System.out.println("No APML XML document found.");

				ApmlJTreeNode emptyxmlnode = new ApmlJTreeNode(document.createElement("empty"));

				model.insertNodeInto(emptyxmlnode, apmlnode, 0);
			}
			else
			{
				NodeList path;

				NodeList name;

				path = Xpathquick.evaluate(manifestdocument, xpath, "//document[@type='apml']/@path");

				name = Xpathquick.evaluate(manifestdocument, xpath, "//document[@type='apml']/@name");

				if (path.getLength() == 1 && name.getLength() == 1)
				{

					String directory = path.item(0).getNodeValue();

					String filename = name.item(0).getNodeValue();

					System.out.println("Oh look, APML XML document found.");

					//

					String filepath = directory + System.getProperty("file.separator") + filename;

					System.err.println("Filepath: " + filepath);

					File apmlxmlfile = new File(filepath);

					ApmlJTreeNode apmlxmlnode = new ApmlJTreeNode(apmlxmlfile.getName(), apmlxmlfile);

					model.insertNodeInto(apmlxmlnode, apmlnode, 0);
				}
				else
					System.out.println("Error in XML manifest file for APML :: file name and/or path not given.");
			}

			//

			for (int i = 0; i < this.getRowCount(); i++)
			{
				this.expandRow(i); //TODO fix this with persistent jtree restoration logic
			}

			//
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void loadfromfile(LoadApmlTreeEvent event)
	{
		File file = event.getFileRef();

		//

		this.last_selected_manifest_file = event.getFileRef();

		this.last_selected_basedir = new File(event.getFileRef().getParent());

		//

		Document document;

		DefaultTreeModel model;

		DefaultMutableTreeNode root;

		ApmlJTreeNode apmlnode;

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

			apmlnode = new ApmlJTreeNode(document.createElement("apml"), true);

			manifestnode = new DefaultMutableTreeNode("manifest", true);

			packagesnode = new DefaultMutableTreeNode("packages", true);

			//

			manifestnode.setAllowsChildren(true);

			packagesnode.setAllowsChildren(true);

			//

			childnode = new ApmlJTreeNode(nodes.item(0));

			childnode.setAllowsChildren(true);

			//

			model.insertNodeInto(manifestnode, root, 0);

			model.insertNodeInto(apmlnode, root, 1);

			model.insertNodeInto(packagesnode, root, 2);

			//

			this.rloadfromnodelist(model, root, packagesnode, childnode, nodes, 0);

			//

			ApmlJTreeNode apmlmanifestnode = new ApmlJTreeNode(file.getName(), file);

			model.insertNodeInto(apmlmanifestnode, manifestnode, 0);

			//TODO move out to load APML XML document subroutine

			NodeList apmlxmldocumentnodelist;

			apmlxmldocumentnodelist = Xpathquick.evaluate(document, xpath, "//document[@type='apml']");

			if (apmlxmldocumentnodelist.getLength() == 0)
			{
				System.out.println("No APML XML document found.");

				ApmlJTreeNode emptyxmlnode = new ApmlJTreeNode(document.createElement("empty"));

				model.insertNodeInto(emptyxmlnode, apmlnode, 0);
			}
			else
			{
				NodeList path;

				NodeList name;

				path = Xpathquick.evaluate(document, xpath, "//document[@type='apml']/@path");

				name = Xpathquick.evaluate(document, xpath, "//document[@type='apml']/@name");

				if (path.getLength() == 1 && name.getLength() == 1)
				{

					String directory = path.item(0).getNodeValue();

					String filename = name.item(0).getNodeValue();

					System.out.println("Oh look, APML XML document found.");

					//

					String filepath = directory + System.getProperty("file.separator") + filename;

					System.err.println("Filepath :" + filepath);

					File apmlxmlfile = new File(filepath);

					ApmlJTreeNode apmlxmlnode = new ApmlJTreeNode(apmlxmlfile.getName(), apmlxmlfile);

					model.insertNodeInto(apmlxmlnode, apmlnode, 0);
				}
				else
					System.out.println("Error in XML manifest file for APML :: file name and/or path not given.");


				//model.reload();
			}

			//TODO move out to load Manifest XML document subroutine
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	//
	private void rloadfromnodelist(DefaultTreeModel model, DefaultMutableTreeNode root /*root*/, DefaultMutableTreeNode parent /*packages*/, DefaultMutableTreeNode child /*project*/, NodeList children, Integer depth)
	{
		try
		{
			if (child == null)
				return;

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
}