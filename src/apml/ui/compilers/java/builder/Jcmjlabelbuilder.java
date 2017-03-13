package apml.ui.compilers.java.builder;

import java.io.File;
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
    
    public Jcmjlabelbuilder(File apml, String tagname, Class classname)
    {
        super(apml, tagname, classname);
                
        this.apml = apml;
        
        this.xpath = XPathFactory.newInstance().newXPath();            
    }        
}
