package apml.ui.compilers.java.builder;

import com.sun.codemodel.JCodeModel;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.xml.xpath.XPathFactory;

/**
 *
 * @author max rupplin
 */
public class Jcmjcheckboxbuilder extends Jcmabstractbuilder
{           
    public static void main(String...args)
    {
        new Jcmjcheckboxbuilder(new File("/home/oem/Desktop/UI/UI.xml")).build("//jcheckbox", JCheckBox.class);
    }    
    
    public Jcmjcheckboxbuilder(File apml)
    {
        this.apml = apml;
        
        this.xpath = XPathFactory.newInstance().newXPath();          
    } 
}
