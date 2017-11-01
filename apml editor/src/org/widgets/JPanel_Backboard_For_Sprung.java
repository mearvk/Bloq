package org.widgets;

import apml.system.Apmlbasesystem;
import apml.system.bodi.Bodi;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
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
public class JPanel_Backboard_For_Sprung extends JPanel
{

	public Integer marginleft = 10;
	public Integer margintop = 10;
	public Integer marginright = 10;
	public Integer marginbottom = 10;
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

	//

	JTree_000 jtree_000;

	//

	public JSplitPane_000 jsplitpane_000;

	//

	public Image backgroundimage;
	public String backgroundimagename;

	//

	public JPanel_Backboard_For_Sprung(Component parent)
	{

		this.backgroundimagename = "";

		// setters

		this.setBackground(new Color(0xff,0xff,0xff));

		this.setLayout(new FlowLayout(FlowLayout.CENTER));

		// instantiation

		this.jsplitpane_000 = new JSplitPane_000(this, JSplitPane.HORIZONTAL_SPLIT, new JPanel_000(this), new JPanel_001(this));

		// hierarchy

		this.add(jsplitpane_000);

		// devolvement

		this.parent = parent;

		this.setVisible(true);

		// listeners

		// bodi

		Bodi.context("editor").put("//editor/ui/jpanel_backboard_for_sprung", this);
	}

	//

	public JPanel_Backboard_For_Sprung(Component parent, Apmlbasesystem system)
	{

		this.backgroundimagename = "";

		// setters

		this.setBackground(new Color(0xff,0xff,0xff));

		this.setLayout(new FlowLayout(FlowLayout.CENTER));

		// instantiation

		this.jsplitpane_000 = new JSplitPane_000(this, JSplitPane.HORIZONTAL_SPLIT, new JPanel_000(this), new JPanel_001(this));

		// hierarchy

		this.add(jsplitpane_000);

		// devolvement

		this.parent = parent;

		this.system = system;

		this.setVisible(true);

		// listeners

		// bodi

		Bodi.context("editor").put("//editor/ui/jpanel_backboard_for_sprung", this);
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		try
		{
			//this.backgroundimage = ImageIO.read(new File("/Users/mrupplin/IdeaProjects/bloq/apml editor/src/org/widgets/images/hexagon_wallpaper.jpg"));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		//g.drawImage(backgroundimage, 0, 0, this);
	}

	public Dimension getPreferredSize()
	{
		return new Dimension(((int) (parent.getWidth() * 1.0) - this.marginleft), ((int) (parent.getHeight() * 1.0) - this.margintop));
	}
}
