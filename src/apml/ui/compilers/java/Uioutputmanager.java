package apml.ui.compilers.java;

import apml.system.bodi.Bodi;

import com.sun.codemodel.ClassType;

import com.sun.codemodel.JCodeModel;

import com.sun.codemodel.JDefinedClass;

import com.sun.codemodel.JMod;

import java.io.File;

import java.util.ArrayList;

import javax.xml.xpath.XPathConstants;

import org.w3c.dom.NamedNodeMap;

import org.w3c.dom.Node;

import org.w3c.dom.NodeList;

/**
 *
 * @author Max Rupplin
 */
public class Uioutputmanager
{
    protected final Integer hash = 0x00888fe8;
    
    public Uicompiler compiler;
    
    public Uioutputmanager(Uicompiler compiler)
    {
        this.compiler = compiler;
    }    
    
    public void generatefiles(ArrayList<JCodeModel> jcodemodels)
    {
        try
        {                   
            for(JCodeModel jcodemodel : jcodemodels)
            {   
                //this.setconstructor(jcodemodel);
                
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
            
        }    
    }
    
    private void setlisteners(JCodeModel jcodemodel)
    {
        Uiparameter uip = (Uiparameter)Bodi.context("widgets").pull(jcodemodel); 
        
        try
        {
            NodeList children = (NodeList)uip.xpath.evaluate("./*", uip.node, XPathConstants.NODESET); 
            
            for(int i=0; i<children.getLength(); i++)
            {
                Uiparameter uipi = (Uiparameter)Bodi.context("widgets").softpull(children.item(i)); 
                
                //System.err.println("CLASSNAME: "+uipi.classname);
            }
            
            uip.constructor1.body().directStatement("/* ------------------  listeners  -------------------- */\n\t");
            uip.constructor2.body().directStatement("/* ------------------  listeners  -------------------- */\n\t");
            
            //add action listener connects
            for(int i=0; i<children.getLength(); i++)
            {
                Uiparameter uipi = (Uiparameter)Bodi.context("widgets").softpull(children.item(i));                                
                        
                if(uipi.classname.contains("JPanel")) continue; //todo fix me more standardly
                
                if(uipi.classname.contains("JMenuBar")) continue; //todo fix me more standardly
                
                String instancename = uipi.instancename;
                
                String listener = instancename+"_actionlistener";       
                
                uip.constructor1.body().directStatement("this."+instancename+".addActionListener("+listener+");\n\t");
                uip.constructor2.body().directStatement("this."+instancename+".addActionListener("+listener+");\n\t");
            }            
            
            //add private final nested classes for action listeners
            for(int i=0; i<children.getLength(); i++)
            {
                Uiparameter uipi = (Uiparameter)Bodi.context("widgets").softpull(children.item(i)); 
                
                if(uipi.classname.contains("JPanel")) continue; //todo fix me more standardly
                
                if(uipi.classname.contains("JMenuBar")) continue; //todo fix me more standardly
                
                String classname = uipi.classname;                                
                
                String nestedlistenerclass = classname+"_ActionListener";                                                               
                
                JDefinedClass nestedclass = uip.jdc._class(JMod.PRIVATE | JMod.FINAL, nestedlistenerclass, ClassType.CLASS);
                
                nestedclass._implements(Class.forName("java.awt.event.ActionListener"));
                
                nestedclass.direct("public void actionPerformed(ActionEvent ae)\n\t");
                
                nestedclass.direct("{\n\t");
                
                nestedclass.direct("\tSystem.out.println(\"ActionCommand: \"+ae.getActionCommand());\n\t");
                
                nestedclass.direct("}\n\t");
            }            
        }
        catch(Exception exception)
        {
            
        }
    }
    
    private void setuisetters(JCodeModel jcodemodel)
    {
        Uiparameter uip = (Uiparameter)Bodi.context("widgets").pull(jcodemodel); 
        
        try
        {              
            NamedNodeMap attribs = uip.element.getAttributes();
            
            for(int i=0; i<attribs.getLength(); i++)
            {
                Node attribute = attribs.item(i);
                
                if(attribute.getNodeName().startsWith("setAccelerator"))
                {
                    String string = "this.setAccelerator(KeyStroke.getKeyStroke("+attribute.getNodeName()+"));";
                    
                    uip.constructor1.body().directStatement("this.setAccelerator(KeyStroke.getKeyStroke("+string+"));\n\t");
                    uip.constructor2.body().directStatement("this.setAccelerator(KeyStroke.getKeyStroke("+string+"));\n\t");
                    
                    continue;
                }
                
                if(attribute.getNodeName().startsWith("setIconAt"))
                {
                    String string = "this.setIconAt(\""+attribute.getNodeValue()+"\");\n\t";
                    
                    uip.constructor1.body().directStatement(string);
                    uip.constructor2.body().directStatement(string);
                    
                    continue;
                }                 
                
                if(attribute.getNodeName().startsWith("setIcon"))
                {
                    String string = "this.setIcon(\""+attribute.getNodeValue()+"\");\n\t";
                    
                    uip.constructor1.body().directStatement(string);
                    uip.constructor2.body().directStatement(string);
                    
                    continue;
                }                
                
                if(attribute.getNodeName().startsWith("setLabel"))
                {
                    String string = "this.setLabel(\""+attribute.getNodeValue()+"\");\n\t";
                    
                    uip.constructor1.body().directStatement(string);
                    uip.constructor2.body().directStatement(string);
                    
                    continue;
                } 
                
                if(attribute.getNodeName().startsWith("setName"))
                {
                    String string = "this.setName(\""+attribute.getNodeValue()+"\");\n\t";
                    
                    uip.constructor1.body().directStatement(string);
                    uip.constructor2.body().directStatement(string);
                    
                    continue;
                }     
                
               if(attribute.getNodeName().startsWith("setText"))
                {
                    String string = "this.setText(\""+attribute.getNodeValue()+"\");\n\t";
                    
                    uip.constructor1.body().directStatement(string);
                    uip.constructor2.body().directStatement(string);
                    
                    continue;
                }                
                
                if(attribute.getNodeName().startsWith("setTitle"))
                {
                    String string = "this.setTitle(\""+attribute.getNodeValue()+"\");\n\t";
                    
                    uip.constructor1.body().directStatement(string);
                    uip.constructor2.body().directStatement(string);
                    
                    continue;
                }    
                
                if(attribute.getNodeName().startsWith("setToolTipText"))
                {
                    String string = "this.setToolTipText(\""+attribute.getNodeValue()+"\");\n\t";
                    
                    uip.constructor1.body().directStatement(string);
                    uip.constructor2.body().directStatement(string);
                    
                    continue;
                }                                                         
                
                if(attribute.getNodeName().startsWith("set"))
                {
                    String string = "this."+attribute.getNodeName()+"("+attribute.getNodeValue()+");\n\t";
                    
                    uip.constructor1.body().directStatement(string);
                    uip.constructor2.body().directStatement(string);
                    
                    continue;
                }
            }                   
        }
        catch(Exception exception)
        {
            
        }
    } 
    
    private void setfields(JCodeModel jcodemodel)
    {
        try
        {                        
            Uiparameter uip = (Uiparameter)Bodi.context("widgets").pull(jcodemodel);                        
               
            uip.jdc.direct("\n\t");

            uip.jdc.field(JMod.PUBLIC, Class.forName("java.awt.event.KeyEvent"), "ref_001");                          

            uip.jdc.field(JMod.PUBLIC, Class.forName("javax.swing.KeyStroke"), "ref_002");               

            uip.jdc.field(JMod.PUBLIC, Class.forName("java.awt.event.ActionEvent"), "ref_003"); 

            uip.jdc.field(JMod.PUBLIC, Class.forName("javax.swing.ImageIcon"), "ref_004");

            uip.jdc.field(JMod.PUBLIC, Class.forName("java.net.URL"), "ref_005");
            
            uip.jdc.field(JMod.PUBLIC, Class.forName("java.awt.Color"), "ref_006");
            
            uip.jdc.field(JMod.PUBLIC, Class.forName("java.awt.BorderLayout"), "ref_007");

            uip.jdc.field(JMod.PUBLIC, Class.forName("java.awt.FlowLayout"), "ref_008");
            
            uip.jdc.field(JMod.PUBLIC, Class.forName("java.awt.GridLayout"), "ref_009");
            
            uip.jdc.field(JMod.PUBLIC, Class.forName("java.awt.Color"), "ref_010");
            
            uip.jdc.field(JMod.PUBLIC, Class.forName("javax.swing.border.EmptyBorder"), "ref_011");
            
                        
            NodeList children = (NodeList)uip.xpath.evaluate("./*", uip.node, XPathConstants.NODESET);  
                        
            for(int i=0; i<children.getLength(); i++)                           
            {       
                Uiparameter uipi = (Uiparameter)Bodi.context("widgets").softpull(children.item(i));
                
                //if(uipi.classname.contains("JPanel")) continue; //todo fix me more standardly
                
                //if(uipi.classname.contains("JMenuBar")) continue; //todo fix me more standardly
                
                uip.jdc.direct("public "+uipi.classname+" "+uipi.classname.toLowerCase()+";\n\n\t");
            }
            
            for(int i=0; i<children.getLength(); i++)                           
            {              
                Uiparameter uipi = (Uiparameter)Bodi.context("widgets").softpull(children.item(i));
                
                if(uipi.classname.contains("JPanel")) continue; //todo fix me more standardly
                
                if(uipi.classname.contains("JMenuBar")) continue; //todo fix me more standardly                
                
                uip.jdc.direct("public "+uipi.classname+"_ActionListener"+" "+(uipi.classname+"_ActionListener").toLowerCase()+";\n\n\t");
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
            
            NodeList nodes = (NodeList)uip.xpath.evaluate("./*", uip.node, XPathConstants.NODESET);                                                    
            
            this.doinstantiation(nodes, uip.node);
           
            this.dohierarchy(nodes, uip.node);
                  
            this.dodevolvement(nodes, uip.node);
        }
        catch(Exception exception)
        {
            
        }        
    }    
    
    private void doinstantiation(NodeList children, Node self)
    {          
        Uiparameter uip = (Uiparameter)Bodi.context("widgets").pull(self); 
        
        uip.constructor1.body().directStatement("/* ------------------  instantiation  ---------------- */\n\t");
        uip.constructor2.body().directStatement("/* ------------------  instantiation  ---------------- */\n\t");
        
        //ui instantiation
        for(int i=0; i<children.getLength(); i++)
        {     
            Uiparameter uipi = (Uiparameter)Bodi.context("widgets").softpull(children.item(i));            
                
            //if(uipi.classname.contains("JPanel")) continue; //todo fix me more standardly
                
            //if(uipi.classname.contains("JMenuBar")) continue; //todo fix me more standardly            
            
            uip.constructor1.body().directStatement("this."+uipi.classname.toLowerCase()+" = new "+uipi.classname+"(this);\n\t");
            uip.constructor2.body().directStatement("this."+uipi.classname.toLowerCase()+" = new "+uipi.classname+"(this);\n\t");
        }
        
        //action listener instantiation
        for(int i=0; i<children.getLength(); i++)
        {     
            Uiparameter uipi = (Uiparameter)Bodi.context("widgets").softpull(children.item(i));            
            
            if(uipi.classname.contains("JPanel")) continue; //todo fix me more standardly
                
            if(uipi.classname.contains("JMenuBar")) continue; //todo fix me more standardly            
                
            uip.constructor1.body().directStatement("this."+uipi.classname.toLowerCase()+"_actionlistener = new "+uipi.classname+"_ActionListener();\n\t");
            uip.constructor2.body().directStatement("this."+uipi.classname.toLowerCase()+"_actionlistener = new "+uipi.classname+"_ActionListener();\n\t");
        }        
    }
    
    private void dohierarchy(NodeList children, Node self)
    {         
        Uiparameter uip = (Uiparameter)Bodi.context("widgets").pull(self); 
        
        uip.constructor1.body().directStatement("/* ------------------  hierarchy  -------------------- */\n\t");
        uip.constructor2.body().directStatement("/* ------------------  hierarchy  -------------------- */\n\t");
            
        for(int i=0; i<children.getLength(); i++)
        {    
            Uiparameter uipi = (Uiparameter)Bodi.context("widgets").softpull(children.item(i));
            
            String classname = uip.classname;
            
            String childsuperclass = uipi.jdc._extends().name();
            
            String childfieldname = uipi.jdc.name().toLowerCase();                            

            if(childsuperclass.contains("JMenuBar"))            
            {
                uip.constructor1.body().directStatement("this.setJMenuBar("+childfieldname+");\n\t"); 
                uip.constructor2.body().directStatement("this.setJMenuBar("+childfieldname+");\n\t");
                
                continue;                
            }                        

            if(!childsuperclass.contains("JMenuBar"))
            {
                uip.constructor1.body().directStatement("this.add("+childfieldname+");\n\t");
                uip.constructor2.body().directStatement("this.add("+childfieldname+");\n\t");
                
                continue;
            }
        }         
    }
    
    private void dodevolvement(NodeList children, Node self)
    {
        Uiparameter uip = (Uiparameter)Bodi.context("widgets").pull(self);         
        
        /* ------------------------ Devolvement setters ------------------------ */
        
        uip.constructor1.body().directStatement("/* ------------------  devolvement  -------------------- */\n\t");                         
        uip.constructor2.body().directStatement("/* ------------------  devolvement  -------------------- */\n\t");   
        
        uip.constructor1.body().directStatement("this.parent = parent;\n\t");                        
        uip.constructor2.body().directStatement("this.parent = parent;\n\t");                        
        
        uip.constructor1.body().directStatement("this.setVisible(true);\n\t");   
        uip.constructor2.body().directStatement("this.setVisible(true);\n\t");   
    }
}