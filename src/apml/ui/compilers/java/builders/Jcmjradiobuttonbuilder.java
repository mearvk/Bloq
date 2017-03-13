package apml.ui.compilers.java.builders;

import java.io.File;
import javax.xml.xpath.XPathFactory;

/**
 *
 * @author max rupplin
 */
public class Jcmjradiobuttonbuilder extends Jcmabstractbuilder
{    
    public static void main(String...args)
    {
        //new Jcmjradiobuttonbuilder(new File("/home/oem/Desktop/UI/UI.xml")).build("//jradiobutton", JRadioButton.class);
    }     
    
    public Jcmjradiobuttonbuilder(File apml, String tagname, Class classname)
    {
        super(apml, tagname, classname);
        
        this.apml = apml;
        
        this.xpath = XPathFactory.newInstance().newXPath();            
    }    
}
