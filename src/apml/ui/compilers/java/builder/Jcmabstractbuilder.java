package apml.ui.compilers.java.builder;

import apml.system.bodi.Bodi;
import apml.ui.compilers.java.Uiparameter;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JMod;
import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author Max Rupplin
 */
public abstract class Jcmabstractbuilder
{   
    protected final Integer hash = 0x888fe8;
    
    public ArrayList<JCodeModel> jcodemodels = new ArrayList<>();
    
    public Jcmabstractbuilder builder = this;
    
    public Document doc;   
    
    public File apml;
        
    public NodeList nodes;
    
    public XPath xpath; 
    
    public String tagname;
    
    public Class classname;
     
    
    public Jcmabstractbuilder(File apml, String tagname, Class classname)
    {                        
        Bodi.setcontext("widgets");  
        
        this.tagname = tagname;
        
        this.apml = apml;
        
        this.classname = classname;
    }
    
    public void setsuperclass(JCodeModel jcodemodel, Integer i)
    {
        jcodemodel.packages().next().classes().next()._extends(this.classname);
    } 
    
    public void setbodi(JCodeModel jcodemodel, Integer i)
    {
        Uiparameter uip = new Uiparameter();
        
        uip.self_node = nodes.item(i);
        
        uip.jcm = jcodemodel;
        
        uip.jdc = jcodemodel.packages().next().classes().next();
        
        uip.xpath = xpath;
        
        uip.parent_node = nodes.item(i).getParentNode();
        
        uip.classname = uip.jdc.fullName();
        
        uip.instancename = uip.jdc.fullName().toLowerCase();
        
        uip.doc = this.doc;
        
        uip.element = (Element)uip.self_node;
        
        uip.constructor = uip.jdc.constructor(JMod.PUBLIC);
        
        Bodi.context("widgets").put(uip.jcm, uip);
        
        Bodi.context("widgets").put(uip.self_node, uip);
    }
    
    
    public void setjcmclass(JCodeModel jcodemodel, Integer i) throws Exception
    {
        jcodemodel.packages().next()._class(this.classname.getSimpleName()+"_"+String.format("%1$03d",i)); 
    }
    
    public void setjcmpackage(JCodeModel jcodemodel, Integer i) throws Exception
    {                        
        jcodemodel._package("org.widgets");                         
    }
    
    public void setdocument() throws Exception
    {
        this.doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(apml);
    }
    
    public void setnodes(JCodeModel jcodemodel, Integer i) throws Exception
    {
        this.nodes = (NodeList)xpath.evaluate(tagname, this.doc, XPathConstants.NODESET);
    }
    
    public ArrayList<JCodeModel> build()
    {                       
        try
        {                     
            for(int i=0; i<this.nodes.getLength(); i++)
            {
                JCodeModel jcodemodel = new JCodeModel();                                                 
            
                this.setdocument();
                
                this.setjcmpackage(jcodemodel, i);
                
                this.setjcmclass(jcodemodel, i);                                
            
                this.setnodes(jcodemodel, i);                                                 
                
                this.setsuperclass(jcodemodel, i);                 
                
                this.setbodi(jcodemodel, i);
            }
        }
        catch(Exception exception)
        {
            System.err.println(exception);
        }
        
        return jcodemodels;        
    }   
    
    protected void setjcodemodel(JCodeModel jcodemodel)
    {
        this.jcodemodels.add(jcodemodel);
    }
}