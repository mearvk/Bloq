package apml.ui.compilers.java;

import apml.system.bodi.Bodi;

import apml.ui.compilers.java.builders.Jcmabstractbuilder;

import com.sun.codemodel.JCodeModel;

import com.sun.codemodel.JMethod;

import com.sun.codemodel.JMod;

import java.io.File;

import java.util.ArrayList;

import javax.xml.xpath.XPathConstants;

import org.w3c.dom.NamedNodeMap;

import org.w3c.dom.Node;

import org.w3c.dom.NodeList;


/**
 * Class for handling output and finalization of JCM files (Jcodemodel) for Bloq implementation version 1.0x
 * 
 * @author Max Rupplin
 * @since 04.30.2017
 * @version Bloq 1.0
 */
public class Uioutputmanager
{
    protected final Integer hash = 0x00888fe8;
    
    public Uicompiler compiler;
    
    public Uioutputmanager(Uicompiler compiler)
    {
        this.compiler = compiler;
    }    
    
    /**
     * Primary method presumed called by Localdriver and then Uicompiler
     * 
     * @param jcodemodels 
     */
    public void generatefiles(ArrayList<JCodeModel> jcodemodels)
    {
        try
        {                   
            for(JCodeModel jcodemodel : jcodemodels)
            {                  
                Uiparameter uip = (Uiparameter)Bodi.context("widgets").pull(jcodemodel); 
                
                this.setuisetters(uip);
                
                this.setfields(uip);
                
                this.setparent(uip);  
                                
                this.setchildren(uip);
                
                this.setlisteners(uip);
                
                this.setconstructorcomments(uip);
                                
                uip.jcm.build(new File("/home/oem/Desktop/UI"));
            }
        }
        catch(Exception exception)
        {
            
        }    
    }
    
    private void setconstructorcomments(Uiparameter uip)
    {
        //uip.constructor1.javadoc().addParam("Apmlsystem system : The APML system object.");
        
        uip.constructor1.javadoc().addParam("parent : The parent AWT object.");
        
        uip.constructor2.javadoc().addParam("system : The APML system object.");
        
        uip.constructor2.javadoc().addParam("parent : The parent AWT object.");            
    }
    
    private void setlisteners(Uiparameter uip)
    {       
        try
        {
            NodeList children = (NodeList)uip.xpath.evaluate("./*", uip.node, XPathConstants.NODESET);             
            
            uip.constructor1.body().directStatement("/* ------------------  listeners  -------------------- */\n\t");
            uip.constructor2.body().directStatement("/* ------------------  listeners  -------------------- */\n\t");            
            
            
            /*------------------------ Listener Additions --------------------------*/            
            for(int i=0; i<children.getLength(); i++)
            {
                new Jcmabstractbuilder().addListeners(uip, children.item(i));            
            }
            
            
            /*------------------------- Overridden Methods--------------------------*/             
            for(int i=0; i<children.getLength(); i++)
            {
                new Jcmabstractbuilder().addListenerMethods(uip, children.item(i));
            }
        }
        catch(Exception exception)
        {
            
        }
    }
    
