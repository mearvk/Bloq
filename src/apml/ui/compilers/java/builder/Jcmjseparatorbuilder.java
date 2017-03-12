package apml.ui.compilers.java.builder;

import java.io.File;
import javax.swing.JSeparator;
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
    
    public Jcmjseparatorbuilder(File apml, String tagname)
    {
        super(apml, tagname);
        
        this.apml = apml;
        
        this.xpath = XPathFactory.newInstance().newXPath();            
    }    
}
