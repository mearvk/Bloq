package apml.ui.compilers.java.builder;

import java.io.File;
import javax.swing.JRootPane;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author max rupplin
 */
public class Jcmjrootpanebuilder extends Jcmabstractbuilder
{
    public Jcmjrootpanebuilder builder = this;
    
    public Document doc;
    
    public Element xml;
    
    public File apml;
    
    public NodeList nodes;
    
    public XPath xpath;    
    
    public static void main(String...args)
    {
        new Jcmjrootpanebuilder(new File("/home/oem/Desktop/UI/UI.xml")).build("//jrootpane", JRootPane.class);
    }     
    
    public Jcmjrootpanebuilder(File apml)
    {
        this.apml = apml;
        
        this.xpath = XPathFactory.newInstance().newXPath();            
    }    
}
