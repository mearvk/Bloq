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
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author max rupplin
 */
public abstract class Jcmabstractbuilder
{   
    public ArrayList<JCodeModel> models = new ArrayList<>();
    
    public Jcmabstractbuilder builder = this;
    
    public Document doc;
    
    public Element xml;
    
    public File apml;
        
    public NodeList nodes;
    
    public XPath xpath; 
    
    public Jcmabstractbuilder()
    {
        Bndi.setcontext("jcm::node");
                
        Bndi.setcontext("node::jcm");
                
        Bndi.setcontext("jcm::xpath");
                
        Bndi.setcontext("node::node");
             
        Bndi.setcontext("jcm::jdc");    
        
        Bndi.setcontext("jcm::jdcname");
        
        Bndi.setcontext("node::jdcname");
        
        Bndi.setcontext("jdc::node");
    }
    
    public void setsuperclass(JDefinedClass jdefinedclass, Class classname)
    {
        jdefinedclass._extends(classname);
    } 
    
    public void setbndi(JCodeModel jcodemodel, Node node, JDefinedClass jdefinedclass)
    {
        Bndi.context("jcm::node").put(jcodemodel, node);                
                
        Bndi.context("node::jcm").put(node, jcodemodel);                
                
        Bndi.context("jcm::xpath").put(jcodemodel, xpath);
               
        Bndi.context("node::node").put(node, node.getParentNode());    
                
        Bndi.context("jcm::jdc").put(jcodemodel, jdefinedclass);
               
        Bndi.context("node::jdcname").put(node, jdefinedclass.fullName());
                
        Bndi.context("jdc::node").put(jdefinedclass, node);        
    }
    
    public ArrayList<JCodeModel> build(String tagname, Class classname)
    {
        ArrayList<JCodeModel> jcodemodels = new ArrayList<>();
        
        try
        {                  
            this.doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(apml);
        
            this.nodes = (NodeList)xpath.evaluate(tagname, this.doc, XPathConstants.NODESET);           
            
            for(int i=0; i<nodes.getLength(); i++)
            {            
                this.xml = (Element)nodes.item(i);

                JCodeModel jcodemodel = new JCodeModel();
                
                JPackage jpackage = jcodemodel._package("org.widgets");                                              
                
                JDefinedClass jdefinedclass = jpackage._class(classname.getSimpleName()+"_"+String.format("%1$03d",i));                                                              
                
                this.setbndi(jcodemodel, nodes.item(i), jdefinedclass);
                
                this.setsuperclass(jdefinedclass, classname);
                                
                jcodemodels.add(jcodemodel);
            }
            
            this.models = jcodemodels;
        }
        catch(ParserConfigurationException | SAXException | IOException | XPathExpressionException | JClassAlreadyExistsException exception)
        {
            exception.printStackTrace(System.err);
        }
        
        return jcodemodels;        
    }        
}
