package apml.ui.compilers.java.builder;

import com.sun.codemodel.JCodeModel;
import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author max rupplin
 */
public class Jcmjlistbuilder
{
    public Jcmjlistbuilder builder = this;
    
    public Document doc;
    
    public Element xml;
    
    public File apml;
    
    public NodeList nodes;
    
    public XPath xpath;    
    
    public Jcmjlistbuilder(File apml)
    {
        this.apml = apml;
        
        this.xpath = XPathFactory.newInstance().newXPath();            
    }    
    
    public ArrayList<JCodeModel> build(String tagname)
    {
        try
        {        
            this.doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(apml);
        
            this.nodes = (NodeList)xpath.evaluate(tagname, this.doc, XPathConstants.NODESET);
            
            //would prefer to go right to jcm from here
            xml.getAttribute("setAction");                                                

            xml.getAttribute("setBorderpainted");

            xml.getAttribute("setDisabledicon");

            xml.getAttribute("setEnabled");                

            xml.getAttribute("setIcon");

            xml.getAttribute("setLabel");

            xml.getAttribute("setLayout");

            xml.getAttribute("setMargin");

            xml.getAttribute("setModel");

            xml.getAttribute("setName");                                                

            xml.getAttribute("setPressedIcon");

            xml.getAttribute("setRolloverIcon");                

            xml.getAttribute("setText"); 
        }
        catch(Exception exception)
        {
            
        }
        
        return null;
    }    
}
