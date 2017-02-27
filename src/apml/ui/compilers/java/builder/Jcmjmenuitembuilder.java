package apml.ui.compilers.java.builder;

import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JPackage;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JMenuItem;
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
public class Jcmjmenuitembuilder
{
    public Jcmjmenuitembuilder builder = this;
    
    public Document doc;
    
    public Element xml;
    
    public File apml;
    
    public NodeList nodes;
    
    public XPath xpath;    
    
    public Jcmjmenuitembuilder(File apml)
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
                
                JDefinedClass jdefinedclass = jpackage._class("JMenuItem_"+String.format("%1$03d",i));   
                
                jdefinedclass._extends(JMenuItem.class);
                
                JMethod constructor = jdefinedclass.constructor(JMod.PUBLIC);      
                
                if(xml.getAttribute("setAutoscrolls")!=null && xml.getAttribute("setAutoscrolls").length()>0)
                {
                    try{constructor.body().directStatement("this.setAutoscrolls("+xml.getAttribute("setAutoscrolls")+");");                 }catch(Exception e){}               
                }
                
                if(xml.getAttribute("setAlignmentX")!=null && xml.getAttribute("setAlignmentX").length()>0)
                {
                    try{constructor.body().directStatement("this.setAlignmentX("+xml.getAttribute("setAlignmentX")+");");                   }catch(Exception e){}                
                }
                
                if(xml.getAttribute("setAlignmentY")!=null && xml.getAttribute("setAlignmentY").length()>0)
                {
                    try{constructor.body().directStatement("this.setAlignmentY("+xml.getAttribute("setAlignmentY")+");");                   }catch(Exception e){}                
                }
                
                if(xml.getAttribute("setAccelerator")!=null && xml.getAttribute("setAccelerator").length()>0)
                {
                    try{constructor.body().directStatement("this.setAccelerator(\""+xml.getAttribute("setAccelerator")+"\");");             }catch(Exception e){}                
                }                
                
                if(xml.getAttribute("setBorderPainted")!=null && xml.getAttribute("setBorderPainted").length()>0)
                {
                    try{constructor.body().directStatement("this.setBorderPainted("+xml.getAttribute("setBorderPainted")+");");             }catch(Exception e){}               
                }
                                
                if(xml.getAttribute("setBounds")!=null && xml.getAttribute("setBounds").length()>0)
                {
                    try{constructor.body().directStatement("this.setBounds("+xml.getAttribute("setBounds")+");");                           }catch(Exception e){}
                }
                                
                if(xml.getAttribute("setBorder")!=null && xml.getAttribute("setBorder").length()>0)
                {
                    try{constructor.body().directStatement("this.setBorder("+xml.getAttribute("setBorder")+");");                           }catch(Exception e){}                    
                }
                
                if(xml.getAttribute("setBackground")!=null && xml.getAttribute("setBackground").length()>0)
                {
                    try{constructor.body().directStatement("this.setBackground("+xml.getAttribute("setBackground")+");");                   }catch(Exception e){}                                    
                }
                
                if(xml.getAttribute("setComponentPopupMenu")!=null && xml.getAttribute("setComponentPopupMenu").length()>0)
                {
                    try{constructor.body().directStatement("this.setComponentPopupMenu("+xml.getAttribute("setComponentPopupMenu")+");");   }catch(Exception e){}                      
                }
                
                if(xml.getAttribute("setDisabledIcon")!=null && xml.getAttribute("setDisabledIcon").length()>0)
                {
                    try{constructor.body().directStatement("this.setDisabledIcon("+xml.getAttribute("setDisabledIcon")+");");               }catch(Exception e){}
                }

                if(xml.getAttribute("setEnabled")!=null && xml.getAttribute("setEnabled").length()>0)
                {
                    try{constructor.body().directStatement("this.setEnabled("+xml.getAttribute("setEnabled")+");");                         }catch(Exception e){}  
                }                
                
                if(xml.getAttribute("setForeground")!=null && xml.getAttribute("setForeground").length()>0)
                {
                    try{constructor.body().directStatement("this.setForeground("+xml.getAttribute("setForeground")+");");                   }catch(Exception e){} 
                }

