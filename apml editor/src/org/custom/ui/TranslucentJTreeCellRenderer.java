package org.custom.ui;

import java.awt.*;

public class TranslucentJTreeCellRenderer extends TransparentJTreeCellRenderer
{
	private final Color backgroundSelectionColor = new Color(55, 55, 55, 100);

	@Override
	public Color getBackgroundSelectionColor()
	{
		return backgroundSelectionColor;
	}
}
