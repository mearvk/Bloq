
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
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import apml.system.Apmlsystem;


/**
 * Bloq software & design courtesy of Max Rupplin. All rights reserved. 2017 AD Earth, Gregorian.
 * APML software & design courtesy of Max Rupplin. All rights reserved. 2017 AD Earth, Gregorian.
 * Sprung software & design courtesy of Max Rupplin. All rights reserved. 2017 AD Earth, Gregorian.
 * 
 * Software programmatically produced by Bloq implementation version 1.0
 * 
 * Hello and thanks to and from the Church of Scientology now in her final Holy Form. /mr /ok /ss
 * Hello and thanks to and from the HUC, Holy Unified Church, (the Church of "Hi, Hello") and welcome now in her lesser manner. /mr /ok /ss
 * 
 * 
 * 
 * 
 * //hi hello
 * 
 */
public class JPanel_000
    extends JPanel
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
    public JPanel_000(Component parent) {
        /* ------------------  setters  ---------------- */
	
        this.setBackground(new Color(210, 202, 228));
	
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
	
        /* ------------------  instantiation  ---------------- */
	
        this.jtree_000 = new JTree_000(this);
	
        /* ------------------  hierarchy  -------------------- */
	
        this.add(jtree_000);
	
        /* ------------------  devolvement  -------------------- */
	
        this.parent = parent;
	
        this.setVisible(true);
	
        /* ------------------  listeners  -------------------- */
	
    }

    /**
     * 
     * @param parent : The parent AWT object.
     * @param system : The APML system object.
     */
    public JPanel_000(Component parent, Apmlsystem system) {
        /* ------------------  setters  ---------------- */
	
        this.setBackground(new Color(210, 202, 228));
	
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
	
        /* ------------------  instantiation  ---------------- */
	
        this.jtree_000 = new JTree_000(this);
	
        /* ------------------  hierarchy  -------------------- */
	
        this.add(jtree_000);
	
        /* ------------------  devolvement  -------------------- */
	
        this.parent = parent;
	
        this.system = system;
	
        this.setVisible(true);
	
        /* ------------------  listeners  -------------------- */
	
    }

    public Dimension getPreferredSize() {
        return new Dimension( ((int)(parent.getWidth()*0.3)-this.marginleft), ((int)(parent.getHeight()*1.0)-this.margintop));
    }
    
	
    JTree_000 jtree_000;

}
