package apml.ui.compilers.java;

import apml.ui.compilers.java.builder.Jcmjbuttonbuilder;
import com.sun.codemodel.JCodeModel;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/**
 * @author max rupplin
 */
public class Uiapmlmanager
{
    Document doc;
    XPath xpath;
    
    ArrayList<JCodeModel> jbuttonmodels;
    ArrayList<JCodeModel> jframemodels;
    ArrayList<JCodeModel> framemodels;
    ArrayList<JCodeModel> jcheckboxmodels;
    ArrayList<JCodeModel> jcomboboxmodels;
    ArrayList<JCodeModel> jlistmodels;
    ArrayList<JCodeModel> jmenumodels;
    ArrayList<JCodeModel> jradiobuttonmodels;
    ArrayList<JCodeModel> jslidermodels;
    ArrayList<JCodeModel> jspinnermodels;
    ArrayList<JCodeModel> jtextfieldmodels;
    ArrayList<JCodeModel> jpasswordfieldmodels;  
    ArrayList<JCodeModel> jcolorchoosermodels;
    ArrayList<JCodeModel> jeditorpanemodels;
    ArrayList<JCodeModel> jtextpanemodels;
    ArrayList<JCodeModel> jfilechoosermodels;
    ArrayList<JCodeModel> jtablemodels;
    ArrayList<JCodeModel> jtextareamodels;
    ArrayList<JCodeModel> jtreemodels;    
    ArrayList<JCodeModel> jlabelmodels;
    ArrayList<JCodeModel> jprogressbarmodels;
    ArrayList<JCodeModel> jseparatormodels;
    ArrayList<JCodeModel> jtooltipmodels;
    ArrayList<JCodeModel> jappletmodels;
    ArrayList<JCodeModel> jdialogmodels; 
    ArrayList<JCodeModel> jpanelmodels;
    ArrayList<JCodeModel> jscrollpanemodels;
    ArrayList<JCodeModel> jsplitpanemodels;
    ArrayList<JCodeModel> jtabbedpanemodels;
    ArrayList<JCodeModel> jtoolbarmodels;
    ArrayList<JCodeModel> jinternalframemodels;    
    ArrayList<JCodeModel> jlayeredpanemodels;
    ArrayList<JCodeModel> jrootpanemodels;
        
    public ArrayList<JCodeModel> generatemodels(File apmlin, String tagname)            
    {
        try
        {
            this.xpath = XPathFactory.newInstance().newXPath();
            
            this.doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(apmlin);        
        
            switch(tagname)
            {
                case "//jbutton": return getjbuttonjcmmodels(apmlin, tagname);

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
        
        }
        catch(ParserConfigurationException | SAXException | IOException e)
        {
            
        }
        
        return new ArrayList<>();
    }
    
    protected ArrayList<JCodeModel> getjbuttonjcmmodels(File apml, String tagname)
    {      
        ArrayList<JCodeModel> jcmjbuttons = new ArrayList<>(0);
        
        try
        {                                        
            NodeList nodejbuttons = (NodeList)xpath.evaluate(tagname, this.doc, XPathConstants.NODESET);
            
            for(int i=0; i<nodejbuttons.getLength(); i++)
            {
                Element current = (Element)nodejbuttons.item(i);
                
                JCodeModel jcodemodel = new Jcmjbuttonbuilder(current).build();     
                
                jcmjbuttons.add(jcodemodel);
            }
        }
        catch(Exception e)
        {
            
        }         
        
        return jcmjbuttons;
    }
}
