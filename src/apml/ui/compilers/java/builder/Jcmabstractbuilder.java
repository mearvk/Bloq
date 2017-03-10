package apml.ui.compilers.java.builder;

import apml.system.bodi.Bndi;
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
        
        Bndi.setcontext("jdc ^ node");
        
        
        /* ---------------- JCM BODI --------------------*/
        
        Bndi.setcontext("jcm ^ jdc");        
        
        Bndi.setcontext("jcm ^ jdcname");
        
        Bndi.setcontext("jcm ^ node");             
        
        Bndi.setcontext("jcm ^ xpath");
        
        
        /* ---------------- NODE BODI -------------------*/
        
        Bndi.setcontext("node ^ jdcname");
        
        Bndi.setcontext("node ^ jcm");
        
        Bndi.setcontext("node ^ node");
    }
    
    public void setsuperclass(JDefinedClass jdefinedclass, Class classname)
    {
        jdefinedclass._extends(classname);
    } 
    
    public void setbndi(JCodeModel jcodemodel, Node node, JDefinedClass jdefinedclass)
    {
        /* ---------------- JDC BNDI --------------------*/
        
        Bndi.context("jdc ^ node").put(jdefinedclass, node);  
        
        
        /* ---------------- JCM BODI --------------------*/
        
        Bndi.context("jcm ^ node").put(jcodemodel, node);                
        
        Bndi.context("jcm ^ xpath").put(jcodemodel, xpath);
                
        Bndi.context("jcm ^ jdc").put(jcodemodel, jdefinedclass);
        
        
        /* ---------------- NODE BODI -------------------*/
        
        Bndi.context("node ^ jdcname").put(node, jdefinedclass.fullName());
        
        Bndi.context("node ^ jcm").put(node, jcodemodel);                
                                                       
        Bndi.context("node ^ node").put(node, node.getParentNode());                                                                             
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
                
                this.setbndi(jcodemodel, nodes.item(i), jdefinedclass);
                
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
