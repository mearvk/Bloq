package apml.ui.compilers.java.builder;

import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JPackage;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JFrame;
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
public class Jcmframebuilder 
{        
    public Jcmframebuilder builder = this;
    
    public Document doc;
    
    public Element xml;    
    
    public File apml;            
    
    public NodeList nodes;    
    
    public XPath xpath;
            
    public static void main(String...args)
    {
        new Jcmframebuilder(new File("/home/oem/Desktop/UI/UI.xml")).build("//jframe");
    }    
    
    public Jcmframebuilder(File apml)
    {
        this.apml = apml;
        
        this.xpath = XPathFactory.newInstance().newXPath();          
    }
    
    public ArrayList<JCodeModel> build(String tagname)
    {
        JCodeModel jcodemodel = new JCodeModel();
        
        try
        {        
            this.doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(apml);
        
            this.nodes = (NodeList)xpath.evaluate(tagname, this.doc, XPathConstants.NODESET);
            
            for(int i=0; i<nodes.getLength(); i++)
            {            
                this.xml = (Element)nodes.item(i);
                
                JPackage jpackage = jcodemodel._package("org.widgets");                
                
                JDefinedClass jdefinedclass = jpackage._class("JFrame_"+String.format("%1$03d",i));     
                
                jdefinedclass._extends(JFrame.class);
                
                JMethod constructor = jdefinedclass.constructor(JMod.PUBLIC);                               
                
                try{constructor.body().directStatement("this.setAutoscrolls("+xml.getAttribute("setAutoscrolls")+");");                 }catch(Exception e){}
                
                try{constructor.body().directStatement("this.setAlignmentY("+xml.getAttribute("setAlignmentY")+");");                   }catch(Exception e){}
                
                try{constructor.body().directStatement("this.setAlignmentX("+xml.getAttribute("setAlignmentX")+");");                   }catch(Exception e){}                
                
                try{constructor.body().directStatement("this.setBorderPainted("+xml.getAttribute("setBorderPainted")+");");             }catch(Exception e){}
                
                try{constructor.body().directStatement("this.setBounds("+xml.getAttribute("setBounds")+");");                           }catch(Exception e){}
                
                try{constructor.body().directStatement("this.setBorder("+xml.getAttribute("setBorder")+");");                           }catch(Exception e){}
                
                try{constructor.body().directStatement("this.setBackground("+xml.getAttribute("setBackground")+");");                   }catch(Exception e){}                
                
                try{constructor.body().directStatement("this.setComponentPopupMenu("+xml.getAttribute("setComponentPopupMenu")+");");   }catch(Exception e){}  
                
                try{constructor.body().directStatement("this.setDisabledIcon("+xml.getAttribute("setDisabledIcon")+");");               }catch(Exception e){}

                try{constructor.body().directStatement("this.setEnabled("+xml.getAttribute("setEnabled")+");");                         }catch(Exception e){}  
                
                try{constructor.body().directStatement("this.setForeground("+xml.getAttribute("setForeground")+");");                   }catch(Exception e){} 

                try{constructor.body().directStatement("this.setIcon("+xml.getAttribute("setIcon")+")");                                }catch(Exception e){}

                try{constructor.body().directStatement("this.setLabel("+xml.getAttribute("setLabel")+")");                              }catch(Exception e){}
                
                try{constructor.body().directStatement("this.setLayout("+xml.getAttribute("setLayout")+");");                           }catch(Exception e){}
                
                try{constructor.body().directStatement("this.setLocation("+xml.getAttribute("setLocation")+");");                       }catch(Exception e){}
                
                try{constructor.body().directStatement("this.setMargin("+xml.getAttribute("setMargin")+");");                           }catch(Exception e){}
                
                try{constructor.body().directStatement("this.setMinimumSize("+xml.getAttribute("setMinimumSize")+");");                 }catch(Exception e){}
                
                try{constructor.body().directStatement("this.setMaximumSize("+xml.getAttribute("setMaximumSize")+");");                 }catch(Exception e){}            
                
                try{constructor.body().directStatement("this.setModel("+xml.getAttribute("setModel")+");");                             }catch(Exception e){}
                
                try{constructor.body().directStatement("this.setName("+xml.getAttribute("setName")+");");                               }catch(Exception e){}
                
                try{constructor.body().directStatement("this.setPressedIcon("+xml.getAttribute("setPressedIcon")+");");                 }catch(Exception e){}
                
                try{constructor.body().directStatement("this.setRolloverIcon("+xml.getAttribute("setRolloverIcon")+");");               }catch(Exception e){}                    

                try{constructor.body().directStatement("this.setText("+xml.getAttribute("setText")+");");                               }catch(Exception e){}   
                
                try{constructor.body().directStatement("this.setSize("+xml.getAttribute("setSize")+");");                               }catch(Exception e){}
                
                try{constructor.body().directStatement("this.setToolTipText("+xml.getAttribute("setToolTipText")+");");                 }catch(Exception e){}
            }
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        finally
        {
            try
            {
                jcodemodel.build(new File("/home/oem/Desktop/UI"));
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        
        return null;
    }
}
