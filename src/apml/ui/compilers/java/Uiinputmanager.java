package apml.ui.compilers.java;

import apml.ui.compilers.java.builder.Jcmjtabbedpanebuilder;

import apml.ui.compilers.java.builder.Jcmjlabelbuilder;

import apml.ui.compilers.java.builder.Jcmjmenubuilder;

import apml.ui.compilers.java.builder.Jcmjappletbuilder;

import apml.ui.compilers.java.builder.Jcmjpasswordfieldbuilder;

import apml.ui.compilers.java.builder.Jcmjprogressbarbuilder;

import apml.ui.compilers.java.builder.Jcmjscrollpanebuilder;

import apml.ui.compilers.java.builder.Jcmjsplitpanebuilder;

import apml.ui.compilers.java.builder.Jcmjtooltipbuilder;

import apml.ui.compilers.java.builder.Jcmjinternalframebuilder;

import apml.ui.compilers.java.builder.Jcmjtoolbarbuilder;

import apml.ui.compilers.java.builder.Jcmjradiobuttonbuilder;

import apml.ui.compilers.java.builder.Jcmjpanelbuilder;

import apml.ui.compilers.java.builder.Jcmjeditorpanebuilder;

import apml.ui.compilers.java.builder.Jcmjlayeredpanebuilder;

import apml.ui.compilers.java.builder.Jcmjtextfieldbuilder;

import apml.ui.compilers.java.builder.Jcmjsliderbuilder;

import apml.ui.compilers.java.builder.Jcmjseparatorbuilder;

import apml.ui.compilers.java.builder.Jcmjrootpanebuilder;

import apml.ui.compilers.java.builder.Jcmjtreebuilder;

import apml.ui.compilers.java.builder.Jcmjspinnerbuilder;

import apml.ui.compilers.java.builder.Jcmjtextpanebuilder;

import apml.ui.compilers.java.builder.Jcmjcolorchooserbuilder;

import apml.ui.compilers.java.builder.Jcmjlistbuilder;

import apml.ui.compilers.java.builder.Jcmframebuilder;

import apml.ui.compilers.java.builder.Jcmjbuttonbuilder;

import apml.ui.compilers.java.builder.Jcmjcheckboxbuilder;

import apml.ui.compilers.java.builder.Jcmjcomboboxbuilder;

import apml.ui.compilers.java.builder.Jcmjframebuilder;

import apml.ui.compilers.java.builder.Jcmjmenubarbuilder;

import apml.ui.compilers.java.builder.Jcmjmenuitembuilder;

import com.sun.codemodel.JCodeModel;

import java.awt.Frame;

import java.io.File;

import java.util.ArrayList;

import javax.swing.JApplet;

import javax.swing.JButton;

import javax.swing.JCheckBox;

import javax.swing.JColorChooser;

import javax.swing.JComboBox;

import javax.swing.JEditorPane;

import javax.swing.JFrame;

import javax.swing.JInternalFrame;

import javax.swing.JLabel;

import javax.swing.JLayeredPane;

import javax.swing.JList;

import javax.swing.JMenu;

import javax.swing.JMenuBar;

import javax.swing.JMenuItem;

import javax.swing.JPanel;

import javax.swing.JPasswordField;

import javax.swing.JProgressBar;

import javax.swing.JRadioButton;

import javax.swing.JRootPane;

import javax.swing.JScrollPane;

import javax.swing.JSeparator;

import javax.swing.JSlider;

import javax.swing.JSpinner;

import javax.swing.JSplitPane;

import javax.swing.JTabbedPane;

import javax.swing.JTextField;

import javax.swing.JTextPane;

import javax.swing.JToolBar;

import javax.swing.JToolTip;

import javax.swing.JTree;

/**
 * @author max rupplin
 */
public class Uiinputmanager
{    
    protected final Integer hash = 0x888fe8;
    
    public Stduserinterfacecompiler compiler;
    
    public Xpathstrings xpathmodels = new Xpathstrings();
    
    public Jcmmodels jcmmodels = new Jcmmodels();                  
    
    public File apml;
    
    public Uiinputmanager(Stduserinterfacecompiler compiler)
    {
        this.compiler = compiler;
        
        this.apml = this.compiler.fileguardian.xmlin;
    }
    
