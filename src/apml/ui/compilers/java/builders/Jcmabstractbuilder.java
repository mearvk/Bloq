package apml.ui.compilers.java.builders;

import apml.system.bodi.Bodi;

import apml.ui.compilers.java.Uiparameter;

import com.sun.codemodel.ClassType;

import com.sun.codemodel.JCodeModel;

import com.sun.codemodel.JDefinedClass;

import com.sun.codemodel.JMethod;

import com.sun.codemodel.JMod;

import java.io.File;

import java.lang.reflect.Method;

import java.lang.reflect.Parameter;

import java.util.ArrayList;

import java.util.Arrays;

import java.util.Collections;

import java.util.Comparator;

import java.util.HashSet;

import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;

import javax.xml.xpath.XPath;

import javax.xml.xpath.XPathConstants;

import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;

import org.w3c.dom.Element;

import org.w3c.dom.Node;

import org.w3c.dom.NodeList;

/**
 *
 * @author Max Rupplin
 */
public class Jcmabstractbuilder //todo check why dodevolvement must be called from concrete class and is not called on output side (uioutputmanager) where it is natively set; ok /mr /ok /ss
{   
    protected final Integer hash = 0x00888fe8;
    
    public ArrayList<JCodeModel> jcodemodels = new ArrayList<>();
    
    public Jcmabstractbuilder builder = this;
    
    public Document doc;   
    
    public File apml;
        
    public NodeList nodes;
    
    public XPath xpath; 
    
    public String tagname;
    
    public Class classname;
    
    public ArrayList<Uiparameter> storage = new ArrayList<Uiparameter>();
    
    public Jcmabstractbuilder()
    {
        
    }
        
    public Jcmabstractbuilder(File apml, String tagname, Class classname)
    {                        
        Bodi.setcontext("widgets");                  
                
        try
        {      
            this.tagname = tagname;
        
            this.apml = apml;
        
            this.classname = classname;            
            
            this.setxpath();
            
            this.setdocument();
        
            this.setnodes();            
        }
        catch(Exception exception)
        {
            System.err.println(exception);
        }
    }
    
    public ArrayList<JCodeModel> build()
    {                       
        try
        {         
            for(int index=0; index<this.nodes.getLength(); index++) //ordering somewhat important
            {
                Uiparameter uip = new Uiparameter(new JCodeModel(), index);                                                           
                
                this.setjcmpackage(uip);
                
                this.setjcmclass(uip);                                            
                
                this.setuipvalues(uip); 
                
                this.setbodi(uip); 
                
                this.setsuperclass(uip);  
                
                this.setjcodemodel(uip);
                
                this.setconstructors(uip);                                     
                
                this.setbackgroundimage(uip); 
                
                this.storage.add(uip);                                
            }
        }
        catch(Exception exception)
        {
            //exception.printStackTrace();
        }                                
        
        return jcodemodels;        
    }    
    
    public void setconstructors(Uiparameter uip)
    {
        try
        {
            /*--------------------- first constructor --------------------------*/
            
            uip.constructor1 = uip.jdc.constructor(JMod.PUBLIC);
            
            uip.constructor1.param(java.awt.Component.class, "parent");
            
            /*--------------------- second constructor -------------------------*/
            
            uip.constructor2 = uip.jdc.constructor(JMod.PUBLIC);
            
            uip.constructor2.param(java.awt.Component.class, "parent");
            
            uip.constructor2.param(apml.system.Apmlsystem.class, "system");
        } 
        catch(Exception e){}
    }
    
    public void setxpath()
    {
        this.xpath = XPathFactory.newInstance().newXPath();
    }
    
    public void setsuperclass(Uiparameter uip)
    {
        uip.jdc._extends(this.classname);
    } 
    
    public void setuipvalues(Uiparameter uip)
    {
        /*----------------------------------------------------------------------*/
        
        try{uip.children = (NodeList)xpath.evaluate("./*", nodes.item(uip.index), XPathConstants.NODESET); }catch(Exception e){}
        
        try{uip.node = nodes.item(uip.index);} catch(Exception e){}
        
        try{uip.backgroundimagename = ((Element)uip.node).getAttribute("setBackgroundImage");} catch(Exception e){}
        
        try{uip.jdc = uip.jcm.packages().next().classes().next();} catch(Exception e){}
        
        try{uip.jpackage = uip.jcm.rootPackage();}catch(Exception e){}
                        
        try{uip.xpath = xpath;} catch(Exception e){}
        
        try{uip.parent_node = nodes.item(uip.index).getParentNode();} catch(Exception e){}
        
        try{uip.classname = uip.jdc.name();} catch(Exception e){}
        
        try{uip.instancename = uip.jdc.name().toLowerCase();} catch(Exception e){}
        
        try{uip.doc = this.doc;} catch(Exception e){}
        
        try{uip.element = (Element)uip.node;} catch(Exception e){}          
    }
    
