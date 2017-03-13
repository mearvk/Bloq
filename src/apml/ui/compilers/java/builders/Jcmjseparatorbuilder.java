package apml.ui.compilers.java.builders;

import java.io.File;
import javax.xml.xpath.XPathFactory;

/**
 *
 * @author max rupplin
 */
public class Jcmjseparatorbuilder extends Jcmabstractbuilder
{    
    public static void main(String...args)
    {
        //new Jcmjseparatorbuilder(new File("/home/oem/Desktop/UI/UI.xml")).build("//jseparator", JSeparator.class);
    }     
    
    public Jcmjseparatorbuilder(File apml, String tagname, Class classname)
    {
        super(apml, tagname, classname);
        
        this.apml = apml;
        
        this.xpath = XPathFactory.newInstance().newXPath();            
    }    
}