    public boolean generatejcmmodels(String tagname)            
    {            
        switch(tagname)
        {
            case "//jbutton": 
                this.jcmmodels.jbuttonmodels.addAll(new Jcmjbuttonbuilder(this.apml, tagname, JButton.class).builder.build()); break;

            case "//jframe": 
                this.jcmmodels.jframemodels.addAll(new Jcmjframebuilder(this.apml, tagname, JFrame.class).builder.build()); break;

            case "//frame": 
                this.jcmmodels.framemodels.addAll(new Jcmframebuilder(this.apml, tagname, Frame.class).builder.build()); break; 

            case "//jcheckbox": 
                this.jcmmodels.jcheckboxmodels.addAll(new Jcmjcheckboxbuilder(this.apml, tagname, JCheckBox.class).builder.build()); break; 

            case "//jcombobox": 
                this.jcmmodels.jcomboboxmodels.addAll(new Jcmjcomboboxbuilder(this.apml, tagname, JComboBox.class).builder.build()); break; 
           
            case "//jlist": 
                this.jcmmodels.jlistmodels.addAll(new Jcmjlistbuilder(this.apml, tagname, JList.class).builder.build()); break;

            case "//jmenu": 
                this.jcmmodels.jmenumodels.addAll(new Jcmjmenubuilder(this.apml, tagname, JMenu.class).builder.build()); break;
            
            case "//jmenubar": 
                this.jcmmodels.jmenubarmodels.addAll(new Jcmjmenubarbuilder(this.apml, tagname, JMenuBar.class).builder.build()); break;
            
            case "//jmenuitem": 
                this.jcmmodels.jmenuitemmodels.addAll(new Jcmjmenuitembuilder(this.apml, tagname, JMenuItem.class).builder.build()); break;

            case "//jradiobutton": 
                this.jcmmodels.jradiobuttonmodels.addAll(new Jcmjradiobuttonbuilder(this.apml, tagname, JRadioButton.class).builder.build()); break; 

            case "//jslider": 
                this.jcmmodels.jslidermodels.addAll(new Jcmjsliderbuilder(this.apml, tagname, JSlider.class).builder.build()); break;

            case "//jspinner": 
                this.jcmmodels.jspinnermodels.addAll(new Jcmjspinnerbuilder(apml, tagname, JSpinner.class).builder.build()); break;

            case "//jtextfield": 
                this.jcmmodels.jtextfieldmodels.addAll(new Jcmjtextfieldbuilder(apml, tagname, JTextField.class).builder.build()); break; 

            case "//jpasswordfield": 
                this.jcmmodels.jpasswordfieldmodels.addAll(new Jcmjpasswordfieldbuilder(apml, tagname, JPasswordField.class).builder.build()); break;

            case "//jcolorchooser": 
                this.jcmmodels.jcolorchoosermodels.addAll(new Jcmjcolorchooserbuilder(apml, tagname, JColorChooser.class).builder.build()); break;

            case "//jeditorpane": 
                this.jcmmodels.jeditorpanemodels.addAll(new Jcmjeditorpanebuilder(apml, tagname, JEditorPane.class).builder.build()); break;

            case "//jtextpane": 
                this.jcmmodels.jtextpanemodels.addAll(new Jcmjtextpanebuilder(apml, tagname, JTextPane.class).builder.build()); break; 

            case "//jtree": 
                this.jcmmodels.jtreemodels.addAll(new Jcmjtreebuilder(apml, tagname, JTree.class).builder.build()); break; 

            case "//jlabel": 
                this.jcmmodels.jlabelmodels.addAll(new Jcmjlabelbuilder(apml, tagname, JLabel.class).builder.build()); break;

            case "//jprogressbar": 
                this.jcmmodels.jprogressbarmodels.addAll(new Jcmjprogressbarbuilder(apml, tagname, JProgressBar.class).builder.build()); break;

            case "//jseparator": 
                this.jcmmodels.jseparatormodels.addAll(new Jcmjseparatorbuilder(apml, tagname, JSeparator.class).builder.build()); break;

            case "//jtooltip": 
                this.jcmmodels.jtooltipmodels.addAll(new Jcmjtooltipbuilder(apml, tagname, JToolTip.class).builder.build()); break;

            case "//japplet": 
                this.jcmmodels.jappletmodels.addAll(new Jcmjappletbuilder(apml, tagname, JApplet.class).builder.build()); break;

            case "//jpanel": 
                this.jcmmodels.jpanelmodels.addAll(new Jcmjpanelbuilder(apml, tagname, JPanel.class).builder.build()); break;

            case "//jscrollpane": 
                this.jcmmodels.jscrollpanemodels.addAll(new Jcmjscrollpanebuilder(apml, tagname, JScrollPane.class).builder.build()); break;

            case "//jsplitpane": 
                this.jcmmodels.jsplitpanemodels.addAll(new Jcmjsplitpanebuilder(apml, tagname, JSplitPane.class).builder.build()); break;

            case "//jtabbedpane": 
                this.jcmmodels.jtabbedpanemodels.addAll(new Jcmjtabbedpanebuilder(apml, tagname, JTabbedPane.class).builder.build()); break;

            case "//jtoolbar": 
                this.jcmmodels.jtoolbarmodels.addAll(new Jcmjtoolbarbuilder(apml, tagname, JToolBar.class).builder.build()); break;

            case "//jinternalframe": 
                this.jcmmodels.jinternalframemodels.addAll(new Jcmjinternalframebuilder(apml, tagname, JInternalFrame.class).builder.build()); break;

            case "//jlayeredpane": 
                this.jcmmodels.jlayeredpanemodels.addAll(new Jcmjlayeredpanebuilder(apml, tagname, JLayeredPane.class).builder.build()); break;

            case "//jrootpane": 
                this.jcmmodels.jrootpanemodels.addAll(new Jcmjrootpanebuilder(apml, tagname, JRootPane.class).builder.build()); break;   
        }        
        
        return true;
    }     
}
