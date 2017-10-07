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


/**
 * Bloq software & design courtesy of Max Rupplin. All rights reserved. 2017 AD Earth, Gregorian.
 * APML software & design courtesy of Max Rupplin. All rights reserved. 2017 AD Earth, Gregorian.
 * Sprung software & design courtesy of Max Rupplin. All rights reserved. 2017 AD Earth, Gregorian.
 * <p>
 * Software programmatically produced by Bloq implementation version 1.0
 * <p>
 * Hello and thanks to and from the Church of Scientology now in her final Holy Form. /mr /ok /ss
 * Hello and thanks to and from the HUC, Holy Unified Church, (the Church of "Hi, Hello") and welcome now in her lesser manner. /mr /ok /ss
 * <p>
 * <p>
 * <p>
 * <p>
 * //hi hello
 */
public class JPanel_000 extends JPanel
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
	JPanel_001 jpanel_001;
	JPanel_002 jpanel_002;

	/**
	 * @param parent : The parent AWT object.
	 */
	public JPanel_000(Component parent)
	{
		/* ------------------  setters  ---------------- */

		this.setBackground(Color.LIGHT_GRAY);

		this.setLayout(new FlowLayout(FlowLayout.LEFT));

		this.setBorder(new EmptyBorder(10, 10, 10, 10));

        /* ------------------  instantiation  ---------------- */

		this.jpanel_001 = new JPanel_001(this);

		this.jpanel_002 = new JPanel_002(this);

        /* ------------------  hierarchy  -------------------- */

		this.add(jpanel_001);

		this.add(jpanel_002);

        /* ------------------  devolvement  -------------------- */

		this.parent = parent;

		this.setVisible(true);

        /* ------------------  listeners  -------------------- */

	}


	/**
	 * @param parent : The parent AWT object.
	 * @param system : The APML system object.
	 */
	public JPanel_000(Component parent, Apmlsystem system)
	{
        /* ------------------  setters  ---------------- */

		this.setBackground(Color.LIGHT_GRAY);

		this.setLayout(new FlowLayout(FlowLayout.LEFT));

        /* ------------------  instantiation  ---------------- */

		this.jpanel_001 = new JPanel_001(this);

		this.jpanel_002 = new JPanel_002(this);

        /* ------------------  hierarchy  -------------------- */

		this.add(jpanel_001);

		this.add(jpanel_002);

        /* ------------------  devolvement  -------------------- */

		this.parent = parent;

		this.system = system;

		this.setVisible(true);

        /* ------------------  listeners  -------------------- */

	}

	public Dimension getPreferredSize()
	{
		return new Dimension((int) (parent.getWidth() * 1.0) - 10, (int) (parent.getHeight() * 1.0) - 54);
	}

}