    public void setbodi(Uiparameter uip)
    {                           
        /*----------------------------------------------------------------------*/
        
        try{Bodi.context("widgets").put(uip.jcm, uip);}     catch(Exception e){e.printStackTrace(System.err);}
        
        try{Bodi.context("widgets").put(uip.node, uip);}    catch(Exception e){e.printStackTrace(System.err);}
    }
    
    
    public void setjcmclass(Uiparameter uip) throws Exception
    {
        JDefinedClass theclass;
        
        theclass = uip.jcm.packages().next()._class(this.classname.getSimpleName()+"_"+String.format("%1$03d",uip.index));      
        
        /*---------------------------------------------------------------------*/
        
        String bodiversion = (String)Bodi.context("//bodi/version").pull("version");       
        
        /*---------------------------------------------------------------------*/            
        
        theclass.javadoc().add("Bloq software & design courtesy of Max Rupplin. All rights reserved. 2017 AD Earth, Gregorian.\n");
        
        theclass.javadoc().add("APML software & design courtesy of Max Rupplin. All rights reserved. 2017 AD Earth, Gregorian.\n");
        
        theclass.javadoc().add("Sprung software & design courtesy of Max Rupplin. All rights reserved. 2017 AD Earth, Gregorian.\n");
        
        theclass.javadoc().add("\n");
        
        theclass.javadoc().add("Software programmatically produced by Bloq implementation version "+bodiversion+"\n");
        
        theclass.javadoc().add("\n");
        
        theclass.javadoc().add("Hello and thanks to and from the Church of Scientology now in her final Holy Form. /mr /ok /ss\n");
        
        theclass.javadoc().add("Hello and thanks to and from the HUC, Holy Unified Church, (the Church of \"Hi, Hello\") and welcome now in her lesser manner. /mr /ok /ss\n");
        
        theclass.javadoc().add("\n");                 
        
        theclass.javadoc().add("\n");      
        
        theclass.javadoc().add("\n");  
        
        theclass.javadoc().add("\n");
        
        theclass.javadoc().add("//hi hello");
        
        // /mr /ok /ss
    }
    
    public void setjcmpackage(Uiparameter uip) throws Exception
    {                        
        uip.jcm._package("org.widgets");                         
    }
    
    public void setdocument() throws Exception
    {
        this.doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(apml);
    }
    
    public void setnodes() throws Exception
    {
        try{this.nodes = (NodeList)xpath.evaluate(this.tagname, this.doc, XPathConstants.NODESET); }catch(Exception e){}
        
        //try{this.}catch(Exception e){}
    }
        
    protected void setjcodemodel(Uiparameter uip)
    {
        this.jcodemodels.add(uip.jcm);
    }
    
    private void setbackgroundimage(Uiparameter uip)
    {   
        if(uip.backgroundimagename==null || uip.backgroundimagename.isEmpty()) return;
        
        /*--------------------------------  setup  -----------------------------------*/
        
        JDefinedClass jdefinedclass;
        
        jdefinedclass = uip.jdc;
        
        
        /*------------------------- set field for bg image ---------------------------*/
        
        jdefinedclass.field(JMod.PUBLIC, java.awt.Image.class, "backgroundimage");
        
        jdefinedclass.field(JMod.PUBLIC, java.lang.String.class, "backgroundimagename");
        
        
        /*----------------------- add paintComponent method --------------------------*/
        
        JMethod method;
        
        method = jdefinedclass.method(JMod.PUBLIC, uip.jcm.VOID, "paintComponent");
        
        method.param(java.awt.Graphics.class, "g");
        
        method.body().directStatement("super.paintComponent(g);\n");
        
        method.body().directStatement("try{this.backgroundimage = ImageIO.read(new File(backgroundimagename));}catch(Exception e){e.printStackTrace();}\n");
        
        method.body().directStatement("g.drawImage(backgroundimage, 0, 0, this);");
    } 
    
