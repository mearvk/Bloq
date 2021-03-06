package org.widgets;

import apml.system.Apmlbasesystem;
import apml.system.bodi.Bodi;
import org.events.ExitProgramEvent;
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

public class JMenuItem_File_Exit extends JMenuItem
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

	/**
	 * @param parent : The tree AWT object.
	 */
	public JMenuItem_File_Exit(Component parent)
	{
		// setters

		this.setText("Exit");

		// instantiation

		// hierarchy

		// devolvement

		this.parent = parent;

		this.setVisible(true);

		// listeners

		this.addActionListener(new JMenuItem_019_ActionListener(this));
	}

	/**
	 * @param parent : The tree AWT object.
	 * @param system : The APML system object.
	 */
	public JMenuItem_File_Exit(Component parent, Apmlbasesystem system)
	{
		// setters

		this.setText("Exit");

		// instantiation

		// hierarchy

		// devolvement

		this.parent = parent;

		this.system = system;

		this.setVisible(true);

		// listeners

		this.addActionListener(new JMenuItem_019_ActionListener(this));
	}
}

class JMenuItem_019_ActionListener implements ActionListener
{
	JComponent source;

	public JMenuItem_019_ActionListener(JComponent source)
	{
		this.source = source;
	}

	@Override
	public void actionPerformed(ActionEvent event)
	{
		UserInterfaceProcessor processor;

		processor = (UserInterfaceProcessor) Bodi.context("editor").pull("//editor/ui/uiprocessor_000");

		processor.enact(new ExitProgramEvent(event));
	}
}