    private void setuisetters(Uiparameter uip)
    {
        uip.constructor1.body().directStatement("/* ------------------  setters  ---------------- */\n\t");
        uip.constructor2.body().directStatement("/* ------------------  setters  ---------------- */\n\t");        
        
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
                
                if(attribute.getNodeName().startsWith("setSize"))
                {                  
                    JMethod method;
                    
                    method = uip.jdc.method(JMod.PUBLIC, java.awt.Dimension.class, "getPreferredSize");                                        
                    
                    String[] sizes = attribute.getNodeValue().trim().split(":");
                    
                    for(String size: sizes)
                    {
                        size = size.trim().toLowerCase();
                    }
                    
                    if( (sizes[0]!=null && !sizes[0].isEmpty()) && (sizes[1]!=null && !sizes[1].isEmpty()) )
                    {                       
                        if(sizes[0].endsWith("%") && sizes[1].endsWith("%")) //uniform; both width and heigh are percentage based
                        {
                            try
                            {
                                Double width = Double.parseDouble(sizes[0].trim().replace("%", ""));
                                
                                Double height = Double.parseDouble(sizes[1].trim().replace("%", ""));
                                
                                method.body().directStatement("return new Dimension( (int)(parent.getWidth()*"+(width/100d)+"), (int)(parent.getHeight()*"+(height/100d)+"));");                                
                            }
                            catch(Exception e)
                            {
                                //
                            }
                            finally
                            {
                                continue;
                            }
                        }
                        
                        if(sizes[0].endsWith("%") && sizes[1].endsWith("px")) //mixed; width is percentage based and height is pixel based
                        {
                            try
                            {
                                Double width = Double.parseDouble(sizes[0].trim().replace("%", ""));
                                
                                Double height = Double.parseDouble(sizes[1].trim().replace("px", ""));
                                
                                method.body().directStatement("return new Dimension( (int)(parent.getWidth()*"+(width/100d)+"), "+(height.intValue())+");");                                
                            }
                            catch(Exception e)
                            {
                                //
                            }
                            finally
                            {
                                continue;
                            }
                        }  
                        
                        if(sizes[0].endsWith("px") && sizes[1].endsWith("%")) //mixed; width is pixel based and height is percentage based
                        {
                            try
                            {
                                Double width = Double.parseDouble(sizes[0].trim().replace("px", ""));
                                
                                Double height = Double.parseDouble(sizes[1].trim().replace("%", ""));
                                
                                method.body().directStatement("return new Dimension("+width.intValue()+", (int)(parent.getHeight()*"+(height/100d)+"));");                                
                            }
                            catch(Exception e)
                            {
                                //
                            }
                            finally
                            {
                                continue;
                            }
                        }

                        if(sizes[0].endsWith("px") && sizes[1].endsWith("px")) //uniform; fixed basis in pixels for both width and height
                        {
                            try
                            {
                                Double width = Double.parseDouble(sizes[0].trim().replace("px", ""));
                                
                                Double height = Double.parseDouble(sizes[1].trim().replace("px", ""));
                                
                                method.body().directStatement("return new Dimension("+(width.intValue())+", "+(height.intValue())+");");                                
                            }
                            catch(Exception e)
                            {
                                //
                            }
                            finally
                            {
                                continue;
                            }
                        }                                                
                    }                  
                    else //default
                    {
                        method.body().directStatement("return new Dimension(parent.getWidth(), 50);");
                    }
                    
                    continue;
                }                  
                
                if(attribute.getNodeName().startsWith("setBackgroundImage"))
                {
                    String string = "this.backgroundimagename = \""+uip.backgroundimagename+"\";\n\t";
                    
                    uip.constructor1.body().directStatement(string);
                    uip.constructor2.body().directStatement(string);
                    
                    continue;                    
                }
                
                if(attribute.getNodeName().startsWith("set"))
                {
                    //
                    
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
    
    private void setfields(Uiparameter uip)
    {
        try
        {                                       
            uip.jdc.direct("\n\t");

            uip.jdc.field(JMod.PUBLIC, Class.forName("java.awt.event.KeyEvent"), "importref_001");                          

            uip.jdc.field(JMod.PUBLIC, Class.forName("javax.swing.KeyStroke"), "importref_002");               

            uip.jdc.field(JMod.PUBLIC, Class.forName("java.awt.event.ActionEvent"), "importref_003"); 

            uip.jdc.field(JMod.PUBLIC, Class.forName("javax.swing.ImageIcon"), "importref_004");

            uip.jdc.field(JMod.PUBLIC, Class.forName("java.net.URL"), "importref_005");
            
            uip.jdc.field(JMod.PUBLIC, Class.forName("java.awt.Color"), "importref_006");
            
            uip.jdc.field(JMod.PUBLIC, Class.forName("java.awt.BorderLayout"), "importref_007");

            uip.jdc.field(JMod.PUBLIC, Class.forName("java.awt.FlowLayout"), "importref_008");
            
            uip.jdc.field(JMod.PUBLIC, Class.forName("java.awt.GridLayout"), "importref_009");
            
            uip.jdc.field(JMod.PUBLIC, Class.forName("java.awt.Color"), "importref_010");
            
            uip.jdc.field(JMod.PUBLIC, Class.forName("javax.swing.border.EmptyBorder"), "importref_011");
            
            uip.jdc.field(JMod.PUBLIC, Class.forName("javax.swing.event.ChangeEvent"), "importref_012");
            
            uip.jdc.field(JMod.PUBLIC, Class.forName("java.awt.Dimension"), "importref_013");
            
            uip.jdc.field(JMod.PUBLIC, Class.forName("java.awt.Rectangle"), "importref_014");
            
            uip.jdc.field(JMod.PUBLIC, Class.forName("javax.imageio.ImageIO"), "importref_015");
            
            uip.jdc.field(JMod.PUBLIC, Class.forName("java.io.File"), "importref_016");
            
                        
            NodeList children = (NodeList)uip.xpath.evaluate("./*", uip.node, XPathConstants.NODESET);  
                        
            for(int i=0; i<children.getLength(); i++)                           
            {       
                Uiparameter uipi = (Uiparameter)Bodi.context("widgets").softpull(children.item(i));
                
                new Jcmabstractbuilder().addListenerFields(uip, children.item(i));
            }
            
            for(int i=0; i<children.getLength(); i++)
            {
                Uiparameter uipi = (Uiparameter)Bodi.context("widgets").softpull(children.item(i));
                
                uip.jdc.direct("\t"+uipi.classname+" "+uipi.instancename+";\n\n");
            }
        }
        catch(Exception exception)
        {
            
        }
    }
    
    private void setparent(Uiparameter uip)
    {
        try
        {               
            //Uiparameter uip = (Uiparameter)Bodi.context("widgets").pull(jcodemodel);            
                
            uip.jdc.direct("\tpublic Component parent;\n\n");
            
            uip.jdc.direct("\tpublic Apmlsystem system;\n");
        }
        catch(Exception exception)
        {
            System.err.println(exception);
        }        
    }
     
    private void setchildren(Uiparameter uip)
    {
        try
        {             
            //Uiparameter uip = (Uiparameter)Bodi.context("widgets").pull(jcodemodel);   
            
            //NodeList nodes = (NodeList)uip.xpath.evaluate("./*", uip.node, XPathConstants.NODESET);                                                    
            
            this.doinstantiation(uip);
           
            this.dohierarchy(uip);
                  
            this.dodevolvement(uip);
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }        
    }    
    
    private void doinstantiation(Uiparameter uip)
    {                  
        uip.constructor1.body().directStatement("/* ------------------  instantiation  ---------------- */\n\t");
        
        uip.constructor2.body().directStatement("/* ------------------  instantiation  ---------------- */\n\t");
        
        //quick sanity check
        if(uip.children==null) return;
        
        //ui component instantiation        
        for(int i=0; i<uip.children.getLength(); i++)
        {               
            Uiparameter child = (Uiparameter)Bodi.context("widgets").softpull(uip.children.item(i)); 
            
            //quick sanity check
            if(child==null) return;
            
            uip.constructor1.body().directStatement("this."+child.classname.toLowerCase()+" = new "+child.classname+"(this);\n\t");
            
            uip.constructor2.body().directStatement("this."+child.classname.toLowerCase()+" = new "+child.classname+"(this);\n\t");            
            
        }
        
        //ui listener instantiation
        for(int i=0; i<uip.children.getLength(); i++)
        {                
            new Jcmabstractbuilder().addListenerInstantiation(uip, uip.children.item(i));
        }      
        
    }
    
    //private void dohierarchy(NodeList children, Node self)
    private void dohierarchy(Uiparameter uip)
    {         
        //Uiparameter uip = (Uiparameter)Bodi.context("widgets").pull(self); 
        
        uip.constructor1.body().directStatement("/* ------------------  hierarchy  -------------------- */\n\t");
        
        uip.constructor2.body().directStatement("/* ------------------  hierarchy  -------------------- */\n\t");
            
        if(uip.children==null) return;
        
        for(int i=0; i<uip.children.getLength(); i++)
        {    
            Uiparameter child = (Uiparameter)Bodi.context("widgets").softpull(uip.children.item(i));
            
            //sanity check
            if(child==null) return;
                        
            String classname = uip.classname;
            
            String childsuperclass = child.jdc._extends().name();
            
            String childfieldname = child.jdc.name().toLowerCase();                            

            //jframe as parent etc
            if(childsuperclass.contains("JMenuBar"))            
            {
                uip.constructor1.body().directStatement("this.setJMenuBar("+childfieldname+");\n\t"); 
                uip.constructor2.body().directStatement("this.setJMenuBar("+childfieldname+");\n\t");
                
                continue;                
            }                        

            //else a more standard case
            if(!childsuperclass.contains("JMenuBar"))
            {
                uip.constructor1.body().directStatement("this.add("+childfieldname+");\n\t");
                uip.constructor2.body().directStatement("this.add("+childfieldname+");\n\t");
                
                continue;
            }
        } 
    }
    
    private void dodevolvement(Uiparameter uip)
    {
        //Uiparameter uip = (Uiparameter)Bodi.context("widgets").pull(self);         
        
        /* ------------------------ Devolvement setters ------------------------ */
        
        uip.constructor1.body().directStatement("/* ------------------  devolvement  -------------------- */\n\t");                         
        uip.constructor2.body().directStatement("/* ------------------  devolvement  -------------------- */\n\t");   
        
        uip.constructor1.body().directStatement("this.parent = parent;\n\t");                        
        uip.constructor2.body().directStatement("this.parent = parent;\n\t");  
        
        uip.constructor2.body().directStatement("this.system = system;\n\t"); 
        
        uip.constructor1.body().directStatement("this.setVisible(true);\n\t");   
        uip.constructor2.body().directStatement("this.setVisible(true);\n\t");            
    }
}