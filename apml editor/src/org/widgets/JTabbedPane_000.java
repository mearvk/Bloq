package org.widgets;

import apml.system.Apmlbasesystem;
import apml.system.bodi.Bodi;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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
public class JTabbedPane_000 extends JTabbedPane implements ChangeListener
{
	public String bodi = "//editor/ui/jtabbedpane_000";

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

	//

	public Component parent;
	public Apmlbasesystem system;

	//

	public final String apmltitlestring = "☬      Apml";

	public final String bloqtitlestring = "☬      Bloq";

	public final String bodititlestring = "☬      Bodi";

	public final String munctiontitlestring = "☬      Munction";

	public final String runyntitlestring = "☬      Runyn";

	public final String sprungtitlestring = "☬      Sprung";

	public final String falthruutitlestring = "☬      Falthruu";

	/**
	 * @param parent : The tree AWT object.
	 */
	public JTabbedPane_000(Component parent)
	{
		// setters

		this.setTabPlacement(JTabbedPane.BOTTOM);

		this.setBackground(new Color(0x6f, 0x6f, 0x6f));

		//

		this.addTab(apmltitlestring, new JTabbedPaneJPanel(this));

		//

		this.addTab(bloqtitlestring, new JTabbedPaneJPanel(this));

		//

		this.addTab(bodititlestring, new JTabbedPaneJPanel(this));

		//

		this.addTab(munctiontitlestring, new JTabbedPaneJPanel(this));

		//

		this.addTab(runyntitlestring, null);

		//

		this.addTab(sprungtitlestring, new JTabbedPaneJPanel(this));

		//

		this.addTab(falthruutitlestring, new JTabbedPaneJPanel(this));

		//

		this.addTab("ø", null);

		//

		this.setEnabledAt(7, false);

		//

		this.addTab("      Radio  ☬", new JTabbedPaneJPanel(this));

		//

		this.addTab("      Messenger  ☬", new JTabbedPaneJPanel(this));

		//

		this.addTab("      Email  ☬", new JTabbedPaneJPanel(this));

		//

		this.addTab("ø", null);

		//

		this.setEnabledAt(11, false);

		//

		this.addTab("      Bible  ☬", new JTabbedPaneJPanel(this));

		//

		this.addTab("      Quran  ☬", new JTabbedPaneJPanel(this));

		// devolvement

		this.parent = parent;

		this.setVisible(true);

		// listeners

		this.addChangeListener(this);

		// bodi

		Bodi.context("editor").put(this.bodi, this);
	}

	/**
	 * @param parent : The tree AWT object.
	 * @param system : The APML system object.
	 */
	public JTabbedPane_000(Component parent, Apmlbasesystem system)
	{
		// setters

		this.setTabPlacement(JTabbedPane.BOTTOM);

		//

		this.addTab(apmltitlestring, new JTabbedPaneJPanel(this));

		//

		this.addTab(bloqtitlestring, new JTabbedPaneJPanel(this));

		//

		this.addTab(bodititlestring, new JTabbedPaneJPanel(this));

		//

		this.addTab(munctiontitlestring, new JTabbedPaneJPanel(this));

		//

		this.addTab(runyntitlestring, null);

		//

		this.addTab(sprungtitlestring, new JTabbedPaneJPanel(this));

		//

		this.addTab(falthruutitlestring, new JTabbedPaneJPanel(this));

		//

		this.addTab("ø", null);

		//

		this.setEnabledAt(7, false);

		//

		this.addTab("      Radio  ☬", new JTabbedPaneJPanel(this));

		//

		this.addTab("      Messenger  ☬", new JTabbedPaneJPanel(this));

		//

		this.addTab("      Email  ☬", new JTabbedPaneJPanel(this));

		//

		this.addTab("ø", null);

		//

		this.setEnabledAt(11, false);

		//

		this.addTab("      Bible  ☬", new JTabbedPaneJPanel(this));

		//

		this.addTab("      Quran  ☬", new JTabbedPaneJPanel(this));

		// devolvement

		this.parent = parent;

		this.system = system;

		this.setVisible(true);

		// listeners

		this.addChangeListener(this);

		// bodi

		Bodi.context("editor").put(this.bodi, this);

	}

	public Dimension getPreferredSize()
	{
		return new Dimension(((int) (parent.getWidth() * 1.0) - this.marginleft), 200);
	}

