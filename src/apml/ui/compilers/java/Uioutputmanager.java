package apml.ui.compilers.java;

import apml.system.bodi.Bodi;
import com.sun.codemodel.ClassType;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JMod;
import java.io.File;
import java.util.ArrayList;
import javax.xml.xpath.XPathConstants;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author max rupplin
 */
public class Uioutputmanager
{
    protected final Integer hash = 0x888fe8;
    
    public void generatejavafiles(ArrayList<JCodeModel> jcodemodels)
    {
        try
        {                   
            for(JCodeModel jcodemodel : jcodemodels)
            {   
                this.setconstructor(jcodemodel);
                
                this.setuisetters(jcodemodel);
                
                this.setfields(jcodemodel);
                
                this.setparent(jcodemodel);  
                                
                this.setchildren(jcodemodel);
                
                this.setlisteners(jcodemodel);
                
                Uiparameter uip = (Uiparameter)Bodi.context("widgets").pull(jcodemodel);
                
                uip.jcm.build(new File("/home/oem/Desktop/UI"));
            }
        }
        catch(Exception exception)
        {
            System.err.println(exception);
        }    
    }
    
    private void setlisteners(JCodeModel jcodemodel)
    {
        Uiparameter uip = (Uiparameter)Bodi.context("widgets").pull(jcodemodel); 
        
        try
        {
            NodeList children = (NodeList)uip.xpath.evaluate("./*", uip.self_node, XPathConstants.NODESET);            
            
            uip.constructor.body().directStatement("/* ------------------  listeners  -------------------- */\n\t");
            
            //Veuillez d'ajouter constructor(s) fields
            for(int i=0; i<children.getLength(); i++)
            {
                Uiparameter uipi = (Uiparameter)Bodi.context("widgets").softpull(children.item(i));                                
                        
                String classname = uipi.classname.toLowerCase();
                
                String listener = classname+"_actionlistener";       
                
                uip.constructor.body().directStatement("this."+classname+".addActionListener("+listener+");\n\t");
            }
            
            //Veuillez d'ajouter actionperformed functions
            for(int i=0; i<children.getLength(); i++)
            {
                Uiparameter uipi = (Uiparameter)Bodi.context("widgets").softpull(children.item(i)); 
                
                String classname = uipi.classname;                                
                
                String nestedlistenerclass = classname+"_ActionListener";                                                               
                
                JDefinedClass nestedclass = uip.jdc._class(JMod.PRIVATE | JMod.FINAL, nestedlistenerclass, ClassType.CLASS);
                
                nestedclass._implements(Class.forName("java.awt.event.ActionListener"));
                
                nestedclass.direct("public void actionPerformed(ActionEvent ae)\n\t");
                
                nestedclass.direct("{\n\t");
                
                nestedclass.direct("/* ------------ stub code goes here ------------ */\n\t");
                
                nestedclass.direct("}\n\t");
            }            
        }
        catch(Exception exception)
        {
            System.err.println(exception);
        }
    }
    
