package apml.helpers;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author Max Rupplin
 */
public class Parser
{
    protected final Integer hash = 0x888fe8;
        
    public Document document;
    public DocumentBuilder builder;
    public String apmlfile;
    public XPath xpath;
    
    public Parser(String apmlfile)
    {        
        try{this.builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();}catch(Exception e){e.printStackTrace(System.err);};
    
        try{this.xpath = XPathFactory.newInstance().newXPath();}catch(Exception e){e.printStackTrace(System.err);}
        
        try
        {
            this.document = this.builder.parse(new File(apmlfile));
        }
        catch(SAXException | IOException e)
        {
            e.printStackTrace(System.err);
        }
    }
}