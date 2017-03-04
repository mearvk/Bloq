package apml.ui.compilers.java.builder;

import com.sun.codemodel.JCodeModel;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JMenu;
import javax.xml.xpath.XPathFactory;

/**
 *
 * @author max rupplin
 */
public class Jcmjmenubuilder extends Jcmabstractbuilder
{        
    public static void main(String...args)
    {
        new Jcmjmenubuilder(new File("/home/oem/Desktop/UI/UI.xml")).build("//jmenu", JMenu.class);
    }     
    
    public Jcmjmenubuilder(File apml)
    {
        this.apml = apml;
        
        this.xpath = XPathFactory.newInstance().newXPath();            
    }    
}
