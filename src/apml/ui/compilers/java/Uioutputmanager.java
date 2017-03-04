package apml.ui.compilers.java;

import apml.system.bndi.Bndi;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import java.io.File;
import java.util.ArrayList;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import org.w3c.dom.Element;
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
                this.setconstructor(jcodemodel);
                
                this.setparent(jcodemodel);

                this.setchildren(jcodemodel);                                                
                
                jcodemodel.build(new File("/home/oem/Desktop/UI"));
            }
        }
        catch(Exception e)
        {
            
        }             
    }
    
    private void setconstructor(JCodeModel jcodemodel)
    {
        
        Node self = (Node)Bndi.context("jcm::node").pull(jcodemodel);
        
        Element xml = (Element)self;
        
        JDefinedClass jdefinedclass = (JDefinedClass)Bndi.context("jcm::jdc").pull(jcodemodel);
        
        try
        {                
            JMethod constructor = jdefinedclass.constructor(JMod.PUBLIC);
            
            //Node self = (Node)Bndi.context("jdc::node").pull(jdefinedclass);                  //return node form of self [new]
            
            Node parent = (Node)Bndi.context("node::node").pull(self);                          //return parent node given self node [exists already]
            
            String fullparentclassname = (String)Bndi.context("node::jdcname").pull(self);     //return parent node jdc classname (fullname) [exists already]
            
            constructor.param(Class.forName("java.awt.Component"), "parent");
                        
            
            if(xml.getAttribute("setAccelerator")!=null && xml.getAttribute("setAccelerator").length()>0)
            {                                
                try{constructor.body().directStatement("this.setAccelerator(KeyStroke.getKeyStroke("+xml.getAttribute("setAccelerator")+"));\n\t");                      }catch(Exception e){};
            }               
            
            if(xml.getAttribute("setAutoscrolls")!=null && xml.getAttribute("setAutoscrolls").length()>0)
            {
                try{constructor.body().directStatement("this.setAutoscrolls("+xml.getAttribute("setAutoscrolls")+");\n\t");                         }catch(Exception e){}               
            }
                
            if(xml.getAttribute("setAlignmentY")!=null && xml.getAttribute("setAlignmentY").length()>0)
            {
                try{constructor.body().directStatement("this.setAlignmentY("+xml.getAttribute("setAlignmentY")+");\n\t");                           }catch(Exception e){}                
            }
                
            if(xml.getAttribute("setAlignmentX")!=null && xml.getAttribute("setAlignmentX").length()>0)
            {
                try{constructor.body().directStatement("this.setAlignmentX("+xml.getAttribute("setAlignmentX")+");\n\t");                           }catch(Exception e){}                
            }
                
            if(xml.getAttribute("setBorderPainted")!=null && xml.getAttribute("setBorderPainted").length()>0)
            {
                try{constructor.body().directStatement("this.setBorderPainted("+xml.getAttribute("setBorderPainted")+");\n\t");                     }catch(Exception e){}               
            }
                                
            if(xml.getAttribute("setBounds")!=null && xml.getAttribute("setBounds").length()>0)
            {
                try{constructor.body().directStatement("this.setBounds("+xml.getAttribute("setBounds")+");\n\t");                                   }catch(Exception e){}
            }
                                
            if(xml.getAttribute("setBorder")!=null && xml.getAttribute("setBorder").length()>0)
            {
                try{constructor.body().directStatement("this.setBorder("+xml.getAttribute("setBorder")+");\n\t");                                   }catch(Exception e){}                    
            }
                
            if(xml.getAttribute("setBackground")!=null && xml.getAttribute("setBackground").length()>0)
            {
                try{constructor.body().directStatement("this.setBackground("+xml.getAttribute("setBackground")+");\n\t");                           }catch(Exception e){}                                    
            }
                
            if(xml.getAttribute("setComponentPopupMenu")!=null && xml.getAttribute("setComponentPopupMenu").length()>0)
            {
                try{constructor.body().directStatement("this.setComponentPopupMenu("+xml.getAttribute("setComponentPopupMenu")+");\n\t");           }catch(Exception e){}                                   
            }
            
            if(xml.getAttribute("setDefaultCloseOperation")!=null && xml.getAttribute("setDefaultCloseOperation").length()>0)
            {
                try{constructor.body().directStatement("this.setDefaultCloseOperation("+xml.getAttribute("setDefaultCloseOperation")+");\n\t");     }catch(Exception e){}                                   
            }                    
                
            if(xml.getAttribute("setDisabledIcon")!=null && xml.getAttribute("setDisabledIcon").length()>0)
            {
                try{constructor.body().directStatement("this.setDisabledIcon("+xml.getAttribute("setDisabledIcon")+");\n\t");                       }catch(Exception e){}
            }

            if(xml.getAttribute("setEnabled")!=null && xml.getAttribute("setEnabled").length()>0)
            {
                try{constructor.body().directStatement("this.setEnabled("+xml.getAttribute("setEnabled")+");\n\t");                                 }catch(Exception e){}  
            }                
                
            if(xml.getAttribute("setForeground")!=null && xml.getAttribute("setForeground").length()>0)
            {
                try{constructor.body().directStatement("this.setForeground("+xml.getAttribute("setForeground")+");\n\t");                           }catch(Exception e){} 
            }    
            
            if(xml.getAttribute("setHeight")!=null && xml.getAttribute("setHeight").length()>0)
            {
                try{constructor.body().directStatement("this.setHeight(\""+xml.getAttribute("setHeight")+"\");\n\t");                           }catch(Exception e){} 
            }            

            if(xml.getAttribute("setIcon")!=null && xml.getAttribute("setIcon").length()>0)
            {
                try{constructor.body().directStatement("this.setIcon("+xml.getAttribute("setIcon")+");\n\t");                                       }catch(Exception e){}
            }

            if(xml.getAttribute("setLabel")!=null && xml.getAttribute("setLabel").length()>0)
            {
                try{constructor.body().directStatement("this.setLabel(\""+xml.getAttribute("setLabel")+"\");\n\t");                                 }catch(Exception e){}
            }
                
            if(xml.getAttribute("setLayout")!=null && xml.getAttribute("setLayout").length()>0)
            {
                try{constructor.body().directStatement("this.setLayout("+xml.getAttribute("setLayout")+");\n\t");                                   }catch(Exception e){}
            }
                
            if(xml.getAttribute("setLocation")!=null && xml.getAttribute("setLocation").length()>0)
            {
                try{constructor.body().directStatement("this.setLocation("+xml.getAttribute("setLocation")+");\n\t");                               }catch(Exception e){}
            }
                
            if(xml.getAttribute("setMargin")!=null && xml.getAttribute("setMargin").length()>0)
            {
                try{constructor.body().directStatement("this.setMargin("+xml.getAttribute("setMargin")+");\n\t");                                   }catch(Exception e){}
            }
                
            if(xml.getAttribute("setMinimumSize")!=null && xml.getAttribute("setMinimumSize").length()>0)
            {
                try{constructor.body().directStatement("this.setMinimumSize("+xml.getAttribute("setMinimumSize")+");\n\t");                         }catch(Exception e){}
            }
                
            if(xml.getAttribute("setMaximumSize")!=null && xml.getAttribute("setMaximumSize").length()>0)
            {
                try{constructor.body().directStatement("this.setMaximumSize("+xml.getAttribute("setMaximumSize")+");\n\t");                         }catch(Exception e){}            
            }
                
            if(xml.getAttribute("setModel")!=null && xml.getAttribute("setModel").length()>0)
            {
                try{constructor.body().directStatement("this.setModel("+xml.getAttribute("setModel")+");\n\t");                                     }catch(Exception e){}
            }
            
            if(xml.getAttribute("setMnemonic")!=null && xml.getAttribute("setMnemonic").length()>0)
            {
                try{constructor.body().directStatement("this.setMnemonic("+xml.getAttribute("setMnemonic")+");\n\t");                               }catch(Exception e){}
            }            
                                
            if(xml.getAttribute("setName")!=null && xml.getAttribute("setName").length()>0)
            {
                try{constructor.body().directStatement("this.setName(\""+xml.getAttribute("setName")+"\");\n\t");                                   }catch(Exception e){}
            }
                
            if(xml.getAttribute("setPressedIcon")!=null && xml.getAttribute("setPressedIcon").length()>0)
            {
                try{constructor.body().directStatement("this.setPressedIcon("+xml.getAttribute("setPressedIcon")+");\n\t");                         }catch(Exception e){}
            }
                
            if(xml.getAttribute("setRolloverIcon")!=null && xml.getAttribute("setRolloverIcon").length()>0)
            {
                try{constructor.body().directStatement("this.setRolloverIcon("+xml.getAttribute("setRolloverIcon")+");\n\t");                       }catch(Exception e){}                    
            }

            if(xml.getAttribute("setText")!=null && xml.getAttribute("setText").length()>0)
            {
                try{constructor.body().directStatement("this.setText(\""+xml.getAttribute("setText")+"\");\n\t");                                   }catch(Exception e){}   
            }
            
            if(xml.getAttribute("setTitle")!=null && xml.getAttribute("setTitle").length()>0)
            {
                try{constructor.body().directStatement("this.setTitle(\""+xml.getAttribute("setTitle")+"\");\n\t");                                 }catch(Exception e){}   
            }            
                
            if(xml.getAttribute("setSize")!=null && xml.getAttribute("setSize").length()>0)
            {
                try{constructor.body().directStatement("this.setSize("+xml.getAttribute("setSize")+");\n\t");                                       }catch(Exception e){}
            }
                
            if(xml.getAttribute("setToolTipText")!=null && xml.getAttribute("setToolTipText").length()>0)
            {
                try{constructor.body().directStatement("this.setToolTipText("+xml.getAttribute("setToolTipText")+");\n\t");                         }catch(Exception e){}
            }
                
            if(xml.getAttribute("setVisible")!=null && xml.getAttribute("setVisible").length()>0)
            {
                try{constructor.body().directStatement("this.setVisible("+xml.getAttribute("setVisible")+");\n\t");                                 }catch(Exception e){}
            } 
            
            if(xml.getAttribute("setWidth")!=null && xml.getAttribute("setWidth").length()>0)
            {
                try{constructor.body().directStatement("this.setWidth(\""+xml.getAttribute("setWidth")+"\");\n\t");                                 }catch(Exception e){}
            }            
        }
        catch(Exception exception)
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
            
            if(parentclassname == null || true)
            {
                jdefinedclass.direct("\n\t");
                
                jdefinedclass.direct("public Component parent;\n\t");                
                
                jdefinedclass.direct("\n\t");
                
                jdefinedclass.field(JMod.PUBLIC, Class.forName("java.awt.event.KeyEvent"), "ke_ref");
                
                jdefinedclass.field(JMod.PUBLIC, Class.forName("javax.swing.KeyStroke"), "ks_ref");
                
                jdefinedclass.field(JMod.PUBLIC, Class.forName("java.awt.event.ActionEvent"), "ae_ref");
            }            
            else
            {                                 
                jdefinedclass.direct("\n\t");

                jdefinedclass.direct("public "+parentclassname+" parent;\n\t");

                jdefinedclass.direct("\n\t");            
            }
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
            
            JDefinedClass parentjdc = (JDefinedClass)Bndi.context("jcm::jdc").pull(jcodemodel);                     
            
            parentjdc.constructors().next().body().directStatement("/* ------------------  instantiation  ---------------- */\n\t");            
            
            for(int i=0; i<nodes.getLength(); i++)
            {                
                JCodeModel childjmodel = (JCodeModel)Bndi.context("node::jcm").softpull(nodes.item(i));
                
                JDefinedClass childjclass = (JDefinedClass)Bndi.context("jcm::jdc").pull(childjmodel);
                
                JDefinedClass jdefinedclass = (JDefinedClass)Bndi.context("jcm::jdc").pull(jcodemodel);
                                              
                jdefinedclass.direct("\n\t");
                
                String s = "public "+childjclass.fullName()+" child_"+String.format("%1$03d",i)+";\n\t";
                
                jdefinedclass.direct("public "+childjclass.fullName()+" "+childjclass.name().toLowerCase()+";\n\t");              
                
                jdefinedclass.direct("\n\t");            
                
                jdefinedclass.constructors().next().body().directStatement("this."+childjclass.name().toLowerCase()+" = new "+childjclass.name()+"(this);\n\t");
            }
            
            parentjdc.constructors().next().body().directStatement("/* ------------------  hierarchy  ---------------- */\n\t");
            
            for(int i=0; i<nodes.getLength(); i++)
            {                
                JCodeModel childjmodel = (JCodeModel)Bndi.context("node::jcm").softpull(nodes.item(i));
                
                JDefinedClass childjclass = (JDefinedClass)Bndi.context("jcm::jdc").pull(childjmodel);
                
                JDefinedClass jdefinedclass = (JDefinedClass)Bndi.context("jcm::jdc").pull(jcodemodel);                                                                                          
                
                jdefinedclass.direct("\n\t");
            
                jdefinedclass.constructors().next().body().directStatement("this.add("+childjclass.name().toLowerCase()+");\n\t");
            }       
            
            parentjdc.constructors().next().body().directStatement("/* ------------------  devolvement  ---------------- */\n\t");     
            
            parentjdc.constructors().next().body().directStatement("this.parent = parent;\n\t");
        }
        catch(Exception e)
        {
            
        }        
    }    
}