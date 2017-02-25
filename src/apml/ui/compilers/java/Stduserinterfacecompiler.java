package apml.ui.compilers.java;

import java.util.ArrayList;
import javax.swing.JButton;

/**
 * @author max rupplin
 * @see http://github.com/mearvk/Bloq
 */
public class Stduserinterfacecompiler
{
    public Uiapmlmanager apmlmanager = new Uiapmlmanager(); 
    
    public Uioutputmanager outputmanager = new Uioutputmanager();
    
    public Uifileguardian fileguardian = new Uifileguardian(); 
    
    public Stduserinterfacecompiler()
    {
        
    }

    public void setapmlfiles(Uiapmlmanager apmlmanager)
    {
        //
        apmlmanager.jbuttonmodels = apmlmanager.generatemodels(fileguardian.xmlin, "//jbutton");
        
        //
        apmlmanager.jframemodels = apmlmanager.generatemodels(fileguardian.xmlin, "//jframe");
        
        //
        apmlmanager.framemodels = apmlmanager.generatemodels(fileguardian.xmlin, "//frame");
        
        //
        apmlmanager.jcheckboxmodels = apmlmanager.generatemodels(fileguardian.xmlin, "//jcheckbox");
        
        //
        apmlmanager.jcomboboxmodels = apmlmanager.generatemodels(fileguardian.xmlin, "//jcombobox");
        
        //
        apmlmanager.jlists = apmlmanager.generatemodels(fileguardian.xmlin, "//jlist");
        
        //
        apmlmanager.jmenus = apmlmanager.generatemodels(fileguardian.xmlin, "//jmenu");
        
        //
        apmlmanager.jradiobuttons = apmlmanager.generatemodels(fileguardian.xmlin, "//jradiobutton");
        
        //
        apmlmanager.jsliders = apmlmanager.generatemodels(fileguardian.xmlin, "//jslider");
        
        //
        apmlmanager.jspinners = apmlmanager.generatemodels(fileguardian.xmlin, "//jspinner");
        
        //
        apmlmanager.jtextfields = apmlmanager.generatemodels(fileguardian.xmlin, "//jtextfield");
        
        //
        apmlmanager.jpasswordfields = apmlmanager.generatemodels(fileguardian.xmlin, "//jpasswordfield");
        
        //
        apmlmanager.jcolorchoosers = apmlmanager.generatemodels(fileguardian.xmlin, "//jcolorchooser");
        
        //
        apmlmanager.jeditorpanes = apmlmanager.generatemodels(fileguardian.xmlin, "//jeditorpane");
        
        //
        apmlmanager.jtextpanes = apmlmanager.generatemodels(fileguardian.xmlin, "//jtextpane");
        
        //
        apmlmanager.jfilechoosers = apmlmanager.generatemodels(fileguardian.xmlin, "//jfilechooser");
        
        //
        apmlmanager.jtable = apmlmanager.generatemodels(fileguardian.xmlin, "//jtable");
        
        //
        apmlmanager.jtextareas = apmlmanager.generatemodels(fileguardian.xmlin, "//jtextarea");
        
        //
        apmlmanager.jtrees = apmlmanager.generatemodels(fileguardian.xmlin, "//jtree");
        
        //
        apmlmanager.jlabels = apmlmanager.generatemodels(fileguardian.xmlin, "//jlabels");
        
        //
        apmlmanager.jprogressbars = apmlmanager.generatemodels(fileguardian.xmlin, "//jprogressbar");
        
        //
        apmlmanager.jseparators = apmlmanager.generatemodels(fileguardian.xmlin, "//jseparator");
        
        //
        apmlmanager.jtooltips = apmlmanager.generatemodels(fileguardian.xmlin, "//jtooltip");
        
        //
        apmlmanager.japplets = apmlmanager.generatemodels(fileguardian.xmlin, "//japplet");
        
        //
        apmlmanager.jdialogs = apmlmanager.generatemodels(fileguardian.xmlin, "//jdialog");
        
        //
        apmlmanager.jpanels = apmlmanager.generatemodels(fileguardian.xmlin, "//jpanel");
        
        //
        apmlmanager.jscrollpanes = apmlmanager.generatemodels(fileguardian.xmlin, "//jscrollpane");
        
        //
        apmlmanager.jsplitpanes = apmlmanager.generatemodels(fileguardian.xmlin, "//jsplitpane");
        
        //
        apmlmanager.jtabbedpanes = apmlmanager.generatemodels(fileguardian.xmlin, "//jtabbedpane");
        
        //
        apmlmanager.jtoolbars = apmlmanager.generatemodels(fileguardian.xmlin, "//jtoolbar");
        
        //
        apmlmanager.jinternalframes = apmlmanager.generatemodels(fileguardian.xmlin, "//jinternalframe");
        
        //
        apmlmanager.jlayeredpanes = apmlmanager.generatemodels(fileguardian.xmlin, "//jlayeredpane");
        
        //
        apmlmanager.jrootpanes = apmlmanager.generatemodels(fileguardian.xmlin, "//jrootpane");
    }
    
    public void settempfiles(Uiapmlmanager apmlmanager)
    {
        
    }
    
    public void setsourcefiles(Uioutputmanager outputmanager)
    {
        
    }
    
    public void setoutputfiles()
    {
        
    }
    
    private void doapmlfiles()
    {
        
    }
}