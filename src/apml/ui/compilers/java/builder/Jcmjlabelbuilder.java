package apml.ui.compilers.java.builder;

import com.sun.codemodel.JCodeModel;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.xml.xpath.XPathFactory;

/**
 *
 * @author max rupplin
 */
public class Jcmjlabelbuilder extends Jcmabstractbuilder
{       
    public static void main(String...args)
    {
        new Jcmjlabelbuilder(new File("/home/oem/Desktop/UI/UI.xml")).build("//jlabel", JLabel.class);
    }     
    
    public Jcmjlabelbuilder(File apml)
    {
        this.apml = apml;
        
        this.xpath = XPathFactory.newInstance().newXPath();            
    }        
}
