package apml.ui.compilers.java.builder;

import java.io.File;
import javax.swing.JTabbedPane;
import javax.xml.xpath.XPathFactory;

/**
 *
 * @author max rupplin
 */
public class Jcmjtabbedpanebuilder extends Jcmabstractbuilder
{   
    public static void main(String...args)
    {
        //new Jcmjtabbedpanebuilder(new File("/home/oem/Desktop/UI/UI.xml")).build("//jtabbedpane", JTabbedPane.class);
    }     
    
    public Jcmjtabbedpanebuilder(File apml, String tagname)
    {
        super(apml, tagname);
        
        this.apml = apml;
        
        this.xpath = XPathFactory.newInstance().newXPath();            
    }    
}
