package apml.ui.compilers.java.builder;

import java.io.File;
import javax.swing.JMenu;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author max rupplin
 */
public class Jcmjmenubuilder extends Jcmabstractbuilder
{
    public Jcmjmenubuilder builder = this;
    
    public Document doc;
    
    public Element xml;
    
    public File apml;
    
    public NodeList nodes;
    
    public XPath xpath;    
    
    public static void main(String...args)
    {
        new Jcmjmenubuilder(new File("/home/oem/Desktop/UI/UI.xml")).build("//jmenu", JMenu.class);
    }     
    
    public Jcmjmenubuilder(File apml)
    {
        this.apml = apml;
        
        this.xpath = XPathFactory.newInstance().newXPath();            
    }    
}
