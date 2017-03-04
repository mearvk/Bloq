package apml.ui.compilers.java.builder;

import com.sun.codemodel.JCodeModel;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.xml.xpath.XPathFactory;

/**
 *
 * @author max rupplin
 */
public class Jcmjcomboboxbuilder extends Jcmabstractbuilder
{       
    public static void main(String...args)
    {
        new Jcmjcomboboxbuilder(new File("/home/oem/Desktop/UI/UI.xml")).build("//jcombobox", JComboBox.class);
    }     
    
    public Jcmjcomboboxbuilder(File apml)
    {
        this.apml = apml;
        
        this.xpath = XPathFactory.newInstance().newXPath();            
    }    
}
