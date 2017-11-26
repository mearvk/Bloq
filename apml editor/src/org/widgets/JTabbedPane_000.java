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

							apmlgui.add(apmlgui.jpanel_backboard_for_apml);

							apmlgui.selected_panel = apmlgui.jpanel_backboard_for_apml;

							apmlgui.jpanel_backboard_for_apml.revalidate();

							apmlgui.jpanel_backboard_for_apml.invalidate();

							apmlgui.jpanel_backboard_for_apml.repaint();

							break;

						case bloqtitlestring:

							apmlgui = (APMLGui) Bodi.context("editor").pull("//editor/ui/apmlgui");

							apmlgui.remove(apmlgui.selected_panel);

							apmlgui.add(apmlgui.jpanel_backboard_for_bloq);

							apmlgui.selected_panel = apmlgui.jpanel_backboard_for_bloq;

							apmlgui.jpanel_backboard_for_bloq.revalidate();

							apmlgui.jpanel_backboard_for_bloq.invalidate();

							apmlgui.jpanel_backboard_for_bloq.repaint();

							break;

						case bodititlestring:

							apmlgui = (APMLGui) Bodi.context("editor").pull("//editor/ui/apmlgui");

							apmlgui.remove(apmlgui.selected_panel);

							apmlgui.add(apmlgui.jpanel_backboard_for_bodi);

							apmlgui.selected_panel = apmlgui.jpanel_backboard_for_bodi;

							apmlgui.jpanel_backboard_for_bodi.revalidate();

							apmlgui.jpanel_backboard_for_bodi.invalidate();

							apmlgui.jpanel_backboard_for_bodi.repaint();

							break;

						case munctiontitlestring:

							apmlgui = (APMLGui) Bodi.context("editor").pull("//editor/ui/apmlgui");

							apmlgui.remove(apmlgui.selected_panel);

							apmlgui.add(apmlgui.jpanel_backboard_for_munction);

							apmlgui.selected_panel = apmlgui.jpanel_backboard_for_munction;

							apmlgui.jpanel_backboard_for_munction.revalidate();

							apmlgui.jpanel_backboard_for_munction.invalidate();

							apmlgui.jpanel_backboard_for_munction.repaint();

							break;

						case sprungtitlestring:

							apmlgui = (APMLGui) Bodi.context("editor").pull("//editor/ui/apmlgui");

							apmlgui.remove(apmlgui.selected_panel);

							apmlgui.add(apmlgui.jpanel_backboard_for_sprung);

							apmlgui.selected_panel = apmlgui.jpanel_backboard_for_sprung;

							apmlgui.jpanel_backboard_for_sprung.revalidate();

							apmlgui.jpanel_backboard_for_sprung.invalidate();

							apmlgui.jpanel_backboard_for_sprung.repaint();

							break;

						case runyntitlestring:

							apmlgui = (APMLGui) Bodi.context("editor").pull("//editor/ui/apmlgui");

							apmlgui.remove(apmlgui.selected_panel);

							apmlgui.add(apmlgui.jpanel_backboard_for_runyn);

							apmlgui.selected_panel = apmlgui.jpanel_backboard_for_runyn;

							apmlgui.jpanel_backboard_for_runyn.revalidate();

							apmlgui.jpanel_backboard_for_runyn.invalidate();

							apmlgui.jpanel_backboard_for_runyn.repaint();

							break;

						case falthruutitlestring:

							apmlgui = (APMLGui) Bodi.context("editor").pull("//editor/ui/apmlgui");

							apmlgui.remove(apmlgui.selected_panel);

							apmlgui.add(apmlgui.jpanel_backboard_for_falthruu);

							apmlgui.selected_panel = apmlgui.jpanel_backboard_for_falthruu;

							apmlgui.jpanel_backboard_for_falthruu.revalidate();

							apmlgui.jpanel_backboard_for_falthruu.invalidate();

							apmlgui.jpanel_backboard_for_falthruu.repaint();

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
}