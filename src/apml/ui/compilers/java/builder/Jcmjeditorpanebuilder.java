package apml.ui.compilers.java.builder;

import java.io.File;
import javax.swing.JEditorPane;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author max rupplin
 */
public class Jcmjeditorpanebuilder extends Jcmabstractbuilder
{
    public Jcmjeditorpanebuilder builder = this;
    
    public Document doc;
    
    public Element xml;
    
    public File apml;
    
    public NodeList nodes;
    
    public XPath xpath;    
    
    public static void main(String...args)
    {
        new Jcmjeditorpanebuilder(new File("/home/oem/Desktop/UI/UI.xml")).build("//jeditorpane", JEditorPane.class);
    }    
    
    public Jcmjeditorpanebuilder(File apml)
    {
        this.apml = apml;
        
        this.xpath = XPathFactory.newInstance().newXPath();            
    }    
}
