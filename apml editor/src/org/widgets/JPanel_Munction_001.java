package org.widgets;

import apml.system.Apmlbasesystem;
import apml.system.bodi.Bodi;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.text.html.HTMLDocument;
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
public class JPanel_Munction_001 extends JPanel
{

	public String bodi = "//editor/ui/jpanel_001";

	public HTMLDocument document;

	public JEditorPane_000 editorpane;

	public RSTextPane_000 rstextpane;

	public RTextScrollPane_000 rstextscrollpane;

	//

	public Integer marginleft = 10;
	public Integer margintop = 10;
	public Integer marginright = 10;
	public Integer marginbottom = 10;

	//

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

	/**
	 * @param parent : The tree AWT object.
	 */
	public JPanel_Munction_001(Component parent)
	{
		this.setLayout(new FlowLayout());

		// setters

		this.setBackground(new Color(0xff, 0xff, 0xff));

		// instantiation

		this.rstextpane = new RSTextPane_000(this);

		this.rstextscrollpane = new RTextScrollPane_000(this);

		//

		this.rstextscrollpane.setViewportView(this.rstextpane);

		this.rstextpane.setscrollpane(this.rstextscrollpane);

		this.rstextscrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		// hierarchy

		this.add(this.rstextscrollpane);

		// devolvement

		this.parent = parent;

		this.setVisible(true);

		// listeners

		// bodi

		Bodi.context("editor").put(this.bodi, this);
	}

	/**
	 * @param parent : The tree AWT object.
	 */
	public JPanel_Munction_001(Component parent, String bodi)
	{
		// setters

		this.setBackground(new Color(0xff, 0xff, 0xff));

		this.setLayout(new FlowLayout());

		this.bodi = bodi;

		// instantiation

		this.rstextpane = new RSTextPane_000(this);

		this.rstextscrollpane = new RTextScrollPane_000(this);

		//

		this.rstextscrollpane.setViewportView(this.rstextpane);

		this.rstextpane.setscrollpane(this.rstextscrollpane);

		this.rstextscrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		// hierarchy

		this.add(this.rstextscrollpane);

		// devolvement

		this.parent = parent;

		this.setVisible(true);

		// listeners

		// bodi

		Bodi.context("editor").put(this.bodi, this);
	}

	/**
	 * @param parent : The tree AWT object.
	 * @param system : The APML system object.
	 */
	public JPanel_Munction_001(Component parent, Apmlbasesystem system)
	{
		// setters

		this.setBackground(new Color(0xff, 0xff, 0xff));

		this.setLayout(new FlowLayout());

		// instantiation

		this.rstextpane = new RSTextPane_000(this);

		this.rstextscrollpane = new RTextScrollPane_000(this);

		//

		this.rstextscrollpane.setViewportView(this.rstextpane);

		this.rstextpane.setscrollpane(this.rstextscrollpane);

		this.rstextscrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		// hierarchy

		this.add(this.rstextscrollpane);

		// devolvement

		this.parent = parent;

		this.system = system;

		this.setVisible(true);

		// listeners

		// bodi

		Bodi.context("editor").put(this.bodi, this);
	}

	/**
	 * @param parent : The tree AWT object.
	 * @param system : The APML system object.
	 */
	public JPanel_Munction_001(Component parent, Apmlbasesystem system, String bodi)
	{
		// setters

		this.setBackground(new Color(0xff, 0xff, 0xff));

		this.setLayout(new FlowLayout());

		this.bodi = bodi;

		// instantiation

		this.rstextpane = new RSTextPane_000(this);

		this.rstextscrollpane = new RTextScrollPane_000(this);

		//

		this.rstextscrollpane.setViewportView(this.rstextpane);

		this.rstextpane.setscrollpane(this.rstextscrollpane);

		this.rstextscrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		// hierarchy

		this.add(this.rstextscrollpane);

		// devolvement

		this.parent = parent;

		this.system = system;

		this.setVisible(true);

		// listeners

		// bodi

		Bodi.context("editor").put(this.bodi, this);
	}


	@Override
	public Dimension getPreferredSize()
	{
		Dimension simple = new Dimension(((int) (parent.getWidth() * 0.7) - this.marginleft), ((int) (parent.getHeight() * 1.0) - this.margintop));

		return simple;
	}


	@Override
	public Dimension getMinimumSize()
	{
		return new Dimension(200, ((int) (parent.getHeight() * 1.0) - this.margintop));
	}
}
