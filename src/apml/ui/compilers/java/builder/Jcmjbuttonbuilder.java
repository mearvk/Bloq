package apml.ui.compilers.java.builder;

import java.io.File;
import javax.swing.JButton;
import javax.xml.xpath.XPathFactory;

/**
 *
 * @author max rupplin
 */
public class Jcmjbuttonbuilder extends Jcmabstractbuilder
{               
    public Class _class = JButton.class;
    
    public static void main(String...args)
    {
        new Jcmjbuttonbuilder(new File("/home/oem/Desktop/UI/UI.xml"),"//button").build();
    }
            
    public Jcmjbuttonbuilder(File apml, String tagname)
    {
        super(apml,tagname);
        
        this.apml = apml;
        
        this.xpath = XPathFactory.newInstance().newXPath();   
    }  
}