    private void setuisetters(JCodeModel jcodemodel)
    {
        Uiparameter uip = (Uiparameter)Bodi.context("widgets").pull(jcodemodel); 
        
        try
        {                        
           /* ------------------------- General setters ----------------------- */
            
            if(uip.element.getAttribute("setAccelerator")!=null && uip.element.getAttribute("setAccelerator").length()>0)
            {                                
                try{uip.constructor.body().directStatement("this.setAccelerator(KeyStroke.getKeyStroke("+uip.element.getAttribute("setAccelerator")+"));\n\t"); }catch(Exception e){};
            }               
            
            if(uip.element.getAttribute("setAutoscrolls")!=null && uip.element.getAttribute("setAutoscrolls").length()>0)
            {
                try{uip.constructor.body().directStatement("this.setAutoscrolls("+uip.element.getAttribute("setAutoscrolls")+");\n\t");                         }catch(Exception e){}               
            }
                
            if(uip.element.getAttribute("setAlignmentY")!=null && uip.element.getAttribute("setAlignmentY").length()>0)
            {
                try{uip.constructor.body().directStatement("this.setAlignmentY("+uip.element.getAttribute("setAlignmentY")+");\n\t");                           }catch(Exception e){}                
            }
                
            if(uip.element.getAttribute("setAlignmentX")!=null && uip.element.getAttribute("setAlignmentX").length()>0)
            {
                try{uip.constructor.body().directStatement("this.setAlignmentX("+uip.element.getAttribute("setAlignmentX")+");\n\t");                           }catch(Exception e){}                
            }
                
            if(uip.element.getAttribute("setBorderPainted")!=null && uip.element.getAttribute("setBorderPainted").length()>0)
            {
                try{uip.constructor.body().directStatement("this.setBorderPainted("+uip.element.getAttribute("setBorderPainted")+");\n\t");                     }catch(Exception e){}               
            }
                                
            if(uip.element.getAttribute("setBounds")!=null && uip.element.getAttribute("setBounds").length()>0)
            {
                try{uip.constructor.body().directStatement("this.setBounds("+uip.element.getAttribute("setBounds")+");\n\t");                                   }catch(Exception e){}
            }
                                
            if(uip.element.getAttribute("setBorder")!=null && uip.element.getAttribute("setBorder").length()>0)
            {
                try{uip.constructor.body().directStatement("this.setBorder("+uip.element.getAttribute("setBorder")+");\n\t");                                   }catch(Exception e){}                    
            }
                
            if(uip.element.getAttribute("setBackground")!=null && uip.element.getAttribute("setBackground").length()>0)
            {
                try{uip.constructor.body().directStatement("this.setBackground("+uip.element.getAttribute("setBackground")+");\n\t");                           }catch(Exception e){}                                    
            }
                
            if(uip.element.getAttribute("setComponentPopupMenu")!=null && uip.element.getAttribute("setComponentPopupMenu").length()>0)
            {
                try{uip.constructor.body().directStatement("this.setComponentPopupMenu("+uip.element.getAttribute("setComponentPopupMenu")+");\n\t");           }catch(Exception e){}                                   
            }
            
            if(uip.element.getAttribute("setDefaultCloseOperation")!=null && uip.element.getAttribute("setDefaultCloseOperation").length()>0)
            {
                try{uip.constructor.body().directStatement("this.setDefaultCloseOperation("+uip.element.getAttribute("setDefaultCloseOperation")+");\n\t");     }catch(Exception e){}                                   
            }                    
                
            if(uip.element.getAttribute("setDisabledIcon")!=null && uip.element.getAttribute("setDisabledIcon").length()>0)
            {
                try{uip.constructor.body().directStatement("this.setDisabledIcon("+uip.element.getAttribute("setDisabledIcon")+");\n\t");                       }catch(Exception e){}
            }

            if(uip.element.getAttribute("setEnabled")!=null && uip.element.getAttribute("setEnabled").length()>0)
            {
                try{uip.constructor.body().directStatement("this.setEnabled("+uip.element.getAttribute("setEnabled")+");\n\t");                                 }catch(Exception e){}  
            }                
                
            if(uip.element.getAttribute("setForeground")!=null && uip.element.getAttribute("setForeground").length()>0)
            {
                try{uip.constructor.body().directStatement("this.setForeground("+uip.element.getAttribute("setForeground")+");\n\t");                           }catch(Exception e){} 
            }              

            if(uip.element.getAttribute("setIcon")!=null && uip.element.getAttribute("setIcon").length()>0)
            {
                try{uip.constructor.body().directStatement("this.setIcon(new ImageIcon(\""+uip.element.getAttribute("setIcon")+"\"));\n\t");                    }catch(Exception e){}
            }
            
            if(uip.element.getAttribute("setIconAt")!=null && uip.element.getAttribute("setIconAt").length()>0)
            {
                try{uip.constructor.body().directStatement("this.setIconAt(new ImageIcon(\""+uip.element.getAttribute("setIconAt")+"\"));\n\t");                }catch(Exception e){}
            }

            if(uip.element.getAttribute("setLabel")!=null && uip.element.getAttribute("setLabel").length()>0)
            {
                try{uip.constructor.body().directStatement("this.setLabel(\""+uip.element.getAttribute("setLabel")+"\");\n\t");                                 }catch(Exception e){}
            }
                
            if(uip.element.getAttribute("setLayout")!=null && uip.element.getAttribute("setLayout").length()>0)
            {
                try{uip.constructor.body().directStatement("this.setLayout("+uip.element.getAttribute("setLayout")+");\n\t");                                   }catch(Exception e){}
            }
                
            if(uip.element.getAttribute("setLocation")!=null && uip.element.getAttribute("setLocation").length()>0)
            {
                try{uip.constructor.body().directStatement("this.setLocation("+uip.element.getAttribute("setLocation")+");\n\t");                               }catch(Exception e){}
            }
                
            if(uip.element.getAttribute("setMargin")!=null && uip.element.getAttribute("setMargin").length()>0)
            {
                try{uip.constructor.body().directStatement("this.setMargin("+uip.element.getAttribute("setMargin")+");\n\t");                                   }catch(Exception e){}
            }
                
            if(uip.element.getAttribute("setMinimumSize")!=null && uip.element.getAttribute("setMinimumSize").length()>0)
            {
                try{uip.constructor.body().directStatement("this.setMinimumSize("+uip.element.getAttribute("setMinimumSize")+");\n\t");                         }catch(Exception e){}
            }
                
            if(uip.element.getAttribute("setMaximumSize")!=null && uip.element.getAttribute("setMaximumSize").length()>0)
            {
                try{uip.constructor.body().directStatement("this.setMaximumSize("+uip.element.getAttribute("setMaximumSize")+");\n\t");                         }catch(Exception e){}            
            }
                
            if(uip.element.getAttribute("setModel")!=null && uip.element.getAttribute("setModel").length()>0)
            {
                try{uip.constructor.body().directStatement("this.setModel("+uip.element.getAttribute("setModel")+");\n\t");                                     }catch(Exception e){}
            }
            
            if(uip.element.getAttribute("setMnemonic")!=null && uip.element.getAttribute("setMnemonic").length()>0)
            {
                try{uip.constructor.body().directStatement("this.setMnemonic("+uip.element.getAttribute("setMnemonic")+");\n\t");                               }catch(Exception e){}
            }            
                                
            if(uip.element.getAttribute("setName")!=null && uip.element.getAttribute("setName").length()>0)
            {
                try{uip.constructor.body().directStatement("this.setName(\""+uip.element.getAttribute("setName")+"\");\n\t");                                   }catch(Exception e){}
            }
                
            if(uip.element.getAttribute("setPressedIcon")!=null && uip.element.getAttribute("setPressedIcon").length()>0)
            {
                try{uip.constructor.body().directStatement("this.setPressedIcon("+uip.element.getAttribute("setPressedIcon")+");\n\t");                         }catch(Exception e){}
            }
                
            if(uip.element.getAttribute("setRolloverIcon")!=null && uip.element.getAttribute("setRolloverIcon").length()>0)
            {
                try{uip.constructor.body().directStatement("this.setRolloverIcon("+uip.element.getAttribute("setRolloverIcon")+");\n\t");                       }catch(Exception e){}                    
            }

            if(uip.element.getAttribute("setText")!=null && uip.element.getAttribute("setText").length()>0)
            {
                try{uip.constructor.body().directStatement("this.setText(\""+uip.element.getAttribute("setText")+"\");\n\t");                                   }catch(Exception e){}   
            }
            
            if(uip.element.getAttribute("setTitle")!=null && uip.element.getAttribute("setTitle").length()>0)
            {
                try{uip.constructor.body().directStatement("this.setTitle(\""+uip.element.getAttribute("setTitle")+"\");\n\t");                                 }catch(Exception e){}   
            }            
                
            if(uip.element.getAttribute("setSize")!=null && uip.element.getAttribute("setSize").length()>0)
            {
                try{uip.constructor.body().directStatement("this.setSize("+uip.element.getAttribute("setSize")+");\n\t");                                       }catch(Exception e){}
            }
                
            if(uip.element.getAttribute("setToolTipText")!=null && uip.element.getAttribute("setToolTipText").length()>0)
            {
                try{uip.constructor.body().directStatement("this.setToolTipText("+uip.element.getAttribute("setToolTipText")+");\n\t");                         }catch(Exception e){}
            }        
        }
        catch(Exception exception)
        {
            System.err.println(exception);
        }
    }
    
