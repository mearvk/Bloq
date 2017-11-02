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
public class JPanel_Tree_Sprung_000 extends JPanel
{

	public String bodi = "//editor/ui/jpanel_tree_sprung_000";

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

	//

	public Component parent;
	public Apmlbasesystem system;

	//

	public JTree_000 jtree_000;

	//

	public Image backgroundimage;
	public String backgroundimagename;

	//

	/**
	 * @param parent : The tree AWT object.
	 */
	public JPanel_Tree_Sprung_000(Component parent)
	{
		// setters

		this.setBackground(new Color(0xff,0xff,0xff));

		this.setLayout(new FlowLayout(FlowLayout.LEFT));

		this.backgroundimagename = "";

		// instantiation

		this.jtree_000 = new JTree_000(this);

		// hierarchy

		this.add(jtree_000);

		// devolvement

		this.parent = parent;

		this.setVisible(true);

		// listeners

		Bodi.context("editor").put(this.bodi, this);
	}

	/**
	 * @param parent : The tree AWT object.
	 */
	public JPanel_Tree_Sprung_000(Component parent, String bodi)
	{
		// setters

		this.setBackground(new Color(0xff,0xff,0xff));

		this.setLayout(new FlowLayout(FlowLayout.LEFT));

		this.backgroundimagename = "";

		this.bodi = bodi;

		// instantiation

		this.jtree_000 = new JTree_000(this);

		// hierarchy

		this.add(jtree_000);

		// devolvement

		this.parent = parent;

		this.setVisible(true);

		// listeners

		Bodi.context("editor").put(this.bodi, this);
	}

	/**
	 * @param parent : The tree AWT object.
	 * @param system : The APML system object.
	 */
	public JPanel_Tree_Sprung_000(Component parent, Apmlbasesystem system)
	{
		// setters

		this.setBackground(new Color(0xff,0xff,0xff));

		this.setLayout(new FlowLayout(FlowLayout.LEFT));

		this.backgroundimagename = "";

		// instantiation

		this.jtree_000 = new JTree_000(this);

		// hierarchy

		this.add(jtree_000);

		// devolvement

		this.parent = parent;

		this.system = system;

		this.setVisible(true);

		// listeners

		Bodi.context("editor").put(this.bodi, this);
	}

	/**
	 * @param parent : The tree AWT object.
	 * @param system : The APML system object.
	 */
	public JPanel_Tree_Sprung_000(Component parent, Apmlbasesystem system, String bodi)
	{
		// setters

		this.setBackground(new Color(0xff,0xff,0xff));

		this.setLayout(new FlowLayout(FlowLayout.LEFT));

		this.backgroundimagename = "";

		this.bodi = bodi;

		// instantiation

		this.jtree_000 = new JTree_000(this);

		// hierarchy

		this.add(jtree_000);

		// devolvement

		this.parent = parent;

		this.system = system;

		this.setVisible(true);

		// listeners

		Bodi.context("editor").put(this.bodi, this);
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		try
		{
			//this.backgroundimage = ImageIO.read(new File(backgroundimagename));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		//g.drawImage(backgroundimage, 0, 0, this);
	}

	public Dimension getPreferredSize()
	{
		return new Dimension(((int) (parent.getWidth() * 0.3) - this.marginleft), ((int) (parent.getHeight() * 1.0) - this.margintop));
	}
}