    public void addListeners(Uiparameter uip, Node child)
    {                       
        /*--------------------------------------------------------------------*/        
        
        try
        {            
            Uiparameter childuip = (Uiparameter)Bodi.context("widgets").softpull(child);
            
            if(child.getNodeName().trim().equalsIgnoreCase("tab")) return;
            
            if(child.getNodeName().trim().equalsIgnoreCase("jtextarea")) return;
            
            
            Class theclass=null;
            
            String trimmed;
            
            try
            {
                trimmed = "javax.swing."+childuip.jdc.name().substring(0, childuip.jdc.name().indexOf("_"));
                
                theclass = Class.forName("javax.swing."+childuip.jdc.name().substring(0, childuip.jdc.name().indexOf("_")));                                    
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
            List<Method> list = Arrays.asList(theclass.getMethods());            
            
            Collections.sort(list, new AlphabeticalComparator());

            /*--------------------------------------------------------------------*/

            for(Method method : list)
            {
                String name = method.getName();

                if(name.endsWith("Listener") && name.startsWith("add"))
                {
                    String suffix = name.replace("add", "").trim().toLowerCase();

                    try
                    {
                        String instancename = childuip.instancename;

                        String listener = instancename+"_"+suffix;       

                        uip.constructor1.body().directStatement("this."+instancename+"."+name+"("+listener+");\n\t");

                        uip.constructor2.body().directStatement("this."+instancename+"."+name+"("+listener+");\n\t");
                    }
                    catch(Exception e)
                    {

                    }                
                }
            }            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }        
    }
    
    public void addListenerMethods(Uiparameter uip, Node child)
    {
        try
        {            
            Uiparameter childuip = (Uiparameter)Bodi.context("widgets").softpull(child);
            
            Class theclass = Class.forName("javax.swing."+childuip.jdc.name().substring(0, childuip.jdc.name().indexOf("_")));                                    
            
            List<Method> list = Arrays.asList(theclass.getMethods());            
            
            Collections.sort(list, new AlphabeticalComparator());


            /*------------------------------------------------------------------*/
        
            for(Method method : list)
            {
                String name = method.getName(); 

                if(name.endsWith("Listener") && name.startsWith("add"))
                {
                    String suffix_lowercase = name.replace("add", "").trim().toLowerCase();
                    
                    String suffix_standard = name.replace("add", "").trim();

                    try
                    {
                        String instancename = childuip.instancename;                        

                        String listener = instancename+"_"+suffix_lowercase;

                        String nestedlistenerclass = childuip.classname+"_"+suffix_standard;      
                        
                        /*------------------------------------------------------*/

                        try
                        {
                            if( Class.forName("java.awt.event."+suffix_standard)==null) continue;
                            
                            JDefinedClass nestedclass = uip.jdc._class(JMod.PRIVATE | JMod.FINAL, nestedlistenerclass, ClassType.CLASS);                    

                            nestedclass._implements(Class.forName("java.awt.event."+suffix_standard));

                            for(Method nestedmethod : Class.forName("java.awt.event."+suffix_standard).getMethods())
                            {
                                for(Parameter parameter : nestedmethod.getParameters())
                                {                           
                                    if(parameter.getType().getSimpleName().contains("Event"))
                                    {
                                        String function = "public void "+nestedmethod.getName()+"("+parameter.getType()+" "+parameter.getName()+")\n";
                                        
                                        function = function.replace("class", "");
                                        
                                        nestedclass.direct(function);                                                                        

                                        nestedclass.direct("\t{\n\t");

                                        nestedclass.direct("\t//to be implemented\n");

                                        nestedclass.direct("\t}\n\t");                                    
                                    }                            
                                }
                            }
                        }
                        catch(Exception e)
                        {
                            //e.printStackTrace();
                        }
                        
                        try
                        {
                            if( Class.forName("javax.swing.event."+suffix_standard)==null) continue;
                            
                            JDefinedClass nestedclass = uip.jdc._class(JMod.PRIVATE | JMod.FINAL, nestedlistenerclass, ClassType.CLASS);                    

                            nestedclass._implements(Class.forName("javax.swing.event."+suffix_standard));

                            for(Method nestedmethod : Class.forName("javax.swing.event."+suffix_standard).getMethods())
                            {
                                for(Parameter parameter : nestedmethod.getParameters())
                                {                           
                                    if(parameter.getType().getSimpleName().contains("Event"))
                                    {                                        
                                        String function = "public void "+nestedmethod.getName()+"("+parameter.getType()+" "+parameter.getName()+")\n";
                                        
                                        function = function.replace("class", "");
                                        
                                        nestedclass.direct(function);                                                                                                                

                                        nestedclass.direct("\t{\n\t");

                                        nestedclass.direct("\t//to be implemented\n");

                                        nestedclass.direct("\t}\n\t");                                    
                                    }                            
                                }
                            }
                        }
                        catch(Exception e)
                        {
                            //e.printStackTrace();
                        }     
                        
                        try
                        {
                            if( Class.forName("java.beans."+suffix_standard)==null) continue;
                            
                            JDefinedClass nestedclass = uip.jdc._class(JMod.PRIVATE | JMod.FINAL, nestedlistenerclass, ClassType.CLASS);                    

                            nestedclass._implements(Class.forName("java.beans."+suffix_standard));

                            for(Method nestedmethod : Class.forName("java.beans."+suffix_standard).getMethods())
                            {
                                for(Parameter parameter : nestedmethod.getParameters())
                                {                           
                                    if(parameter.getType().getSimpleName().contains("Event"))
                                    {
                                        String function = "public void "+nestedmethod.getName()+"("+parameter.getType()+" "+parameter.getName()+")\n";
                                        
                                        function = function.replace("class", "");
                                        
                                        nestedclass.direct(function);                                                                                                                                              

                                        nestedclass.direct("\t{\n\t");

                                        nestedclass.direct("\t//to be implemented\n");

                                        nestedclass.direct("\t}\n\t");                                    
                                    }                            
                                }
                            }
                        }
                        catch(Exception e)
                        {
                            //e.printStackTrace();
                        }                        
                    }
                    catch(Exception e)
                    {
                        //e.printStackTrace();
                    }                
                }
            }                              
        }
        catch(Exception e)
        {
            //e.printStackTrace();
        }
    }
    
    public void addListenerFields(Uiparameter uip, Node child)
    {
        try
        {
            Uiparameter childuip = (Uiparameter)Bodi.context("widgets").softpull(child);    
            
            if(child.getNodeName().trim().equalsIgnoreCase("tab")) return;
            
            if(child.getNodeName().trim().equalsIgnoreCase("jtextarea")) return;

            Class theclass = Class.forName("javax.swing."+childuip.jdc.name().substring(0, childuip.jdc.name().indexOf("_")));                                    

            List<Method> list = Arrays.asList(theclass.getMethods());            

            Collections.sort(list, new AlphabeticalComparator());        

            for(Method method : list)
            {
                String name = method.getName(); 

                if(name.endsWith("Listener") && name.startsWith("add"))
                {
                    String suffix_lowercase = name.replace("add", "").trim().toLowerCase();

                    String suffix_standard = name.replace("add", "").trim();

                    String instancename = childuip.instancename;                   
                    
                    String listener = instancename+"_"+suffix_lowercase;

                    String nestedlistenerclass = childuip.classname+"_"+suffix_standard;      

                    /*------------------------------------------------------*/

                    try
                    {
                        uip.jdc.direct("\t"+nestedlistenerclass+" "+listener+";\n\n");
                    }
                    catch(Exception e)
                    {
                        //e.printStackTrace();
                    }
                }                                
            }
        
            uip.jdc.direct("\n");
        }
        catch(Exception e)
        {
            //e.printStackTrace();
        }        
    }
    
    public void addListenerInstantiation(Uiparameter uip, Node child)
    {
        try
        {
            Uiparameter childuip = (Uiparameter)Bodi.context("widgets").softpull(child);    
            
            if(child.getNodeName().trim().equalsIgnoreCase("tab")) return;
            
            if(child.getNodeName().trim().equalsIgnoreCase("jtextarea")) return; //errant fix me please

            Class theclass = Class.forName("javax.swing."+childuip.jdc.name().substring(0, childuip.jdc.name().indexOf("_")));                                    

            List<Method> list = Arrays.asList(theclass.getMethods());            

            Collections.sort(list, new AlphabeticalComparator());   
            
            //Arrays.stream(list.toArray()).distinct().
                    
            List<Method> unique = new ArrayList<Method>(new HashSet<Method>(list));

            for(Method method : unique)
            {
                String name = method.getName(); 

                if(name.endsWith("Listener") && name.startsWith("add"))
                {
                    String suffix_lowercase = name.replace("add", "").trim().toLowerCase();

                    String suffix_standard = name.replace("add", "").trim();

                    String instancename = childuip.instancename;                   
                    
                    String listener = instancename+"_"+suffix_lowercase;

                    String nestedlistenerclass = childuip.classname+"_"+suffix_standard;      

                    /*------------------------------------------------------*/

                    try
                    {
                        uip.constructor1.body().directStatement("this."+listener+" = new "+nestedlistenerclass+"();\n");
                        
                        uip.constructor2.body().directStatement("this."+listener+" = new "+nestedlistenerclass+"();\n");
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }
                }                                
            }
        
            uip.jdc.direct("\n");
        }
        catch(Exception e)
        {
            //e.printStackTrace();
        }              
    }
}

class AlphabeticalComparator implements Comparator
{    
    @Override
    public int compare(Object o1, Object o2)
    {
        if(o1==null || o2==null) return -1;
        
        Method m1 = (Method)o1;
        
        Method m2 = (Method)o2;               
        
        return m1.getName().compareToIgnoreCase(m2.getName());
    }
    
}