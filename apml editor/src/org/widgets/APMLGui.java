package org.widgets;

import apml.system.Apmlbasesystem;
import apml.system.bodi.Bodi;

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
public class APMLGui extends JFrame
{

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
	public JSplitPane importref_017;

	//

	public Component parent;
	public Apmlbasesystem system;

	//

	public JMenuBar_000 jmenubar_000;
	public JTabbedPane_000 jtabbedpane_000;
	public JPanel_002 jpanel_002;

	//

	public JPanel_Backboard_For_Apml jpanel_backboard_for_apml;
	public JPanel_Backboard_For_Bloq jpanel_backboard_for_bloq;
	public JPanel_Backboard_For_Bodi jpanel_backboard_for_bodi;
	public JPanel_Backboard_For_Falthruu jpanel_backboard_for_falthruu;
	public JPanel_Backboard_For_Munction jpanel_backboard_for_munction;
	public JPanel_Backboard_For_Runyn jpanel_backboard_for_runyn;
	public JPanel_Backboard_For_Sprung jpanel_backboard_for_sprung;

	//

	public JPanel selected_panel;

	//

	public String bodi = "//editor/ui/apmlgui";

	//

	//
	public APMLGui()
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

					UIManager.put("nimbusBase", new Color(0x91,0x91,0x91)); //menuitems, jfilechooser

					UIManager.put("nimbusBlueGrey", new Color(0xd1,0xd1,0xd1)); //jmenubar

					UIManager.put("control", new Color(0xdf,0xdf,0xdf)); //jframe background

					UIManager.put("nimbusFocus", new Color(0xff,0xff,0xff, Color.TRANSLUCENT));

					UIManager.put("nimbusSelectionBackground", Color.LIGHT_GRAY);

					UIManager.put("TabbedPane.background", Color.DARK_GRAY);

					//

					ImageIcon open=null;

					ImageIcon closed=null;

					ImageIcon leaf=null;

					try
					{
						open = new ImageIcon("/Users/mrupplin/IdeaProjects/bloq/apml editor/src/org/widgets/images/folder_icon_open_20x20.png");

						closed = new ImageIcon("/Users/mrupplin/IdeaProjects/bloq/apml editor/src/org/widgets/images/folder_icon_closed_20x20.png");

						leaf = new ImageIcon("/Users/mrupplin/IdeaProjects/bloq/apml editor/src/org/widgets/images/java_icon.png");
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}

					if(leaf!=null)
						UIManager.put("Tree.leafIcon", leaf);

					if(open!=null)
						UIManager.put("Tree.openIcon", open);

					if(closed!=null)
						UIManager.put("Tree.closedIcon", closed);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}

				new APMLGui(null);
			}
		});
	}

	//
	public APMLGui(Component parent)
	{
		// setters

		this.setBounds(0, 0, 1920, 1200);

		this.setLayout(new FlowLayout(FlowLayout.CENTER));

		this.setTitle("Kuanta :: Apml Style Editor");

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// instantiation

		this.jmenubar_000 = new JMenuBar_000(this);

		this.jtabbedpane_000 = new JTabbedPane_000(this);

		this.jpanel_002 = new JPanel_002(this);

		//

		this.jpanel_backboard_for_apml = new JPanel_Backboard_For_Apml(this);

		this.jpanel_backboard_for_bloq = new JPanel_Backboard_For_Bloq(this);

		this.jpanel_backboard_for_bodi = new JPanel_Backboard_For_Bodi(this);

		this.jpanel_backboard_for_falthruu = new JPanel_Backboard_For_Falthruu(this);

		this.jpanel_backboard_for_munction = new JPanel_Backboard_For_Munction(this);

		this.jpanel_backboard_for_runyn = new JPanel_Backboard_For_Runyn(this);

		this.jpanel_backboard_for_sprung = new JPanel_Backboard_For_Sprung(this);

		// hierarchy

		this.setJMenuBar(jmenubar_000);

		this.add(jtabbedpane_000);

		this.add(jpanel_backboard_for_apml);

		this.add(new JScrollPane_000(jpanel_002));

		// devolvement

		this.parent = parent;

		this.selected_panel = this.jpanel_backboard_for_apml;

		this.setVisible(true);

		// listeners

		// bodi

		Bodi.context("editor").put(this.bodi,this);
	}

	/**
	 * @param parent : The tree AWT object.
	 * @param system : The APML system object.
	 */
	public APMLGui(Component parent, Apmlbasesystem system)
	{
		// setters

		this.setBounds(150, 50, 1200, 800);

		this.setLayout(new FlowLayout(FlowLayout.CENTER));

		this.setTitle("Kuanta :: Apml Style Editor");

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// instantiation

		this.jmenubar_000 = new JMenuBar_000(this);

		this.jtabbedpane_000 = new JTabbedPane_000(this);

		this.jpanel_002 = new JPanel_002(this);

		//

		this.jpanel_backboard_for_apml = new JPanel_Backboard_For_Apml(this);

		this.jpanel_backboard_for_bloq = new JPanel_Backboard_For_Bloq(this);

		this.jpanel_backboard_for_bodi = new JPanel_Backboard_For_Bodi(this);

		this.jpanel_backboard_for_falthruu = new JPanel_Backboard_For_Falthruu(this);

		this.jpanel_backboard_for_munction = new JPanel_Backboard_For_Munction(this);

		this.jpanel_backboard_for_runyn = new JPanel_Backboard_For_Runyn(this);

		this.jpanel_backboard_for_sprung = new JPanel_Backboard_For_Sprung(this);

		// hierarchy

		this.setJMenuBar(jmenubar_000);

		this.add(jtabbedpane_000);

		this.add(jpanel_backboard_for_apml);

		this.add(new JScrollPane_000(jpanel_002));

		// devolvement

		this.selected_panel = this.jpanel_backboard_for_apml;

		this.parent = parent;

		this.system = system;

		this.setVisible(true);

		// listeners

		// bodi

		Bodi.context("editor").put(this.bodi,this);
	}

	public static void main(String... args)
	{
		new APMLGui(null);
	}

}
