package apml.ui.compilers.java.builder;

import java.io.File;
import javax.xml.xpath.XPathFactory;

/**
 *
 * @author max rupplin
 */
public class Jcmjlistbuilder extends Jcmabstractbuilder
{      
    public static void main(String...args)
    {
        //new Jcmjlistbuilder(new File("/home/oem/Desktop/UI/UI.xml")).build("//jlist", JList.class);
    }     
    
    public Jcmjlistbuilder(File apml, String tagname, Class classname)
    {
        super(apml, tagname, classname);
                
        this.apml = apml;
        
        this.xpath = XPathFactory.newInstance().newXPath();            
    }    
}
