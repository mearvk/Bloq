package apml.ui.compilers.java.builder;

import java.io.File;
import javax.swing.JFrame;
import javax.xml.xpath.XPathFactory;

/**
 *
 * @author max rupplin
 */
public class Jcmjframebuilder extends Jcmabstractbuilder
{                 
    public Class _class = JFrame.class;
            
    public static void main(String...args)
    {
        //new Jcmjframebuilder(new File("/home/oem/Desktop/UI/UI.xml")).build("//jframe", JFrame.class);
    }    
    
    public Jcmjframebuilder(File apml, String tagname)
    {
        super(apml, tagname);
                
        this.apml = apml;
        
        this.xpath = XPathFactory.newInstance().newXPath();          
    }  
}
