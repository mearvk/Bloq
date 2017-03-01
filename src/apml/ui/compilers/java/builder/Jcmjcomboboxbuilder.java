package apml.ui.compilers.java.builder;

import java.io.File;
import javax.swing.JComboBox;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author max rupplin
 */
public class Jcmjcomboboxbuilder extends Jcmabstractbuilder
{
    public Jcmjcomboboxbuilder builder = this;
    
    public Document doc;
    
    public Element xml;
    
    public File apml;
    
    public NodeList nodes;
    
    public XPath xpath;    
    
    public static void main(String...args)
    {
        new Jcmjcomboboxbuilder(new File("/home/oem/Desktop/UI/UI.xml")).build("//jcombobox", JComboBox.class);
    }     
    
    public Jcmjcomboboxbuilder(File apml)
    {
        this.apml = apml;
        
        this.xpath = XPathFactory.newInstance().newXPath();            
    }    
}
