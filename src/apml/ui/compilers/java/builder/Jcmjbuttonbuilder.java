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
    protected final Integer hash = 0x888fe8;
    
    public Class _class = JButton.class;
    
    public static void main(String...args)
    {
        //new Jcmjbuttonbuilder(new File("/home/oem/Desktop/UI/UI.xml"),"//button").build();
    }
            
    public Jcmjbuttonbuilder(File apml, String tagname, Class classname)
    {
        super(apml,tagname, classname);
        
        this.apml = apml;
        
        this.xpath = XPathFactory.newInstance().newXPath();   
    }  
}
