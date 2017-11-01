package org.widgets;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class JTabbedPaneJPanel extends JPanel
{
	public Image backgroundimage;

	public String backgroundimagename;

	public Component parent;

	public JTabbedPaneJPanel(Component parent)
	{
		this.backgroundimagename = "/Users/mrupplin/IdeaProjects/bloq/apml editor/src/org/widgets/images/kuanta_bg.png";

		this.setBackground(new Color(80,80, 80));

		this.parent = parent;
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		try
		{
			this.backgroundimage = ImageIO.read(new File(backgroundimagename));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		g.drawImage(backgroundimage, 0, 0, this);
	}

	@Override
	public Dimension getPreferredSize()
	{
		return new Dimension(this.parent.getWidth(), this.parent.getHeight());
	}
}