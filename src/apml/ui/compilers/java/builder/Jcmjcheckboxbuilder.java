package apml.ui.compilers.java.builder;

import java.io.File;
import javax.swing.JCheckBox;
import javax.xml.xpath.XPathFactory;

/**
 *
 * @author max rupplin
 */
public class Jcmjcheckboxbuilder extends Jcmabstractbuilder
{           
    public Class _class = JCheckBox.class;
    
    public static void main(String...args)
    {
        //new Jcmjcheckboxbuilder(new File("/home/oem/Desktop/UI/UI.xml")).build("//jcheckbox", JCheckBox.class);
    }    
    
    public Jcmjcheckboxbuilder(File apml, String tagname)
    {
        super(apml, tagname);
        
        this.apml = apml;
        
        this.xpath = XPathFactory.newInstance().newXPath();          
    } 
}
