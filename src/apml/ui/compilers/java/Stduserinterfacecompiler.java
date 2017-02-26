package apml.ui.compilers.java;

/**
 * @author max rupplin
 * @see http://github.com/mearvk/Bloq
 */
public class Stduserinterfacecompiler
{
    public Uiapmlmanager apmlmanager = new Uiapmlmanager(); 
    
    public Uioutputmanager outputmanager = new Uioutputmanager();
    
    public Uifileguardian fileguardian = new Uifileguardian(); 
    
    public static void main(String...args)
    {
        Stduserinterfacecompiler compiler = new Stduserinterfacecompiler();
        
        compiler.setapmlfiles(compiler.apmlmanager);
    }
    
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
        apmlmanager.jlistmodels = apmlmanager.generatemodels(fileguardian.xmlin, "//jlist");
        
        //
        apmlmanager.jmenumodels = apmlmanager.generatemodels(fileguardian.xmlin, "//jmenu");
        
        //
        apmlmanager.jmenubarmodels = apmlmanager.generatemodels(fileguardian.xmlin, "//jmenubar");

        //
        apmlmanager.jmenuitemmodels = apmlmanager.generatemodels(fileguardian.xmlin, "//jmenuitem");        
        
        //
        apmlmanager.jradiobuttonmodels = apmlmanager.generatemodels(fileguardian.xmlin, "//jradiobutton");
        
        //
        apmlmanager.jslidermodels = apmlmanager.generatemodels(fileguardian.xmlin, "//jslider");
        
        //
        apmlmanager.jspinnermodels = apmlmanager.generatemodels(fileguardian.xmlin, "//jspinner");
        
        //
        apmlmanager.jtextfieldmodels = apmlmanager.generatemodels(fileguardian.xmlin, "//jtextfield");
        
        //
        apmlmanager.jpasswordfieldmodels = apmlmanager.generatemodels(fileguardian.xmlin, "//jpasswordfield");
        
        //
        apmlmanager.jcolorchoosermodels = apmlmanager.generatemodels(fileguardian.xmlin, "//jcolorchooser");
        
        //
        apmlmanager.jeditorpanemodels = apmlmanager.generatemodels(fileguardian.xmlin, "//jeditorpane");
        
        //
        apmlmanager.jtextpanemodels = apmlmanager.generatemodels(fileguardian.xmlin, "//jtextpane");
        
        //
        apmlmanager.jfilechoosermodels = apmlmanager.generatemodels(fileguardian.xmlin, "//jfilechooser");
        
        //
        apmlmanager.jtablemodels = apmlmanager.generatemodels(fileguardian.xmlin, "//jtable");
        
        //
        apmlmanager.jtextareamodels = apmlmanager.generatemodels(fileguardian.xmlin, "//jtextarea");
        
        //
        apmlmanager.jtreemodels = apmlmanager.generatemodels(fileguardian.xmlin, "//jtree");
        
        //
        apmlmanager.jlabelmodels = apmlmanager.generatemodels(fileguardian.xmlin, "//jlabels");
        
        //
        apmlmanager.jprogressbarmodels = apmlmanager.generatemodels(fileguardian.xmlin, "//jprogressbar");
        
        //
        apmlmanager.jseparatormodels = apmlmanager.generatemodels(fileguardian.xmlin, "//jseparator");
        
        //
        apmlmanager.jtooltipmodels = apmlmanager.generatemodels(fileguardian.xmlin, "//jtooltip");
        
        //
        apmlmanager.jappletmodels = apmlmanager.generatemodels(fileguardian.xmlin, "//japplet");
        
        //
        apmlmanager.jdialogmodels = apmlmanager.generatemodels(fileguardian.xmlin, "//jdialog");
        
        //
        apmlmanager.jpanelmodels = apmlmanager.generatemodels(fileguardian.xmlin, "//jpanel");
        
        //
        apmlmanager.jscrollpanemodels = apmlmanager.generatemodels(fileguardian.xmlin, "//jscrollpane");
        
        //
        apmlmanager.jsplitpanemodels = apmlmanager.generatemodels(fileguardian.xmlin, "//jsplitpane");
        
        //
        apmlmanager.jtabbedpanemodels = apmlmanager.generatemodels(fileguardian.xmlin, "//jtabbedpane");
        
        //
        apmlmanager.jtoolbarmodels = apmlmanager.generatemodels(fileguardian.xmlin, "//jtoolbar");
        
        //
        apmlmanager.jinternalframemodels = apmlmanager.generatemodels(fileguardian.xmlin, "//jinternalframe");
        
        //
        apmlmanager.jlayeredpanemodels = apmlmanager.generatemodels(fileguardian.xmlin, "//jlayeredpane");
        
        //
        apmlmanager.jrootpanemodels = apmlmanager.generatemodels(fileguardian.xmlin, "//jrootpane");
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