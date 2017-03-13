package apml.ui.compilers.java.builder;

import java.io.File;
import javax.xml.xpath.XPathFactory;

/**
 *
 * @author max rupplin
 */
public class Jcmjtreebuilder extends Jcmabstractbuilder
{   
    protected final Integer hash = 0x888fe8;
    
    public static void main(String...args)
    {
        //new Jcmjtreebuilder(new File("/home/oem/Desktop/UI/UI.xml")).build("//jtree", JTree.class);
    }     
    
    public Jcmjtreebuilder(File apml, String tagname, Class classname)
    {
        super(apml, tagname, classname);
        
        this.apml = apml;
        
        this.xpath = XPathFactory.newInstance().newXPath();            
    }    
}
