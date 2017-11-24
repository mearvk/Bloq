package org.widgets;

import apml.system.Apmlbasesystem;
import apml.system.bodi.Bodi;
import org.events.BuildApmlSubsystemEvent;
import org.events.BuildApmlUisubsystemEvent;
import org.events.RebuildApmlSystemEvent;
import org.system.UserInterfaceProcessor;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class JMenu_006 extends JMenu
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
	public Apmlbasesystem system;

	//

	public String bodi = "//editor/ui/jmenu_006";

	//

	public JMenuItem_002 jmenuitem_002;

	public JMenuItem_003 jmenuitem_003;

	public JMenuItem_004 jmenuitem_004;

	//
	public BuildApmlStandaloneListener menu_listener_000 = new BuildApmlStandaloneListener();

	/**
	 * @param parent : The tree AWT object.
	 */
	public JMenu_006(Component parent)
	{
		// setters

		this.setText("  Build  ");

		this.setMnemonic(KeyEvent.VK_B);

		// instantiation

		this.menu_listener_000 = new BuildApmlStandaloneListener();

		this.jmenuitem_002 = new JMenuItem_002(this);

		this.jmenuitem_003 = new JMenuItem_003(this);

		this.jmenuitem_004 = new JMenuItem_004(this);

		// hierarchy

		this.add(jmenuitem_002);

		this.add(jmenuitem_003);

		this.add(jmenuitem_004);

		// devolvement

		this.parent = parent;

		this.setVisible(true);

		// listeners

		//bodi

		Bodi.context("editor").put(this.bodi, this);
	}

	/**
	 * @param parent : The tree AWT object.
	 * @param system : The APML system object.
	 */
	public JMenu_006(Component parent, Apmlbasesystem system)
	{
		// setters

		this.setText("  Build  ");

		this.setMnemonic(KeyEvent.VK_B);

		// instantiation

		this.menu_listener_000 = new BuildApmlStandaloneListener();

		this.jmenuitem_002 = new JMenuItem_002(this);

		this.jmenuitem_003 = new JMenuItem_003(this);

		this.jmenuitem_004 = new JMenuItem_004(this);

		// hierarchy

		this.add(jmenuitem_002);

		this.add(jmenuitem_003);

		this.add(jmenuitem_004);

		// devolvement

		this.parent = parent;

		this.system = system;

		this.setVisible(true);

		// listeners

		//bodi

		Bodi.context("editor").put(this.bodi, this);
	}
}

class BuildApmlStandaloneListener implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		switch (ae.getActionCommand())
		{
			case "Apml Subsystem":

				this.doApmlsubsystembuild(ae);

				break;

			case "Apml UI Subsystem":

				this.doApmluisystembuild(ae);

				break;

			case "Rebuild":

				this.doApmlrebuildcurrent(ae);

				break;

			default:
				break;
		}
	}

	//
	private void doApmlsubsystembuild(ActionEvent ae)
	{
		UserInterfaceProcessor processor;

		processor = (UserInterfaceProcessor) Bodi.context("editor").pull("//editor/ui/uiprocessor_000");

		processor.enact(new BuildApmlSubsystemEvent(ae));
	}

	//
	private void doApmluisystembuild(ActionEvent ae)
	{
		UserInterfaceProcessor processor;

		processor = (UserInterfaceProcessor) Bodi.context("editor").pull("//editor/ui/uiprocessor_000");

		processor.enact(new BuildApmlUisubsystemEvent(ae));
	}

	//
	private void doApmlrebuildcurrent(ActionEvent ae)
	{
		UserInterfaceProcessor processor;

		processor = (UserInterfaceProcessor) Bodi.context("editor").pull("//editor/ui/uiprocessor_000");

		processor.enact(new RebuildApmlSystemEvent(ae));
	}
}