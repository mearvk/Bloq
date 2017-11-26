package org.widgets;

import apml.system.Apmlbasesystem;
import apml.system.bodi.Bodi;
import apml.xpath.helpers.Xpathquick;
import org.custom.ui.ApmlJTreeNode;
import org.custom.ui.BodiJTreeNode;
import org.custom.ui.TranslucentJTreeCellRenderer;
import org.events.CloseBodiDocumentEvent;
import org.events.LoadBodiTreeEvent;
import org.listeners.BodiJTreeOnClickListener;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
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
public class JTree_Bodi_000 extends JTree
{
	public String bodi = "//editor/ui/jtree_bodi_000";

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
	public JTree_Bodi_000(Component parent)
	{
		// setters

		this.setBackground(null);

		this.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));

		this.setCellRenderer(new TranslucentJTreeCellRenderer());

		// instantiation

		this.init();

		// hierarchy

		// devolvement

		this.parent = parent;

		this.setVisible(true);

		// listeners

		this.addTreeSelectionListener(new BodiJTreeOnClickListener(this));

		this.addMouseListener(new BodiJTreeOnClickListener(this));

		// bodi

		Bodi.context("editor").put(this.bodi, this);
	}

	/**
	 * @param parent : The tree AWT object.
	 * @param system : The APML system object.
	 */
	public JTree_Bodi_000(Component parent, Apmlbasesystem system)
	{
		// setters

		this.setBackground(null);

		this.setCellRenderer(new TranslucentJTreeCellRenderer());

		// instantiation

		this.init();

		// hierarchy

		// devolvement

		this.parent = parent;

		this.system = system;

		this.setVisible(true);

		// listeners

		this.addTreeSelectionListener(new BodiJTreeOnClickListener(this));

		this.addMouseListener(new BodiJTreeOnClickListener(this));

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

			root.setUserObject("Bodi Informatics");

			model.reload();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void rloadfromnodelist(CloseBodiDocumentEvent event)
	{
		((DefaultMutableTreeNode) this.getModel().getRoot()).removeAllChildren();
	}

	public void rloadfromnodelist(LoadBodiTreeEvent event)
	{
		File file = event.getFileRef();

		Document document;

		DefaultTreeModel model;

		DefaultMutableTreeNode root;

		DefaultMutableTreeNode manifests;

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

			manifests = new DefaultMutableTreeNode("manifests", true);

			//

			model.insertNodeInto(manifests, root, 0);

			//

			this.rloadfromnodelist(event.getFileRef(), event, document, model, root, root, manifests, nodes, 0);

			//

			BodiJTreeNode filenode = new BodiJTreeNode(file.getName(), file);

			model.insertNodeInto(filenode, manifests, 0);

			//

			model.reload();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	//
	private void rloadfromnodelist(File file, LoadBodiTreeEvent event, Document document, DefaultTreeModel model, DefaultMutableTreeNode root, DefaultMutableTreeNode parent, DefaultMutableTreeNode manifest, NodeList children, Integer depth)
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

		for (int j = 0; j < files.length; j++)
		{
			System.out.println(files[j].getName());

			Node node = children.item(j);

			try
			{
				DefaultMutableTreeNode item;

				//

				if (files[j].getName().endsWith("txt"))
					continue;

				item = new BodiJTreeNode(document.createElement(files[j].getName()), files[j]);

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
			catch (Exception exception)
			{
				exception.printStackTrace();
			}
		}

		model.reload();
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

			if (current_object instanceof String)
			{
				System.out.println(current_object);
			}

			//

			else if (current_type == Node.TEXT_NODE)
			{
				//System.out.println("Removing carriage return: "+((Node)current_object).getNodeName()+" : "+((Node)current_object).getNodeValue().replace("\n","\\n"));

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