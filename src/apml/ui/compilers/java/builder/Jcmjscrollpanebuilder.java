package apml.ui.compilers.java.builder;

import java.io.File;
import javax.swing.JScrollPane;
import javax.xml.xpath.XPathFactory;

/**
 *
 * @author max rupplin
 */
public class Jcmjscrollpanebuilder extends Jcmabstractbuilder
{         
    public static void main(String...args)
    {
        //new Jcmjscrollpanebuilder(new File("/home/oem/Desktop/UI/UI.xml")).build("//jscrollpane", JScrollPane.class);
    }     
    
    public Jcmjscrollpanebuilder(File apml, String tagname)
    {
        super(apml, tagname);
        
        this.apml = apml;
        
        this.xpath = XPathFactory.newInstance().newXPath();            
    }    
}
