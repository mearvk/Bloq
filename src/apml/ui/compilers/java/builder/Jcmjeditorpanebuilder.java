package apml.ui.compilers.java.builder;

import com.sun.codemodel.JCodeModel;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JEditorPane;
import javax.xml.xpath.XPathFactory;

/**
 *
 * @author max rupplin
 */
public class Jcmjeditorpanebuilder extends Jcmabstractbuilder
{            
    public static void main(String...args)
    {
        new Jcmjeditorpanebuilder(new File("/home/oem/Desktop/UI/UI.xml")).build("//jeditorpane", JEditorPane.class);
    }    
    
    public Jcmjeditorpanebuilder(File apml)
    {
        this.apml = apml;
        
        this.xpath = XPathFactory.newInstance().newXPath();            
    }    
}
