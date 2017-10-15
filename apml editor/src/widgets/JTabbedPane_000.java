package widgets;

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
public class JTabbedPane_000 extends JTabbedPane
{
	public Image backgroundimage;
	public String backgroundimagename;

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
	public Apmlsystem system;

	/**
	 * @param parent : The parent AWT object.
	 */
	public JTabbedPane_000(Component parent)
	{
		// setters

		//this.backgroundimagename = "/Users/mrupplin/IdeaProjects/bloq/examples/src/org/widgets/tabbedpane_bg_1.png";

		//this.setBackground(Color.DARK_GRAY);

		// instantiation

		// hierarchy

		//this.a

		this.addTab("◎ Apml", new JTabbedPaneJPanel(this));

		this.addTab("◎ Bloq", new JTabbedPaneJPanel(this));

		this.addTab("◎ Bodi", new JTabbedPaneJPanel(this));

		this.addTab("◎ Runyn", null);

		this.addTab("◎ Sprung", new JTabbedPaneJPanel(this));

		this.addTab("◎ Falthruu", new JTabbedPaneJPanel(this));

		this.addTab("::", null);

		//

		this.setEnabledAt(this.indexOfTab("::"), false);

		//

		this.addTab("◎ Radio", new JTabbedPaneJPanel(this));

		this.addTab("◎ Messenger", new JTabbedPaneJPanel(this));

		this.addTab("◎ Email", new JTabbedPaneJPanel(this));


		// devolvement

		this.parent = parent;

		this.setVisible(true);

		// listeners

	}

	/**
	 * @param parent : The parent AWT object.
	 * @param system : The APML system object.
	 */
	public JTabbedPane_000(Component parent, Apmlsystem system)
	{
		// setters

		//this.backgroundimagename = "/Users/mrupplin/IdeaProjects/bloq/examples/src/org/widgets/tabbedpane_bg_1.png";

		this.setBackground(Color.DARK_GRAY);

		// instantiation

		// hierarchy

		this.addTab("◎ Apml", new JLabel(new ImageIcon(this.backgroundimagename)));

		this.addTab("◎ Bloq", null);

		this.addTab("◎ Bodi", null);

		this.addTab("◎ Runyn", null);

		this.addTab("◎ Sprung", null);

		this.addTab("◎ Falthruu", null);

		this.addTab("::", null);

		//

		this.setEnabledAt(this.indexOfTab("::"), false);

		//

		this.addTab("◎ Radio", null);

		this.addTab("◎ Messenger", null);

		this.addTab("◎ Email", null);

		// devolvement

		this.parent = parent;

		this.system = system;

		this.setVisible(true);

		// listeners

	}

	public Dimension getPreferredSize()
	{
		return new Dimension(((int) (parent.getWidth() * 1.0) - this.marginleft), 100);
	}
}
