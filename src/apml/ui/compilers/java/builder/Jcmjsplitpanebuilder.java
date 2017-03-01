package apml.ui.compilers.java.builder;

import java.io.File;
import javax.swing.JSplitPane;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author max rupplin
 */
public class Jcmjsplitpanebuilder extends Jcmabstractbuilder
{
    public Jcmjsplitpanebuilder builder = this;
    
    public Document doc;
    
    public Element xml;
    
    public File apml;
    
    public NodeList nodes;
    
    public XPath xpath;    
    
    public static void main(String...args)
    {
        new Jcmjsplitpanebuilder(new File("/home/oem/Desktop/UI/UI.xml")).build("//jsplitpane", JSplitPane.class);
    }     
    
    public Jcmjsplitpanebuilder(File apml)
    {
        this.apml = apml;
        
        this.xpath = XPathFactory.newInstance().newXPath();            
    }    
}
