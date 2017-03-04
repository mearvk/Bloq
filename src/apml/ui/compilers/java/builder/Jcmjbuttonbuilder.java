package apml.ui.compilers.java.builder;

import com.sun.codemodel.JCodeModel;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.xml.xpath.XPathFactory;

/**
 *
 * @author max rupplin
 */
public class Jcmjbuttonbuilder extends Jcmabstractbuilder
{               
    public static void main(String...args)
    {
        new Jcmjbuttonbuilder(new File("/home/oem/Desktop/UI/UI.xml")).build("//jbutton", JButton.class);
    }
            
    public Jcmjbuttonbuilder(File apml)
    {
        this.apml = apml;
        
        this.xpath = XPathFactory.newInstance().newXPath();   
    }  
}
