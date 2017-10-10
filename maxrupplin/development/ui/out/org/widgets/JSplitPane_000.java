
package org.widgets;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JSplitPane;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import apml.system.Apmlsystem;


/**
 * Software programmatically produced by Bloq implementation version 1.05 - Bodi Remote Version
 * 
 * @author
 * @see
 * @since
 * @version
 * 
 */
public class JSplitPane_000
    extends JSplitPane
{

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
    public Apmlsystem system;

    /**
     * 
     * @param parent : The parent AWT object.
     */
    public JSplitPane_000(Component parent, Integer orientation, Component component_000, Component component_001) {
        // super
	
        super(orientation, component_000, component_001);
	
        // setters 
	
        this.setBackground(new Color(76, 44, 147));
	
        this.setDividerLocation(150);
	
        // instantiation 
	
        this.jpanel_000 = (JPanel_000)component_000;
	
        this.jpanel_001 = (JPanel_001)component_001;
	
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
    public JSplitPane_000(Component parent, Apmlsystem system, Integer orientation, Component component_000, Component component_001) {
        // super 
	
        super(orientation, component_000, component_001);
	
        // setters 
	
        this.setBackground(new Color(76, 44, 147));
	
        this.setDividerLocation(150);
	
        // instantiation 
	
        this.jpanel_000 = (JPanel_000)component_000;
	
        this.jpanel_001 = (JPanel_001)component_001;
	
        // hierarchy 
	
        // devolvement 
	
        this.parent = parent;
	
        this.system = system;
	
        this.setVisible(true);
	
        // listeners 
	
    }

    public Dimension getPreferredSize() {
        return new Dimension( ((int)(parent.getWidth()*1.0)-this.marginleft), ((int)(parent.getHeight()*1.0)-this.margintop));
    }
    
	
    JPanel_000 jpanel_000;
    JPanel_001 jpanel_001;

}
