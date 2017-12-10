package org.widgets;

import apml.system.Apmlbasesystem;
import apml.system.bodi.Bodi;
import org.bible.widgets.JPanel_Backboard_For_Bible;
import org.quran.widgets.JPanel_Backboard_For_Quran;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.plaf.FontUIResource;
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

	//

	public Component parent;

	public Apmlbasesystem system;

	//

	public JMenuBar jmenubar_000;

	public JPanel_002 jpanel_002;

	public JTabbedPane_000 jtabbedpane_000;

	//

	public ApmlBackboard apml_board;

	public BloqBackboard bloq_board;

	public BodiBackboard bodi_board;

	public FalthruuBackboard falthruu_board;

	public MunctionBackboard munction_board;

	public RunynBackboard runyn_board;

	public SprungBackboard sprung_board;

	//

	public JPanel_Backboard_For_Bible jpanel_backboard_for_bible;

	//

	public JPanel_Backboard_For_Quran jpanel_backboard_for_quran;

	//

	public JPanel selected_panel;

	//

	public String bodi = "//editor/ui/apmlgui";

	//

	public static void main(String... args)
	{
		new APMLGui(null);
	}

	//
	public APMLGui()
	{
		APMLGui apmlgui;

		apmlgui = new APMLGui(null);

		apmlgui.selected_panel = apmlgui.apml_board;
	}

	//
	public APMLGui(Component parent)
	{
		// init

		this.initguisettings();

		// setters

		this.setBounds(0, 0, 1920, 1200);

		this.setLayout(new FlowLayout(FlowLayout.CENTER));

		this.setTitle("Quanta :: Apml Style Editor");

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		// instantiation

		this.jmenubar_000 = new JMenuBar(this);

		this.jtabbedpane_000 = new JTabbedPane_000(this);

		this.jpanel_002 = new JPanel_002(this);

		//

		this.apml_board = new ApmlBackboard(this);

		this.bloq_board = new BloqBackboard(this);

		this.bodi_board = new BodiBackboard(this);

		this.falthruu_board = new FalthruuBackboard(this);

		this.munction_board = new MunctionBackboard(this);

		this.runyn_board = new RunynBackboard(this);

		this.sprung_board = new SprungBackboard(this);

		//

		//this.jpanel_backboard_for_bible = new JPanel_Backboard_For_Bible(this);

		//

		//this.jpanel_backboard_for_quran = new JPanel_Backboard_For_Quran(this);

		// hierarchy

		this.setJMenuBar(jmenubar_000);

		this.add(jtabbedpane_000);

		this.add(apml_board);

		this.add(new JScrollPane_000(jpanel_002));

		// devolvement

		this.parent = parent;

		this.selected_panel = this.apml_board;

		this.setVisible(true);

		// listeners

		//

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

		this.setTitle("Quanta :: Apml Style Editor");

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// instantiation

		this.jmenubar_000 = new JMenuBar(this);

		this.jpanel_002 = new JPanel_002(this);

		this.jtabbedpane_000 = new JTabbedPane_000(this);

		//

		this.apml_board = new ApmlBackboard(this);

		this.bloq_board = new BloqBackboard(this);

		this.bodi_board = new BodiBackboard(this);

		this.falthruu_board = new FalthruuBackboard(this);

		this.munction_board = new MunctionBackboard(this);

		this.runyn_board = new RunynBackboard(this);

		this.sprung_board = new SprungBackboard(this);

		//

		this.jpanel_backboard_for_quran = new JPanel_Backboard_For_Quran(this);

		//

		this.jpanel_backboard_for_bible = new JPanel_Backboard_For_Bible(this);

		// hierarchy

		this.setJMenuBar(jmenubar_000);

		this.add(jtabbedpane_000);

		this.add(apml_board);

		this.add(new JScrollPane_000(jpanel_002));

		// devolvement

		this.selected_panel = this.apml_board;

		this.parent = parent;

		this.system = system;

		this.setVisible(true);

		// listeners

		//

		// bodi

		Bodi.context("editor").put(this.bodi, this);
	}

	public void initguisettings()
	{
		try
		{
			SwingUtilities.invokeAndWait(new Runnable()
			{
				@Override
				public void run()
				{
					try
					{
						UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

						//

						UIManager.getLookAndFeelDefaults().put("defaultFont", new Font("Optima", Font.PLAIN, 12));

						//
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
			});
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
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

			//

			for (int i = 0; i < height; i++)
			{
				int red = (color.getRed() - (2 * (i + 2))) < 0 ? 0 : (color.getRed() - (2 * (i + 2)));

				int green = (color.getGreen() - (2 * (i + 2))) < 0 ? 0 : (color.getGreen() - (2 * (i + 2)));

				int blue = (color.getBlue() - (2 * (i + 2))) < 0 ? 0 : (color.getBlue() - (2 * (i + 2)));

				//

				Color newcolor = new Color(red, green, blue);

				g.setColor(newcolor);

				g.drawLine(0, height - i, width - 1, height - i);
			}

			//

			g.setColor(Color.LIGHT_GRAY); //

			g.fillRect(2, -4, width - 2, 5);

			g.fillRect(0, height, width, 3);                //bottom highlight 3

			//
		}
	}
}