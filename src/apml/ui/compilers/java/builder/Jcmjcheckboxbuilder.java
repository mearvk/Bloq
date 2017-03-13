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
    protected final Integer hash = 0x888fe8;

    public Class _class = JCheckBox.class;
    
    public static void main(String...args)
    {
        //new Jcmjcheckboxbuilder(new File("/home/oem/Desktop/UI/UI.xml")).build("//jcheckbox", JCheckBox.class);
    }    
    
    public Jcmjcheckboxbuilder(File apml, String tagname, Class classname)
    {
        super(apml, tagname, classname);
        
        this.apml = apml;
        
        this.xpath = XPathFactory.newInstance().newXPath();          
    } 
}
