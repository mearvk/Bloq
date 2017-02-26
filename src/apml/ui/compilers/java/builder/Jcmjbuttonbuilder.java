package apml.ui.compilers.java.builder;

import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JPackage;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author max rupplin
 */
public class Jcmjbuttonbuilder 
{        
    public Jcmjbuttonbuilder builder = this;
    
    public Document doc;
    
    public Element xml;    
    
    public File apml;            
    
    public NodeList nodes;    
    
    public XPath xpath;
            
    public Jcmjbuttonbuilder(File apml)
    {
        this.apml = apml;
        
        this.xpath = XPathFactory.newInstance().newXPath();          
    }
    
    public ArrayList<JCodeModel> build(String tagname)
    {
        try
        {        
            this.doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(apml);
        
            this.nodes = (NodeList)xpath.evaluate(tagname, this.doc, XPathConstants.NODESET);
            
            for(int i=0; i<nodes.getLength(); i++)
            {            
                JCodeModel jcodemodel = new JCodeModel();

                JPackage jpackage = jcodemodel._package("org.widgets.one");                
                
                JDefinedClass jdefinedclass = jcodemodel._class("JButton"+i);      
                
                jdefinedclass._extends(JButton.class);
                
                JMethod constructor = jdefinedclass.constructor(JMod.PUBLIC);                               
                
                constructor.body().directStatement("this.setAutoscrolls("+xml.getAttribute("setAutoscrolls")+");");  
                
                constructor.body().directStatement("this.setAlignmentY("+xml.getAttribute("setAlignmentY")+");");  
                
                constructor.body().directStatement("this.setAlignmentX("+xml.getAttribute("setAlignmentX")+");");                 
                
                constructor.body().directStatement("this.setBorderPainted("+xml.getAttribute("setBorderPainted")+");");
                
                constructor.body().directStatement("this.setBounds("+xml.getAttribute("setBounds")+");");  
                
                constructor.body().directStatement("this.setBorder("+xml.getAttribute("setBorder")+");");                
                
                constructor.body().directStatement("this.setBackground("+xml.getAttribute("setBackground")+");");                 
                
                constructor.body().directStatement("this.setComponentPopupMenu("+xml.getAttribute("setComponentPopupMenu")+");");  
                
                constructor.body().directStatement("this.setDisabledIcon("+xml.getAttribute("setDisabledIcon")+");");

                constructor.body().directStatement("this.setEnabled("+xml.getAttribute("setEnabled")+");");     
                
                constructor.body().directStatement("this.setForeground("+xml.getAttribute("setForeground")+");");  

                constructor.body().directStatement("this.setIcon("+xml.getAttribute("setIcon")+")");

                constructor.body().directStatement("this.setLabel("+xml.getAttribute("setLabel")+")");
                
                constructor.body().directStatement("this.setLayout("+xml.getAttribute("setLayout")+");");
                
                constructor.body().directStatement("this.setLocation("+xml.getAttribute("setLocation")+");");
                
                constructor.body().directStatement("this.setMargin("+xml.getAttribute("setMargin")+");");
                
                constructor.body().directStatement("this.setMinimumSize("+xml.getAttribute("setMinimumSize")+");"); 
                
                constructor.body().directStatement("this.setMaximumSize("+xml.getAttribute("setMaximumSize")+");");                 
                
                constructor.body().directStatement("this.setModel("+xml.getAttribute("setModel")+");");
                
                constructor.body().directStatement("this.setName("+xml.getAttribute("setName")+");");
                
                constructor.body().directStatement("this.setPressedIcon("+xml.getAttribute("setPressedIcon")+");");
                
                constructor.body().directStatement("this.setRolloverIcon("+xml.getAttribute("setRolloverIcon")+");");                                

                constructor.body().directStatement("this.setText("+xml.getAttribute("setText")+");");               
                
                constructor.body().directStatement("this.setSize("+xml.getAttribute("setSize")+");"); 
                
                constructor.body().directStatement("this.setToolTipText("+xml.getAttribute("setToolTipText")+");");  
            }
        }
        catch(Exception exception)
        {
            
        }
        
        return null;
    }
}
