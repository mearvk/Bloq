
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
public class JMenuBar_000
    extends JMenuBar
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
    public JMenuBar_000(Component parent) {
        // setters 
	
        // instantiation 
	
        this.jmenu_000 = new JMenu_000(this);
	
        this.jmenu_001 = new JMenu_001(this);
	
        this.jmenu_002 = new JMenu_002(this);
	
        this.jmenu_003 = new JMenu_003(this);
	
        this.jmenu_004 = new JMenu_004(this);
	
        this.jmenu_005 = new JMenu_005(this);
	
        this.jmenu_006 = new JMenu_006(this);
	
        this.jmenu_007 = new JMenu_007(this);
	
        this.jmenu_008 = new JMenu_008(this);
	
        // hierarchy 
	
        this.add(jmenu_000);
	
        this.add(jmenu_001);
	
        this.add(jmenu_002);
	
        this.add(jmenu_003);
	
        this.add(jmenu_004);
	
        this.add(jmenu_005);
	
        this.add(jmenu_006);
	
        this.add(jmenu_007);
	
        this.add(jmenu_008);
	
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
    public JMenuBar_000(Component parent, Apmlbasesystem system) {
        // setters 
	
        // instantiation 
	
        this.jmenu_000 = new JMenu_000(this);
	
        this.jmenu_001 = new JMenu_001(this);
	
        this.jmenu_002 = new JMenu_002(this);
	
        this.jmenu_003 = new JMenu_003(this);
	
        this.jmenu_004 = new JMenu_004(this);
	
        this.jmenu_005 = new JMenu_005(this);
	
        this.jmenu_006 = new JMenu_006(this);
	
        this.jmenu_007 = new JMenu_007(this);
	
        this.jmenu_008 = new JMenu_008(this);
	
        // hierarchy 
	
        this.add(jmenu_000);
	
        this.add(jmenu_001);
	
        this.add(jmenu_002);
	
        this.add(jmenu_003);
	
        this.add(jmenu_004);
	
        this.add(jmenu_005);
	
        this.add(jmenu_006);
	
        this.add(jmenu_007);
	
        this.add(jmenu_008);
	
        // devolvement 
	
        this.parent = parent;
	
        this.system = system;
	
        this.setVisible(true);
	
        // listeners 
	
    }
    
	
    JMenu_000 jmenu_000;
    JMenu_001 jmenu_001;
    JMenu_002 jmenu_002;
    JMenu_003 jmenu_003;
    JMenu_004 jmenu_004;
    JMenu_005 jmenu_005;
    JMenu_006 jmenu_006;
    JMenu_007 jmenu_007;
    JMenu_008 jmenu_008;

}
