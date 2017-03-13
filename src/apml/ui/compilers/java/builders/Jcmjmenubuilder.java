package apml.ui.compilers.java.builders;

import java.io.File;
import javax.xml.xpath.XPathFactory;

/**
 *
 * @author max rupplin
 */
public class Jcmjmenubuilder extends Jcmabstractbuilder
{        
    public static void main(String...args)
    {
        //new Jcmjmenubuilder(new File("/home/oem/Desktop/UI/UI.xml")).build("//jmenu", JMenu.class);
    }     
    
    public Jcmjmenubuilder(File apml, String tagname, Class classname)
    {
        super(apml, tagname, classname);
                
        this.apml = apml;
        
        this.xpath = XPathFactory.newInstance().newXPath();            
    }    
}
