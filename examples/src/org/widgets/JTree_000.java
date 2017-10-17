package org.widgets;

import apml.system.Apmlbasesystem;
import apml.system.bodi.Bodi;
import apml.xpath.helpers.Xpathquick;
import org.events.LoadApmlDocumentEvent;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.URL;


/**
 * Software programmatically produced by Bloq implementation version 1.05 - Bodi Remote Version
 *
 * @author
 * @see
 * @since
 */
public class JTree_000 extends JTree
{
	public String bodi = "//ui/editor/jtree_000";

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

	/**
	 * @param parent : The parent AWT object.
	 */
	public JTree_000(Component parent)
	{
		// setters

		this.setBackground(null);

		this.setCellRenderer(new TranslucentTreeCellRenderer());

		// instantiation

		this.initTree();

		// hierarchy

		// devolvement

		this.parent = parent;

		this.setVisible(true);

		// listeners

		// bodi

		Bodi.context("editor").put(this.bodi, this);
	}

	/**
	 * @param parent : The parent AWT object.
	 * @param system : The APML system object.
	 */
	public JTree_000(Component parent, Apmlbasesystem system)
	{
		// setters

		this.setBackground(null);

		this.setCellRenderer(new TranslucentTreeCellRenderer());

		// instantiation

		this.initTree();

		// hierarchy

		// devolvement

		this.parent = parent;

		this.system = system;

		this.setVisible(true);

		// listeners

		// bodi

		Bodi.context("editor").put(this.bodi, this);
	}

	public void initTree()
	{
		DefaultTreeModel model;

		model = (DefaultTreeModel)this.getModel();

		DefaultMutableTreeNode root;

		root = (DefaultMutableTreeNode)model.getRoot();

		//
	}

	public void updateTree(LoadApmlDocumentEvent event)
	{
		File file = event.file;

		Document document;

		XPath xpath;

		NodeList nodes;

		try
		{
			document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);

			xpath = XPathFactory.newInstance().newXPath();

			nodes = Xpathquick.evaluate(document, xpath, "/*");

			//

			DefaultTreeModel model = (DefaultTreeModel)this.getModel();

			//

			((DefaultMutableTreeNode)model.getRoot()).removeAllChildren();

			//

			DefaultMutableTreeNode root = ((DefaultMutableTreeNode)model.getRoot());

			root.setUserObject("Design Area");

			//

			DefaultMutableTreeNode treenode = new DefaultMutableTreeNode("Project Area", true);

			//

			this.updateTree(model, root, root, treenode, nodes, 0);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	//
	private void updateTree(DefaultTreeModel model, DefaultMutableTreeNode root, DefaultMutableTreeNode parent, DefaultMutableTreeNode child, NodeList children, Integer depth)
	{
		try
		{
			if(child==null) return;

			//

			if(parent==null) return;

			//

			model.insertNodeInto(child, parent, depth);

			//

			for(int i = 0; i<children.getLength(); i++)
			{
				DefaultMutableTreeNode treenode;

				treenode = new DefaultMutableTreeNode(children.item(i));

				treenode.setAllowsChildren(true);

				//

				model.insertNodeInto(treenode, child, i);

				//

				updateTree(model, root, child, treenode, children.item(i).getChildNodes(), i);
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

class TransparentTreeCellRenderer extends DefaultTreeCellRenderer
{
	private final Color ALPHA_OF_ZERO = new Color(0, true);

	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean isSelected, boolean expanded, boolean leaf, int row, boolean hasFocus)
	{
		JComponent c = (JComponent) super.getTreeCellRendererComponent(tree, value, isSelected, expanded, leaf, row, hasFocus);
		c.setOpaque(false);
		return c;
	}

	@Override
	public Color getBackgroundNonSelectionColor()
	{
		return ALPHA_OF_ZERO;
	}

	@Override
	public Color getBackgroundSelectionColor()
	{
		return ALPHA_OF_ZERO;
	}
}

class TranslucentTreeCellRenderer extends TransparentTreeCellRenderer
{
	private final Color backgroundSelectionColor = new Color(100, 100, 255, 100);

	@Override
	public Color getBackgroundSelectionColor()
	{
		return backgroundSelectionColor;
	}
}

class APMLJTreeNode extends DefaultMutableTreeNode
{
	public APMLJTreeNode(Node node, Boolean children)
	{
		super(node, children);
	}

	public APMLJTreeNode(String object, Boolean children)
	{
		super(object, children);
	}

	@Override
	public String toString()
	{
		return ((Node)this.getUserObject()).getNodeName();
	}
}
