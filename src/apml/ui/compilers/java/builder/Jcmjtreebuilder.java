package apml.ui.compilers.java.builder;

import java.io.File;
import javax.swing.JTree;
import javax.xml.xpath.XPathFactory;

/**
 *
 * @author max rupplin
 */
public class Jcmjtreebuilder extends Jcmabstractbuilder
{   
    public static void main(String...args)
    {
        new Jcmjtreebuilder(new File("/home/oem/Desktop/UI/UI.xml")).build("//jtree", JTree.class);
    }     
    
    public Jcmjtreebuilder(File apml)
    {
        this.apml = apml;
        
        this.xpath = XPathFactory.newInstance().newXPath();            
    }    
}
