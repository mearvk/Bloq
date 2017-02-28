package apml.ui.compilers.java;

import com.sun.codemodel.JCodeModel;
import java.util.ArrayList;

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
        //javac 
    }
    
    public void setsourcefiles(Uioutputmanager outputmanager)
    {               
        //
        outputmanager.generaterelations(this.apmlmanager.jbuttonmodels);
        
        //
        outputmanager.generaterelations(this.apmlmanager.jframemodels);
        
        //
        outputmanager.generaterelations(this.apmlmanager.framemodels);
        
        //
        outputmanager.generaterelations(this.apmlmanager.jcheckboxmodels);
        
        //
        outputmanager.generaterelations(this.apmlmanager.jcomboboxmodels);
        
        //
        outputmanager.generaterelations(this.apmlmanager.jlistmodels);
        
        //
        outputmanager.generaterelations(this.apmlmanager.jmenumodels);
        
        //
        outputmanager.generaterelations(this.apmlmanager.jmenubarmodels);

        //
        outputmanager.generaterelations(this.apmlmanager.jmenuitemmodels);        
        
        //
        outputmanager.generaterelations(this.apmlmanager.jradiobuttonmodels);
        
        //
        outputmanager.generaterelations(this.apmlmanager.jslidermodels);
        
        //
        outputmanager.generaterelations(this.apmlmanager.jspinnermodels);
        
        //
        outputmanager.generaterelations(this.apmlmanager.jtextfieldmodels);
        
        //
        outputmanager.generaterelations(this.apmlmanager.jpasswordfieldmodels);
        
        //
        outputmanager.generaterelations(this.apmlmanager.jcolorchoosermodels);
        
        //
        outputmanager.generaterelations(this.apmlmanager.jeditorpanemodels);
        
        //
        outputmanager.generaterelations(this.apmlmanager.jtextpanemodels);
        
        //
        outputmanager.generaterelations(this.apmlmanager.jfilechoosermodels);
        
        //
        outputmanager.generaterelations(this.apmlmanager.jtablemodels);
        
        //
        outputmanager.generaterelations(this.apmlmanager.jtextareamodels);
        
        //
        outputmanager.generaterelations(this.apmlmanager.jtreemodels);
        
        //
        outputmanager.generaterelations(this.apmlmanager.jlabelmodels);
        
        //
        outputmanager.generaterelations(this.apmlmanager.jprogressbarmodels);
        
        //
        outputmanager.generaterelations(this.apmlmanager.jseparatormodels);
        
        //
        outputmanager.generaterelations(this.apmlmanager.jtooltipmodels);
        
        //
        outputmanager.generaterelations(this.apmlmanager.jappletmodels);
        
        //
        outputmanager.generaterelations(this.apmlmanager.jdialogmodels);
        
        //
        outputmanager.generaterelations(this.apmlmanager.jpanelmodels);
        
        //
        outputmanager.generaterelations(this.apmlmanager.jscrollpanemodels);
        
        //
        outputmanager.generaterelations(this.apmlmanager.jsplitpanemodels);
        
        //
        outputmanager.generaterelations(this.apmlmanager.jtabbedpanemodels);
        
        //
        outputmanager.generaterelations(this.apmlmanager.jtoolbarmodels);
        
        //
        outputmanager.generaterelations(this.apmlmanager.jinternalframemodels);
        
        //
        outputmanager.generaterelations(this.apmlmanager.jlayeredpanemodels);
        
        //
        outputmanager.generaterelations(this.apmlmanager.jrootpanemodels);        
    }
    
    public void setoutputfiles()
    {
        
    }
    
    private void doapmlfiles()
    {
        
    }
}