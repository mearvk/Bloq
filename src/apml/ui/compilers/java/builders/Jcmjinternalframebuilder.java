package apml.ui.compilers.java.builders;

import java.io.File;
import javax.swing.JInternalFrame;
import javax.xml.xpath.XPathFactory;
/**
 *
 * @author max rupplin
 */
public class Jcmjinternalframebuilder extends Jcmabstractbuilder
{        
    public Class _class = JInternalFrame.class;
    
    public static void main(String...args)
    {
        //new Jcmjinternalframebuilder(new File("/home/oem/Desktop/UI/UI.xml")).build("//jinternalframe", JInternalFrame.class);
    }     
    
    public Jcmjinternalframebuilder(File apml, String tagname, Class classname)
    { 
        super(apml, tagname, classname);
    
        this.apml = apml;
        
        this.xpath = XPathFactory.newInstance().newXPath();            
    }        
}
