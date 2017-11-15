package org.custom.ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class AttributeSensitiveJTreeCellRenderer extends DefaultTreeCellRenderer
{

	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean exp, boolean leaf, int row, boolean hasFocus)
	{
		//

		if (value instanceof AttributeFolderJTreeNode || value instanceof AttributeLeafJTreeNode)
		{
			setOpenIcon(new ImageIcon("/Users/mrupplin/IdeaProjects/bloq/apml editor/src/org/widgets/images/orange_folder_open.png"));

			setClosedIcon(new ImageIcon("/Users/mrupplin/IdeaProjects/bloq/apml editor/src/org/widgets/images/orange_folder_closed.png"));

			//setLeafIcon("");
		}
		else
		{
			setOpenIcon(getDefaultOpenIcon());

			setClosedIcon(getDefaultClosedIcon());

			setLeafIcon(getDefaultLeafIcon());
		}

		super.getTreeCellRendererComponent(tree, value, sel, exp, leaf, row, hasFocus);

		//

		return this;
	}
}