package apml.ui.compilers.java.builders;

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
    
    public Jcmjmenubarbuilder(File apml, String tagname, Class classname)
    {
        super(apml, tagname, classname);
                
        this.apml = apml;
        
        this.xpath = XPathFactory.newInstance().newXPath();            
    }    
}
