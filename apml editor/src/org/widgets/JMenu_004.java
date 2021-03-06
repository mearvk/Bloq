package org.widgets;

import apml.system.Apmlbasesystem;

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
public class JMenu_004 extends JMenu
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
	public Apmlbasesystem system;


	public JMenuItem_012 jmenuitem_012;

	public JMenuItem_013 jmenuitem_013;

	public JMenuItem_014 jmenuitem_014;

	public JMenuItem_007 jmenuitem_007;

	public JMenuItem_008 jmenuitem_008;

	public JMenuItem_009 jmenuitem_009;

	public JMenuItem_010 jmenuitem_010;

	/**
	 * @param parent : The tree AWT object.
	 */
	public JMenu_004(Component parent)
	{
		// setters

		this.setText("  Analyze  ");

		this.setMnemonic(KeyEvent.VK_A);

		// instantiation

		this.jmenuitem_012 = new JMenuItem_012(this);

		this.jmenuitem_013 = new JMenuItem_013(this);

		this.jmenuitem_014 = new JMenuItem_014(this);

		this.jmenuitem_007 = new JMenuItem_007(this);

		this.jmenuitem_008 = new JMenuItem_008(this);

		this.jmenuitem_009 = new JMenuItem_009(this);

		this.jmenuitem_010 = new JMenuItem_010(this);

		// hierarchy

		this.add(jmenuitem_012);

		this.add(jmenuitem_013);

		this.add(jmenuitem_014);

		this.add(jmenuitem_007);

		this.add(jmenuitem_008);

		this.add(jmenuitem_009);

		this.add(jmenuitem_010);

		// devolvement

		this.parent = parent;

		this.setVisible(true);

		// listeners

	}
	/**
	 * @param parent : The tree AWT object.
	 * @param system : The APML system object.
	 */
	public JMenu_004(Component parent, Apmlbasesystem system)
	{
		// setters

		this.setText("  Analyze  ");

		this.setMnemonic(KeyEvent.VK_A);

		// instantiation

		this.jmenuitem_012 = new JMenuItem_012(this);

		this.jmenuitem_013 = new JMenuItem_013(this);

		this.jmenuitem_014 = new JMenuItem_014(this);

		this.jmenuitem_007 = new JMenuItem_007(this);

		this.jmenuitem_008 = new JMenuItem_008(this);

		this.jmenuitem_009 = new JMenuItem_009(this);

		this.jmenuitem_010 = new JMenuItem_010(this);

		// hierarchy

		this.add(jmenuitem_012);

		this.add(jmenuitem_013);

		this.add(jmenuitem_014);

		this.add(jmenuitem_007);

		this.add(jmenuitem_008);

		this.add(jmenuitem_009);

		this.add(jmenuitem_010);

		// devolvement

		this.parent = parent;

		this.system = system;

		this.setVisible(true);

		// listeners

	}

}
