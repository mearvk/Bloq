package apml.ui.compilers.java.builders;

import java.io.File;
import javax.xml.xpath.XPathFactory;

/**
 *
 * @author max rupplin
 */
public class Jcmjtoolbarbuilder extends Jcmabstractbuilder
{     
    public static void main(String...args)
    {
        //new Jcmjtoolbarbuilder(new File("/home/oem/Desktop/UI/UI.xml")).build("//jtoolbar", JToolBar.class);
    }     
    
    public Jcmjtoolbarbuilder(File apml, String tagname, Class classname)
    {
        super(apml, tagname, classname);
        
        this.apml = apml;
        
        this.xpath = XPathFactory.newInstance().newXPath();            
    }    
}
