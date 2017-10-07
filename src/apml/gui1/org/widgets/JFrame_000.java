package apml.gui1.org.widgets;

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

public class JFrame_000 extends JFrame
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
	JMenuBar_000 jmenubar_000;
	JPanel_000 jpanel_000;

	/**
	 * @param parent : The parent AWT object.
	 */
	public JFrame_000(Component parent)
	{
		/* ------------------  setters  ---------------- */

		this.setBounds(50, 50, 800, 600);

		this.setLayout(new FlowLayout(FlowLayout.LEFT));

		this.setTitle("APML Editor");

        /* ------------------  instantiation  ---------------- */

		this.jmenubar_000 = new JMenuBar_000(this);

		this.jpanel_000 = new JPanel_000(this);

        /* ------------------  hierarchy  -------------------- */

		this.setJMenuBar(jmenubar_000);

		this.add(jpanel_000);

        /* ------------------  devolvement  -------------------- */

		this.parent = parent;

		this.setVisible(true);

        /* ------------------  listeners  -------------------- */

	}


	/**
	 * @param parent : The parent AWT object.
	 * @param system : The APML system object.
	 */
	public JFrame_000(Component parent, Apmlsystem system)
	{
        /* ------------------  setters  ---------------- */

		this.setBounds(50, 50, 800, 600);

		this.setLayout(new FlowLayout(FlowLayout.LEFT));

		this.setTitle("APML Editor");

        /* ------------------  instantiation  ---------------- */

		this.jmenubar_000 = new JMenuBar_000(this);

		this.jpanel_000 = new JPanel_000(this);

        /* ------------------  hierarchy  -------------------- */

		this.setJMenuBar(jmenubar_000);

		this.add(jpanel_000);

        /* ------------------  devolvement  -------------------- */

		this.parent = parent;

		this.system = system;

		this.setVisible(true);

        /* ------------------  listeners  -------------------- */

	}

	public static void main(String... args)
	{
		new JFrame_000(null);
	}

}
