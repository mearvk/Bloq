
package org.widgets;

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
public class JPanel_000
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
    public JPanel_000(Component parent) {
        // setters 

        this.setPreferredSize(new Dimension(parent.getWidth(),parent.getHeight()));

        //this.setHeight(100%);
	
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
	
        //this.setWidth(100%);
	
        // instantiation 
	
        this.jpanel_001 = new JPanel_001(this);
	
        this.jpanel_002 = new JPanel_002(this);
	
        this.jpanel_003 = new JPanel_003(this);
	
        this.jpanel_004 = new JPanel_004(this);
	
        // hierarchy 
	
        this.add(jpanel_001);
	
        this.add(jpanel_002);
	
        this.add(jpanel_003);
	
        this.add(jpanel_004);
	
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
    public JPanel_000(Component parent, Apmlbasesystem system) {
        // setters 

        this.setPreferredSize(new Dimension(parent.getWidth(),parent.getHeight()));
	
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
	
        // instantiation 
	
        this.jpanel_001 = new JPanel_001(this);
	
        this.jpanel_002 = new JPanel_002(this);
	
        this.jpanel_003 = new JPanel_003(this);
	
        this.jpanel_004 = new JPanel_004(this);
	
        // hierarchy 
	
        this.add(jpanel_001);
	
        this.add(jpanel_002);
	
        this.add(jpanel_003);
	
        this.add(jpanel_004);
	
        // devolvement 
	
        this.parent = parent;
	
        this.system = system;
	
        this.setVisible(true);
	
        // listeners 
	
    }
    
	
    JPanel_001 jpanel_001;
    JPanel_002 jpanel_002;
    JPanel_003 jpanel_003;
    JPanel_004 jpanel_004;

	@Override
	public Dimension getPreferredSize()
	{
		return new Dimension(this.parent.getWidth(),this.parent.getHeight());
	}
}
