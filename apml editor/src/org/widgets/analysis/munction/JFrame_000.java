
package org.widgets.analysis.munction;

import apml.system.Apmlbasesystem;
import apml.system.bodi.Bodi;
import org.widgets.APMLGui;

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
public class JFrame_000
    extends JFrame
{
    public String bodi = "//editor/ui/munction_analysis_ui_jframe";

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


	public static void main(String...args)
	{
		new JFrame_000(null);
	}

    /**
     * 
     * @param parent : The parent AWT object.
     */
    public JFrame_000(Component parent) {

        APMLGui apmlgui;

        apmlgui = (APMLGui)Bodi.context("editor").pull("//editor/ui/apmlgui");

        int x = apmlgui.getX();

        int y = apmlgui.getY();

        int width = apmlgui.getWidth();

        int height = apmlgui.getHeight();

        // setters

        this.setTitle("Munction Analysis");

        this.setBounds( x+(width/2)-(this.getPreferredSize().width/2),y+(height/2)-(this.getPreferredSize().height/2)-250,910,420);

        this.setLayout(new FlowLayout());

        this.setResizable(false);

        this.setAlwaysOnTop(true);

        // instantiation

        this.jpanel_000 = new org.widgets.analysis.munction.JPanel_000(this);

        // bodi

        Bodi.context("editor").put(this.bodi, this);

        // hierarchy 

        this.add(jpanel_000);

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
    public JFrame_000(Component parent, Apmlbasesystem system) {

        APMLGui apmlgui;

        apmlgui = (APMLGui)Bodi.context("editor").pull("//editor/ui/apmlgui");

        int x = apmlgui.getX();

        int y = apmlgui.getY();

        int width = apmlgui.getWidth();

        int height = apmlgui.getHeight();

        // setters

        this.setTitle("Munction Analysis");

        this.setBounds( x+(width/2)-(this.getPreferredSize().width/2),y+(height/2)-(this.getPreferredSize().height/2)-250,910,420);

        this.setLayout(new FlowLayout());

        this.setResizable(false);

        this.setAlwaysOnTop(true);

        // instantiation

        this.jpanel_000 = new org.widgets.analysis.munction.JPanel_000(this);

        // bodi

        Bodi.context("editor").put(this.bodi, this);

        // hierarchy

        this.add(jpanel_000);

        // devolvement

        this.parent = parent;

        this.system = system;

        this.setVisible(true);

        // listeners 

    }
    
	
    JPanel_000 jpanel_000;

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(910,420);
    }
}
