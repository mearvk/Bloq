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
	 * @param parent : The parent AWT object.
	 */
	public JTabbedPane_000(Component parent)
	{
		// setters

		this.addTab("☬   Apml", new JTabbedPaneJPanel(this));

		this.setForegroundAt(0, Color.WHITE);

		this.setBackgroundAt(0, Color.ORANGE);

		//

		this.addTab("☬   Bloq", new JTabbedPaneJPanel(this));

		this.setForegroundAt(1, Color.WHITE);

		this.setBackgroundAt(1, Color.BLACK);

		//

		this.addTab("☬   Bodi", new JTabbedPaneJPanel(this));

		this.setForegroundAt(2, Color.WHITE);

		this.setBackgroundAt(2, Color.BLACK);

		//

		this.addTab("☬   Runyn", null);

		this.setForegroundAt(3, Color.WHITE);

		this.setBackgroundAt(3, Color.BLACK);

		//

		this.addTab("☬   Sprung", new JTabbedPaneJPanel(this));

		this.setForegroundAt(4, Color.WHITE);

		this.setBackgroundAt(4, Color.BLACK);

		//

		this.addTab("☬   Falthruu", new JTabbedPaneJPanel(this));

		this.setForegroundAt(5, Color.WHITE);

		this.setBackgroundAt(5, Color.BLACK);

		//

		this.addTab("ø", null);

		//

		this.setEnabledAt(this.indexOfTab("ø"), false);

		//

		this.addTab("☬   Radio", new JTabbedPaneJPanel(this));

		this.setForegroundAt(7, Color.WHITE);

		this.setBackgroundAt(7, Color.BLACK);

		//

		this.addTab("☬   Messenger", new JTabbedPaneJPanel(this));

		this.setForegroundAt(8, Color.WHITE);

		this.setBackgroundAt(8, Color.BLACK);

		//

		this.addTab("☬   Email", new JTabbedPaneJPanel(this));

		this.setForegroundAt(9, Color.WHITE);

		this.setBackgroundAt(9, Color.BLACK);

		// devolvement

		this.parent = parent;

		this.setVisible(true);

		// listeners

		this.addChangeListener(this);

	}

	/**
	 * @param parent : The parent AWT object.
	 * @param system : The APML system object.
	 */
	public JTabbedPane_000(Component parent, Apmlbasesystem system)
	{
		// setters

		this.addTab("☬   Apml", new JTabbedPaneJPanel(this));

		this.setForegroundAt(0, Color.WHITE);

		this.setBackgroundAt(0, Color.ORANGE);

		//

		this.addTab("☬   Bloq", new JTabbedPaneJPanel(this));

		this.setForegroundAt(1, Color.WHITE);

		this.setBackgroundAt(1, Color.BLACK);

		//

		this.addTab("☬   Bodi", new JTabbedPaneJPanel(this));

		this.setForegroundAt(2, Color.WHITE);

		this.setBackgroundAt(2, Color.BLACK);

		//

		this.addTab("☬   Runyn", null);

		this.setForegroundAt(3, Color.WHITE);

		this.setBackgroundAt(3, Color.BLACK);

		//

		this.addTab("☬   Sprung", new JTabbedPaneJPanel(this));

		this.setForegroundAt(4, Color.WHITE);

		this.setBackgroundAt(4, Color.BLACK);

		//

		this.addTab("☬   Falthruu", new JTabbedPaneJPanel(this));

		this.setForegroundAt(5, Color.WHITE);

		this.setBackgroundAt(5, Color.BLACK);

		//

		this.addTab("ø", null);

		//

		this.setEnabledAt(this.indexOfTab("ø"), false);

		//

		this.addTab("☬   Radio", new JTabbedPaneJPanel(this));

		this.setForegroundAt(7, Color.WHITE);

		this.setBackgroundAt(7, Color.BLACK);

		//

		this.addTab("☬   Messenger", new JTabbedPaneJPanel(this));

		this.setForegroundAt(8, Color.WHITE);

		this.setBackgroundAt(8, Color.BLACK);

		//

		this.addTab("☬   Email", new JTabbedPaneJPanel(this));

		this.setForegroundAt(9, Color.WHITE);

		this.setBackgroundAt(9, Color.BLACK);

		// devolvement

		this.parent = parent;

		this.system = system;

		this.setVisible(true);

		// listeners

		this.addChangeListener(this);

	}

	public Dimension getPreferredSize()
	{
		return new Dimension(((int) (parent.getWidth() * 1.0) - this.marginleft), 151);
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

						JTabbedPane_000.this.setBackgroundAt(i, Color.BLACK);
					}

					JTabbedPane_000.this.setBackgroundAt(pane.getSelectedIndex(), Color.ORANGE);
				}
			}
		});
	}
}