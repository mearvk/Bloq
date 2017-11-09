package org.widgets.analysis.bloq;

import apml.system.Apmlbasesystem;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.URL;

public class JTextPane_000 extends JTextPane
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

	public Integer horizontalMargin = 10;
	public Integer verticalMargin = 10;

	//

	public Component parent;
	public Apmlbasesystem system;

	public JTextPane_000(Component parent)
	{
		// setters

		this.setText("APML Program 1.05 ... \n\nAnalysis Services\n\n--- --- --- --- ---\n\nStep 001. Syntactic Analysis\n\nStep 002. Suggestive Analysis\n\nStep 003. Integrative Analysis\n\nStep 004. Conclusion\n\n* Check APML/Bodi values for autocomplete");

		// instantiation

		// hierarchy

		// devolvement

		this.parent = parent;

		this.setVisible(true);

		// listeners
	}

	public JTextPane_000(Component parent, Apmlbasesystem system)
	{
		// setters

		this.setText("APML Program 1.05 ... \n\nAnalysis Services\n\n--- --- --- --- ---\n\nStep 001. Syntactic Analysis\n\nStep 002. Suggestive Analysis\n\nStep 003. Integrative Analysis\n\nStep 004. Conclusion\n\n* Check APML/Bodi values for autocomplete");

		// instantiation

		// hierarchy

		// devolvement

		this.parent = parent;

		this.system = system;

		this.setVisible(true);

		// listeners
	}

	@Override
	public Dimension getPreferredSize()
	{
		return new Dimension(this.parent.getWidth()-this.horizontalMargin,this.parent.getHeight()-this.verticalMargin);
	}
}
