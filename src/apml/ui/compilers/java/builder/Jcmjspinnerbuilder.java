package apml.ui.compilers.java.builder;

import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JPackage;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JSpinner;
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
public class Jcmjspinnerbuilder extends Jcmabstractbuilder
{
    public Jcmjspinnerbuilder builder = this;
    
    public Document doc;
    
    public Element xml;
    
    public File apml;
    
    public NodeList nodes;
    
    public XPath xpath;    
    
    public Jcmjspinnerbuilder(File apml)
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
                
                JDefinedClass jdefinedclass = jpackage._class("JSpinner_"+String.format("%1$03d",i));     
                
                JMethod constructor = jdefinedclass.constructor(JMod.PUBLIC);
                
                this.setsuperclass(jdefinedclass, JSpinner.class);
                
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
    public void setparent(JCodeModel jcodemodel, Node parent)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setchildren(JCodeModel jcodemodel, Node node)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
