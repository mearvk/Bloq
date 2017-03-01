package apml.ui.compilers.java;

import apml.system.bndi.Bndi;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import java.io.File;
import java.util.ArrayList;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author max rupplin
 */
public class Uioutputmanager
{
    public void generaterelations(ArrayList<JCodeModel> jcodemodels)
    {      
        try
        {                   
            for(JCodeModel jcodemodel : jcodemodels)
            {
                this.setparent(jcodemodel);

                this.setchildren(jcodemodel);   
                
                jcodemodel.build(new File("/home/oem/Desktop/UI"));
            }
        }
        catch(Exception e)
        {
            
        }             
    }
    
    private void setparent(JCodeModel jcodemodel)
    {
        try
        {                                            
            Object o = Bndi.contexts;                                  
                        
            Node childnode = (Node)Bndi.context("jcm::node").pull(jcodemodel);                                          
            
            Node parentnode = (Node)Bndi.context("node::node").pull(childnode);
            
            String parentclassname = (String)Bndi.context("node::jdcname").softpull(parentnode);
            
            JDefinedClass jdefinedclass = (JDefinedClass)Bndi.context("jcm::jdc").pull(jcodemodel);
            
            jdefinedclass.direct("\n\t");
            
            jdefinedclass.direct("private "+parentclassname+" parent;\n\t");
        }
        catch(Exception e)
        {
            
        }        
    }
     
    private void setchildren(JCodeModel jcodemodel)
    {
        try
        {                   
            Node node = (Node)Bndi.context("jcm::node").pull(jcodemodel);
            
            XPath xpath = (XPath)Bndi.context("jcm::xpath").pull(jcodemodel);
            
            NodeList nodes = (NodeList)xpath.evaluate("./*", node, XPathConstants.NODESET);
            
            for(int i=0; i<nodes.getLength(); i++)
            {                
                JCodeModel childjmodel = (JCodeModel)Bndi.context("node::jcm").softpull(nodes.item(i));
                
                JDefinedClass childjclass = (JDefinedClass)Bndi.context("jcm::jdc").pull(childjmodel);
                
                JDefinedClass jdefinedclass = (JDefinedClass)Bndi.context("jcm::jdc").pull(jcodemodel);
                                              
                jdefinedclass.direct("\n\t");
                
                String s = "private "+childjclass.fullName()+" child_"+String.format("%1$03d",i)+";\n\t";
                
                jdefinedclass.direct("private "+childjclass.fullName()+" child_"+String.format("%1$03d",i)+";\n\t");              
            }
        }
        catch(Exception e)
        {
            
        }        
    }
}