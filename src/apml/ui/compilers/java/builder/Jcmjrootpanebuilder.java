package apml.ui.compilers.java.builder;

import java.io.File;
import javax.swing.JRootPane;
import javax.xml.xpath.XPathFactory;

/**
 *
 * @author max rupplin
 */
public class Jcmjrootpanebuilder extends Jcmabstractbuilder
{    
    public static void main(String...args)
    {
        //new Jcmjrootpanebuilder(new File("/home/oem/Desktop/UI/UI.xml")).build("//jrootpane", JRootPane.class);
    }     
    
    public Jcmjrootpanebuilder(File apml, String tagname)
    {
        super(apml, tagname);
        
        this.apml = apml;
        
        this.xpath = XPathFactory.newInstance().newXPath();            
    }    
}
