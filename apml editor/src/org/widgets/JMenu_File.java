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
public class JMenu_File extends JMenu
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

	//

	public Component parent;
	public Apmlbasesystem system;

	//

	public JMenuItem_File_Open jmenuitem_000;
	public JMenuItem_File_Close jmenuitem_001;
	public JMenuItem_File_Save jmenuitem_018;
	public JMenuItem_File_SaveAll jmenuitem_016;
	public JMenuItem_File_Exit jmenuitem_019;
	/**
	 * @param parent : The tree AWT object.
	 */
	public JMenu_File(Component parent)
	{
		// setters

		this.setText("  File  ");

		this.setMnemonic(KeyEvent.VK_F);

		// instantiation

		this.jmenuitem_000 = new JMenuItem_File_Open(this);

		this.jmenuitem_001 = new JMenuItem_File_Close(this);

		this.jmenuitem_018 = new JMenuItem_File_Save(this);

		this.jmenuitem_016 = new JMenuItem_File_SaveAll(this);

		this.jmenuitem_019 = new JMenuItem_File_Exit(this);

		// hierarchy

		this.add(jmenuitem_000);

		this.add(jmenuitem_001);

		this.add(jmenuitem_018);

		this.add(jmenuitem_016);

		this.add(jmenuitem_019);

		// devolvement

		this.parent = parent;

		this.setVisible(true);

		// listeners

	}
	/**
	 * @param parent : The tree AWT object.
	 * @param system : The APML system object.
	 */
	public JMenu_File(Component parent, Apmlbasesystem system)
	{
		// setters

		this.setText("  File  ");

		this.setMnemonic(KeyEvent.VK_F);

		// instantiation

		this.jmenuitem_000 = new JMenuItem_File_Open(this);

		this.jmenuitem_001 = new JMenuItem_File_Close(this);

		this.jmenuitem_018 = new JMenuItem_File_Save(this);

		this.jmenuitem_016 = new JMenuItem_File_SaveAll(this);

		this.jmenuitem_019 = new JMenuItem_File_Exit(this);

		// hierarchy

		this.add(jmenuitem_000);

		this.add(jmenuitem_001);

		this.add(jmenuitem_018);

		this.add(jmenuitem_016);

		this.add(jmenuitem_019);

		// devolvement

		this.parent = parent;

		this.system = system;

		this.setVisible(true);

		// listeners

	}
}
