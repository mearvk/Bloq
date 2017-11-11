package org.widgets;

import apml.system.Apmlbasesystem;
import apml.system.bodi.Bodi;
import org.quran.widgets.JPanel_Backboard_For_Quran;

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

	public JPanel_Backboard_For_Quran jpanel_backboard_for_quran;

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

					//UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");

					//UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());

					//UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

					//

					UIManager.put("nimbusBase", new Color(0x61, 0x61, 0x61)); //menuitems, jfilechooser

					UIManager.put("nimbusBlueGrey", new Color(0xd1, 0xd1, 0xd1)); //jmenubar

					UIManager.put("control", new Color(0xdf, 0xdf, 0xdf)); //jframe background

					UIManager.put("nimbusFocus", new Color(0xff, 0xff, 0xff, Color.TRANSLUCENT));

					UIManager.put("nimbusSelectionBackground", new Color(0x61, 0x61, 0x61));

					//

					UIManager.getLookAndFeelDefaults().put("TabbedPane:TabbedPaneTab[Enabled].backgroundPainter", new BackgroundPainter(new Color(0xac,0xac,0xac))); //86,198,192

					UIManager.getLookAndFeelDefaults().put("TabbedPane:TabbedPaneTab[Enabled+MouseOver].backgroundPainter", new BackgroundPainter(new Color(0x9c,0x9c,0x9c))); //52, 168, 171

					UIManager.getLookAndFeelDefaults().put("TabbedPane:TabbedPaneTab[Enabled+Pressed].backgroundPainter", new BackgroundPainter(new Color(0x4f, 0x5a, 0x5a)));

					UIManager.getLookAndFeelDefaults().put("TabbedPane:TabbedPaneTab[Enabled+Selected].backgroundPainter", new BackgroundPainter(new Color(0x4f, 0x5a, 0x5a)));



					UIManager.getLookAndFeelDefaults().put("TabbedPane:TabbedPaneTab[Focused+MouseOver+Selected].backgroundPainter", new BackgroundPainter(new Color(0x9c, 0x9c, 0x9c))); //56, 160, 161

					UIManager.getLookAndFeelDefaults().put("TabbedPane:TabbedPaneTab[Focused+Pressed+Selected].backgroundPainter", new BackgroundPainter(new Color(56, 160, 161)));

					UIManager.getLookAndFeelDefaults().put("TabbedPane:TabbedPaneTab[Focused+Selected].backgroundPainter", new BackgroundPainter(new Color(62, 172, 176)));


					UIManager.getLookAndFeelDefaults().put("TabbedPane:TabbedPaneTab[MouseOver+Selected].backgroundPainter", new BackgroundPainter(new Color(0xfa,0xfa,0xfa)));

					UIManager.getLookAndFeelDefaults().put("TabbedPane:TabbedPaneTab[Pressed+Selected].backgroundPainter", new BackgroundPainter(new Color(0xfa,0xfa,0xfa)));

					UIManager.getLookAndFeelDefaults().put("TabbedPane:TabbedPaneTab[Selected].backgroundPainter", new BackgroundPainter(new Color(0xfa,0xfa,0xfa)));


					//

					UIManager.getLookAndFeelDefaults().put("TabbedPane:TabbedPaneTab[Enabled].textForeground", Color.BLACK);

					UIManager.getLookAndFeelDefaults().put("TabbedPane:TabbedPaneTab[Enabled+Selected].textForeground", Color.WHITE);

					UIManager.getLookAndFeelDefaults().put("TabbedPane:TabbedPaneTab[Focused+Selected].textForeground", Color.WHITE);

					//

					ImageIcon open = null;

					ImageIcon closed = null;

					ImageIcon leaf = null;

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

					//if (leaf != null)
						//UIManager.put("Tree.leafIcon", leaf);

					//if (open != null)
						//UIManager.put("Tree.openIcon", open);

					//if (closed != null)
						//UIManager.put("Tree.closedIcon", closed);
				}
				catch (Exception e)
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

		//

		this.jpanel_backboard_for_quran = new JPanel_Backboard_For_Quran(this);

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

		Bodi.context("editor").put(this.bodi, this);
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

		//

		this.jpanel_backboard_for_quran = new JPanel_Backboard_For_Quran(this);

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

		Bodi.context("editor").put(this.bodi, this);
	}

	public static void main(String... args)
	{
		new APMLGui(null);
	}

}

class BackgroundPainter implements Painter<JComponent>
{
	private Color color = null;

	BackgroundPainter(Color c)
	{
		color = c;
	}

	@Override
	public void paint(Graphics2D g, JComponent object, int width, int height) {

		if (color != null)
		{
			g.setColor(color);

			g.fillRect(0, 0, width - 1, height - 1);

			/*for(int i=0; i<height-20; i++)
			{
				Color newcolor = new Color(color.getRed()-(3*i), color.getGreen()-(3*i), color.getBlue()-(3*i));

				g.setColor(newcolor);

				g.drawLine(8,11-i, width-8, 11-i);
			}*/

			/*for(int i=0; i<height/2; i++)
			{
				int red = (color.getRed()-(1*i)) < 0 ? 0 : color.getRed()-(1*i);

				int green = (color.getGreen()-(1*i)) < 0 ? 0 : color.getGreen()-(1*i);

				int blue = (color.getBlue()-(1*i)) < 0 ? 0 : color.getBlue()-(1*i);

				Color newcolor = new Color(red, green, blue);

				g.setColor(newcolor);

				g.drawLine(0,i, width-1, height-1);
			}*/

			for(int i=height; i>0; i--)
			{
				int red = (color.getRed()-(1*i)) < 0 ? 0 : color.getRed()-(1*i);

				int green = (color.getGreen()-(1*i)) < 0 ? 0 : color.getGreen()-(1*i);

				int blue = (color.getBlue()-(1*i)) < 0 ? 0 : color.getBlue()-(1*i);

				Color newcolor = new Color(red, green, blue);

				g.setColor(newcolor);

				g.drawLine(0, i, width-1, height-i);
			}

			//

			g.setColor(Color.WHITE.darker()); //top

			g.drawLine(0,height-1,width-1,height-1);

			//

			g.setColor(Color.LIGHT_GRAY); //left

			g.drawLine(0,0,0,height-1);

			//

			g.setColor(Color.GRAY); //right

			g.drawLine(width,0,width,height-1);

			//

			g.setColor(Color.GRAY); //bottom

			g.drawLine(2,1,width-2,1);

			//

			g.setColor(Color.LIGHT_GRAY);

			g.fillRect(2,height-6,2,4); //highlight 1

			//

			g.fillRect(5,height-6,2,4); //highlight 2

			//

			g.setColor(new Color(45,180,190).darker()); //bottom

			g.fillRect(0,height-16,1,13);

			g.setColor(new Color(45,180,190).darker().darker()); //bottom

			g.fillRect(0,12,1,10);

			g.setColor(new Color(45,180,190)); //bottom

			g.fillRect(0,2,1,6);

			g.fillRect(width,2,1,6);
		}
	}
}