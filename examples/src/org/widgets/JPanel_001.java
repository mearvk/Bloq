package org.widgets;

import apml.system.Apmlbasesystem;

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
public class JPanel_001 extends JPanel
{

	public String bodi = "//ui/editor/jpanel_001";

	public HTMLDocument document;

	public JEditorPane_000 editorpane;

	//

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

	/**
	 * @param parent : The parent AWT object.
	 */
	public JPanel_001(Component parent)
	{
		// setters

		this.setBackground(new Color(229, 229, 229));

		// instantiation

		this.editorpane = new JEditorPane_000(this);

		// hierarchy

		this.add(editorpane);

		// devolvement

		this.parent = parent;

		this.setVisible(true);

		// listeners

	}

	/**
	 * @param parent : The parent AWT object.
	 * @param system : The APML system object.
	 */
	public JPanel_001(Component parent, Apmlbasesystem system)
	{
		// setters

		this.setBackground(new Color(229, 229, 229));

		// instantiation

		this.editorpane = new JEditorPane_000(this, system);

		// hierarchy

		this.add(editorpane);

		// devolvement

		this.parent = parent;

		this.system = system;

		this.setVisible(true);

		// listeners

	}

	@Override
	public Dimension getPreferredSize()
	{
		return new Dimension(((int) (parent.getWidth() * 0.7) - this.marginleft), ((int) (parent.getHeight() * 1.0) - this.margintop));
	}

	@Override
	public Dimension getMinimumSize()
	{
		return new Dimension( 200, ((int) (parent.getHeight() * 1.0) - this.margintop));
	}
}
