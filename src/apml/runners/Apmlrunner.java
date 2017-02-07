package apml.runners;

import apml.compilers.Stdcompiler;
import apml.drivers.Stddriver;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Max Rupplin
 */
public final class Apmlrunner 
{
    public ArrayList<Stdcompiler> compilers = new ArrayList();
    public ArrayList<Stddriver> drivers = new ArrayList();
    
    public static File apmlfile = new File("/home/oem/Desktop/apml/apml/echoserver.xml");
    
    public Apmlrunner()
    {
        this.loadcompilers();
        this.loaddrivers();        
    }
    
    public static void main()
    {        
        //find runner for apml
        Apmlrunner runner = new Apmlrunner();
        
        //set apml input file
        runner.setapmlfile(apmlfile);
        
        //run the runner to generate source via compiler
        runner.compilers.get(0).compiletosource();
        
        //run the runner to start system via driver
        runner.drivers.get(0).drive();
    }
    
    public void setapmlfile(File file)
    {
        
    }
    
    public Class loadcompilers() 
    {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();              
        
        try
        {
            DocumentBuilder builder = builderFactory.newDocumentBuilder();  
            Document document = builder.parse(new FileInputStream(""));
            XPath xpath = XPathFactory.newInstance().newXPath();
            
            NodeList stdampltojavacompilers = (NodeList)xpath.compile("//compiler[@to=\"Java\" and @from=\"APML\"]").evaluate(document, XPathConstants.NODESET);
            
            NodeList stdjavatoapmlcompilers = (NodeList)xpath.compile("//compiler[@to=\"APML\" and @from=\"Java\"]").evaluate(document, XPathConstants.NODESET);
        }
        catch(ParserConfigurationException | SAXException | IOException | XPathExpressionException e)
        {
            e.printStackTrace(System.err);
        }     
        
        return null;
    }
    
    public Class loaddrivers()
    {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();              
        
        try
        {
            DocumentBuilder builder = builderFactory.newDocumentBuilder();  
            Document document = builder.parse(new FileInputStream(""));
            XPath xpath = XPathFactory.newInstance().newXPath();
            
            xpath.compile("//driver").evaluate(document);
        }
        catch(ParserConfigurationException | SAXException | IOException | XPathExpressionException e)
        {
            e.printStackTrace(System.err);
        }     
        
        return null;        
    }    
}
