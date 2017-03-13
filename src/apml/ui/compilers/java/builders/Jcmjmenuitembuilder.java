package apml.ui.compilers.java.builders;

import java.io.File;
import javax.swing.JMenuItem;
import javax.xml.xpath.XPathFactory;

/**
 *
 * @author max rupplin
 */
public class Jcmjmenuitembuilder extends Jcmabstractbuilder
{    
    public Class _class = JMenuItem.class;
    
    public static void main(String...args)
    {
        //new Jcmjmenuitembuilder(new File("/home/oem/Desktop/UI/UI.xml")).build("//jmenuitem", JMenuItem.class);
    }     
    
    public Jcmjmenuitembuilder(File apml, String tagname, Class classname)
    {
        super(apml, tagname, classname);
                
        this.apml = apml;
        
        this.xpath = XPathFactory.newInstance().newXPath();            
    } 
}
