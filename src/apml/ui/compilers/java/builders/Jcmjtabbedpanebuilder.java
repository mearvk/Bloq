package apml.ui.compilers.java.builders;

import apml.system.bodi.Bodi;
import apml.system.bodi.Bodicontext;
import apml.ui.compilers.java.Uiparameter;
import com.sun.codemodel.ClassType;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JMod;
import java.io.File;
import java.util.ArrayList;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author Max Rupplin
 */
public class Jcmjtabbedpanebuilder extends Jcmabstractbuilder
{   
    public static void main(String...args)
    {
        //new Jcmjtabbedpanebuilder(new File("/home/oem/Desktop/UI/UI.xml")).build("//jtabbedpane", JTabbedPane.class);
    }     
    
    public Jcmjtabbedpanebuilder()
    {
        
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
        //may not need at yet
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
    
    public void addListeners(Uiparameter uip)
    {
        try
        {
            String instancename = uip.instancename;
                
            String listener = instancename+"_changelistener";       
                
            uip.constructor1.body().directStatement("this."+instancename+".addChangeListener("+listener+");\n\t");
            uip.constructor2.body().directStatement("this."+instancename+".addChangeListener("+listener+");\n\t");                                           
        }
        catch(Exception e)
        {
            
        }
    }
    
    public void addListenerMethods(Uiparameter uip)
    {
        try
        {
            String nestedlistenerclass = classname+"_ChangeListener";                                                               

            JDefinedClass nestedclass = uip.jdc._class(JMod.PRIVATE | JMod.FINAL, nestedlistenerclass, ClassType.CLASS);

            nestedclass._implements(Class.forName("javax.swing.event.ChangeListener"));

            nestedclass.direct("public void stateChanged(ChangeEvent ce)\n\t");

            nestedclass.direct("{\n\t");

            nestedclass.direct("\tSystem.out.println(\"Event Source: \"+ce.getSource());\n\t");

            nestedclass.direct("}\n\t");                    
        }
        catch(Exception e)
        {
            
        }        
    }
}
