package org.widgets;

import apml.helpers.Filegrepper;
import apml.system.Apmlbasesystem;
import apml.system.bodi.Bodi;
import apml.xpath.helpers.Xpathquick;
import org.custom.ui.*;
import org.events.CloseApmlDocumentEvent;
import org.events.LoadBloqTreeEvent;
import org.helpers.TreeExpansionUtil;
import org.listeners.*;
import org.system.ModelInterfaceSystem;
import org.w3c.dom.*;

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
public class JTree_Bloq_000 extends JTree_000
{
	public String bodi = "//editor/ui/jtree_bloq_000";

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

	/**
	 * @param parent : The tree AWT object.
	 */
	public JTree_Bloq_000(Component parent)
	{
		super(parent);

		// setters

		this.setBackground(new Color(213, 213, 213));

		this.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));

		this.setCellRenderer(new TranslucentJTreeCellRenderer());

		this.setCellRenderer(new AttributeSensitiveJTreeCellRenderer());

		// instantiation

		this.init();

		// hierarchy

		// devolvement

		this.parent = parent;

		this.setVisible(true);

		// listeners

		this.addTreeSelectionListener(new BloqJTreeOnClickListener(this));

		this.addMouseListener(new BloqJTreeOnClickListener(this));

		// bodi

		Bodi.context("editor").put(this.bodi, this);
	}

	/**
	 * @param parent : The tree AWT object.
	 * @param system : The APML system object.
	 */
	public JTree_Bloq_000(Component parent, Apmlbasesystem system)
	{
		super(parent, system);

		// setters

		this.setBackground(new Color(213, 213, 213));

		this.setCellRenderer(new TranslucentJTreeCellRenderer());

		this.setCellRenderer(new AttributeSensitiveJTreeCellRenderer());

		// instantiation

		this.init();

		// hierarchy

		// devolvement

		this.parent = parent;

		this.system = system;

		this.setVisible(true);

		// listeners

		this.addTreeSelectionListener(new BloqJTreeOnClickListener(this));

		this.addMouseListener(new BloqJTreeOnClickListener(this));

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

			root.setUserObject("Bloq Informatics");

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

	public void loadfromfile(LoadBloqTreeEvent event)
	{
		File file = event.getFileRef();

		Document document;

		DefaultTreeModel model;

		DefaultMutableTreeNode root;

		BloqJTreeNode bloqnode;

		DefaultMutableTreeNode manifestnode;

		DefaultMutableTreeNode packagesnode;

		DefaultMutableTreeNode attributesnode;

		DefaultMutableTreeNode childnode;

		XPath xpath;

		//

		TreeExpansionUtil util = new TreeExpansionUtil(this);

		String state = util.getExpansionState();

		//

		NodeList nodes;

		//

		try
		{
			document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);

			xpath = XPathFactory.newInstance().newXPath();

			nodes = Xpathquick.evaluate(document, xpath, "//project/packages/package[@name]");

			model = (DefaultTreeModel) this.getModel();

			root = (DefaultMutableTreeNode) model.getRoot();

			//

			bloqnode = new BloqJTreeNode("☬ Bloq view", true);

			manifestnode = new DefaultMutableTreeNode("☬ Manifest view", true);

			packagesnode = new DefaultMutableTreeNode("☬ Packages view", true);

			//

			manifestnode.setAllowsChildren(true);

			packagesnode.setAllowsChildren(true);

			//

			childnode = new ApmlJTreeNode(nodes.item(0));

			childnode.setAllowsChildren(true);

			//

			manifestnode.insert(new DefaultMutableTreeNode("manifest.xml"), manifestnode.getChildCount() == 0 ? 0 : manifestnode.getChildCount() - 1);

			//

			model.insertNodeInto(bloqnode, root, 0);

			model.insertNodeInto(manifestnode, root, 1);

			model.insertNodeInto(packagesnode, root, 2);

			//

			NodeList basedirnodelist;

			basedirnodelist = Xpathquick.evaluate(document, xpath, "//project/packages[@basedir]");

			//

			NodeList packagesnodelist;

			packagesnodelist = Xpathquick.evaluate(document, xpath, "//project/packages/package");

			//

			String basedirstring;

			if (basedirnodelist.getLength() == 0)
			{
				System.out.println("Bloq manifest document did not have a basedir value; debug before continuing.");

				return;
			}

			if (basedirnodelist.getLength() == 1)
			{
				System.out.println("Bloq manifest document did have a basedir value; checked value was/is '" + ((Element) basedirnodelist.item(0)).getAttribute("basedir") + "' .");
			}

			if (basedirnodelist.getLength() > 1)
			{
				System.out.println("Bloq manifest document has ambiguous basedir value; debug before continuing.");

				return;
			}

			//

			System.out.println(basedirnodelist.item(0).getNodeType());

			basedirstring = ((Element) basedirnodelist.item(0)).getAttribute("basedir");

			File basedir = new File(basedirstring + "/source");

			this.rloadfromnodelist(basedir, event, document, model, root, packagesnode, childnode, nodes, 0);

			//

			BloqJTreeNode bloqfilenode = new BloqJTreeNode(file.getName(), file);

			model.insertNodeInto(bloqfilenode, bloqnode, 0);

			//
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	//
	private void rloadfromnodelist(File file, LoadBloqTreeEvent event, Document document, DefaultTreeModel model, DefaultMutableTreeNode root, DefaultMutableTreeNode parent, DefaultMutableTreeNode manifest, NodeList children, Integer depth)
	{
		try
		{
			if (manifest == null)
				return;

			if (parent == null)
				return;

			//

			DefaultMutableTreeNode item_previous = null;

			//

			File[] files = file.isDirectory() ? file.listFiles() : new File(file.getParent()).listFiles();

			//

			for(int j=0; j<files.length; j++)
			{
				//System.out.println(files[j].getName());

				Node node = children.item(j);

				try
				{
					DefaultMutableTreeNode item;

					//

					if (files[j].getName().endsWith("txt"))
						continue; //TODO outsource for checking against formal manifest file value

					item = new BloqJTreeNode(document.createElement(files[j].getName()), files[j]);

					item.setAllowsChildren(true);

					//

					model.insertNodeInto(item, parent, parent.getChildCount() == 0 ? 0 : parent.getChildCount() - 1);

					//

					if (files[j].isDirectory())
					{
						this.rloadfromnodelist(files[j], event, document, model, root, item, manifest, children, depth);
					}

					//
				}
				catch (DOMException dom_exception)
				{
					//
				}
				catch(Exception exception)
				{
					exception.printStackTrace();
				}
			}

			model.reload();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}