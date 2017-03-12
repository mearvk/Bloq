package apml.ui.compilers.java.builder;

import java.io.File;
import javax.swing.JTextPane;
import javax.xml.xpath.XPathFactory;

/**
 *
 * @author max rupplin
 */
public class Jcmjtextpanebuilder extends Jcmabstractbuilder
{    
    public static void main(String...args)
    {
        //new Jcmjtextpanebuilder(new File("/home/oem/Desktop/UI/UI.xml")).build("//jtextpane", JTextPane.class);
    }     
    
    public Jcmjtextpanebuilder(File apml, String tagname)
    {
        super(apml, tagname);
        
        this.apml = apml;
        
        this.xpath = XPathFactory.newInstance().newXPath();            
    }    
}
