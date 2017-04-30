package apml.ui.compilers.java;

import com.sun.codemodel.JCodeModel;

import java.util.ArrayList;

/**
 * The list of various JCM models that are filled by Uiinputmanager and readied for Uioutputmanager
 * 
 * @since 04.30.2017
 * @author Max Rupplin
 * @version Bloq 1.0
 */
public class Uijcmmodels
{
    public ArrayList<JCodeModel> jbuttonmodels = new ArrayList<JCodeModel>();

    public ArrayList<JCodeModel> jframemodels = new ArrayList<JCodeModel>();

    public ArrayList<JCodeModel> framemodels = new ArrayList<JCodeModel>();

    public ArrayList<JCodeModel> jcheckboxmodels = new ArrayList<JCodeModel>();

    public ArrayList<JCodeModel> jcomboboxmodels = new ArrayList<JCodeModel>();

    public ArrayList<JCodeModel> jlistmodels = new ArrayList<JCodeModel>();

    public ArrayList<JCodeModel> jmenumodels = new ArrayList<JCodeModel>();

    public ArrayList<JCodeModel> jmenubarmodels = new ArrayList<JCodeModel>();

    public ArrayList<JCodeModel> jmenuitemmodels = new ArrayList<JCodeModel>();

    public ArrayList<JCodeModel> jradiobuttonmodels = new ArrayList<JCodeModel>();

    public ArrayList<JCodeModel> jslidermodels = new ArrayList<JCodeModel>();

    public ArrayList<JCodeModel> jspinnermodels = new ArrayList<JCodeModel>();

    public ArrayList<JCodeModel> jtextfieldmodels = new ArrayList<JCodeModel>();

    public ArrayList<JCodeModel> jpasswordfieldmodels = new ArrayList<JCodeModel>();  

    public ArrayList<JCodeModel> jcolorchoosermodels = new ArrayList<JCodeModel>();

    public ArrayList<JCodeModel> jeditorpanemodels = new ArrayList<JCodeModel>();

    public ArrayList<JCodeModel> jtextpanemodels = new ArrayList<JCodeModel>();

    public ArrayList<JCodeModel> jfilechoosermodels = new ArrayList<JCodeModel>();

    public ArrayList<JCodeModel> jtablemodels = new ArrayList<JCodeModel>();

    public ArrayList<JCodeModel> jtextareamodels = new ArrayList<JCodeModel>();

    public ArrayList<JCodeModel> jtreemodels = new ArrayList<JCodeModel>();    

    public ArrayList<JCodeModel> jlabelmodels = new ArrayList<JCodeModel>();

    public ArrayList<JCodeModel> jprogressbarmodels = new ArrayList<JCodeModel>();

    public ArrayList<JCodeModel> jseparatormodels = new ArrayList<JCodeModel>();

    public ArrayList<JCodeModel> jtooltipmodels = new ArrayList<JCodeModel>();

    public ArrayList<JCodeModel> jappletmodels = new ArrayList<JCodeModel>();

    public ArrayList<JCodeModel> jdialogmodels = new ArrayList<JCodeModel>(); 

    public ArrayList<JCodeModel> jpanelmodels = new ArrayList<JCodeModel>();

    public ArrayList<JCodeModel> jscrollpanemodels = new ArrayList<JCodeModel>();

    public ArrayList<JCodeModel> jsplitpanemodels = new ArrayList<JCodeModel>();

    public ArrayList<JCodeModel> jtabbedpanemodels = new ArrayList<JCodeModel>();

    public ArrayList<JCodeModel> jtoolbarmodels = new ArrayList<JCodeModel>();

    public ArrayList<JCodeModel> jinternalframemodels = new ArrayList<JCodeModel>();    

    public ArrayList<JCodeModel> jlayeredpanemodels = new ArrayList<JCodeModel>();

    public ArrayList<JCodeModel> jrootpanemodels = new ArrayList<JCodeModel>();
    
    public ArrayList<ArrayList<JCodeModel>> list = new ArrayList<ArrayList<JCodeModel>>(0);      
    
    public Uijcmmodels()
    {
        list.add(jbuttonmodels);

        list.add(jframemodels);

        list.add(framemodels);        

        list.add(jcheckboxmodels);
        
        list.add(jcomboboxmodels);

        list.add(jlistmodels);
        
        list.add(jmenumodels);
        
        list.add(jmenubarmodels);
        
        list.add(jmenuitemmodels);
        
        list.add(jradiobuttonmodels);
        
        list.add(jslidermodels);
        
        list.add(jspinnermodels);
        
        list.add(jtextfieldmodels);
        
        list.add(jpasswordfieldmodels);
        
        list.add(jcolorchoosermodels);
        
        list.add(jeditorpanemodels);
        
        list.add(jtextpanemodels);
        
        list.add(jfilechoosermodels);
        
        list.add(jtablemodels);
        
        list.add(jtextareamodels);
        
        list.add(jtreemodels);
        
        list.add(jlabelmodels);
        
        list.add(jprogressbarmodels);
        
        list.add(jseparatormodels);
        
        list.add(jtooltipmodels);
        
        list.add(jappletmodels);
        
        list.add(jdialogmodels);
        
        list.add(jpanelmodels);
        
        list.add(jscrollpanemodels);
        
        list.add(jsplitpanemodels);
        
        list.add(jtabbedpanemodels);
        
        list.add(jtoolbarmodels);
        
        list.add(jinternalframemodels);
        
        list.add(jlayeredpanemodels);
        
        list.add(jrootpanemodels);       
    }
}
