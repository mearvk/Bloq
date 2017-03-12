package apml.ui.compilers.java.builder;

import apml.system.bodi.Bodi;
import apml.ui.compilers.java.Uiparameter;
import com.sun.codemodel.JCodeModel;
import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 *
 * @author Max Rupplin
 */
public abstract class Jcmabstractbuilder
{   
    public ArrayList<JCodeModel> jcodemodels = new ArrayList<>();
    
    public Jcmabstractbuilder builder = this;
    
    public Document doc;   
    
    public File apml;
        
    public NodeList nodes;
    
    public XPath xpath; 
    
    public String tagname;
    
    
    public Jcmabstractbuilder(File apml, String tagname)
    {                        
        Bodi.setcontext("widgets");  
        
        this.tagname = tagname;
    }
    
    public void setsuperclass(JCodeModel jcodemodel, Integer i, Class _class)
    {
        jcodemodel.packages().next().classes().next()._extends(_class);
    } 
    
    public void setbodi(JCodeModel jcodemodel, Integer i, Class _class)
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
        
        Bodi.context("widgets").put(uip.jcm, uip);
    }
    
    
    public void setjcmclass(JCodeModel jcodemodel, Integer i, Class _class) throws Exception
    {
        jcodemodel.packages().next()._class(_class.getSimpleName()+"_"+String.format("%1$03d",i)); 
    }
    
    public void setjcmpackage(JCodeModel jcodemodel, Integer i, Class _class) throws Exception
    {                        
        jcodemodel._package("org.widgets");                         
    }
    
    public void setdocument(File apml) throws Exception
    {
        this.doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(apml);
    }
    
    public void setnodes(JCodeModel jcodemodel, Integer i, Class _class) throws Exception
    {
        this.nodes = (NodeList)xpath.evaluate(tagname, this.doc, XPathConstants.NODESET);
    }
    
    public ArrayList<JCodeModel> build()
    {                       
        try
        {                              
            Class _class = null;
            
            for(int i=0; i<this.nodes.getLength(); i++)
            {       
                JCodeModel jcodemodel = new JCodeModel();                
                                
                //
            
                this.setdocument(apml);
                
                this.setjcmpackage(jcodemodel, i, _class);
                
                this.setjcmclass(jcodemodel, i, _class);                                
            
                this.setnodes(jcodemodel, i, _class);                                                 
                
                this.setsuperclass(jcodemodel, i, _class);                 
                
                this.setbodi(jcodemodel, i, _class);
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
