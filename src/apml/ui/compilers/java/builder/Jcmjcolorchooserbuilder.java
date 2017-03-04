package apml.ui.compilers.java.builder;

import com.sun.codemodel.JCodeModel;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JColorChooser;
import javax.xml.xpath.XPathFactory;

/**
 *
 * @author max rupplin
 */
public class Jcmjcolorchooserbuilder extends Jcmabstractbuilder
{                  
    public static void main(String...args)
    {
        new Jcmjcolorchooserbuilder(new File("/home/oem/Desktop/UI/UI.xml")).build("//jcolorchooser", JColorChooser.class);
    }    
    
    public Jcmjcolorchooserbuilder(File apml)
    {
        this.apml = apml;
        
        this.xpath = XPathFactory.newInstance().newXPath();          
    }    
}
