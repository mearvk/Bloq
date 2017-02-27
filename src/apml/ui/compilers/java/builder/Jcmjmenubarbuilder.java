package apml.ui.compilers.java.builder;

import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JMod;
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
                
                this.setsuperclass(jdefinedclass, JMenuBar.class);
                
                this.setconstructor(jdefinedclass, xml);   
                
                this.setparent(jdefinedclass, nodes.item(i));
                
                this.setchildren(jdefinedclass, nodes.item(i));
                
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
    public void setparent(JDefinedClass jdefinedclass, Node node)
    {
        try
        {
            String tagname = (String)this.xpath.evaluate("name(./parent::*)", node, XPathConstants.STRING);
            
            String fullclassname = jdefinedclass.fullName();
            
            jdefinedclass.direct("private "+fullclassname+" parent;\n\t");
        }
        catch(Exception e)
        {
            e.getCause();
        }
    }

    @Override
    public void setchildren(JDefinedClass jdefinedclass, Node node)
    {
        try
        {
            NodeList nodes = (NodeList)this.xpath.evaluate("./*", node, XPathConstants.NODESET);
            
            for(int i=0; i<nodes.getLength(); i++)
            {
                String tagname = (String)this.xpath.evaluate("name(./*)", node, XPathConstants.STRING);
            
                String fullclassname = jdefinedclass.fullName();
            
                jdefinedclass.direct("private "+fullclassname+" child_"+String.format("%1$03d",i)+";\n\t");                
            }
        }
        catch(Exception e)
        {
            e.getCause();
        }
    }
}
