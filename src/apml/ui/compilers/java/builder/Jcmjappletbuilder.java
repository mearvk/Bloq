package apml.ui.compilers.java.builder;

import com.sun.codemodel.JCodeModel;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JApplet;
import javax.xml.xpath.XPathFactory;

/**
 *
 * @author max rupplin
 */
public class Jcmjappletbuilder extends Jcmabstractbuilder
{       
    public static void main(String...args)
    {
        new Jcmjappletbuilder(new File("/home/oem/Desktop/UI/UI.xml")).build("//japplet", JApplet.class);
    }     
    
    public Jcmjappletbuilder(File apml)
    {
        this.apml = apml;
        
        this.xpath = XPathFactory.newInstance().newXPath();            
    }
}
