package apml.ui.compilers.java.builder;

import apml.system.bndi.Bndi;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JPackage;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JMenuBar;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author max rupplin
 */
public class Jcmjmenubarbuilder extends Jcmabstractbuilder
{
    public Jcmjmenubarbuilder builder = this;
    
    public Document doc;
    
    public Element xml;
    
    public File apml;
    
    public NodeList nodes;
    
    public XPath xpath;    
    
    public Jcmjmenubarbuilder(File apml)
    {
        this.apml = apml;
        
        this.xpath = XPathFactory.newInstance().newXPath();            
    }    
    
    public ArrayList<JCodeModel> build(String tagname)
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
                
                JDefinedClass jdefinedclass = jpackage._class("JMenuBar_"+String.format("%1$03d",i));                    
                
                Bndi.setcontext("//framing/nodes");
                
                Bndi.setcontext("//framing/xpath");                
                
                Bndi.context("//framing/jcm/nodes").put(jcodemodel, nodes.item(i));
                
                Bndi.context("//framing/jcm/xpath").put(jcodemodel, xpath);
                
                this.setsuperclass(jdefinedclass, JMenuBar.class);
                
                this.setconstructor(jdefinedclass, xml);                   
                
                jcodemodel.build(new File("/home/oem/Desktop/UI"));
                                
                jcodemodels.add(jcodemodel);
            }                        
        }
        catch(Exception exception)
        {
            
        }
        
        return jcodemodels;
    }    

    @Override
    public void setparent(JCodeModel jcodemodel, Node node)
    {
        try
        {                 
            Node tagnode = (Node)Bndi.context("//framing/").pull((Object)jcodemodel);
                        
            Node parent = (Node)this.xpath.evaluate("./parent::*", tagnode, XPathConstants.NODE);                    
            
            String fullclassname = jcodemodel.packages().next().classes().next().fullName();
            
            JDefinedClass jdefinedclass = jcodemodel.packages().next().classes().next();
            
            jdefinedclass.direct("\n\t");
            jdefinedclass.direct("private "+fullclassname+" parent;\n\t");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void setchildren(JCodeModel jcodemodel, Node node)
    {
        try
        {
            NodeList nodes = (NodeList)this.xpath.evaluate("./*", node, XPathConstants.NODESET);
            
            for(int i=0; i<nodes.getLength(); i++)
            {                
                JDefinedClass jdefinedclass = (JDefinedClass)Bndi.context("//framing/").pull(nodes.item(i).toString());
                                              
                jdefinedclass.direct("\n\t");
                jdefinedclass.direct("private "+jdefinedclass.fullName()+" child_"+String.format("%1$03d",i)+";\n\t");              
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
