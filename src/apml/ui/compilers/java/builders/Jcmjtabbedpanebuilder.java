package apml.ui.compilers.java.builders;

import apml.system.bodi.Bodi;
import apml.system.bodi.Bodicontext;
import apml.ui.compilers.java.Uiparameter;
import com.sun.codemodel.JCodeModel;
import java.io.File;
import java.util.ArrayList;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author max rupplin
 */
public class Jcmjtabbedpanebuilder extends Jcmabstractbuilder
{   
    public static void main(String...args)
    {
        //new Jcmjtabbedpanebuilder(new File("/home/oem/Desktop/UI/UI.xml")).build("//jtabbedpane", JTabbedPane.class);
    }     
    
    public Jcmjtabbedpanebuilder(File apml, String tagname, Class classname)
    {
        super(apml, tagname, classname);
        
        this.apml = apml;
        
        this.xpath = XPathFactory.newInstance().newXPath();            
    }    
    
    @Override
    public ArrayList<JCodeModel> build()
    {
        super.build();
                       
        try
        {  
            Bodicontext widgets = Bodi.context("widgets");
            
            for(int index=0; index<nodes.getLength(); index++)
            {                                  
                Uiparameter uip = (Uiparameter)widgets.pull(this.jcodemodels.get(index)); 
                                
                this.addtabs(uip);
                
                this.setSelected(uip);
            }
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }

        return jcodemodels;
    }
    
    private void setSelected(Uiparameter uip)
    {
        
    }
    
    private void addtabs(Uiparameter uip)
    {
        uip.constructor1.body().directStatement("/*------------------------ tab items -------------------*/");
        uip.constructor2.body().directStatement("/*------------------------ tab items -------------------*/");
        
        try
        {
            NodeList nodes = (NodeList)this.xpath.evaluate("./tab", uip.node, XPathConstants.NODESET);
            
            for(int index=0; index<nodes.getLength(); index++)
            {
                Element element = (Element)nodes.item(index);
                
                /*----------------------- grab element attribs ---------------------*/
                
                String icon = (!element.getAttribute("setIcon").isEmpty()) ? element.getAttribute("setIcon") : "null";
                
                String tooltip = (!element.getAttribute("setTooltip").isEmpty()) ? element.getAttribute("setTooltip") : "null";
                
                String text = (!element.getAttribute("setText").isEmpty()) ? element.getAttribute("setText") : "null";
                
                String parent = (!element.getAttribute("setParent").isEmpty()) ? element.getAttribute("setParent") : "null";
                
                /*----------------------- add to constructors ---------------------*/
                
                uip.constructor1.body().directStatement("this.addTab(\""+text+"\", "+icon+", "+parent+", \""+tooltip+"\");\n");
                
                uip.constructor2.body().directStatement("this.addTab(\""+text+"\", "+icon+", "+parent+", \""+tooltip+"\");\n");
            }
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }        
    }
}
