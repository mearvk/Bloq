
package org.widgets.analysis.bodi;

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


/**
 * Software programmatically produced by Bloq implementation version 1.05 - Bodi Remote Version
 * 
 * @author
 * @see
 * @since
 * @version
 * 
 */
public class JPanel_002
    extends JPanel
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
     * 
     * @param parent : The parent AWT object.
     */
    public JPanel_002(Component parent) {
        // setters 
	
        this.setBackground(new Color(0x54,0x54,0x54));

        this.setLayout(new FlowLayout(FlowLayout.LEFT));
	
        // instantiation

        JLabel label;

        label = new JLabel("☬ Step 2: Suggestive Analysis");

        label.setForeground(Color.WHITE);

        this.add(label);
	
        // hierarchy 
	
        // devolvement 
	
        this.parent = parent;
	
        this.setVisible(true);
	
        // listeners 
	
    }

    /**
     * 
     * @param parent : The parent AWT object.
     * @param system : The APML system object.
     */
    public JPanel_002(Component parent, Apmlbasesystem system) {
        // setters 
	
        this.setBackground(new Color(0x54,0x54,0x54));

        this.setLayout(new FlowLayout(FlowLayout.LEFT));
	
        // instantiation

        JLabel label;

        label = new JLabel("☬ Step 2: Suggestive Analysis");

        label.setForeground(Color.WHITE);

        this.add(label);
	
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
        return new Dimension(220,100);
    }
}
