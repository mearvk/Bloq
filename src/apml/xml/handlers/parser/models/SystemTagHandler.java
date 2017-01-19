package apml.xml.handlers.parser.models;

//
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JPackage;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

//
import org.w3c.dom.Document;
import org.w3c.dom.Element;

//
import java.io.File;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.NodeList;

public class SystemTagHandler implements Runnable
{
    public static void main(String...args) 
    {                
        String in = "/home/oem/Desktop/echoserver.xml";
        String out = "/home/oem/Desktop/output";
        
        try
        {     
            SystemTagHandler sth = new SystemTagHandler();
            DocumentBuilderFactory domfactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = domfactory.newDocumentBuilder();
            Document document = builder.parse(in);
            
            XPathFactory xpf;
            XPath xpath;
            XPathExpression expr;

            xpf = XPathFactory.newInstance();
            xpath = xpf.newXPath();
            
            Object result;
            NodeList nodes;

            //
            xpf = XPathFactory.newInstance();
            xpath = xpf.newXPath();
            expr = xpath.compile("//definitions");
            result = expr.evaluate(document, XPathConstants.NODESET);                        
            nodes = (NodeList) result;
            sth.convertDefinitionsTags(nodes);   
            
            //
            xpf = XPathFactory.newInstance();
            xpath = xpf.newXPath();
            expr = xpath.compile("//system");  
            result = expr.evaluate(document, XPathConstants.NODESET);                        
            nodes = (NodeList) result;            
            sth.convertSystemTags(nodes);   
            
            //
            xpf = XPathFactory.newInstance();
            xpath = xpf.newXPath();
            expr = xpath.compile("//driver");   
            result = expr.evaluate(document, XPathConstants.NODESET);                        
            nodes = (NodeList) result;            
            sth.convertDriverTags(nodes);   
            
            //
            xpf = XPathFactory.newInstance();
            xpath = xpf.newXPath();
            expr = xpath.compile("//compiler");
            result = expr.evaluate(document, XPathConstants.NODESET);                        
            nodes = (NodeList) result;            
            sth.convertCompilerTags(nodes);   
        }
        catch(Exception ex)
        {
            ex.printStackTrace(System.err);
        }
    }   
    
    void convertDefinitionsTags(NodeList nodes)
    {
        for(int i=0;i<nodes.getLength(); i++)
        {
            JCodeModel jcodemodel = new JCodeModel();
            JPackage jpackage;
            JDefinedClass jdefinedclass;
            JMethod jmethod;
            
            try
            {
                jpackage = jcodemodel._package(nodes.item(i).getLocalName());                
                jdefinedclass = jpackage._class(nodes.item(i).getLocalName());
 
                
            }
            catch(Exception e)
            {
                e.printStackTrace(System.err);
            }
            
        }
    }
    
    void convertSystemTags(NodeList nodes)
    {
        for(int i=0;i<nodes.getLength(); i++)
        {
            
        }
    }
    
    void convertDriverTags(NodeList nodes)
    {
        for(int i=0;i<nodes.getLength(); i++)
        {
            
        }        
    }
    
    void convertCompilerTags(NodeList nodes)
    {
        for(int i=0;i<nodes.getLength(); i++)
        {
            
        }        
    }
    
    void printNodes(NodeList nodes){}          
    
    public void convertTags(NodeList nodes){}
    {
        
    }   
    
    public void run(){}
    {
  
    }
    
    public SystemTagHandler(){}
    {

    }
    
    public SystemTagHandler(Element root, String output) throws Exception
    {

    }
    
    public SystemTagHandler(File input, File output) throws Exception
    {
       
    }    
    
    public SystemTagHandler(String inputURL, String outputURL) throws Exception
    {
       
    }
    
    private void _extends(Element e)
    {
       
    }
    
    private void _package(Element e)
    {
           
    }
    
    private void _class(Element e)
    {
        
    }
    
    private void _start(Element e)
    {
        
    }
    
    private void _autostart(Element e)
    {
       
    }
    
    private void _id(Element e)
    {
        
    }
    
    public void create_source_codes(String input_dir, String output_dir)
    {
        
    }
    
    public void create_source_codes(Element e, String output_dir)
    {

    }
    
    
    public void create_source_program(String input_dir, String output_dir)
    {

    }  
    
    public void create_source_program(Element e, String output_dir)
    {
       
    }    
}
