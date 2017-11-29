package org.widgets;

import apml.system.Apmlbasesystem;
import apml.system.bodi.Bodi;
import apml.xpath.helpers.Xpathquick;
import org.custom.ui.*;
import org.events.CloseApmlDocumentEvent;
import org.events.LoadApmlTreeEvent;
import org.events.ReloadApmlTreeEvent;
import org.listeners.*;
import org.w3c.dom.*;

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
public class JTree_Apml_000 extends JTree_000
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

	/**
	 * @param parent : The tree AWT object.
	 */
	public JTree_Apml_000(Component parent)
	{
		super(parent);

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
		super(parent, system);

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

			root.setUserObject("APML Informatics");

			model.reload();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * @param event
	 */
	public void loadfrombytes(ReloadApmlTreeEvent event)
	{
		ByteArrayInputStream bytes = event.getByteRef();

		Document document = null;

		Document apmldocument = null;

		Document manifestdocument = null;

		DefaultTreeModel model;

		DefaultMutableTreeNode root;

		DefaultMutableTreeNode apmlfoldernode;

		DefaultMutableTreeNode manifestfoldernode;

		DefaultMutableTreeNode packagesfoldernode;

		XPath xpath;

		NodeList apmlnodes = null;

		NodeList manifestnodes = null;

		String doctypestring;

		xpath = XPathFactory.newInstance().newXPath();

		//

		try
		{
			//

			doctypestring = this.getdocumenttype(Xpathquick.evaluate(DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(bytes), xpath, "//project[@doctype]"));

			bytes.reset();

			//

			if (doctypestring.contains("apml"))
			{
				this.last_selected_apml_file_bytes = event.byteRef;

				//

				apmldocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(bytes);

				apmlnodes = Xpathquick.evaluate(apmldocument, xpath, "/*");

				bytes.reset();

				//

				if (this.last_selected_manifest_file_bytes != null)
				{
					manifestdocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(last_selected_manifest_file_bytes);

					manifestnodes = Xpathquick.evaluate(manifestdocument, xpath, "/*");

					last_selected_manifest_file_bytes.reset();
				}
				else if (this.last_selected_manifest_file != null)
				{
					manifestdocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(last_selected_manifest_file);

					manifestnodes = Xpathquick.evaluate(manifestdocument, xpath, "/*");
				}
				else
				{
					System.out.println("Unable to determine if there is an APML file for parsing; debug further.");

					//return;
				}
			}

			if (doctypestring.contains("manifest"))
			{
				this.last_selected_manifest_file_bytes = event.byteRef;

				//

				manifestdocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(bytes);

				manifestnodes = Xpathquick.evaluate(manifestdocument, xpath, "/*");

				bytes.reset();

				//

				if (this.last_selected_apml_file_bytes != null)
				{
					apmldocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(last_selected_apml_file_bytes);

					apmlnodes = Xpathquick.evaluate(apmldocument, xpath, "/*");

					last_selected_apml_file_bytes.reset();
				}
				else if (this.last_selected_apml_file != null)
				{
					apmldocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(last_selected_apml_file);

					apmlnodes = Xpathquick.evaluate(apmldocument, xpath, "/*");
				}
				else
				{
					System.out.println("Unable to determine if there is a Manifest file for parsing; debug further.");

					//return;
				}
			}

			//

			model = (DefaultTreeModel) this.getModel();

			root = (DefaultMutableTreeNode) model.getRoot();

			//

			root.removeAllChildren();

			model.reload();

			//

			apmlfoldernode = new DefaultMutableTreeNode("apml", true);

			manifestfoldernode = new DefaultMutableTreeNode("manifest", true);

			packagesfoldernode = new DefaultMutableTreeNode("packages", true);

			//

			apmlfoldernode.setAllowsChildren(true);

			manifestfoldernode.setAllowsChildren(true);

			packagesfoldernode.setAllowsChildren(true);

			//

			//childnode = new DefaultMutableTreeNode(nodes.item(0));

			//childnode.setAllowsChildren(true);

			//

			model.insertNodeInto(apmlfoldernode, root, 0);

			model.insertNodeInto(manifestfoldernode, root, 1);

			model.insertNodeInto(packagesfoldernode, root, 2);

			//

			model.reload();

			//

			if (apmlnodes != null)
				this.rloadfromnodelist(model, root, packagesfoldernode, null, apmlnodes, 0);

			if (manifestnodes != null)
				this.rloadfromnodelist(model, root, manifestfoldernode, null, manifestnodes, 0);

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

				model.insertNodeInto(emptyxmlnode, apmlfoldernode, 0);
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

					model.insertNodeInto(apmlxmlnode, apmlfoldernode, 0);

					//
				}
				else
					System.out.println("Error in XML manifest file for APML :: file name and/or path not given.");
			}

			//

			this.expandRow(1);

			this.expandRow(2);

			//
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 *
	 * @param event
	 */
	public void loadfromfile(LoadApmlTreeEvent event)
	{
		File file = event.getFileRef();

		Document document = null;

		Document apmldocument = null;

		Document manifestdocument = null;

		DefaultTreeModel model;

		DefaultMutableTreeNode root;

		DefaultMutableTreeNode apmlfoldernode;

		DefaultMutableTreeNode manifestfoldernode;

		DefaultMutableTreeNode packagesfoldernode;

		XPath xpath;

		NodeList apmlnodes = null;

		NodeList manifestnodes = null;

		//

		String doctypestring;

		xpath = XPathFactory.newInstance().newXPath();

		//

		try
		{
			//

			doctypestring = this.getdocumenttype(Xpathquick.evaluate(DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file), xpath, "//project[@doctype]"));

			//

			if (doctypestring.contains("apml"))
			{
				this.last_selected_apml_file = event.getFileRef();

				//

				apmldocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);

				apmlnodes = Xpathquick.evaluate(apmldocument, xpath, "/*");

				//

				if (this.last_selected_manifest_file_bytes != null)
				{
					manifestdocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(this.last_selected_manifest_file_bytes);

					manifestnodes = Xpathquick.evaluate(manifestdocument, xpath, "/*");

					this.last_selected_manifest_file_bytes.reset();
				}
				else if (this.last_selected_manifest_file != null)
				{
					manifestdocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(last_selected_manifest_file);

					manifestnodes = Xpathquick.evaluate(manifestdocument, xpath, "/*");
				}
				else
				{
					System.out.println("Unable to determine if there is a Manifest file for parsing; debug further.");

					//return;
				}
			}

			if (doctypestring.contains("manifest"))
			{
				this.last_selected_manifest_file = event.getFileRef();

				//

				manifestdocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(last_selected_manifest_file);

				manifestnodes = Xpathquick.evaluate(manifestdocument, xpath, "/*");

				//

				if (this.last_selected_apml_file_bytes != null)
				{
					apmldocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(last_selected_apml_file_bytes);

					apmlnodes = Xpathquick.evaluate(apmldocument, xpath, "/*");

					last_selected_apml_file_bytes.reset();
				}
				else if (this.last_selected_apml_file != null)
				{
					apmldocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(last_selected_apml_file);

					apmlnodes = Xpathquick.evaluate(apmldocument, xpath, "/*");
				}
				else
				{
					System.out.println("Unable to determine if there is an APML file for parsing; debug further.");

					//return;
				}
			}

			//

			model = (DefaultTreeModel) this.getModel();

			root = (DefaultMutableTreeNode) model.getRoot();

			//

			root.removeAllChildren();

			model.reload();

			//

			apmlfoldernode = new DefaultMutableTreeNode("apml", true);

			manifestfoldernode = new DefaultMutableTreeNode("manifest", true);

			packagesfoldernode = new DefaultMutableTreeNode("packages", true);

			//

			apmlfoldernode.setAllowsChildren(true);

			manifestfoldernode.setAllowsChildren(true);

			packagesfoldernode.setAllowsChildren(true);

			//

			//childnode = new DefaultMutableTreeNode(nodes.item(0));

			//childnode.setAllowsChildren(true);

			//

			model.insertNodeInto(apmlfoldernode, root, 0);

			model.insertNodeInto(manifestfoldernode, root, 1);

			model.insertNodeInto(packagesfoldernode, root, 2);

			//

			model.reload();

			//

			if (apmlnodes != null)
				this.rloadfromnodelist(model, root, packagesfoldernode, null, apmlnodes, 0);

			if (manifestnodes != null)
				this.rloadfromnodelist(model, root, manifestfoldernode, null, manifestnodes, 0);

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

				model.insertNodeInto(emptyxmlnode, apmlfoldernode, 0);
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

					model.insertNodeInto(apmlxmlnode, apmlfoldernode, 0);

					//

					Xpathquick.evaluate(manifestdocument, xpath, "/*");

					//

					apmldocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(apmlxmlfile);

					apmlnodes = Xpathquick.evaluate(apmldocument, xpath, "/*");

					if (apmlnodes != null)
						this.rloadfromnodelist(model, root, packagesfoldernode, null, apmlnodes, 0);
				}
				else
					System.out.println("Error in XML manifest file for APML :: file name and/or path not given.");
			}

			//

			this.expandRow(1);

			this.expandRow(2);

			//
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 *
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