
package org.widgets;

import apml.system.Apmlsystem;

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
public class JMenu_000
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
    public Apmlsystem system;

    /**
     * 
     * @param parent : The parent AWT object.
     */
    public JMenu_000(Component parent) {
        // setters 
	
        this.setText("View");
	
        // instantiation 
	
        this.jmenuitem_000 = new JMenuItem_000(this);
	
        this.jmenuitem_001 = new JMenuItem_001(this);
	
        // hierarchy 
	
        this.add(jmenuitem_000);
	
        this.add(jmenuitem_001);
	
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
    public JMenu_000(Component parent, Apmlsystem system) {
        // setters 
	
        this.setText("View");
	
        // instantiation 
	
        this.jmenuitem_000 = new JMenuItem_000(this);
	
        this.jmenuitem_001 = new JMenuItem_001(this);
	
        // hierarchy 
	
        this.add(jmenuitem_000);
	
        this.add(jmenuitem_001);
	
        // devolvement 
	
        this.parent = parent;
	
        this.system = system;
	
        this.setVisible(true);
	
        // listeners 
	
    }
    
	
    JMenuItem_000 jmenuitem_000;
    JMenuItem_001 jmenuitem_001;

}
