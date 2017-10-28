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
	public Component parent;
	public Apmlbasesystem system;
	public JSplitPane importref_017;

	public JMenuBar_000 jmenubar_000;
	public JTabbedPane_000 jtabbedpane_000;
	public JSplitPane_000 jsplitpane_000;
	public JPanel_002 jpanel_002;

	public String bodi = "//editor/ui/apmlgui";

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

					//

					ImageIcon icon=null;

					try
					{
						icon = new ImageIcon("/Users/mrupplin/IdeaProjects/bloq/apml editor/src/org/widgets/images/base_folder_sm.png");
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}

					if(icon!=null)
						UIManager.put("Tree.leafIcon", icon);

					if(icon!=null)
						UIManager.put("Tree.openIcon", icon);

					if(icon!=null)
						UIManager.put("Tree.closedIcon", icon);
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

		//

		this.setBounds(150, 50, 1200, 800);

		this.setLayout(new FlowLayout(FlowLayout.CENTER));

		this.setTitle("APML Editor");

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// instantiation

		this.jmenubar_000 = new JMenuBar_000(this);

		this.jtabbedpane_000 = new JTabbedPane_000(this);

		this.jsplitpane_000 = new JSplitPane_000(this, JSplitPane.HORIZONTAL_SPLIT, new JPanel_000(this), new JPanel_001(this));

		this.jpanel_002 = new JPanel_002(this);

		// hierarchy

		this.setJMenuBar(jmenubar_000);

		this.add(jtabbedpane_000);

		this.add(jsplitpane_000);

		this.add(jpanel_002);

		// devolvement

		this.parent = parent;

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

		//

		this.setBounds(150, 50, 1200, 800);

		this.setLayout(new FlowLayout(FlowLayout.CENTER));

		this.setTitle("APML Editor");

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// instantiation

		this.jmenubar_000 = new JMenuBar_000(this);

		this.jtabbedpane_000 = new JTabbedPane_000(this);

		this.jsplitpane_000 = new JSplitPane_000(this, system, JSplitPane.HORIZONTAL_SPLIT, new JPanel_000(this), new JPanel_001(this));

		this.jpanel_002 = new JPanel_002(this);

		// hierarchy

		this.setJMenuBar(jmenubar_000);

		this.add(jtabbedpane_000);

		this.add(jsplitpane_000);

		this.add(jpanel_002);

		// devolvement

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
