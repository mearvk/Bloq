package apml.xml.handlers.parser.models;

//
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JPackage;

//
import java.lang.reflect.Method;

//
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

//
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.io.File;
import org.w3c.dom.NodeList;

public class SystemTagHandler_bk implements Runnable
{
    //
    String sin = "/home/oem/Desktop/echoserver.xml";
    String sout = "/home/oem/Desktop/output";
    File fin;
    File fout;
        
    //
    JCodeModel jcm;   
    JDefinedClass jdc;  
    JPackage jp;    
    
    //
    Document d;
    DocumentBuilder db;      
    Element e;
        
    //
    public static void main(String...args)
    {        
        String in = "/home/oem/Desktop/test.xml";
        String out = "/home/oem/Desktop/output";
        
        try
        {            
            //touch
            //new ApmlTagHandler(in,out).create_source_program(in, out);
        }
        catch(Exception ex)
        {
            ex.printStackTrace(System.err);
        }
    }    
    
    //
    public void run()
    {
        //touch
    }
    
    public SystemTagHandler_bk()
    {
        this.sin = null;
        this.sout = null;
        this.fin = null;
        this.fout = null;              
        this.jcm = new JCodeModel();
    }
    
    public SystemTagHandler_bk(Element root, String output) throws Exception
    {
        this.e = root;
        this.fout = new File(output);        
        this.jcm = new JCodeModel();
    }
    
    public SystemTagHandler_bk(File input, File output) throws Exception
    {
        this.fin = input;
        this.fout = output;
        this.jcm = new JCodeModel();
        
        this.db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        this.d = db.parse(fout);   
        this.e = d.getDocumentElement();        
    }    
    
    public SystemTagHandler_bk(String inputURL, String outputURL) throws Exception
    {
        this.fin = new File(inputURL);
        this.jcm = new JCodeModel();
        
        this.db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        this.d = db.parse(new File(inputURL));   
        this.e = d.getDocumentElement();       
    }
    
    private void _extends(Element e)
    {
        if(e.getNodeType() != Node.ELEMENT_NODE) return;
        
        if(e.hasAttribute("extends"))
        {
            System.err.println("extends => "+e.getNodeName()+"====>"+e.getAttribute("extends"));
            
            try
            {
                Class extended_class = Class.forName(e.getAttribute("extends"));
                
                jdc = jdc._extends(extended_class);
                
                Method[] methods = extended_class.getMethods();
                
                for(Method m:methods)
                {                  
                    jdc.method(JMod.PUBLIC, m.getReturnType(), m.getName()); 
                }             
            }
            catch(Exception exc)
            {
                exc.printStackTrace(System.err);
            }
        }        
    }
    
    private void _package(Element e)
    {
        if(e.hasAttribute("package"))
        {
            System.err.println("package => "+e.getNodeName()+"====>"+e.getAttribute("package"));
             
            try
            {
                jp = jcm._package(e.getAttribute("package"));                        
            }
            catch(Exception exc)
            {
                exc.printStackTrace(System.err);
            }
        }          
    }
    
    private void _class(Element e)
    {
        if(e.hasAttribute("class"))
        {
            System.err.println("class => "+e.getNodeName()+"====>"+e.getAttribute("class"));
            
            try
            {
                jdc = jcm._class(Class.forName(e.getAttribute("class")).toString());
            }
            catch(Exception exc)
            {
                exc.printStackTrace(System.err);
            }
        }         
    }
    
    private void _start(Element e)
    {
        if(e.hasAttribute("start"))
        {
            try
            {
                //jclass._implements(apml.interfaces.Startable.class);                
            }
            catch(Exception exc)
            {
                exc.printStackTrace(System.err);
            }             
        }        
    }
    
    private void _autostart(Element e)
    {
        if(e.hasAttribute("autostart"))
        {
            try
            {
                //jclass._implements(apml.interfaces.AutoStartable.class);
            }
            catch(Exception exc)
            {
                exc.printStackTrace(System.err);
            }             
        }        
    }
    
    private void _id(Element e)
    {
        if(e.hasAttribute("id"))
        {
            try
            {
                //jclass.annotate(apml.annotations.ApmlId.class);
            }
            catch(Exception exc)
            {
                exc.printStackTrace(System.err);
            }            
        }        
    }
    
    public void create_source_codes(String input_dir, String output_dir)
    {
        
    }
    
    public void create_source_codes(Element e, String output_dir)
    {

    }
    
    
    public void create_source_program(String input_dir, String output_dir)
    {
        try
        {                          
            this.db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            this.d = db.parse(new File(input_dir));   
            this.e = d.getDocumentElement();
            
            do
            {
                if(e.getNodeType() == Node.ELEMENT_NODE)
                {
                    e = traverseDF(e);               

                    this._package(e);
                    this._class(e);            
                    this._extends(e);
                    this._autostart(e);
                    this._start(e);
                    this._id(e); 
                }
            }
            while(e.hasChildNodes());
                                   
            jcm.build(new File(output_dir)); 
        }
        catch(Exception exception)
        {
            exception.printStackTrace(System.err);
        }
    }  
    
    public Element traverseDF(Element e)
    {
        if(e.getNodeType() != Node.ELEMENT_NODE) return e;            
                
        System.err.println("====>"+e.getNodeName());
        
        //
        if(e.getNextSibling()==null && e.hasChildNodes()) 
        {
            NodeList list = e.getChildNodes();
            
            for(int i=0;i<list.getLength();i++)
            {
                Node node = list.item(i);
                
                if(node.getNodeType() != Node.ELEMENT_NODE) continue;
                
                return traverseDF((Element)node);
            }
        }
        
        //
        if(e.getNextSibling()==null && !e.hasChildNodes()) 
        {
            return e; 
        }
        
        //
        if(e.getNextSibling()!=null && e.hasChildNodes()) 
        {
            NodeList list = e.getChildNodes();
            
            for(int i=0;i<list.getLength();i++)
            {
                Node node = list.item(i);
                
                if(node.getNodeType() != Node.ELEMENT_NODE) continue;
                
                return traverseDF((Element)node);
            }
        }

        //
        if(e.getNextSibling()!=null && !e.hasChildNodes()) 
        {
            return e;
        }     
        
        //
        return e;
    }
    
    public void create_source_program(Element e, String output_dir)
    {
        if(e.getNodeType() != Node.ELEMENT_NODE) return;
        
        try
        {
           do
            {
                e = traverseDF(e);
                
                this._package(e);
                this._class(e);            
                this._extends(e);
                this._autostart(e);
                this._start(e);
                this._id(e); 
            }
            while(e.hasChildNodes());
            
            jcm.build(new File(output_dir));                
        }
        catch(Exception exception)
        {
            exception.printStackTrace(System.err);
        }        
    }    
}
