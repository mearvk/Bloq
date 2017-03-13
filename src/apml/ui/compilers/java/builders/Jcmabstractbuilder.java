package apml.ui.compilers.java.builders;

import apml.system.bodi.Bodi;
import apml.ui.compilers.java.Uiparameter;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JMod;
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
                
        try
        {      
            this.tagname = tagname;
        
            this.apml = apml;
        
            this.classname = classname;            
            
            this.setxpath(null);
            
            this.setdocument(null);
        
            this.setnodes(null);
        }
        catch(Exception exception)
        {
            System.err.println(exception);
        }
    }
    
    public void setxpath(Uiparameter uip)
    {
        this.xpath = XPathFactory.newInstance().newXPath();
    }
    
    public void setsuperclass(Uiparameter uip)
    {
        uip.jdc._extends(this.classname);
    } 
    
    public void setbodi(Uiparameter uip)
    {        
        try{uip.node = nodes.item(uip.index);} catch(Exception e){}
        
        try{uip.jdc = uip.jcm.packages().next().classes().next();} catch(Exception e){}
        
        try{uip.xpath = xpath;} catch(Exception e){}
        
        try{uip.parent_node = nodes.item(uip.index).getParentNode();} catch(Exception e){}
        
        try{uip.classname = uip.jdc.name();} catch(Exception e){}
        
        try{uip.instancename = uip.jdc.name().toLowerCase();} catch(Exception e){}
        
        try{uip.doc = this.doc;} catch(Exception e){}
        
        try{uip.element = (Element)uip.node;} catch(Exception e){}
        
        try{uip.constructor = uip.jdc.constructor(JMod.PUBLIC);} catch(Exception e){}
        
        try{Bodi.context("widgets").put(uip.jcm, uip);} catch(Exception e){}
        
        try{Bodi.context("widgets").put(uip.node, uip);} catch(Exception e){}
    }
    
    
    public void setjcmclass(Uiparameter uip) throws Exception
    {
        uip.jcm.packages().next()._class(this.classname.getSimpleName()+"_"+String.format("%1$03d",uip.index)); 
    }
    
    public void setjcmpackage(Uiparameter uip) throws Exception
    {                        
        uip.jcm._package("org.widgets");                         
    }
    
    public void setdocument(Uiparameter uip) throws Exception
    {
        this.doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(apml);
    }
    
    public void setnodes(Uiparameter uip) throws Exception
    {
        try{this.nodes = (NodeList)xpath.evaluate(this.tagname, this.doc, XPathConstants.NODESET); }catch(Exception e){}
    }
    

    public ArrayList<JCodeModel> build()
    {                       
        try
        {         
            for(int index=0; index<this.nodes.getLength(); index++)
            {
                Uiparameter uip = new Uiparameter(new JCodeModel(), index);                                           
                
                this.setdocument(uip);
                
                this.setjcmpackage(uip);
                
                this.setjcmclass(uip);                                
            
                this.setnodes(uip);                   
                
                this.setbodi(uip); 
                
                this.setsuperclass(uip);  
                
                this.setjcodemodel(uip);
            }
        }
        catch(Exception exception)
        {
            System.err.println(exception);
        }
        
        return jcodemodels;        
    }   
    
    protected void setjcodemodel(Uiparameter uip)
    {
        this.jcodemodels.add(uip.jcm);
    }
}