package apml.xml.handlers.parser.models;

import apml.helpers.filegrepper;
import apml.interfaces.Autostartable;
import apml.interfaces.Startable;
import apml.modeling.apmlmodelfile;
import apml.xml.handlers.parser.models.io.apmlmodelpopulator;
import apml.xml.handlers.parser.models.io.sourcefilenamer;
import com.sun.codemodel.JClass;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JPackage;
import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Max Rupplin
 */
public class apmltaghandler
{
    protected final Integer hash = 0x888fe8;
    
    protected File manifestfile;
    protected File manifestdir;    
    protected File outputdir;   
    protected File apmlfile;
    
    protected static final String APMLIN = "/home/oem/Desktop/apml/apml/echoserver.xml";
    protected static final String SOURCEOUTDIR = "/home/oem/Desktop/apml/output";
    protected static final String MANIFESTDIR = "/home/oem/Desktop/apml/output/manifest";
    protected static final String MANIFESTFILE = "/home/oem/Desktop/apml/output/manifest/manifest.txt";
        
    /**
     * 
     */
    public apmltaghandler()
    {
        try
        {
            this.outputdir = new File(apmltaghandler.SOURCEOUTDIR);            
            if(!this.outputdir.exists())
                this.outputdir.mkdirs();   
            
            this.manifestdir = new File(apmltaghandler.MANIFESTDIR);            
            if(!this.manifestdir.exists())
                this.manifestdir.mkdirs();   
            
            this.apmlfile = new File(apmltaghandler.APMLIN);
            if(!this.apmlfile.exists())
                throw new Exception("ApmlTagHandler::constructor:Could not find the system's APML file");     
        }
        catch(Exception e)
        {
            e.printStackTrace(System.err);
        }
    }
    
