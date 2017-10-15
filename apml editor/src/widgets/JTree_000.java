package widgets;

import apml.system.Apmlsystem;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;


/**
 * Software programmatically produced by Bloq implementation version 1.05 - Bodi Remote Version
 *
 * @author
 * @see
 * @since
 */
public class JTree_000 extends JTree
{

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
	public Apmlsystem system;

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

	}

	/**
	 * @param parent : The parent AWT object.
	 * @param system : The APML system object.
	 */
	public JTree_000(Component parent, Apmlsystem system)
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

	}

	public void initTree()
	{
		DefaultTreeModel model = (DefaultTreeModel)this.getModel();

		DefaultMutableTreeNode root = (DefaultMutableTreeNode)model.getRoot();

		//

		root.removeAllChildren();

		root.setUserObject("APML Design");
	}

	public void updateTree(DefaultMutableTreeNode base, ArrayList<DefaultMutableTreeNode> nodes)
	{
		DefaultTreeModel model = (DefaultTreeModel)this.getModel();

		DefaultMutableTreeNode root = (DefaultMutableTreeNode)model.getRoot();

		//

		for(int i=0; i<nodes.size(); i++)
		{
			model.insertNodeInto(nodes.get(i), base, i);
		}

		model.insertNodeInto(base, root, root.getChildCount());

		//

		model.reload();

		model.reload(root);
	}

	public void updateTree(ArrayList<DefaultMutableTreeNode> nodes)
	{
		DefaultTreeModel model = (DefaultTreeModel)this.getModel();

		DefaultMutableTreeNode root = (DefaultMutableTreeNode)model.getRoot();

		//

		for(int i=0; i<nodes.size(); i++)
		{
			model.insertNodeInto(nodes.get(i), root, i);
		}

		//

		model.reload();

		model.reload(root);
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
