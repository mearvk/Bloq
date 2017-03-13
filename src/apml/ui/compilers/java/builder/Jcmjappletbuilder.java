package apml.ui.compilers.java.builder;

import java.io.File;
import javax.swing.JApplet;
import javax.xml.xpath.XPathFactory;

/**
 *
 * @author max rupplin
 */
public class Jcmjappletbuilder extends Jcmabstractbuilder
{       
    protected final Integer hash = 0x888fe8;
    
    public Class _class = JApplet.class;
    
    public static void main(String...args)
    {
        //new Jcmjappletbuilder(new File("/home/oem/Desktop/UI/UI.xml")).build("//japplet", JApplet.class);
    }     
    
    public Jcmjappletbuilder(File apml, String tagname, Class classname)
    {
        super(apml, tagname, classname);
        
        this.apml = apml;
        
        this.xpath = XPathFactory.newInstance().newXPath();            
    }
}
