package apml.ui.compilers.java.builder;

import java.io.File;
import javax.swing.JSlider;
import javax.xml.xpath.XPathFactory;

/**
 *
 * @author max rupplin
 */
public class Jcmjsliderbuilder extends Jcmabstractbuilder
{       
    public static void main(String...args)
    {
        //new Jcmjsliderbuilder(new File("/home/oem/Desktop/UI/UI.xml")).build("//jframe", JSlider.class);
    }     
    
    public Jcmjsliderbuilder(File apml, String tagname)
    {
        super(apml, tagname);
        
        this.apml = apml;
        
        this.xpath = XPathFactory.newInstance().newXPath();            
    }    
}
