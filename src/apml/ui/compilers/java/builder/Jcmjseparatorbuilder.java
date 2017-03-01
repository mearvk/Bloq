package apml.ui.compilers.java.builder;

import java.io.File;
import javax.swing.JSeparator;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author max rupplin
 */
public class Jcmjseparatorbuilder extends Jcmabstractbuilder
{
    public Jcmjseparatorbuilder builder = this;
    
    public Document doc;
    
    public Element xml;
    
    public File apml;
    
    public NodeList nodes;
    
    public XPath xpath;    
    
    public static void main(String...args)
    {
        new Jcmjseparatorbuilder(new File("/home/oem/Desktop/UI/UI.xml")).build("//jseparator", JSeparator.class);
    }     
    
    public Jcmjseparatorbuilder(File apml)
    {
        this.apml = apml;
        
        this.xpath = XPathFactory.newInstance().newXPath();            
    }    
}
