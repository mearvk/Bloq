package apml.ui.compilers.java.builder;

import java.io.File;
import javax.swing.JSplitPane;
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
    
    public Jcmjsplitpanebuilder(File apml, String tagname)
    {
        super(apml, tagname);
        
        this.apml = apml;
        
        this.xpath = XPathFactory.newInstance().newXPath();            
    }    
}
