package apml.ui.compilers.java.builder;

import java.io.File;
import javax.swing.JTextField;
import javax.xml.xpath.XPathFactory;

/**
 *
 * @author max rupplin
 */
public class Jcmjtextfieldbuilder extends Jcmabstractbuilder
{           
    public static void main(String...args)
    {
        //new Jcmjtextfieldbuilder(new File("/home/oem/Desktop/UI/UI.xml")).build("//jtextfield", JTextField.class);
    }     
    
    public Jcmjtextfieldbuilder(File apml, String tagname)
    {
        super(apml, tagname);
        
        this.apml = apml;
        
        this.xpath = XPathFactory.newInstance().newXPath();            
    }    
}
