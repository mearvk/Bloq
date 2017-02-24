package apml.runners;

import apml.compilers.Stdabstractcompiler;
import apml.drivers.Stddriver;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
 * For starting parsing, compiling by 3rd parties directly from command line, etc.
 * 
 * @author Max Rupplin
 * @version 1.00
 * @see http://github.com/mearvk/Bloq/wiki
 */
public final class Apmlrunner 
{
    protected final Integer hash = 0x888fe8;
        
    public Stdabstractcompiler compiler;
    public Stddriver driver;
        
    public File apmlfile;
    
    protected String basedir = "";
    protected String builddir = "";
    protected String srcdir = "";    
    
    public Apmlrunner()
    {
        this.loadcompilers();
        this.loaddrivers();        
    }
    
    public static void main()
    {        
        Apmlrunner runner = new Apmlrunner();
        
        runner.setapmlfile(new File("/home/oem/Desktop/apml/apml/echoserver.xml"));
        
        runner.setbasedir("");
        
        runner.setsrcdir("");
        
        runner.setbuilddir("");
        
        //runner.compiler.setsourcefiles(astmanager);
        
        runner.driver.drive();
    }
    
    public void setdriver(Stddriver driver)
    {
        this.driver = driver;
    }
    
    public void setcompiler(Stdabstractcompiler compiler)
    {
        this.compiler = compiler;
    }
    
    public void setapmlfile(File file)
    {
        this.apmlfile = file;
    }    
    
    public void setbasedir(String basedir)
    {
        this.basedir = basedir;
    }
    
    public void setbuilddir(String builddir)
    {
        this.builddir = builddir;
    }
    
    public void setsrcdir(String srcdir)
    {
        this.srcdir = srcdir;
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