	public void stateChanged(ChangeEvent event)
	{
		try
		{
			SwingUtilities.invokeLater(new Runnable()
			{
				@Override
				public void run()
				{
					if (event.getSource() instanceof JTabbedPane)
					{
						JTabbedPane selectedPane = (JTabbedPane) event.getSource();

						int selectedIndex = selectedPane.getSelectedIndex();

						String panetitle = selectedPane.getTitleAt(selectedIndex);

						selectedPane.setForeground(Color.WHITE);

						selectedPane.setBackground(Color.WHITE);

						APMLGui apmlgui;

						switch (panetitle)
						{
							case apmltitlestring:

								apmlgui = (APMLGui) Bodi.context("editor").pull("//editor/ui/apmlgui");

								apmlgui.remove(apmlgui.selected_panel);

								apmlgui.add(apmlgui.apml_board);

								apmlgui.selected_panel = apmlgui.apml_board;

								apmlgui.apml_board.revalidate();

								apmlgui.apml_board.invalidate();

								apmlgui.apml_board.repaint();

								break;

							case bloqtitlestring:

								apmlgui = (APMLGui) Bodi.context("editor").pull("//editor/ui/apmlgui");

								JPanel panel = apmlgui.selected_panel;

								apmlgui.remove(apmlgui.selected_panel);

								apmlgui.add(apmlgui.bloq_board);

								apmlgui.selected_panel = apmlgui.bloq_board;

								apmlgui.bloq_board.revalidate();

								apmlgui.bloq_board.invalidate();

								apmlgui.bloq_board.repaint();

								break;

							case bodititlestring:

								apmlgui = (APMLGui) Bodi.context("editor").pull("//editor/ui/apmlgui");

								apmlgui.remove(apmlgui.selected_panel);

								apmlgui.add(apmlgui.bodi_board);

								apmlgui.selected_panel = apmlgui.bodi_board;

								apmlgui.bodi_board.revalidate();

								apmlgui.bodi_board.invalidate();

								apmlgui.bodi_board.repaint();

								break;

							case munctiontitlestring:

								apmlgui = (APMLGui) Bodi.context("editor").pull("//editor/ui/apmlgui");

								apmlgui.remove(apmlgui.selected_panel);

								apmlgui.add(apmlgui.munction_board);

								apmlgui.selected_panel = apmlgui.munction_board;

								apmlgui.munction_board.revalidate();

								apmlgui.munction_board.invalidate();

								apmlgui.munction_board.repaint();

								break;

							case sprungtitlestring:

								apmlgui = (APMLGui) Bodi.context("editor").pull("//editor/ui/apmlgui");

								apmlgui.remove(apmlgui.selected_panel);

								apmlgui.add(apmlgui.sprung_board);

								apmlgui.selected_panel = apmlgui.sprung_board;

								apmlgui.sprung_board.revalidate();

								apmlgui.sprung_board.invalidate();

								apmlgui.sprung_board.repaint();

								break;

							case runyntitlestring:

								apmlgui = (APMLGui) Bodi.context("editor").pull("//editor/ui/apmlgui");

								apmlgui.remove(apmlgui.selected_panel);

								apmlgui.add(apmlgui.runyn_board);

								apmlgui.selected_panel = apmlgui.runyn_board;

								apmlgui.runyn_board.revalidate();

								apmlgui.runyn_board.invalidate();

								apmlgui.runyn_board.repaint();

								break;

							case falthruutitlestring:

								apmlgui = (APMLGui) Bodi.context("editor").pull("//editor/ui/apmlgui");

								apmlgui.remove(apmlgui.selected_panel);

								apmlgui.add(apmlgui.falthruu_board);

								apmlgui.selected_panel = apmlgui.falthruu_board;

								apmlgui.falthruu_board.revalidate();

								apmlgui.falthruu_board.invalidate();

								apmlgui.falthruu_board.repaint();

								break;

							case "      Bible  ☬":

								apmlgui = (APMLGui) Bodi.context("editor").pull("//editor/ui/apmlgui");

								apmlgui.remove(apmlgui.selected_panel);

								apmlgui.add(apmlgui.jpanel_backboard_for_bible);

								apmlgui.selected_panel = apmlgui.jpanel_backboard_for_bible;

								apmlgui.jpanel_backboard_for_bible.revalidate();

								apmlgui.jpanel_backboard_for_bible.invalidate();

								apmlgui.jpanel_backboard_for_bible.repaint();

								break;

							case "      Quran  ☬":

								apmlgui = (APMLGui) Bodi.context("editor").pull("//editor/ui/apmlgui");

								apmlgui.remove(apmlgui.selected_panel);

								apmlgui.add(apmlgui.jpanel_backboard_for_quran);

								apmlgui.selected_panel = apmlgui.jpanel_backboard_for_quran;

								apmlgui.jpanel_backboard_for_quran.revalidate();

								apmlgui.jpanel_backboard_for_quran.invalidate();

								apmlgui.jpanel_backboard_for_quran.repaint();

								break;
						}
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