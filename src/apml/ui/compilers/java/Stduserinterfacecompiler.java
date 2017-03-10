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
        
        compiler.setsourcefiles(compiler.outputmanager);        
    }
    
    public Stduserinterfacecompiler()
    {
        
    }

    /**
     * @author bodoprimsys 1.0 **_
     * 
     * @param apmlmanager 
     */
    public void setapmlfiles(Uiapmlmanager apmlmanager)
    {
        //
        apmlmanager.generatejcmmodels(this.fileguardian.xmlin, "//jbutton");
        
        //
        apmlmanager.generatejcmmodels(this.fileguardian.xmlin, "//jframe");
        
        //
        apmlmanager.generatejcmmodels(this.fileguardian.xmlin, "//frame");
        
        //
        apmlmanager.generatejcmmodels(this.fileguardian.xmlin, "//jcheckbox");
        
        //
        apmlmanager.generatejcmmodels(this.fileguardian.xmlin, "//jcombobox");
        
        //
        apmlmanager.generatejcmmodels(this.fileguardian.xmlin, "//jlist");
        
        //
        apmlmanager.generatejcmmodels(this.fileguardian.xmlin, "//jmenu");
        
        //
        apmlmanager.generatejcmmodels(this.fileguardian.xmlin, "//jmenubar");

        //
        apmlmanager.generatejcmmodels(this.fileguardian.xmlin, "//jmenuitem");        
        
        //
        apmlmanager.generatejcmmodels(this.fileguardian.xmlin, "//jradiobutton");
        
        //
        apmlmanager.generatejcmmodels(this.fileguardian.xmlin, "//jslider");
        
        //
        apmlmanager.generatejcmmodels(this.fileguardian.xmlin, "//jspinner");
        
        //
        apmlmanager.generatejcmmodels(this.fileguardian.xmlin, "//jtextfield");
        
        //
        apmlmanager.generatejcmmodels(this.fileguardian.xmlin, "//jpasswordfield");
        
        //
        apmlmanager.generatejcmmodels(this.fileguardian.xmlin, "//jcolorchooser");
        
        //
        apmlmanager.generatejcmmodels(this.fileguardian.xmlin, "//jeditorpane");
        
        //
        apmlmanager.generatejcmmodels(this.fileguardian.xmlin, "//jtextpane");
        
        //
        apmlmanager.generatejcmmodels(this.fileguardian.xmlin, "//jfilechooser");
        
        //
        apmlmanager.generatejcmmodels(this.fileguardian.xmlin, "//jtable");
        
        //
        apmlmanager.generatejcmmodels(this.fileguardian.xmlin, "//jtextarea");
        
        //
        apmlmanager.generatejcmmodels(this.fileguardian.xmlin, "//jtree");
        
        //
        apmlmanager.generatejcmmodels(this.fileguardian.xmlin, "//jlabels");
        
        //
        apmlmanager.generatejcmmodels(this.fileguardian.xmlin, "//jprogressbar");
        
        //
        apmlmanager.generatejcmmodels(this.fileguardian.xmlin, "//jseparator");
        
        //
        apmlmanager.generatejcmmodels(this.fileguardian.xmlin, "//jtooltip");
        
        //
        apmlmanager.generatejcmmodels(this.fileguardian.xmlin, "//japplet");
        
        //
        apmlmanager.generatejcmmodels(this.fileguardian.xmlin, "//jdialog");
        
        //
        apmlmanager.generatejcmmodels(this.fileguardian.xmlin, "//jpanel");
        
        //
        apmlmanager.generatejcmmodels(this.fileguardian.xmlin, "//jscrollpane");
        
        //
        apmlmanager.generatejcmmodels(this.fileguardian.xmlin, "//jsplitpane");
        
        //
        apmlmanager.generatejcmmodels(this.fileguardian.xmlin, "//jtabbedpane");
        
        //
        apmlmanager.generatejcmmodels(this.fileguardian.xmlin, "//jtoolbar");
        
        //
        apmlmanager.generatejcmmodels(this.fileguardian.xmlin, "//jinternalframe");
        
        //
        apmlmanager.generatejcmmodels(this.fileguardian.xmlin, "//jlayeredpane");
        
        //
        apmlmanager.generatejcmmodels(this.fileguardian.xmlin, "//jrootpane");
    }
    
    public void settempfiles(Uiapmlmanager apmlmanager)
    {
        //javac etc
    }
    
    public void setsourcefiles(Uioutputmanager outputmanager)
    {               
        //
        outputmanager.generatejavafiles(this.apmlmanager.jbuttonmodels);
        
        //
        outputmanager.generatejavafiles(this.apmlmanager.jframemodels);
        
        //
        outputmanager.generatejavafiles(this.apmlmanager.framemodels);
        
        //
        outputmanager.generatejavafiles(this.apmlmanager.jcheckboxmodels);
        
        //
        outputmanager.generatejavafiles(this.apmlmanager.jcomboboxmodels);
        
        //
        outputmanager.generatejavafiles(this.apmlmanager.jlistmodels);
        
        //
        outputmanager.generatejavafiles(this.apmlmanager.jmenumodels);
        
        //
        outputmanager.generatejavafiles(this.apmlmanager.jmenubarmodels);

        //
        outputmanager.generatejavafiles(this.apmlmanager.jmenuitemmodels);        
        
        //
        outputmanager.generatejavafiles(this.apmlmanager.jradiobuttonmodels);
        
        //
        outputmanager.generatejavafiles(this.apmlmanager.jslidermodels);
        
        //
        outputmanager.generatejavafiles(this.apmlmanager.jspinnermodels);
        
        //
        outputmanager.generatejavafiles(this.apmlmanager.jtextfieldmodels);
        
        //
        outputmanager.generatejavafiles(this.apmlmanager.jpasswordfieldmodels);
        
        //
        outputmanager.generatejavafiles(this.apmlmanager.jcolorchoosermodels);
        
        //
        outputmanager.generatejavafiles(this.apmlmanager.jeditorpanemodels);
        
        //
        outputmanager.generatejavafiles(this.apmlmanager.jtextpanemodels);
        
        //
        outputmanager.generatejavafiles(this.apmlmanager.jfilechoosermodels);
        
        //
        outputmanager.generatejavafiles(this.apmlmanager.jtablemodels);
        
        //
        outputmanager.generatejavafiles(this.apmlmanager.jtextareamodels);
        
        //
        outputmanager.generatejavafiles(this.apmlmanager.jtreemodels);
        
        //
        outputmanager.generatejavafiles(this.apmlmanager.jlabelmodels);
        
        //
        outputmanager.generatejavafiles(this.apmlmanager.jprogressbarmodels);
        
        //
        outputmanager.generatejavafiles(this.apmlmanager.jseparatormodels);
        
        //
        outputmanager.generatejavafiles(this.apmlmanager.jtooltipmodels);
        
        //
        outputmanager.generatejavafiles(this.apmlmanager.jappletmodels);
        
        //
        outputmanager.generatejavafiles(this.apmlmanager.jdialogmodels);
        
        //
        outputmanager.generatejavafiles(this.apmlmanager.jpanelmodels);
        
        //
        outputmanager.generatejavafiles(this.apmlmanager.jscrollpanemodels);
        
        //
        outputmanager.generatejavafiles(this.apmlmanager.jsplitpanemodels);
        
        //
        outputmanager.generatejavafiles(this.apmlmanager.jtabbedpanemodels);
        
        //
        outputmanager.generatejavafiles(this.apmlmanager.jtoolbarmodels);
        
        //
        outputmanager.generatejavafiles(this.apmlmanager.jinternalframemodels);
        
        //
        outputmanager.generatejavafiles(this.apmlmanager.jlayeredpanemodels);
        
        //
        outputmanager.generatejavafiles(this.apmlmanager.jrootpanemodels);        
    }
    
    public void setoutputfiles()
    {
        
    }
    
    private void doapmlfiles()
    {
        
    }
}