    /**
     * 
     * @param args 
     */
    public static void main(String...args) 
    {                          
        try
        {     
            //
            apmltaghandler sth = new apmltaghandler();
            
            //
            sth.convertapmltagstosource("//definitions");                     //todo fill this in how it makes sense
            
            //          
            sth.convertapmltagstosource("//system");   
            
            //         
            sth.convertapmltagstosource("//driver");   
            
            //            
            sth.convertapmltagstosource("//compiler");  
        }
        catch(Exception ex)
        {
            Logger.getLogger(apmltaghandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    
    /**
     * 
     * @param sourcefile
     * @param apmlmodelfile
     * @return
     * @throws Exception 
     */
    private JCodeModel createjcodemodel(File sourcefile, apmlmodelfile apmlmodelfile) throws Exception
    {       
        JCodeModel jcodemodel = new JCodeModel();                
        
        try
        {
            // 
            filegrepper grepper = new filegrepper();
            String classname = grepper.getclassname(sourcefile.getPath());
            String packagename = grepper.getpackagename(sourcefile.getPath());

            JDefinedClass classfile=null;
            JPackage jpackage=null;

            //
            try
            {
                jpackage = jcodemodel._package(packagename);
            }
            catch(Exception ex)
            {
                Logger.getLogger(apmltaghandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            //
            try
            {
                //
                if(jpackage==null) 
                    throw new Exception("Package not set; unable to set Class name");
                
                classfile = jpackage._class(classname);  
            }
            catch(Exception ex)
            {
                Logger.getLogger(apmltaghandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            //
            try
            {
                //
                if(classfile==null) 
                    throw new Exception("Classfile not set; unable to determine if Class extends another");
                
                classfile = classfile._extends(Class.forName(apmlmodelfile.apml_extends));
            }
            catch(Exception ex)
            {
                Logger.getLogger(apmltaghandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            //
            try
            {
                //
                if(classfile==null) 
                    throw new Exception("Classfile not set; unable to set interfaces for Class");
                
                classfile = classfile._implements(Class.forName(apmlmodelfile.apml_interface));
            }
            catch(Exception ex)
            {
                Logger.getLogger(apmltaghandler.class.getName()).log(Level.SEVERE, null, ex);
            }
                 
            //
            try
            {
                //
                if(classfile==null) 
                    throw new Exception("Classfile not set; unable to load interface methods for JCodeModel builder");
                
                if(apmlmodelfile.apml_run!=null)
                    classfile = classfile._implements(Runnable.class);
            }
            catch(Exception ex)
            {
                Logger.getLogger(apmltaghandler.class.getName()).log(Level.SEVERE, null, ex);
            }               
            
            //
            try
            {
                //
                if(classfile==null) 
                    throw new Exception("Classfile not set; unable to load interface methods for JCodeModel builder");
                
                if(apmlmodelfile.apml_start!=null)
                    classfile = classfile._implements(Startable.class);
            }
            catch(Exception ex)
            {
                Logger.getLogger(apmltaghandler.class.getName()).log(Level.SEVERE, null, ex);
            }            
            
            //
            try
            {
                //
                if(classfile==null) 
                    throw new Exception("Classfile not set; unable to load interface methods for JCodeModel builder");
                
                if(apmlmodelfile.apml_autostart!=null)
                    classfile = classfile._implements(Autostartable.class);
            }
            catch(Exception ex)
            {
                Logger.getLogger(apmltaghandler.class.getName()).log(Level.SEVERE, null, ex);
            }           
            
            //
            try
            {
                //
                if(classfile==null) 
                    throw new Exception("Classfile not set; unable to load interface methods for JCodeModel builder");
                
                if(apmlmodelfile.apml_initializable!=null)
                    classfile = classfile._implements(Autostartable.class);
            }
            catch(Exception ex)
            {
                Logger.getLogger(apmltaghandler.class.getName()).log(Level.SEVERE, null, ex);
            }                
            
            //
            try
            {
                //
                if(classfile==null) 
                    throw new Exception("Classfile not set; unable to load superclass methods for JCodeModel builder");
                
                Class superclass = Class.forName(apmlmodelfile.apml_extends);
                Method[] methods = superclass.getMethods();

                //
                for (Method method : methods) 
                {
                    if(method==null) break;
                    
                    switch (method.getModifiers()) 
                    {
                        case Modifier.PUBLIC:
                            classfile.method(JMod.PUBLIC, method.getReturnType(), method.getName());
                            break;
                        case Modifier.PROTECTED:
                            classfile.method(JMod.PROTECTED, method.getReturnType(), method.getName());
                            break;
                        case Modifier.NATIVE:
                            classfile.method(JMod.NATIVE, method.getReturnType(), method.getName());
                            break;
                        case Modifier.PRIVATE:
                            classfile.method(JMod.PRIVATE, method.getReturnType(), method.getName());
                            break;
                        default:
                            classfile.method(JMod.NONE, method.getReturnType(), method.getName());
                            break;
                    }
                }                 
            }
            catch(ClassNotFoundException | SecurityException ex)
            {
                Logger.getLogger(apmltaghandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            //
            try
            {
                //
                Iterator<JClass> intrfaces;
                
                //
                if(classfile==null) 
                    throw new Exception("Classfile not set; unable to load interface methods for JCodeModel builder");
                
                //
                intrfaces = classfile._implements();
                
                //
                for(;intrfaces.hasNext();)
                {
                    JClass class_ = intrfaces.next();                    
                    Class intrface = Class.forName(class_.fullName());
                    
                    Method[] methods = intrface.getMethods();

                    //
                    for(Method m: methods)
                    {                
                        switch(m.getModifiers())
                        {
                            case Modifier.ABSTRACT |  Modifier.PUBLIC:
                                classfile.method(JMod.PUBLIC, m.getReturnType(), m.getName());
                                break;
                                
                            case Modifier.ABSTRACT |  Modifier.PROTECTED:                                            
                                classfile.method(JMod.PROTECTED, m.getReturnType(), m.getName());
                                break;                    
                                
                            case Modifier.ABSTRACT |  Modifier.NATIVE:
                                classfile.method(JMod.NATIVE, m.getReturnType(), m.getName());
                                break;                        
                                
                            case Modifier.ABSTRACT |  Modifier.PRIVATE:
                                classfile.method(JMod.PRIVATE, m.getReturnType(), m.getName());
                                break;                                    
                                
                            default: 
                                classfile.method(JMod.NONE, m.getReturnType(), m.getName()); 
                                break;                                
                        }                
                    }                   
                }                 
            }
            catch(ClassNotFoundException | SecurityException ex)
            {
                Logger.getLogger(apmltaghandler.class.getName()).log(Level.SEVERE, null, ex);
            }            
           
            //
            try
            {
                //
                Iterator<JClass> intrfaces;
                
                //
                if(classfile==null) 
                    throw new Exception("Classfile not set; unable to load interface methods for JCodeModel builder");
                
                //
                intrfaces = classfile._implements();
                   
                //
                for(;intrfaces.hasNext();)
                {
                    JClass class_ = intrfaces.next();
                    Class intrface = Class.forName(class_.fullName());

                    Method[] methods = intrface.getMethods();

                    //
                    for(Method m: methods)
                    {                                      
                        switch(m.getModifiers())
                        {
                            case Modifier.ABSTRACT | Modifier.PUBLIC:
                                classfile.method(JMod.PUBLIC, m.getReturnType(), m.getName());
                                break;
                                
                            case Modifier.ABSTRACT | Modifier.PROTECTED:                                            
                                classfile.method(JMod.PROTECTED, m.getReturnType(), m.getName());
                                break;                    
                                
                            case Modifier.ABSTRACT | Modifier.NATIVE:
                                classfile.method(JMod.NATIVE, m.getReturnType(), m.getName());
                                break;   
                                
                            case Modifier.ABSTRACT | Modifier.PRIVATE:
                                classfile.method(JMod.PRIVATE, m.getReturnType(), m.getName());
                                break;  
                                
                            case Modifier.ABSTRACT | Modifier.INTERFACE:
                                System.err.println("Unknown origin detected; symmetry unbounded by this algorithm./detected..");
                                break;
                                
                            default: 
                                classfile.method(JMod.NONE, m.getReturnType(), m.getName()); 
                                break;
                        }                
                    }                    
                }                 
            }
            catch(ClassNotFoundException | SecurityException ex)
            {
                Logger.getLogger(apmltaghandler.class.getName()).log(Level.SEVERE, null, ex);
            }

            //
            return jcodemodel;
        }
        catch(Exception ex)
        {
            Logger.getLogger(apmltaghandler.class.getName()).log(Level.SEVERE, null, ex);
        }        
        
        throw new Exception("ApmlTagHandler::createJCodeModel: Unable to return a JCodeModel");
    }    
    
    /**
     * 
     * @param filenames
     * @return 
     */
    private JCodeModel[] createjcodemodel(File[] filenames)
    {       
        JCodeModel[] models = new JCodeModel[filenames.length];
        
        //
        for (File filename : filenames) {
            try
            {
                System.err.println("fix this shit");
            }
            catch(Exception ex)
            {
                Logger.getLogger(apmltaghandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
        
        return models;
    }
    
    /**
     * 
     * @param xpathstring 
     */
    void convertapmltagstosource(String xpathstring)
    {
        try
        {   
            //
            apmlmodelpopulator apmlmodeler = new apmlmodelpopulator();                          
            sourcefilenamer sourcenamer = new sourcefilenamer();
            
            //
            apmlmodelfile[] apmlfiles = apmlmodeler.getapmlfiles(this.apmlfile, xpathstring);            
            File[] sourcefiles = sourcenamer.getoutputfiles(xpathstring);
            
            //
            JCodeModel model;            
                              
            //
            for(int i=0; i<sourcefiles.length; i++)
            {                                     
                //
                model = this.createjcodemodel(sourcefiles[i],apmlfiles[i]);
                
                //
                File sourcedirs = new File(outputdir.getPath()+File.separator+new filegrepper().getpackagenameaspathname(sourcefiles[i].getPath()));
                if(!sourcedirs.exists()) 
                    sourcedirs.mkdirs();
                                
                //
                File mandir = new File(outputdir.getPath()+File.separator+new filegrepper().getpackagenameaspathname(sourcefiles[i].getPath()));
                if(!mandir.exists()) 
                    mandir.mkdirs();   
                
                //
                model.build(this.outputdir,System.err);
            }
        }
        catch(Exception ex)
        {
            Logger.getLogger(apmltaghandler.class.getName()).log(Level.SEVERE, null, ex);
        }                  
    }     
}

/**
 * 
 * @author oem
 */
class JCodeModelHelper
{
    public static Object doApmlListeners(JCodeModel jcodemodel, String[] s) throws Exception
    {
        return null;
    }
    
    public static Object doApmlSubscribers(JCodeModel jcodemodel, String[] s) throws Exception
    {
        return null;
    }    
    
     public static Object doClassname(JCodeModel jcodemodel, String classname) throws Exception
    {
        return jcodemodel._class(classname);
    }
        
    public static Object doInterfacesFunctional(JCodeModel jcodemodel, String[] interfaces) throws Exception
    {
        return null;
    }
    
    public static Object doInterfacesNominal(JCodeModel jcodemodel, String[] interfaces) throws Exception
    {
        return null;
    }       
    
    public static Object doLocalClasses(JCodeModel jcodemodel, String[] classes) throws Exception
    {
        return null;
    }
    
    public static Object doLocalInterfaces(JCodeModel jcodemodel, String[] classes) throws Exception
    {
        return null;
    }    
    
    public static Object doNestedClasses(JCodeModel jcodemodel, String[] interfaces) throws Exception
    {
        return null;
    }
    
    public static Object doOverrides(JCodeModel jcodemodel, String[] s) throws Exception
    {
        return null;
    }    
    
    public static Object doPackage(JCodeModel jcodemodel, String packagename) throws Exception
    {
        return jcodemodel._package(packagename);
    }       
    
    public static Object doSuperclassFunctional(JCodeModel jcodemodel, String[] extendss) throws Exception
    {
        return null;
    }        
    
    public static Object doSuperclassNominal(JCodeModel jcodemodel, String[] extendss) throws Exception
    {
        return null;
    }      
}