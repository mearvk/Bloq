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
            case "//jbutton": return jbuttonmodels = new Jcmjbuttonbuilder(apml).builder.build(tagname, JButton.class);

            case "//jframe": return jframemodels =  new Jcmjframebuilder(apml).builder.build(tagname, JFrame.class); 

            case "//frame": return framemodels =  new Jcmframebuilder(apml).builder.build(tagname, Frame.class); 

            case "//jcheckbox": return jcheckboxmodels = new Jcmjcheckboxbuilder(apml).builder.build(tagname, JCheckBox.class); 

            case "//jcombobox": return jcomboboxmodels = new Jcmjcomboboxbuilder(apml).builder.build(tagname, JComboBox.class); 

            case "//jlist": return jlistmodels = new Jcmjlistbuilder(apml).builder.build(tagname, JList.class); 

            case "//jmenu": return jmenumodels = new Jcmjmenubuilder(apml).builder.build(tagname, JMenu.class);
            
            case "//jmenubar": return jmenubarmodels = new Jcmjmenubarbuilder(apml).builder.build(tagname, JMenuBar.class);
            
            case "//jmenuitem": return jmenuitemmodels = new Jcmjmenuitembuilder(apml).builder.build(tagname, JMenuItem.class);

            case "//jradiobutton": return jradiobuttonmodels = new Jcmjradiobuttonbuilder(apml).builder.build(tagname, JRadioButton.class); 

            case "//jslider": return jslidermodels = new Jcmjsliderbuilder(apml).builder.build(tagname, JSlider.class); 

            case "//jspinner": return jspinnermodels = new Jcmjspinnerbuilder(apml).builder.build(tagname, JSpinner.class); 

            case "//jtextfield": return jtextfieldmodels = new Jcmjtextfieldbuilder(apml).builder.build(tagname, JTextField.class); 

            case "//jpasswordfield": return jpasswordfieldmodels = new Jcmjpasswordfieldbuilder(apml).builder.build(tagname, JPasswordField.class); 

            case "//jcolorchooser": return jcolorchoosermodels = new Jcmjcolorchooserbuilder(apml).builder.build(tagname, JColorChooser.class); 

            case "//jeditorpane": return jeditorpanemodels = new Jcmjeditorpanebuilder(apml).builder.build(tagname, JEditorPane.class); 

            case "//jtextpane": return jtextpanemodels = new Jcmjtextpanebuilder(apml).builder.build(tagname, JTextPane.class); 

            case "//jtree": return jtreemodels = new Jcmjtreebuilder(apml).builder.build(tagname, JTree.class); 

            case "//jlabel": return jlabelmodels = new Jcmjlabelbuilder(apml).builder.build(tagname, JLabel.class); 

            case "//jprogressbar": return jprogressbarmodels = new Jcmjprogressbarbuilder(apml).builder.build(tagname, JProgressBar.class); 

            case "//jseparator": return jseparatormodels = new Jcmjseparatorbuilder(apml).builder.build(tagname, JSeparator.class);

            case "//jtooltip": return jtooltipmodels = new Jcmjtooltipbuilder(apml).builder.build(tagname, JToolTip.class);

            case "//japplet": return jappletmodels = new Jcmjappletbuilder(apml).builder.build(tagname, JApplet.class);

            case "//jpanel": return jpanelmodels = new Jcmjpanelbuilder(apml).builder.build(tagname, JPanel.class);

            case "//jscrollpane": return jscrollpanemodels = new Jcmjscrollpanebuilder(apml).builder.build(tagname, JScrollPane.class);

            case "//jsplitpane": return jsplitpanemodels = new Jcmjsplitpanebuilder(apml).builder.build(tagname, JSplitPane.class);

            case "//jtabbedpane": return jtabbedpanemodels = new Jcmjtabbedpanebuilder(apml).builder.build(tagname, JTabbedPane.class);

            case "//jtoolbar": return jtoolbarmodels = new Jcmjtoolbarbuilder(apml).builder.build(tagname, JToolBar.class);

            case "//jinternalframe": return jinternalframemodels = new Jcmjinternalframebuilder(apml).builder.build(tagname, JInternalFrame.class);

            case "//jlayeredpane": return jlayeredpanemodels = new Jcmjlayeredpanebuilder(apml).builder.build(tagname, JLayeredPane.class);

            case "//jrootpane": return jrootpanemodels = new Jcmjrootpanebuilder(apml).builder.build(tagname, JRootPane.class);            
        }        
        
        return new ArrayList<>();
    }     
}
