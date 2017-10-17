package org.widgets;

import apml.system.Apmlbasesystem;
import apml.system.bodi.Bodi;
import org.editor.UserInterfaceProcessor;
import org.events.LoadApmlDocumentEvent;

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
public class JMenuItem_000 extends JMenuItem
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

	public ActionListener_000 actionlistener;

	/**
	 * @param parent : The parent AWT object.
	 */
	public JMenuItem_000(Component parent)
	{
		// setters

		this.setText("Open");

		// instantiation

		this.actionlistener = new ActionListener_000(this);

		// hierarchy

		// devolvement

		this.parent = parent;

		this.setVisible(true);

		// listeners

		this.addActionListener(actionlistener);

	}

	/**
	 * @param parent : The parent AWT object.
	 * @param system : The APML system object.
	 */
	public JMenuItem_000(Component parent, Apmlbasesystem system)
	{
		// setters

		this.setText("Open");

		// instantiation

		this.actionlistener = new ActionListener_000(this);

		// hierarchy

		// devolvement

		this.parent = parent;

		this.system = system;

		this.setVisible(true);

		// listeners

		this.addActionListener(actionlistener);

	}

	class ActionListener_000 implements ActionListener
	{
		public Component parent;

		public ActionListener_000(Component parent)
		{
			this.parent = parent;
		}

		public void actionPerformed(ActionEvent event)
		{
			JFileChooser chooser = new JFileChooser();

			//

			int retval = chooser.showOpenDialog(this.parent);

			if (retval == JFileChooser.APPROVE_OPTION)
			{
				try
				{
					File file = chooser.getSelectedFile();

					//

					UserInterfaceProcessor processor;

					processor = (UserInterfaceProcessor)Bodi.context("editor").pull("//events/editor/{id}/uiprocessor");

					processor.update(new LoadApmlDocumentEvent(event, file));
				}
				catch(Exception e)
				{
					//
				}
			}

			if (retval == JFileChooser.CANCEL_OPTION)
			{
				return;
			}
		}
	}
}
