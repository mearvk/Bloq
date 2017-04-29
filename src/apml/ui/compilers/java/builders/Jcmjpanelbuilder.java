package apml.ui.compilers.java.builders;

import apml.ui.compilers.java.Uiparameter;
import java.io.File;
import javax.swing.JPanel;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Element;

/**
 *
 * @author max rupplin
 */
public class Jcmjpanelbuilder extends Jcmabstractbuilder
{   
    public Class _class = JPanel.class;
    
    public static void main(String...args)
    {
        //new Jcmjpanelbuilder(new File("/home/oem/Desktop/UI/UI.xml")).build("//jpanel", JPanel.class);
    }     
    
    public Jcmjpanelbuilder()
    {
        
    }
    
    public Jcmjpanelbuilder(File apml, String tagname, Class classname)
    {
        super(apml, tagname, classname);
        
        this.apml = apml;
        
        this.xpath = XPathFactory.newInstance().newXPath();            
    }    
    
    public void addListeners(Uiparameter uip)
    {
        try
        {
            Element element = (Element)uip.node;
            
            String vetoablelistener = element.getAttribute("setVetoableChangeListener");
            
            if(vetoablelistener!=null && !vetoablelistener.isEmpty() && !vetoablelistener.trim().toLowerCase().equalsIgnoreCase("false"))
            {            
                String instancename = uip.instancename;

                String listener = instancename+"_vetoablechangelistener";       

                uip.constructor1.body().directStatement("this."+instancename+".addVetoableChangeListener("+listener+");\n\t");

                uip.constructor2.body().directStatement("this."+instancename+".addVetoableChangeListener("+listener+");\n\t");                                                    
            }
        }
        catch(Exception e)
        {
            //e.printStackTrace();
        }
    }
    
    public void addListenerMethods(Uiparameter uip)
    {
        
    }
}
