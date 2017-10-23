package org.widgets;

import apml.system.Apmlbasesystem;
import apml.system.bodi.Bodi;
import apml.xpath.helpers.Xpathquick;
import org.events.CloseApmlDocumentEvent;
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

		this.setLayout(new FlowLayout(FlowLayout.CENTER, 5,10));

		this.setCellRenderer(new TranslucentTreeCellRenderer());

		// instantiation

		this.initTree();

		// hierarchy

		// devolvement

		this.parent = parent;

		this.setVisible(true);

		// listeners

		// bodi

		try
		{
			Bodi.context("editor").put(this.bodi, this);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
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

		try
		{
			Bodi.context("editor").put(this.bodi, this);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void initTree()
	{
		DefaultMutableTreeNode root = ((DefaultMutableTreeNode)this.getModel().getRoot());

		DefaultTreeModel model = (DefaultTreeModel)this.getModel();

		Document document;

		Node node;

		//

		try
		{
			root.removeAllChildren();

			root.setUserObject("Design Area");

			model.reload();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void updatetree(CloseApmlDocumentEvent event)
	{
		((DefaultMutableTreeNode)this.getModel().getRoot()).removeAllChildren();
	}

	public void updatetree(LoadApmlDocumentEvent event)
	{
		File file = event.file;

		Document document;

		DefaultTreeModel model;

		DefaultMutableTreeNode root;

		DefaultMutableTreeNode treenode;

		XPath xpath;

		NodeList nodes;

		//

		try
		{
			document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);

			xpath = XPathFactory.newInstance().newXPath();

			nodes = Xpathquick.evaluate(document, xpath, "/*");

			model = (DefaultTreeModel)this.getModel();

			root = (DefaultMutableTreeNode)model.getRoot();

			//

			treenode = new DefaultMutableTreeNode("APML Projects", true);

			//

			model.insertNodeInto(treenode, root, 0);

			//

			this.updatetree(model, root, root, treenode, nodes, 0);

			//

			model.reload();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	//
	private void updatetree(DefaultTreeModel model, DefaultMutableTreeNode root, DefaultMutableTreeNode parent, DefaultMutableTreeNode child, NodeList children, Integer depth)
	{
		try
		{
			if(child==null) return;

			//

			if(parent==null) return;

			//

			for(int i = 0; i<children.getLength(); i++)
			{
				Node node = children.item(i);

				//

				DefaultMutableTreeNode treenode;

				treenode = new APMLJTreeNode(node);

				treenode.setAllowsChildren(true);

				//

				model.insertNodeInto(treenode, child, i);

				//

				NodeList rawnodes = children.item(i).getChildNodes();

				updatetree(model, root, child, treenode, rawnodes, depth+1);

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
		DefaultMutableTreeNode root = (DefaultMutableTreeNode)this.getModel().getRoot();

		DefaultTreeModel model = (DefaultTreeModel)this.getModel();

		Enumeration children = root.breadthFirstEnumeration();

		//

		DefaultMutableTreeNode current_child;

		Object current_object;

		Short current_type;

		ArrayList<DefaultMutableTreeNode> remove = new ArrayList<>(500);

		for(;children.hasMoreElements();)
		{
			current_child = (DefaultMutableTreeNode) children.nextElement();

			current_object = current_child.getUserObject();

			current_type = current_object instanceof Node ? ((Node)current_object).getNodeType() : -1;

			//

			if(current_object instanceof String)
			{
				System.out.println(current_object);
			}

			//

			else if(current_type==Node.TEXT_NODE)
			{
				//System.out.println("Removing carriage return: "+((Node)current_object).getNodeName()+" : "+((Node)current_object).getNodeValue().replace("\n","\\n"));

				remove.add(current_child);
			}
		}

		//

		for(int i=0; i<remove.size(); i++)
		{
			model.removeNodeFromParent(remove.get(i));
		}

		//

		model.reload();
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
		return new Color(120,106,123);
	}

	@Override
	public Color getBackgroundSelectionColor()
	{
		return new Color(120,106,123);
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
	public APMLJTreeNode(Node node)
	{
		super(node, false);
	}

	public APMLJTreeNode(Node node, Boolean allowsChildren)
	{
		super(node, allowsChildren);
	}

	public APMLJTreeNode(String object, Boolean allowsChildren)
	{
		super(object, allowsChildren);
	}

	@Override
	public String toString()
	{
		return ((Node)this.getUserObject()).getNodeName();
	}
}
