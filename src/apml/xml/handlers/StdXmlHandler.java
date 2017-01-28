package apml.xml.handlers;

import apml.compilers.codemodel.StdCodeModelCompiler;
import apml.helpers.StdApmlSanityChecker;
import apml.helpers.StdApmlWriter;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class StdXmlHandler 
{
    protected final Integer hash = 0x888fe8;
    
    public StdCodeModelCompiler compiler;
    public StdApmlSanityChecker checker;    
    public StdXmlHandler handler;        
    public StdApmlWriter writer;
    
    public DocumentBuilderFactory factory;
    public DocumentBuilder builder;
    public Document document;
    public Element root;
    public File in;
    public List<String> list;
          
    public static void main(String[] args)
    {
        
    }     
    
    public StdXmlHandler(File in)
    {
        this.in = in;
        this.checker = new StdApmlSanityChecker(this);
        this.compiler = new StdCodeModelCompiler(this);
        this.handler = new StdXmlHandler(this);
        this.writer = new StdApmlWriter(this);
    }
    
    public StdXmlHandler(StdXmlHandler handler)
    {
        this.handler = handler;
    }        
    
    public void parse(File apml_input_file)
    {
        try
        {            
            factory = DocumentBuilderFactory.newInstance();
            builder = factory.newDocumentBuilder();            
            document = builder.parse(apml_input_file);   
            root = document.getDocumentElement();
            list = new ArrayList<>();

            this.parse(document, list, root);
            
            System.err.println(root.getNodeName());
            
            for(String s : list)
            {
                System.err.println(s);
            }
        } 
        catch (Exception e)
        {
            e.printStackTrace(System.err);
        }               
    }   
    
    public String getElement(String element)
    {
        return this.root.getElementsByTagName(element).item(0).getNodeName();
    }
    
    public String getElementValue(String element)
    {
        return this.root.getElementsByTagName(element).item(0).getNodeValue();
    }    
    
    public void check(File apml_input_file)
    {
        this.checker.check(apml_input_file);
    }
    
    public void compile(StdXmlHandler handler) throws Exception
    {
        this.compiler.compile(handler);
    }
    
    public void write(StdXmlHandler handler, File java_output_file) throws Exception
    {
        this.writer.write(handler.compiler, java_output_file);
    }    
    
    public Element getRoot()
    {
        return document.getDocumentElement();
    }
    
    public String getRootName()
    {
        return document.getDocumentElement().getNodeName();
    }
    
    public List<String> getList()
    {
        return this.list;
    }
    
    public void doIt() //traverse
    {
        String file = "/home/oem/Desktop/echoserver.xml";               
    } 
    
    private void parse(final Document doc, final List<String> list, final Element e) 
    {
        final NodeList children = e.getChildNodes();
        
        for (int i = 0; i < children.getLength(); i++) 
        {
            final Node n = children.item(i);
            
            if (n.getNodeType() == Node.ELEMENT_NODE) 
            {
                list.add(n.getNodeName());
                parse(doc, list, (Element) n);
            }
        }
    }     
}

