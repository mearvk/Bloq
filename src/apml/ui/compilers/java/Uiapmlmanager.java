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
            case "//jbutton": return jbuttonmodels = new Jcmjbuttonbuilder(apml, tagname).builder.build();

            case "//jframe": return jframemodels =  new Jcmjframebuilder(apml, tagname).builder.build();

            case "//frame": return framemodels =  new Jcmframebuilder(apml, tagname).builder.build(); 

            case "//jcheckbox": return jcheckboxmodels = new Jcmjcheckboxbuilder(apml, tagname).builder.build(); 

            case "//jcombobox": return jcomboboxmodels = new Jcmjcomboboxbuilder(apml, tagname).builder.build(); 

            case "//jlist": return jlistmodels = new Jcmjlistbuilder(apml, tagname).builder.build();

            case "//jmenu": return jmenumodels = new Jcmjmenubuilder(apml, tagname).builder.build();
            
            case "//jmenubar": return jmenubarmodels = new Jcmjmenubarbuilder(apml, tagname).builder.build();
            
            case "//jmenuitem": return jmenuitemmodels = new Jcmjmenuitembuilder(apml, tagname).builder.build();

            case "//jradiobutton": return jradiobuttonmodels = new Jcmjradiobuttonbuilder(apml, tagname).builder.build(); 

            case "//jslider": return jslidermodels = new Jcmjsliderbuilder(apml, tagname).builder.build();

            case "//jspinner": return jspinnermodels = new Jcmjspinnerbuilder(apml, tagname).builder.build();

            case "//jtextfield": return jtextfieldmodels = new Jcmjtextfieldbuilder(apml, tagname).builder.build(); 

            case "//jpasswordfield": return jpasswordfieldmodels = new Jcmjpasswordfieldbuilder(apml, tagname).builder.build();

            case "//jcolorchooser": return jcolorchoosermodels = new Jcmjcolorchooserbuilder(apml, tagname).builder.build();

            case "//jeditorpane": return jeditorpanemodels = new Jcmjeditorpanebuilder(apml, tagname).builder.build();

            case "//jtextpane": return jtextpanemodels = new Jcmjtextpanebuilder(apml, tagname).builder.build(); 

            case "//jtree": return jtreemodels = new Jcmjtreebuilder(apml, tagname).builder.build(); 

            case "//jlabel": return jlabelmodels = new Jcmjlabelbuilder(apml, tagname).builder.build(); 

            case "//jprogressbar": return jprogressbarmodels = new Jcmjprogressbarbuilder(apml, tagname).builder.build();

            case "//jseparator": return jseparatormodels = new Jcmjseparatorbuilder(apml, tagname).builder.build();

            case "//jtooltip": return jtooltipmodels = new Jcmjtooltipbuilder(apml, tagname).builder.build();

            case "//japplet": return jappletmodels = new Jcmjappletbuilder(apml, tagname).builder.build();

            case "//jpanel": return jpanelmodels = new Jcmjpanelbuilder(apml, tagname).builder.build();

            case "//jscrollpane": return jscrollpanemodels = new Jcmjscrollpanebuilder(apml, tagname).builder.build();

            case "//jsplitpane": return jsplitpanemodels = new Jcmjsplitpanebuilder(apml, tagname).builder.build();

            case "//jtabbedpane": return jtabbedpanemodels = new Jcmjtabbedpanebuilder(apml, tagname).builder.build();

            case "//jtoolbar": return jtoolbarmodels = new Jcmjtoolbarbuilder(apml, tagname).builder.build();

            case "//jinternalframe": return jinternalframemodels = new Jcmjinternalframebuilder(apml, tagname).builder.build();

            case "//jlayeredpane": return jlayeredpanemodels = new Jcmjlayeredpanebuilder(apml, tagname).builder.build();

            case "//jrootpane": return jrootpanemodels = new Jcmjrootpanebuilder(apml, tagname).builder.build();            
        }        
        
        return new ArrayList<>();
    }     
}
