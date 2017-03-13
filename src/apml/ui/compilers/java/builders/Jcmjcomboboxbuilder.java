package apml.ui.compilers.java.builders;

import java.io.File;
import javax.swing.JComboBox;
import javax.xml.xpath.XPathFactory;

/**
 *
 * @author max rupplin
 */
public class Jcmjcomboboxbuilder extends Jcmabstractbuilder
{       
    public Class _class = JComboBox.class;
    
    public static void main(String...args)
    {
        //new Jcmjcomboboxbuilder(new File("/home/oem/Desktop/UI/UI.xml")).build("//jcombobox", JComboBox.class);
    }     
    
    public Jcmjcomboboxbuilder(File apml, String tagname, Class classname)
    {
        super(apml, tagname, classname);
    
        this.apml = apml;
        
        this.xpath = XPathFactory.newInstance().newXPath();            
    }    
}
