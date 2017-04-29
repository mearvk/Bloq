package apml.ui.compilers.java.builders;

import apml.ui.compilers.java.Uiparameter;
import java.io.File;
import javax.xml.xpath.XPathFactory;

/**
 *
 * @author max rupplin
 */
public class Jcmjmenubarbuilder extends Jcmabstractbuilder
{        
    public static void main(String...args)
    {
        //new Jcmjmenubarbuilder(new File("/home/oem/Desktop/UI/UI.xml")).build("//jmenubar", JMenuBar.class);
    }     
    
    public Jcmjmenubarbuilder()
    {
        
    }
    
    public Jcmjmenubarbuilder(File apml, String tagname, Class classname)
    {
        super(apml, tagname, classname);
                
        this.apml = apml;
        
        this.xpath = XPathFactory.newInstance().newXPath();            
    }    
    
    public void addListeners(Uiparameter uip)
    {
        try
        {
            String instancename = uip.instancename;
                
            String listener = instancename+"_componentelistener";       
                
            uip.constructor1.body().directStatement("this."+instancename+".addComponentListener("+listener+");\n\t");
                    
            uip.constructor2.body().directStatement("this."+instancename+".addComponentListener("+listener+");\n\t");                                                    
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void addListenerMethods(Uiparameter uip)
    {
        try
        {
            
        }
        catch(Exception e)
        {
            
        }
    }
}
