package apml.ui.compilers.java.builder;

import com.sun.codemodel.JCodeModel;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JLayeredPane;
import javax.xml.xpath.XPathFactory;

/**
 *
 * @author max rupplin
 */
public class Jcmjlayeredpanebuilder extends Jcmabstractbuilder
{            
    public static void main(String...args)
    {
        new Jcmjlayeredpanebuilder(new File("/home/oem/Desktop/UI/UI.xml")).build("//jlayeredpane", JLayeredPane.class);
    }     
    
    public Jcmjlayeredpanebuilder(File apml)
    {
        this.apml = apml;
        
        this.xpath = XPathFactory.newInstance().newXPath();            
    }        
}