                if(xml.getAttribute("setIcon")!=null && xml.getAttribute("setIcon").length()>0)
                {
                    try{constructor.body().directStatement("this.setIcon("+xml.getAttribute("setIcon")+")");                                }catch(Exception e){}
                }

                if(xml.getAttribute("setLabel")!=null && xml.getAttribute("setLabel").length()>0)
                {
                    try{constructor.body().directStatement("this.setLabel("+xml.getAttribute("setLabel")+")");                              }catch(Exception e){}
                }
                
                if(xml.getAttribute("setLayout")!=null && xml.getAttribute("setLayout").length()>0)
                {
                    try{constructor.body().directStatement("this.setLayout("+xml.getAttribute("setLayout")+");");                           }catch(Exception e){}
                }
                
                if(xml.getAttribute("setLocation")!=null && xml.getAttribute("setLocation").length()>0)
                {
                    try{constructor.body().directStatement("this.setLocation("+xml.getAttribute("setLocation")+");");                       }catch(Exception e){}
                }
                
                if(xml.getAttribute("setMargin")!=null && xml.getAttribute("setMargin").length()>0)
                {
                    try{constructor.body().directStatement("this.setMargin("+xml.getAttribute("setMargin")+");");                           }catch(Exception e){}
                }
                
                if(xml.getAttribute("setMinimumSize")!=null && xml.getAttribute("setMinimumSize").length()>0)
                {
                    try{constructor.body().directStatement("this.setMinimumSize("+xml.getAttribute("setMinimumSize")+");");                 }catch(Exception e){}
                }
                
                if(xml.getAttribute("setMaximumSize")!=null && xml.getAttribute("setMaximumSize").length()>0)
                {
                    try{constructor.body().directStatement("this.setMaximumSize("+xml.getAttribute("setMaximumSize")+");");                 }catch(Exception e){}            
                }
                
                if(xml.getAttribute("setModel")!=null && xml.getAttribute("setModel").length()>0)
                {
                    try{constructor.body().directStatement("this.setModel("+xml.getAttribute("setModel")+");");                             }catch(Exception e){}
                }
                                
                if(xml.getAttribute("setName")!=null && xml.getAttribute("setName").length()>0)
                {
                    try{constructor.body().directStatement("this.setName("+xml.getAttribute("setName")+");");                               }catch(Exception e){}
                }
                
                if(xml.getAttribute("setPressedIcon")!=null && xml.getAttribute("setPressedIcon").length()>0)
                {
                    try{constructor.body().directStatement("this.setPressedIcon("+xml.getAttribute("setPressedIcon")+");");                 }catch(Exception e){}
                }
                
                if(xml.getAttribute("setRolloverIcon")!=null && xml.getAttribute("setRolloverIcon").length()>0)
                {
                    try{constructor.body().directStatement("this.setRolloverIcon("+xml.getAttribute("setRolloverIcon")+");");               }catch(Exception e){}                    
                }

                if(xml.getAttribute("setText")!=null && xml.getAttribute("setText").length()>0)
                {
                    try{constructor.body().directStatement("this.setText(\""+xml.getAttribute("setText")+"\");");                           }catch(Exception e){}   
                }
                
                if(xml.getAttribute("setSize")!=null && xml.getAttribute("setSize").length()>0)
                {
                    try{constructor.body().directStatement("this.setSize("+xml.getAttribute("setSize")+");");                               }catch(Exception e){}
                }
                
                if(xml.getAttribute("setToolTipText")!=null && xml.getAttribute("setToolTipText").length()>0)
                {
                    try{constructor.body().directStatement("this.setToolTipText("+xml.getAttribute("setToolTipText")+");");                 }catch(Exception e){}
                }
                
                if(xml.getAttribute("setVisible")!=null && xml.getAttribute("setVisible").length()>0)
                {
                    try{constructor.body().directStatement("this.setVisible("+xml.getAttribute("setVisible")+");");                         }catch(Exception e){}
                }
                
                jcodemodel.build(new File("/home/oem/Desktop/UI"));                
            }
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        
        return null;
    }    
}
