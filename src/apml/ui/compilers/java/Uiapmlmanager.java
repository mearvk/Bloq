/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apml.ui.compilers.java;

import java.io.File;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComponent;


/**
 * @author max rupplin
 */
public class Uiapmlmanager
{
    ArrayList<? extends JComponent> jbuttonmodels;
    ArrayList<? extends JComponent> jframemodels;
    ArrayList<? extends JComponent> framemodels;
    ArrayList<? extends JComponent> jcheckboxmodels;
    ArrayList<? extends JComponent> jcomboboxmodels;
    ArrayList<? extends JComponent> jlistmodels;
    ArrayList<? extends JComponent> jmenumodels;
    ArrayList<? extends JComponent> jradiobuttonmodels;
    ArrayList<? extends JComponent> jslidermodels;
    ArrayList<? extends JComponent> jspinnermodels;
    ArrayList<? extends JComponent> jtextfieldmodels;
    ArrayList<? extends JComponent> jpasswordfieldmodels;  
    ArrayList<? extends JComponent> jcolorchoosermodels;
    ArrayList<? extends JComponent> jeditorpanemodels;
    ArrayList<? extends JComponent> jtextpanemodels;
    ArrayList<? extends JComponent> jfilechoosermodels;
    ArrayList<? extends JComponent> jtablemodels;
    ArrayList<? extends JComponent> jtextareamodels;
    ArrayList<? extends JComponent> jtreemodels;    
    ArrayList<? extends JComponent> jlabelmodels;
    ArrayList<? extends JComponent> jprogressbarmodels;
    ArrayList<? extends JComponent> jseparatormodels;
    ArrayList<? extends JComponent> jtooltipmodels;
    ArrayList<? extends JComponent> jappletmodels;
    ArrayList<? extends JComponent> jdialogmodels; 
    ArrayList<? extends JComponent> jpanelmodels;
    ArrayList<? extends JComponent> jscrollpanemodels;
    ArrayList<? extends JComponent> jsplitpanemodels;
    ArrayList<? extends JComponent> jtabbedpanemodels;
    ArrayList<? extends JComponent> jtoolbarmodels;
    ArrayList<? extends JComponent> jinternalframemodels;    
    ArrayList<? extends JComponent> jlayeredpanemodels;
    ArrayList<? extends JComponent> jrootpanemodels;
        
    public ArrayList<? extends JComponent> generatemodels(File file, String tagname)            
    {
        switch(tagname)
        {
            case "//jbutton": return null;
            
            case "//jframe": return null; 
            
            case "//frame": return null; 
            
            case "//jcheckbox": return null; 
            
            case "//jcombobox": return null; 
            
            case "//jlist": return null; 
            
            case "//jmenu": return null; 
            
            case "//jradiobutton": return null; 
            
            case "//jslider": return null; 
            
            case "//jspinner": return null; 
            
            case "//jtextfield": return null; 
            
            case "//jpasswordfield": return null; 
            
            case "//jcolorchooser": return null; 
            
            case "//jeditorpane": return null; 
            
            case "//jtextpane": return null; 
            
            case "//jtree": return null; 
            
            case "//jlabel": return null; 
            
            case "//jprogressbar": return null; 
            
            case "//jseparator": return null;
            
            case "//jtooltip": return null;
            
            case "//japplet": return null;
            
            case "//jpanel": return null;
            
            case "//jscrollpane": return null;
            
            case "//jsplitpane": return null;
            
            case "//jtabbedpane": return null;
            
            case "//jtoolbar": return null;
            
            case "//jinternalframe": return null;
            
            case "//jlayeredpane": return null;
            
            case "//jrootpane": return null;            
        }
        
        return new ArrayList<JButton>(1);
    }
}
