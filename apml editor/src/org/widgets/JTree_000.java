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
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.regex.Pattern;


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

	public Integer selected_child_index;

	/**
	 * @param parent : The tree AWT object.
	 */
	public JTree_000(Component parent)
	{
		// setters

		this.setBackground(null);

		this.setLayout(new FlowLayout(FlowLayout.CENTER, 5,10));

		this.setCellRenderer(new JTreeTranslucentTransparentCellRenderer());

		// instantiation

		this.initTree();

		// hierarchy

		// devolvement

		this.parent = parent;

		this.setVisible(true);

		// listeners

		this.addTreeSelectionListener(new JTreeSelectionListener(this));

		this.addMouseListener(new JTreeMouseListener((this)));

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

		this.setBackground(null);

		this.setCellRenderer(new JTreeTranslucentTransparentCellRenderer());

		// instantiation

		this.initTree();

		// hierarchy

		// devolvement

		this.parent = parent;

		this.system = system;

		this.setVisible(true);

		// listeners

		this.addTreeSelectionListener(new JTreeSelectionListener(this));

		this.addMouseListener(new JTreeMouseListener((this)));

		// bodi

		Bodi.context("editor").put(this.bodi, this);
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

				treenode = new JTreeApmlNode(node);

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

class JTreeTransparentCellRenderer extends DefaultTreeCellRenderer
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
		return new Color(230,230,230);
	}

	@Override
	public Color getBackgroundSelectionColor()
	{
		return new Color(230,230,230);
	}
}

class JTreeTranslucentTransparentCellRenderer extends JTreeTransparentCellRenderer
{
	private final Color backgroundSelectionColor = new Color(55, 55, 55, 100);

	@Override
	public Color getBackgroundSelectionColor()
	{
		return backgroundSelectionColor;
	}
}

class JTreeApmlNode extends DefaultMutableTreeNode
{
	Integer my_index;

	public JTreeApmlNode(Node node)
	{
		super(node, false);
	}

	public JTreeApmlNode(Node node, Integer child_index)
	{
		super(node, false);

		this.my_index = child_index;
	}

	public JTreeApmlNode(Node node, Boolean allowsChildren)
	{
		super(node, allowsChildren);
	}

	public JTreeApmlNode(String object, Boolean allowsChildren)
	{
		super(object, allowsChildren);
	}

	@Override
	public String toString()
	{
		return ((Node)this.getUserObject()).getNodeName();
	}
}

class JTreeSelectionListener implements TreeSelectionListener
{
	public JTree tree;

	public JTreeSelectionListener(JTree tree)
	{
		this.tree = tree;
	}

	@Override
	public void valueChanged(TreeSelectionEvent event)
	{

		RSTextPane_000 textpane;

		textpane = (RSTextPane_000)Bodi.context("editor").pull("//ui/editor/rstextpane_000");

		//

		try
		{
			DefaultMutableTreeNode treenode;

			treenode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
		}
		catch(Exception e)
		{
			//
		}
	}
}

class JTreeMouseListener implements MouseListener
{
	public JTree_000 tree;

	public JTreeMouseListener(JTree_000 tree)
	{
		this.tree = tree;
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		DefaultMutableTreeNode selected;

		Enumeration<DefaultMutableTreeNode> siblings;

		String finalfullstring = "";

		String finalparentstring = "";

		//

		selected = (DefaultMutableTreeNode)this.tree.getLastSelectedPathComponent();

		if(selected!=null || selected.getParent()!=null)
		{

			siblings = selected.getParent().children();

			//

			if (selected == null || selected.getParent() == null)
			{
				this.tree.selected_child_index = 0;
			}
			else
			{
				for (int i = 0; i < selected.getParent().getChildCount(); i++)
				{
					if (selected.equals(selected.getParent().getChildAt(i)))
					{
						this.tree.selected_child_index = i;

						System.out.println("ith index is " + i);
					}
				}
			}

			//

			Point point = e.getPoint();

			TreePath path = this.tree.getClosestPathForLocation(point.x, point.y);

			String pathstring = "";

			//

			String[] tokens;

			pathstring = path.toString().replace("[", "");

			pathstring = pathstring.toString().replace("]", "");

			pathstring = pathstring.toString().replace("Design Area,", "");

			pathstring = pathstring.toString().replace("APML Projects,", "");

			pathstring = pathstring.toString().replace(" ", "");

			//

			tokens = new String[pathstring.split(",").length];

			tokens = pathstring.split(",");

			//

			for (int i = 0; i < tokens.length - 1; i++)
			{
				finalfullstring += "/" + tokens[i];
			}

			//

			ArrayList<DefaultMutableTreeNode> _siblings = new ArrayList<>();

			for (int i = 0; i < this.tree.selected_child_index; i++)
			{
				DefaultMutableTreeNode node = siblings.nextElement();

				_siblings.add(node);

				//System.out.println("Siblings: " + node);
			}

			//

			for (int i = 0; i < this.tree.selected_child_index; i++)
			{
				finalfullstring = finalfullstring + "/" + _siblings.get(i);
			}

			finalfullstring += "/" + selected.toString();

			System.out.println("Child token presumes its own CES line and begins: " + finalfullstring);

		}
		else
		{
			//
		}

		//

		RSTextPane_000 textpane;

		textpane = (RSTextPane_000) Bodi.context("editor").pull("//ui/editor/rstextpane_000");

		//

		String document = textpane.getText();

		BufferedReader reader = new BufferedReader(new StringReader(document));

		String line = "";

		Integer charcount = 0;

		Integer watched_index = 1;

		try
		{
			for (int i=0; (line = reader.readLine()) != null; i++)
			{
				charcount += line.length();

				//

				String watching = finalfullstring.split("/")[watched_index];

				if(watching.equals(""))
				{
					System.out.println("Correct line could not be found; unable to parse walkable tree path.");

					break;
				}

				//

				Pattern regex = Pattern.compile("<("+watching+")[(\\s.*)(>)]"); //(<jframe)[(\s.*)(>)]

				if(regex.matcher(line).find())
				{
					watched_index+=1;

					System.out.println("Matcher found: "+regex+" in "+line);

					if(watched_index==finalparentstring.split("/").length)
					{
						System.out.println("Correct parent line should be around "+i);
					}

					if(watched_index==finalfullstring.split("/").length)
					{
						System.out.println("Correct child line should be around "+i);

						break;
					}
				}
				else
				{
					System.out.println("Matcher did not find: "+regex+" in "+line);
				}
			}

			textpane.setCaretPosition(charcount);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) { }

	@Override
	public void mouseReleased(MouseEvent e) { }

	@Override
	public void mouseEntered(MouseEvent e) { }

	@Override
	public void mouseExited(MouseEvent e) { }
}
