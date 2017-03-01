package apml.ui.compilers.java.builder;

import java.io.File;
import javax.swing.JTree;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author max rupplin
 */
public class Jcmjtreebuilder extends Jcmabstractbuilder
{
    public Jcmjtreebuilder builder = this;
    
    public Document doc;
    
    public Element xml;
    
    public File apml;
    
    public NodeList nodes;
    
    public XPath xpath;    
    
    public static void main(String...args)
    {
        new Jcmjtreebuilder(new File("/home/oem/Desktop/UI/UI.xml")).build("//jtree", JTree.class);
    }     
    
    public Jcmjtreebuilder(File apml)
    {
        this.apml = apml;
        
        this.xpath = XPathFactory.newInstance().newXPath();            
    }    
}
