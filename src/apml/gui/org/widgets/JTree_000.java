package apml.gui.org.widgets;

import apml.system.Apmlsystem;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.URL;


/**
 * Bloq software & design courtesy of Max Rupplin. All rights reserved. 2017 AD Earth, Gregorian.
 * APML software & design courtesy of Max Rupplin. All rights reserved. 2017 AD Earth, Gregorian.
 * Sprung software & design courtesy of Max Rupplin. All rights reserved. 2017 AD Earth, Gregorian.
 * <p>
 * Software programmatically produced by Bloq implementation version 1.0
 * <p>
 * Hello and thanks to and from the Church of Scientology now in her final Holy Form. /mr /ok /ss
 * Hello and thanks to and from the HUC, Holy Unified Church, (the Church of "Hi, Hello") and welcome now in her lesser manner. /mr /ok /ss
 * <p>
 * <p>
 * <p>
 * <p>
 * //hi hello
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
		/* ------------------  setters  ---------------- */

		this.setCellRenderer(new TransparentTreeCellRenderer());

		this.setBackground(Color.LIGHT_GRAY);
	
        /* ------------------  instantiation  ---------------- */
	
        /* ------------------  hierarchy  -------------------- */
	
        /* ------------------  devolvement  -------------------- */

		this.parent = parent;

		this.setVisible(true);
	
        /* ------------------  listeners  -------------------- */

	}

	/**
	 * @param parent : The parent AWT object.
	 * @param system : The APML system object.
	 */
	public JTree_000(Component parent, Apmlsystem system)
	{
        /* ------------------  setters  ---------------- */
	
        /* ------------------  instantiation  ---------------- */
	
        /* ------------------  hierarchy  -------------------- */
	
        /* ------------------  devolvement  -------------------- */

		this.parent = parent;

		this.system = system;

		this.setVisible(true);
	
        /* ------------------  listeners  -------------------- */

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
