package apml.ui.compilers.java.builder;

import java.io.File;
import javax.swing.JScrollPane;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author max rupplin
 */
public class Jcmjscrollpanebuilder extends Jcmabstractbuilder
{
    public Jcmjscrollpanebuilder builder = this;
    
    public Document doc;
    
    public Element xml;
    
    public File apml;
    
    public NodeList nodes;
    
    public XPath xpath;    
    
    public static void main(String...args)
    {
        new Jcmjscrollpanebuilder(new File("/home/oem/Desktop/UI/UI.xml")).build("//jscrollpane", JScrollPane.class);
    }     
    
    public Jcmjscrollpanebuilder(File apml)
    {
        this.apml = apml;
        
        this.xpath = XPathFactory.newInstance().newXPath();            
    }    
}
