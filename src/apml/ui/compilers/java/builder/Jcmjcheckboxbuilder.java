package apml.ui.compilers.java.builder;

import java.io.File;
import javax.swing.JCheckBox;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author max rupplin
 */
public class Jcmjcheckboxbuilder extends Jcmabstractbuilder
{        
    public Jcmjcheckboxbuilder builder = this;
    
    public Document doc;
    
    public Element xml;    
    
    public File apml;            
    
    public NodeList nodes;    
    
    public XPath xpath;
            
    public static void main(String...args)
    {
        new Jcmjcheckboxbuilder(new File("/home/oem/Desktop/UI/UI.xml")).build("//jcheckbox", JCheckBox.class);
    }    
    
    public Jcmjcheckboxbuilder(File apml)
    {
        this.apml = apml;
        
        this.xpath = XPathFactory.newInstance().newXPath();          
    } 
}