    private void setfields(JCodeModel jcodemodel)
    {
        try
        {                        
            Uiparameter uip = (Uiparameter)Bodi.context("widgets").pull(jcodemodel);                        
               
            uip.jdc.direct("\n\t");

            uip.jdc.field(JMod.PUBLIC, Class.forName("java.awt.event.KeyEvent"), "keyevent");                          

            uip.jdc.field(JMod.PUBLIC, Class.forName("javax.swing.KeyStroke"), "keystroke");               

            uip.jdc.field(JMod.PUBLIC, Class.forName("java.awt.event.ActionEvent"), "actionevent"); 

            uip.jdc.field(JMod.PUBLIC, Class.forName("javax.swing.ImageIcon"), "imageicon");

            uip.jdc.field(JMod.PUBLIC, Class.forName("java.net.URL"), "url");
                        
            //children lookup/finding(s)/etc.
            NodeList kids = (NodeList)uip.xpath.evaluate("./*", uip.self_node, XPathConstants.NODESET);  
            
            
            //user interface fields 
            for(int i=0; i<kids.getLength(); i++)                           
            {       
                Uiparameter uipi = (Uiparameter)Bodi.context("widgets").softpull(kids.item(i));
                
                uip.jdc.direct("public "+uipi.classname+" "+uipi.classname.toLowerCase()+";\n\n\t");
            }
            
            //actionlistener fields
            for(int i=0; i<kids.getLength(); i++)                           
            {              
                Uiparameter uipi = (Uiparameter)Bodi.context("widgets").softpull(kids.item(i));
                
                uip.jdc.direct("public "+uipi.classname+"_ActionListener"+" "+(uipi.classname+"_ActionListener").toLowerCase()+";\n\n\t");
            }            
        }
        catch(Exception exception)
        {
            System.err.println(exception);
        }
    }
    
