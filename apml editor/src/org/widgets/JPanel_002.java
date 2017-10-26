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
public class JPanel_002 extends JPanel
{

	public String bodi = "//ui/editor/jpanel_003";

	//

	public Integer marginleft = 30;
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

	public JLabel status;

	/**
	 * @param parent : The tree AWT object.
	 */
	public JPanel_002(Component parent)
	{
		// setters

		this.setBackground(new Color(110,96,113));

		this.setLayout(new FlowLayout(FlowLayout.LEFT));

		this.setForeground(Color.GRAY);

		// instantiation

		this.status = new JLabel("Status: APML Editor 1.0");

		this.status.setForeground(Color.WHITE);

		this.status.setFont(new Font("Courier", Font.PLAIN, 12));

		this.status.setBounds(0,15,this.status.getWidth(), this.status.getHeight());

		// hierarchy

		this.add(status);

		// devolvement

		this.parent = parent;

		this.setVisible(true);

		// listeners
	}

	/**
	 * @param parent : The tree AWT object.
	 * @param system : The APML system object.
	 */
	public JPanel_002(Component parent, Apmlbasesystem system)
	{
		// setters

		this.setBackground(new Color(110,96,113));

		this.setLayout(new FlowLayout(FlowLayout.LEFT));

		this.setForeground(Color.GRAY);

		// instantiation

		this.status = new JLabel("Status: APML Editor 1.0");

		this.status.setForeground(Color.WHITE);

		this.status.setFont(new Font("Courier", Font.PLAIN, 12));

		this.status.setBounds(0,5,this.status.getWidth(), this.status.getHeight());

		// hierarchy

		this.add(status);

		// devolvement

		this.parent = parent;

		this.system = system;

		this.setVisible(true);

		// listeners
	}

	@Override
	public Dimension getPreferredSize()
	{
		return new Dimension(((int) (parent.getWidth() * 1.0) - this.marginleft), 35);
	}
}
