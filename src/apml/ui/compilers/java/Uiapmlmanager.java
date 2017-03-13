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
public class Uiapmlmanager
{    
    protected final Integer hash = 0x888fe8;
    
    public ArrayList<JCodeModel> jbuttonmodels;
    
    public ArrayList<JCodeModel> jframemodels;
    
    public ArrayList<JCodeModel> framemodels;
    
    public ArrayList<JCodeModel> jcheckboxmodels;
    
    public ArrayList<JCodeModel> jcomboboxmodels;
    
    public ArrayList<JCodeModel> jlistmodels;
    
    public ArrayList<JCodeModel> jmenumodels;
    
    public ArrayList<JCodeModel> jmenubarmodels;
    
    public ArrayList<JCodeModel> jmenuitemmodels;
    
    public ArrayList<JCodeModel> jradiobuttonmodels;
    
    public ArrayList<JCodeModel> jslidermodels;
    
    public ArrayList<JCodeModel> jspinnermodels;
    
    public ArrayList<JCodeModel> jtextfieldmodels;
    
    public ArrayList<JCodeModel> jpasswordfieldmodels;  
    
    public ArrayList<JCodeModel> jcolorchoosermodels;
    
    public ArrayList<JCodeModel> jeditorpanemodels;
    
    public ArrayList<JCodeModel> jtextpanemodels;
    
    public ArrayList<JCodeModel> jfilechoosermodels;
    
    public ArrayList<JCodeModel> jtablemodels;
    
    public ArrayList<JCodeModel> jtextareamodels;
    
    public ArrayList<JCodeModel> jtreemodels;    
    
    public ArrayList<JCodeModel> jlabelmodels;
    
    public ArrayList<JCodeModel> jprogressbarmodels;
    
    public ArrayList<JCodeModel> jseparatormodels;
    
    public ArrayList<JCodeModel> jtooltipmodels;
    
    public ArrayList<JCodeModel> jappletmodels;
    
    public ArrayList<JCodeModel> jdialogmodels; 
    
    public ArrayList<JCodeModel> jpanelmodels;
    
    public ArrayList<JCodeModel> jscrollpanemodels;
    
    public ArrayList<JCodeModel> jsplitpanemodels;
    
    public ArrayList<JCodeModel> jtabbedpanemodels;
    
    public ArrayList<JCodeModel> jtoolbarmodels;
    
    public ArrayList<JCodeModel> jinternalframemodels;    
    
    public ArrayList<JCodeModel> jlayeredpanemodels;
    
    public ArrayList<JCodeModel> jrootpanemodels;           
    
    public Object generaterelations(File apml, String tagname)
    {
        return null;
    }
    
    public ArrayList<JCodeModel> generatejcmmodels(File apml, String tagname)            
    {            
        switch(tagname)
        {
            case "//jbutton": return this.jbuttonmodels = new Jcmjbuttonbuilder(apml, tagname, JButton.class).builder.build();

            case "//jframe": return this.jframemodels =  new Jcmjframebuilder(apml, tagname, JFrame.class).builder.build();

            case "//frame": return this.framemodels =  new Jcmframebuilder(apml, tagname, Frame.class).builder.build(); 

            case "//jcheckbox": return this.jcheckboxmodels = new Jcmjcheckboxbuilder(apml, tagname, JCheckBox.class).builder.build(); 

            case "//jcombobox": return this.jcomboboxmodels = new Jcmjcomboboxbuilder(apml, tagname, JComboBox.class).builder.build(); 

            case "//jlist": return this.jlistmodels = new Jcmjlistbuilder(apml, tagname, JList.class).builder.build();

            case "//jmenu": return this.jmenumodels = new Jcmjmenubuilder(apml, tagname, JMenu.class).builder.build();
            
            case "//jmenubar": return this.jmenubarmodels = new Jcmjmenubarbuilder(apml, tagname, JMenuBar.class).builder.build();
            
            case "//jmenuitem": return this.jmenuitemmodels = new Jcmjmenuitembuilder(apml, tagname, JMenuItem.class).builder.build();

            case "//jradiobutton": return this.jradiobuttonmodels = new Jcmjradiobuttonbuilder(apml, tagname, JRadioButton.class).builder.build(); 

            case "//jslider": return this.jslidermodels = new Jcmjsliderbuilder(apml, tagname, JSlider.class).builder.build();

            case "//jspinner": return this.jspinnermodels = new Jcmjspinnerbuilder(apml, tagname, JSpinner.class).builder.build();

            case "//jtextfield": return this.jtextfieldmodels = new Jcmjtextfieldbuilder(apml, tagname, JTextField.class).builder.build(); 

            case "//jpasswordfield": return this.jpasswordfieldmodels = new Jcmjpasswordfieldbuilder(apml, tagname, JPasswordField.class).builder.build();

            case "//jcolorchooser": return this.jcolorchoosermodels = new Jcmjcolorchooserbuilder(apml, tagname, JColorChooser.class).builder.build();

            case "//jeditorpane": return this.jeditorpanemodels = new Jcmjeditorpanebuilder(apml, tagname, JEditorPane.class).builder.build();

            case "//jtextpane": return this.jtextpanemodels = new Jcmjtextpanebuilder(apml, tagname, JTextPane.class).builder.build(); 

            case "//jtree": return this.jtreemodels = new Jcmjtreebuilder(apml, tagname, JTree.class).builder.build(); 

            case "//jlabel": return this.jlabelmodels = new Jcmjlabelbuilder(apml, tagname, JLabel.class).builder.build(); 

            case "//jprogressbar": return this.jprogressbarmodels = new Jcmjprogressbarbuilder(apml, tagname, JProgressBar.class).builder.build();

            case "//jseparator": return this.jseparatormodels = new Jcmjseparatorbuilder(apml, tagname, JSeparator.class).builder.build();

            case "//jtooltip": return this.jtooltipmodels = new Jcmjtooltipbuilder(apml, tagname, JToolTip.class).builder.build();

            case "//japplet": return this.jappletmodels = new Jcmjappletbuilder(apml, tagname, JApplet.class).builder.build();

            case "//jpanel": return this.jpanelmodels = new Jcmjpanelbuilder(apml, tagname, JPanel.class).builder.build();

            case "//jscrollpane": return this.jscrollpanemodels = new Jcmjscrollpanebuilder(apml, tagname, JScrollPane.class).builder.build();

            case "//jsplitpane": return this.jsplitpanemodels = new Jcmjsplitpanebuilder(apml, tagname, JSplitPane.class).builder.build();

            case "//jtabbedpane": return this.jtabbedpanemodels = new Jcmjtabbedpanebuilder(apml, tagname, JTabbedPane.class).builder.build();

            case "//jtoolbar": return this.jtoolbarmodels = new Jcmjtoolbarbuilder(apml, tagname, JToolBar.class).builder.build();

            case "//jinternalframe": return this.jinternalframemodels = new Jcmjinternalframebuilder(apml, tagname, JInternalFrame.class).builder.build();

            case "//jlayeredpane": return this.jlayeredpanemodels = new Jcmjlayeredpanebuilder(apml, tagname, JLayeredPane.class).builder.build();

            case "//jrootpane": return this.jrootpanemodels = new Jcmjrootpanebuilder(apml, tagname, JRootPane.class).builder.build();            
        }        
        
        return new ArrayList<>();
    }     
}
