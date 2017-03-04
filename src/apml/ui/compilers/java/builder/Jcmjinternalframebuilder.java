package apml.ui.compilers.java.builder;

import com.sun.codemodel.JCodeModel;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JInternalFrame;
import javax.xml.xpath.XPathFactory;
/**
 *
 * @author max rupplin
 */
public class Jcmjinternalframebuilder extends Jcmabstractbuilder
{        
    public static void main(String...args)
    {
        new Jcmjinternalframebuilder(new File("/home/oem/Desktop/UI/UI.xml")).build("//jinternalframe", JInternalFrame.class);
    }     
    
    public Jcmjinternalframebuilder(File apml)
    {
        this.apml = apml;
        
        this.xpath = XPathFactory.newInstance().newXPath();            
    }        
}
