package apml.ui.compilers.java;

import apml.ui.compilers.java.builders.*;

import java.io.File;

/**
 * Handler for the Bloq UI input (conversion from raw APML/XML input) to modelling in the system as JCM models ready for output
 * 
 * @author Max Rupplin
 * @since 04.30.2017
 * @version Bloq 1.0
 */
public class Uiinputmanager
{
    protected final Integer hash = 0x00888FE8;

	//

    public Uicompiler compiler;

    public Uixpathstrings xpathstrings = new Uixpathstrings();
    
    public Uijcmmodels jcmmodels = new Uijcmmodels();

    public File file;

	/**
	 * @param compiler The compiler object used by Bloq
	 */
	public Uiinputmanager(Uicompiler compiler)
    {
        this.compiler = compiler;

        this.file = this.compiler.fileguardian.xmlin;
    }

    /**
	 *
	 * @param tagname The APML tag element
	 * @return
	 */
	public boolean generatemodels(String tagname)
    {            
        switch(tagname)
        {
            case "//frame":

                this.jcmmodels.framemodels.addAll(new Jcmframebuilder(file).builder.build());
                break;

            case "//japplet":

                this.jcmmodels.jappletmodels.addAll(new Jcmjappletbuilder(file).builder.build());
                break;

            case "//jbutton":

                this.jcmmodels.jbuttonmodels.addAll(new Jcmjbuttonbuilder(file).builder.build());
                break;

            case "//jcheckbox":

                this.jcmmodels.jcheckboxmodels.addAll(new Jcmjcheckboxbuilder(file).builder.build());
                break;

            case "//jcolorchooser":

                this.jcmmodels.jcolorchoosermodels.addAll(new Jcmjcolorchooserbuilder(file).builder.build());
                break;
                
            case "//jcombobox":

                this.jcmmodels.jcomboboxmodels.addAll(new Jcmjcomboboxbuilder(file).builder.build());
                break;

            case "//jeditorpane":

                this.jcmmodels.jeditorpanemodels.addAll(new Jcmjeditorpanebuilder(file).builder.build());
                break;
                
            case "//jfilechooser":

                this.jcmmodels.jfilechoosermodels.addAll(new Jcmjfilechooserbuilder(file).builder.build());
                break;

            case "//jframe":

                this.jcmmodels.jframemodels.addAll(new Jcmjframebuilder(file).builder.build());
                break;

            case "//jinternalframe":

                this.jcmmodels.jinternalframemodels.addAll(new Jcmjinternalframebuilder(file).builder.build());
                break;
                
            case "//jlabel":

                this.jcmmodels.jlabelmodels.addAll(new Jcmjlabelbuilder(file).builder.build());
                break;
                
            case "//jlayeredpane":

                this.jcmmodels.jlayeredpanemodels.addAll(new Jcmjlayeredpanebuilder(file).builder.build());
                break;
                
            case "//jlist":

                this.jcmmodels.jlistmodels.addAll(new Jcmjlistbuilder(file).builder.build());
                break;

            case "//jmenu":

                this.jcmmodels.jmenumodels.addAll(new Jcmjmenubuilder(file).builder.build());
                break;
            
            case "//jmenubar":

                this.jcmmodels.jmenubarmodels.addAll(new Jcmjmenubarbuilder(file).builder.build());
                break;
            
            case "//jmenuitem":

                this.jcmmodels.jmenuitemmodels.addAll(new Jcmjmenuitembuilder(file).builder.build());
                break;

            case "//jpanel":

                this.jcmmodels.jpanelmodels.addAll(new Jcmjpanelbuilder(file).builder.build());
                break;

            case "//jpasswordfield":

                this.jcmmodels.jpasswordfieldmodels.addAll(new Jcmjpasswordfieldbuilder(file).builder.build());
                break;
                
            case "//jprogressbar":

                this.jcmmodels.jprogressbarmodels.addAll(new Jcmjprogressbarbuilder(file).builder.build());
                break;
                
            case "//jradiobutton":

                this.jcmmodels.jradiobuttonmodels.addAll(new Jcmjradiobuttonbuilder(this.file).builder.build());
                break;

            case "//jrootpane":

                this.jcmmodels.jrootpanemodels.addAll(new Jcmjrootpanebuilder(file).builder.build());
                break;
                
            case "//jscrollpane":

                this.jcmmodels.jscrollpanemodels.addAll(new Jcmjscrollpanebuilder(file).builder.build());
                break;
                
            case "//jseparator":

                this.jcmmodels.jseparatormodels.addAll(new Jcmjseparatorbuilder(file).builder.build());
                break;
                
            case "//jslider":

                this.jcmmodels.jslidermodels.addAll(new Jcmjsliderbuilder(file).builder.build());
                break;

            case "//jspinner":

                this.jcmmodels.jspinnermodels.addAll(new Jcmjspinnerbuilder(file).builder.build());
                break;

            case "//jsplitpane":

                this.jcmmodels.jsplitpanemodels.addAll(new Jcmjsplitpanebuilder(file).builder.build());
                break;

            case "//jtable":

                this.jcmmodels.jsplitpanemodels.addAll(new Jcmjtablebuilder(file).builder.build());
                break;
                
            case "//jtabbedpane":

                this.jcmmodels.jtabbedpanemodels.addAll(new Jcmjtabbedpanebuilder(file).builder.build());
                break;
                
            case "//jtextfield":

                this.jcmmodels.jtextfieldmodels.addAll(new Jcmjtextfieldbuilder(file).builder.build());
                break;

            case "//jtextpane":

                this.jcmmodels.jtextpanemodels.addAll(new Jcmjtextpanebuilder(file).builder.build());
                break;

            case "//jtree":

                this.jcmmodels.jtreemodels.addAll(new Jcmjtreebuilder(file).builder.build());
                break;

            case "//jtoolbar":

                this.jcmmodels.jtoolbarmodels.addAll(new Jcmjtoolbarbuilder(file).builder.build());
                break;
                
            case "//jtooltip":

                this.jcmmodels.jtooltipmodels.addAll(new Jcmjtooltipbuilder(file).builder.build());
                break;
        }        
        
        return true;
    }     
}
