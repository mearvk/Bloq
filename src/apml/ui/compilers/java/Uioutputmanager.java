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
 * @author max rupplin
 */
public class Uioutputmanager
{
    protected final Integer hash = 0x888fe8;
    
    public Uicompiler compiler;
    
    public Uioutputmanager(Uicompiler compiler)
    {
        this.compiler = compiler;
    }    
    
    public void generateoutputfiles(ArrayList<JCodeModel> jcodemodels)
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
            
        }    
    }
    
    private void setlisteners(JCodeModel jcodemodel)
    {
        Uiparameter uip = (Uiparameter)Bodi.context("widgets").pull(jcodemodel); 
        
        try
        {
            NodeList children = (NodeList)uip.xpath.evaluate("./*", uip.node, XPathConstants.NODESET);            
            
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
                    
                    uip.constructor.body().directStatement("this.setAccelerator(KeyStroke.getKeyStroke("+string+"));\n\t");
                }
                
                if(attribute.getNodeName().startsWith("set"))
                {
                    String string = "this."+attribute.getNodeName()+"("+attribute.getNodeValue()+")\n\t";
                    
                    uip.constructor.body().directStatement(string);
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

            uip.jdc.field(JMod.PUBLIC, Class.forName("java.awt.event.KeyEvent"), "keyevent");                          

            uip.jdc.field(JMod.PUBLIC, Class.forName("javax.swing.KeyStroke"), "keystroke");               

            uip.jdc.field(JMod.PUBLIC, Class.forName("java.awt.event.ActionEvent"), "actionevent"); 

            uip.jdc.field(JMod.PUBLIC, Class.forName("javax.swing.ImageIcon"), "imageicon");

            uip.jdc.field(JMod.PUBLIC, Class.forName("java.net.URL"), "url");
                        
            //children lookup/finding(s)/etc.
            NodeList kids = (NodeList)uip.xpath.evaluate("./*", uip.node, XPathConstants.NODESET);  
            
            
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