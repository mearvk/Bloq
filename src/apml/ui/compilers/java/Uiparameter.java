/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apml.ui.compilers.java;

import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JMethod;
import javax.xml.xpath.XPath;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author oem
 */
public class Uiparameter
{    
    protected final Integer hash = 0x888fe8;
    
    public JCodeModel jcm;
    
    public JMethod constructor;
    
    public JDefinedClass jdc;
    
    public String classname;
    
    public String instancename;
    
    public String tagname;
    
    public Node parent_node;    
    
    public Node self_node;    
        
    public Node child_node;
    
    public NodeList children;  
    
    public XPath xpath;
    
    public Document doc;
    
    public Element element;    
}
