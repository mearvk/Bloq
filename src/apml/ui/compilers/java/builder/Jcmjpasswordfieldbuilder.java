package apml.ui.compilers.java.builder;

import java.io.File;
import javax.swing.JPasswordField;
import javax.xml.xpath.XPathFactory;

/**
 *
 * @author max rupplin
 */
public class Jcmjpasswordfieldbuilder extends Jcmabstractbuilder
{    
    public static void main(String...args)
    {
        new Jcmjpasswordfieldbuilder(new File("/home/oem/Desktop/UI/UI.xml")).build("//jpasswordfield", JPasswordField.class);
    }     
    
    public Jcmjpasswordfieldbuilder(File apml)
    {
        this.apml = apml;
        
        this.xpath = XPathFactory.newInstance().newXPath();            
    }   
}
