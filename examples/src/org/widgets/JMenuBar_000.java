package org.widgets;

import apml.system.Apmlsystem;

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
public class JMenuBar_000 extends JMenuBar
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
	JMenu_000 jmenu_000;
	JMenu_001 jmenu_001;


	/**
	 * @param parent : The parent AWT object.
	 */
	public JMenuBar_000(Component parent)
	{
		// setters

		// instantiation

		this.jmenu_000 = new JMenu_000(this);

		this.jmenu_001 = new JMenu_001(this);

		// hierarchy

		this.add(jmenu_000);

		this.add(jmenu_001);

		// devolvement

		this.parent = parent;

		this.setVisible(true);

		// listeners

	}

	/**
	 * @param parent : The parent AWT object.
	 * @param system : The APML system object.
	 */
	public JMenuBar_000(Component parent, Apmlsystem system)
	{
		// setters

		// instantiation

		this.jmenu_000 = new JMenu_000(this);

		this.jmenu_001 = new JMenu_001(this);

		// hierarchy

		this.add(jmenu_000);

		this.add(jmenu_001);

		// devolvement

		this.parent = parent;

		this.system = system;

		this.setVisible(true);

		// listeners

	}
}
