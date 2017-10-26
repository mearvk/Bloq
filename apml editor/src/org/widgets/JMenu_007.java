
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
public class JMenu_007
    extends JMenu
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
     * @param parent : The tree AWT object.
     */
    public JMenu_007(Component parent) {
        // setters 
	
        this.setText("ˇ Run");
	
        // instantiation 
	
        this.jmenuitem_014 = new JMenuItem_014(this);
	
        this.jmenuitem_015 = new JMenuItem_015(this);
	
        // hierarchy 
	
        this.add(jmenuitem_014);
	
        this.add(jmenuitem_015);
	
        // devolvement 
	
        this.parent = parent;
	
        this.setVisible(true);
	
        // listeners 
	
    }

    /**
     * 
     * @param parent : The tree AWT object.
     * @param system : The APML system object.
     */
    public JMenu_007(Component parent, Apmlbasesystem system) {
        // setters 
	
        this.setText("٠ Run");
	
        // instantiation 
	
        this.jmenuitem_014 = new JMenuItem_014(this);
	
        this.jmenuitem_015 = new JMenuItem_015(this);
	
        // hierarchy 
	
        this.add(jmenuitem_014);
	
        this.add(jmenuitem_015);
	
        // devolvement 
	
        this.parent = parent;
	
        this.system = system;
	
        this.setVisible(true);
	
        // listeners 
	
    }
    
	
    JMenuItem_014 jmenuitem_014;
    JMenuItem_015 jmenuitem_015;

}
