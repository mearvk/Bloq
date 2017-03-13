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
    
    public Xpathstrings xpathmodels = new Xpathstrings();
    
    public Jcmmodels jcmmodels = new Jcmmodels();              
    
    public Object generaterelations(File apml, String tagname)
    {
        return null;
    }
    
    public ArrayList<JCodeModel> generatejcmmodels(File apml, String tagname)            
    {            
        switch(tagname)
        {
            case "//jbutton": return this.jcmmodels.jbuttonmodels = new Jcmjbuttonbuilder(apml, tagname, JButton.class).builder.build();

            case "//jframe": return this.jcmmodels.jframemodels =  new Jcmjframebuilder(apml, tagname, JFrame.class).builder.build();

            case "//frame": return this.jcmmodels.framemodels =  new Jcmframebuilder(apml, tagname, Frame.class).builder.build(); 

            case "//jcheckbox": return this.jcmmodels.jcheckboxmodels = new Jcmjcheckboxbuilder(apml, tagname, JCheckBox.class).builder.build(); 

            case "//jcombobox": return this.jcmmodels.jcomboboxmodels = new Jcmjcomboboxbuilder(apml, tagname, JComboBox.class).builder.build(); 

            case "//jlist": return this.jcmmodels.jlistmodels = new Jcmjlistbuilder(apml, tagname, JList.class).builder.build();

            case "//jmenu": return this.jcmmodels.jmenumodels = new Jcmjmenubuilder(apml, tagname, JMenu.class).builder.build();
            
            case "//jmenubar": return this.jcmmodels.jmenubarmodels = new Jcmjmenubarbuilder(apml, tagname, JMenuBar.class).builder.build();
            
            case "//jmenuitem": return this.jcmmodels.jmenuitemmodels = new Jcmjmenuitembuilder(apml, tagname, JMenuItem.class).builder.build();

            case "//jradiobutton": return this.jcmmodels.jradiobuttonmodels = new Jcmjradiobuttonbuilder(apml, tagname, JRadioButton.class).builder.build(); 

            case "//jslider": return this.jcmmodels.jslidermodels = new Jcmjsliderbuilder(apml, tagname, JSlider.class).builder.build();

            case "//jspinner": return this.jcmmodels.jspinnermodels = new Jcmjspinnerbuilder(apml, tagname, JSpinner.class).builder.build();

            case "//jtextfield": return this.jcmmodels.jtextfieldmodels = new Jcmjtextfieldbuilder(apml, tagname, JTextField.class).builder.build(); 

            case "//jpasswordfield": return this.jcmmodels.jpasswordfieldmodels = new Jcmjpasswordfieldbuilder(apml, tagname, JPasswordField.class).builder.build();

            case "//jcolorchooser": return this.jcmmodels.jcolorchoosermodels = new Jcmjcolorchooserbuilder(apml, tagname, JColorChooser.class).builder.build();

            case "//jeditorpane": return this.jcmmodels.jeditorpanemodels = new Jcmjeditorpanebuilder(apml, tagname, JEditorPane.class).builder.build();

            case "//jtextpane": return this.jcmmodels.jtextpanemodels = new Jcmjtextpanebuilder(apml, tagname, JTextPane.class).builder.build(); 

            case "//jtree": return this.jcmmodels.jtreemodels = new Jcmjtreebuilder(apml, tagname, JTree.class).builder.build(); 

            case "//jlabel": return this.jcmmodels.jlabelmodels = new Jcmjlabelbuilder(apml, tagname, JLabel.class).builder.build(); 

            case "//jprogressbar": return this.jcmmodels.jprogressbarmodels = new Jcmjprogressbarbuilder(apml, tagname, JProgressBar.class).builder.build();

            case "//jseparator": return this.jcmmodels.jseparatormodels = new Jcmjseparatorbuilder(apml, tagname, JSeparator.class).builder.build();

            case "//jtooltip": return this.jcmmodels.jtooltipmodels = new Jcmjtooltipbuilder(apml, tagname, JToolTip.class).builder.build();

            case "//japplet": return this.jcmmodels.jappletmodels = new Jcmjappletbuilder(apml, tagname, JApplet.class).builder.build();

            case "//jpanel": return this.jcmmodels.jpanelmodels = new Jcmjpanelbuilder(apml, tagname, JPanel.class).builder.build();

            case "//jscrollpane": return this.jcmmodels.jscrollpanemodels = new Jcmjscrollpanebuilder(apml, tagname, JScrollPane.class).builder.build();

            case "//jsplitpane": return this.jcmmodels.jsplitpanemodels = new Jcmjsplitpanebuilder(apml, tagname, JSplitPane.class).builder.build();

            case "//jtabbedpane": return this.jcmmodels.jtabbedpanemodels = new Jcmjtabbedpanebuilder(apml, tagname, JTabbedPane.class).builder.build();

            case "//jtoolbar": return this.jcmmodels.jtoolbarmodels = new Jcmjtoolbarbuilder(apml, tagname, JToolBar.class).builder.build();

            case "//jinternalframe": return this.jcmmodels.jinternalframemodels = new Jcmjinternalframebuilder(apml, tagname, JInternalFrame.class).builder.build();

            case "//jlayeredpane": return this.jcmmodels.jlayeredpanemodels = new Jcmjlayeredpanebuilder(apml, tagname, JLayeredPane.class).builder.build();

            case "//jrootpane": return this.jcmmodels.jrootpanemodels = new Jcmjrootpanebuilder(apml, tagname, JRootPane.class).builder.build();            
        }        
        
        return new ArrayList<>();
    }     
}
