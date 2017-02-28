package apml.ui.compilers.java;

import apml.system.bndi.Bndi;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
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
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }             
    }
    
    private void setparent(JCodeModel jcodemodel)
    {
        try
        {                                            
            Node node = (Node)Bndi.context("//framing/jcm/nodes").pull((Object)jcodemodel);
            
            XPath xpath = (XPath)Bndi.context("//framing/jcm/xpath").pull((Object)jcodemodel);
                        
            Node parent = (Node)xpath.evaluate("./parent::*", node, XPathConstants.NODE);                    
            
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
    
    private void setchildren(JCodeModel jcodemodel)
    {
        try
        {
            Node node = (Node)Bndi.context("//framing/jcm/nodes").pull((Object)jcodemodel);
            
            XPath xpath = (XPath)Bndi.context("//framing/jcm/xpath").pull((Object)jcodemodel);
            
            NodeList nodes = (NodeList)xpath.evaluate("./*", node, XPathConstants.NODESET);
            
            for(int i=0; i<nodes.getLength(); i++)
            {                
                JDefinedClass jdefinedclass = jcodemodel.packages().next().classes().next();
                                              
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
