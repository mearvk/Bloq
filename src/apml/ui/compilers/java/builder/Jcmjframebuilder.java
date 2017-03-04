package apml.ui.compilers.java.builder;

import com.sun.codemodel.JCodeModel;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.xml.xpath.XPathFactory;

/**
 *
 * @author max rupplin
 */
public class Jcmjframebuilder extends Jcmabstractbuilder
{                 
    public static void main(String...args)
    {
        new Jcmjframebuilder(new File("/home/oem/Desktop/UI/UI.xml")).build("//jframe", JFrame.class);
    }    
    
    public Jcmjframebuilder(File apml)
    {
        this.apml = apml;
        
        this.xpath = XPathFactory.newInstance().newXPath();          
    }  
}