    private void setconstructor(JCodeModel jcodemodel)
    {               
        try
        {                        
            Uiparameter uip = (Uiparameter)Bodi.context("widgets").pull(jcodemodel);                                                     
            
            uip.constructor = uip.jdc.constructor(JMod.PUBLIC);           
            
            uip.constructor.param(Class.forName("java.awt.Component"), "parent");                                     
        }
        catch(Exception exception)
        {
            System.err.println(exception);
        }       
    }
    
    private void setparent(JCodeModel jcodemodel)
    {
        try
        {               
            Uiparameter uip = (Uiparameter)Bodi.context("widgets").pull(jcodemodel);            
                
            uip.jdc.direct("public Component parent;\n\t");             
        }
        catch(Exception exception)
        {
            System.err.println(exception);
        }        
    }
     
    private void setchildren(JCodeModel jcodemodel)
    {
        try
        {             
            Uiparameter uip = (Uiparameter)Bodi.context("widgets").pull(jcodemodel);   
            
            NodeList nodes = (NodeList)uip.xpath.evaluate("./*", uip.self_node, XPathConstants.NODESET);                                    
            
            this.doinstantiation(nodes, uip.self_node);
           
            this.dohierarchy(nodes, uip.self_node);
                  
            this.dodevolvement(nodes, uip.self_node);
        }
        catch(Exception exception)
        {
            System.err.println(exception);
        }        
    }    
    
    private void doinstantiation(NodeList children, Node self)
    {          
        Uiparameter uip = (Uiparameter)Bodi.context("widgets").pull(self); 
        
        uip.constructor.body().directStatement("/* ------------------  instantiation  ---------------- */\n\t");
        
        for(int i=0; i<children.getLength(); i++)
        {     
            Uiparameter uipi = (Uiparameter)Bodi.context("widgets").softpull(children.item(i));            
                
            uip.constructor.body().directStatement("this."+uipi.classname.toLowerCase()+" = new "+uipi.classname+"(this);\n\t");
        }
    }
    
    private void dohierarchy(NodeList children, Node self)
    {         
        Uiparameter uip = (Uiparameter)Bodi.context("widgets").pull(self); 
        
        uip.constructor.body().directStatement("/* ------------------  hierarchy  -------------------- */\n\t");
            
        for(int i=0; i<children.getLength(); i++)
        {    
            Uiparameter uipi = (Uiparameter)Bodi.context("widgets").softpull(children.item(i));
            
            String childsuperclass = uipi.jdc._extends().name();
            
            String childfieldname = uipi.jdc.name().toLowerCase();                            

            if(childsuperclass.contains("JMenuBar"))            
            {
                uip.constructor.body().directStatement("this.setJMenuBar("+childfieldname+");\n\t"); 
                
                continue;                
            }
                        
            if(childsuperclass.contains("JTabbedPane"))
            {
                uip.constructor.body().directStatement("this.addTab("+childfieldname+");\n\t"); 
                
                continue;                
            }            

            if(!childsuperclass.contains("JMenuBar") && !childsuperclass.contains("JTabbedPane"))
            {
                uip.constructor.body().directStatement("this.add("+childfieldname+");\n\t");
                
                continue;
            }
        }         
    }
    
    private void dodevolvement(NodeList children, Node self)
    {
        Uiparameter uip = (Uiparameter)Bodi.context("widgets").pull(self);         
        
        /* ------------------------ Devolvement setters ------------------------ */
        
        uip.constructor.body().directStatement("/* ------------------  devolvement  -------------------- */\n\t");                         
        
        uip.constructor.body().directStatement("this.parent = parent;\n\t");                        
        
        uip.constructor.body().directStatement("this.setVisible(true);\n\t");   
    }
}