package apml.ui.compilers.java.builder;

import com.sun.codemodel.JCodeModel;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JMenuBar;
import javax.xml.xpath.XPathFactory;

/**
 *
 * @author max rupplin
 */
public class Jcmjmenubarbuilder extends Jcmabstractbuilder
{        
    public static void main(String...args)
    {
        //new Jcmjmenubarbuilder(new File("/home/oem/Desktop/UI/UI.xml")).build("//jmenubar", JMenuBar.class);
    }     
    
    public Jcmjmenubarbuilder(File apml, String tagname)
    {
        super(apml, tagname);
                
        this.apml = apml;
        
        this.xpath = XPathFactory.newInstance().newXPath();            
    }    
}
