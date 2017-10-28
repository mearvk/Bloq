package org.widgets;

import apml.system.Apmlbasesystem;

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
	public Apmlbasesystem system;

	/**
	 * @param parent : The tree AWT object.
	 */
	public JTabbedPane_000(Component parent)
	{
		// setters

		this.setTabPlacement(JTabbedPane.BOTTOM);

		//

		this.addTab("☬   Apml ", new JTabbedPaneJPanel(this));

		//

		this.addTab("☬   Bloq ", new JTabbedPaneJPanel(this));

		//

		this.addTab("☬   Bodi ", new JTabbedPaneJPanel(this));

		//

		this.addTab("☬   Munction ", new JTabbedPaneJPanel(this));

		//

		this.addTab("☬   Runyn ", null);

		//

		this.addTab("☬   Sprung ", new JTabbedPaneJPanel(this));

		//

		this.addTab("☬   Falthruu ", new JTabbedPaneJPanel(this));

		//

		this.addTab("ø", null);

		//

		this.setEnabledAt(7, false);

		//

		this.addTab("☬   Radio ", new JTabbedPaneJPanel(this));

		//

		this.addTab("☬   Messenger ", new JTabbedPaneJPanel(this));

		//

		this.addTab("☬   Email ", new JTabbedPaneJPanel(this));

		//

		this.addTab("ø", null);

		//

		this.setEnabledAt(11, false);

		//

		this.addTab("☬   Bible ", new JTabbedPaneJPanel(this));

		//

		this.addTab("☬   Quran ", new JTabbedPaneJPanel(this));

		// devolvement

		this.parent = parent;

		this.setVisible(true);

		// listeners

		this.addChangeListener(this);

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

		this.addTab("☬   Apml ", new JTabbedPaneJPanel(this));

		//

		this.addTab("☬   Bloq ", new JTabbedPaneJPanel(this));

		//

		this.addTab("☬   Bodi ", new JTabbedPaneJPanel(this));

		//

		this.addTab("☬   Munction ", new JTabbedPaneJPanel(this));

		//

		this.addTab("☬   Runyn ", null);

		//

		this.addTab("☬   Sprung ", new JTabbedPaneJPanel(this));

		//

		this.addTab("☬   Falthruu ", new JTabbedPaneJPanel(this));

		//

		this.addTab("ø", null);

		//

		this.setEnabledAt(7, false);

		//

		this.addTab("☬   Radio ", new JTabbedPaneJPanel(this));

		//

		this.addTab("☬   Messenger ", new JTabbedPaneJPanel(this));

		//

		this.addTab("☬   Email ", new JTabbedPaneJPanel(this));

		//

		this.addTab("ø", null);

		//

		this.setEnabledAt(11, false);

		//

		this.addTab("☬   Bible ", new JTabbedPaneJPanel(this));

		//

		this.addTab("☬   Quran ", new JTabbedPaneJPanel(this));

		// devolvement

		this.parent = parent;

		this.system = system;

		this.setVisible(true);

		// listeners

		this.addChangeListener(this);

	}

	public Dimension getPreferredSize()
	{
		return new Dimension(((int) (parent.getWidth() * 1.0) - this.marginleft), 181);
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
					JTabbedPane pane = (JTabbedPane) event.getSource();

					for(int i=0; i<JTabbedPane_000.this.getTabCount(); i++)
					{
						if(i==6) continue;

						JTabbedPane_000.this.setBackgroundAt(i, new Color(0,0,0,Color.TRANSLUCENT));
					}

					JTabbedPane_000.this.setBackgroundAt(pane.getSelectedIndex(), Color.RED);
				}
			}
		});
	}
}