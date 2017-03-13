package apml.ui.compilers.java.builders;

import java.io.File;
import javax.swing.JColorChooser;
import javax.xml.xpath.XPathFactory;

/**
 *
 * @author max rupplin
 */
public class Jcmjcolorchooserbuilder extends Jcmabstractbuilder
{    
    public Class _class = JColorChooser.class;
    
    public static void main(String...args)
    {
        //new Jcmjcolorchooserbuilder(new File("/home/oem/Desktop/UI/UI.xml")).build("//jcolorchooser", JColorChooser.class);
    }    
    
    public Jcmjcolorchooserbuilder(File apml, String tagname, Class classname)
    {
        super(apml, tagname, classname);
        
        this.apml = apml;
        
        this.xpath = XPathFactory.newInstance().newXPath();          
    }    
}
