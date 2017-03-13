package apml.ui.compilers.java.builders;

import java.io.File;
import javax.xml.xpath.XPathFactory;

/**
 *
 * @author max rupplin
 */
public class Jcmjsplitpanebuilder extends Jcmabstractbuilder
{    
    public static void main(String...args)
    {
        //new Jcmjsplitpanebuilder(new File("/home/oem/Desktop/UI/UI.xml")).build("//jsplitpane", JSplitPane.class);
    }     
    
    public Jcmjsplitpanebuilder(File apml, String tagname, Class classname)
    {
        super(apml, tagname, classname);
        
        this.apml = apml;
        
        this.xpath = XPathFactory.newInstance().newXPath();            
    }    
}
