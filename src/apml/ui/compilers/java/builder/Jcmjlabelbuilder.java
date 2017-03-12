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
    public Class _class = JLabel.class;
    
    public static void main(String...args)
    {
        //new Jcmjlabelbuilder(new File("/home/oem/Desktop/UI/UI.xml")).build("//jlabel", JLabel.class);
    }     
    
    public Jcmjlabelbuilder(File apml, String tagname)
    {
        super(apml, tagname);
                
        this.apml = apml;
        
        this.xpath = XPathFactory.newInstance().newXPath();            
    }        
}
