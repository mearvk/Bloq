package apml.ui.compilers.java.builder;

import apml.system.bodi.Bodi;
import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JPackage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author max rupplin
 */
public abstract class Jcmabstractbuilder
{      
    public Jcmabstractbuilder builder = this;
    
    public Document doc;   
    
    public File apml;
        
    public NodeList nodes;
    
    public XPath xpath; 
    
    public ArrayList<JCodeModel> jcodemodels = new ArrayList<>();
    
    public Jcmabstractbuilder()
    {
        /* ---------------- JDC BNDI --------------------*/
        
        Bodi.setcontext("jdc ^ node");
        
        
        /* ---------------- JCM BODI --------------------*/
        
        Bodi.setcontext("jcm ^ jdc");        
        
        Bodi.setcontext("jcm ^ jdcname");
        
        Bodi.setcontext("jcm ^ node");             
        
        Bodi.setcontext("jcm ^ xpath");
        
        
        /* ---------------- NODE BODI -------------------*/
        
        Bodi.setcontext("node ^ jdcname");
        
        Bodi.setcontext("node ^ jcm");
        
        Bodi.setcontext("node ^ node");
    }
    
    public void setsuperclass(JDefinedClass jdefinedclass, Class classname)
    {
        jdefinedclass._extends(classname);
    } 
    
    public void setbodi(JCodeModel jcodemodel, Node node, JDefinedClass jdefinedclass)
    {
        /* ---------------- JDC BNDI --------------------*/
        
        Bodi.context("jdc ^ node").put(jdefinedclass, node);  
        
        
        /* ---------------- JCM BODI --------------------*/
        
        Bodi.context("jcm ^ node").put(jcodemodel, node);                
        
        Bodi.context("jcm ^ xpath").put(jcodemodel, xpath);
                
        Bodi.context("jcm ^ jdc").put(jcodemodel, jdefinedclass);
        
        
        /* ---------------- NODE BODI -------------------*/
        
        Bodi.context("node ^ jdcname").put(node, jdefinedclass.fullName());
        
        Bodi.context("node ^ jcm").put(node, jcodemodel);                
                                                       
        Bodi.context("node ^ node").put(node, node.getParentNode());                                                                             
    }
    
    public ArrayList<JCodeModel> build(String tagname, Class classname)
    {                
        try
        {                  
            this.doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(apml);
        
            this.nodes = (NodeList)xpath.evaluate(tagname, this.doc, XPathConstants.NODESET);           
            
            for(int i=0; i<nodes.getLength(); i++)
            {                                            
                JCodeModel jcodemodel = new JCodeModel();
                
                JPackage jpackage = jcodemodel._package("org.widgets");                                              
                
                JDefinedClass jdefinedclass = jpackage._class(classname.getSimpleName()+"_"+String.format("%1$03d",i));                                                              
                
                this.setbodi(jcodemodel, nodes.item(i), jdefinedclass);
                
                this.setsuperclass(jdefinedclass, classname);
                 
                this.setjcodemodel(jcodemodel);
            }            
        }
        catch(ParserConfigurationException | SAXException | IOException | XPathExpressionException | JClassAlreadyExistsException exception)
        {
            exception.printStackTrace(System.err);
        }
        
        return jcodemodels;        
    }   
    
    protected void setjcodemodel(JCodeModel jcodemodel)
    {
        this.jcodemodels.add(jcodemodel);
    }
}
