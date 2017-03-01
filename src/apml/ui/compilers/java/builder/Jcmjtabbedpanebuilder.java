package apml.ui.compilers.java.builder;

import java.io.File;
import javax.swing.JTabbedPane;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author max rupplin
 */
public class Jcmjtabbedpanebuilder extends Jcmabstractbuilder
{
    public Jcmjtabbedpanebuilder builder = this;
    
    public Document doc;
    
    public Element xml;
    
    public File apml;
    
    public NodeList nodes;
    
    public XPath xpath;    
    
    public static void main(String...args)
    {
        new Jcmjtabbedpanebuilder(new File("/home/oem/Desktop/UI/UI.xml")).build("//jtabbedpane", JTabbedPane.class);
    }     
    
    public Jcmjtabbedpanebuilder(File apml)
    {
        this.apml = apml;
        
        this.xpath = XPathFactory.newInstance().newXPath();            
    }    
}
