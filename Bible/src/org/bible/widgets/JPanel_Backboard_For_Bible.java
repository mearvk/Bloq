package org.bible.widgets;

import apml.system.Apmlbasesystem;
import apml.system.bodi.Bodi;
import org.widgets.JPanel_000;
import org.widgets.JPanel_001;
import org.widgets.JPanel_002;
import org.widgets.JSplitPane_000;

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
public class JPanel_Backboard_For_Bible extends JPanel
{

	public String bodi = "//editor/ui/jpanel_backboard_for_bible";

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

	public JSplitPane_000 jsplitpane_000;
	public Image backgroundimage;
	public String backgroundimagename;

	//

	JPanel_000 jpanel_000;

	//

	JPanel_001 jpanel_001;
	JPanel_002 jpanel_002;

	//

	public JPanel_Backboard_For_Bible(Component parent)
	{
		// setters

		this.setBackground(new Color(223, 223, 223));

		this.setLayout(new FlowLayout(FlowLayout.CENTER));

		this.backgroundimagename = "";

		// instantiation

		this.jsplitpane_000 = new JSplitPane_000(this, JSplitPane.HORIZONTAL_SPLIT, new JPanel_Tree_Bible_000(this, "//editor/ui/jpanel_bible_000"), new JPanel_Bible_001(this, "//editor/ui/jpanel_bible_001"));

		// hierarchy

		this.add(jsplitpane_000);

		// devolvement

		this.parent = parent;

		this.setVisible(true);

		// listeners

		// bodi

		Bodi.context("editor").put(this.bodi, this);
	}

	//

	public JPanel_Backboard_For_Bible(Component parent, Apmlbasesystem system)
	{
		// setters

		this.setBackground(new Color(223, 223, 223));

		this.setLayout(new FlowLayout(FlowLayout.CENTER));

		this.backgroundimagename = "";

		// instantiation

		this.jsplitpane_000 = new JSplitPane_000(this, JSplitPane.HORIZONTAL_SPLIT, new JPanel_Tree_Bible_000(this, "//editor/ui/jpanel_bible_000"), new JPanel_Bible_001(this, "//editor/ui/jpanel_bible_001"));

		// hierarchy

		this.add(jsplitpane_000);

		// devolvement

		this.parent = parent;

		this.system = system;

		this.setVisible(true);

		// listeners

		// bodi

		Bodi.context("editor").put(this.bodi, this);
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
	}

	public Dimension getPreferredSize()
	{
		return new Dimension(((int) (parent.getWidth() * 1.0) - this.marginleft), ((int) (parent.getHeight() * 1.0) - this.margintop));
	}
}
