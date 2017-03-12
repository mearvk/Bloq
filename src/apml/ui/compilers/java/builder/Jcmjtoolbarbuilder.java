package apml.ui.compilers.java.builder;

import java.io.File;
import javax.swing.JToolBar;
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
    
    public Jcmjtoolbarbuilder(File apml, String tagname)
    {
        super(apml, tagname);
        
        this.apml = apml;
        
        this.xpath = XPathFactory.newInstance().newXPath();            
    }    
}
