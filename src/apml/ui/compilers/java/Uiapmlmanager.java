package apml.ui.compilers.java;

import apml.ui.compilers.java.builder.Jcmjtabbedpanebuilder;
import apml.ui.compilers.java.builder.Jcmjlabelbuilder;
import apml.ui.compilers.java.builder.Jcmjmenubuilder;
import apml.ui.compilers.java.builder.Jcmjappletbuilder;
import apml.ui.compilers.java.builder.Jcmjpasswordbuilder;
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
import com.sun.codemodel.JCodeModel;
import java.io.File;
import java.util.ArrayList;

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
    
    public ArrayList<JCodeModel> generatemodels(File apml, String tagname)            
    {            
        switch(tagname)
        {
            case "//jbutton": return new Jcmjbuttonbuilder(apml).builder.build(tagname);

            case "//jframe": return new Jcmjframebuilder(apml).builder.build(tagname); 

            case "//frame": return new Jcmframebuilder(apml).builder.build(tagname); 

            case "//jcheckbox": return new Jcmjcheckboxbuilder(apml).builder.build(tagname); 

            case "//jcombobox": return new Jcmjcomboboxbuilder(apml).builder.build(tagname); 

            case "//jlist": return new Jcmjlistbuilder(apml).builder.build(tagname); 

            case "//jmenu": return new Jcmjmenubuilder(apml).builder.build(tagname);

            case "//jradiobutton": return new Jcmjradiobuttonbuilder(apml).builder.build(tagname); 

            case "//jslider": return new Jcmjsliderbuilder(apml).builder.build(tagname); 

            case "//jspinner": return new Jcmjspinnerbuilder(apml).builder.build(tagname); 

            case "//jtextfield": return new Jcmjtextfieldbuilder(apml).builder.build(tagname); 

            case "//jpasswordfield": return new Jcmjpasswordbuilder(apml).builder.build(tagname); 

            case "//jcolorchooser": return new Jcmjcolorchooserbuilder(apml).builder.build(tagname); 

            case "//jeditorpane": return new Jcmjeditorpanebuilder(apml).builder.build(tagname); 

            case "//jtextpane": return new Jcmjtextpanebuilder(apml).builder.build(tagname); 

            case "//jtree": return new Jcmjtreebuilder(apml).builder.build(tagname); 

            case "//jlabel": return new Jcmjlabelbuilder(apml).builder.build(tagname); 

            case "//jprogressbar": return new Jcmjprogressbarbuilder(apml).builder.build(tagname); 

            case "//jseparator": return new Jcmjseparatorbuilder(apml).builder.build(tagname);

            case "//jtooltip": return new Jcmjtooltipbuilder(apml).builder.build(tagname);

            case "//japplet": return new Jcmjappletbuilder(apml).builder.build(tagname);

            case "//jpanel": return new Jcmjpanelbuilder(apml).builder.build(tagname);

            case "//jscrollpane": return new Jcmjscrollpanebuilder(apml).builder.build(tagname);

            case "//jsplitpane": return new Jcmjsplitpanebuilder(apml).builder.build(tagname);

            case "//jtabbedpane": return new Jcmjtabbedpanebuilder(apml).builder.build(tagname);

            case "//jtoolbar": return new Jcmjtoolbarbuilder(apml).builder.build(tagname);

            case "//jinternalframe": return new Jcmjinternalframebuilder(apml).builder.build(tagname);

            case "//jlayeredpane": return new Jcmjlayeredpanebuilder(apml).builder.build(tagname);

            case "//jrootpane": return new Jcmjrootpanebuilder(apml).builder.build(tagname);            
        }        
        
        return new ArrayList<>();
    }     
}
