package org.custom.ui;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

class TransparentJTreeCellRenderer extends DefaultTreeCellRenderer
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
