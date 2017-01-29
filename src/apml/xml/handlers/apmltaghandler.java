package apml.xml.handlers;

import apml.helpers.filegrepper;
import apml.modeling.apmlmodelfile;
import com.sun.codemodel.JClass;
import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JPackage;
import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.security.InvalidParameterException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import apml.interfaces.startable;
import apml.interfaces.initializable;
import apml.interfaces.autostartable;

/**
 * 
 * @author Max Rupplin
 */
public class apmltaghandler extends Object
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
        catch(Exception ex)
        {
            Logger.getLogger(apmltaghandler.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
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
            apmltaghandler handler = new apmltaghandler();
            
            //
            //handler.convertapmltagstosource("//definitions");
            
            //          
            handler.convertapmltagstosource("//system");   
            
            //         
            //handler.convertapmltagstosource("//driver");   
            
            //            
            //handler.convertapmltagstosource("//compiler");  
        }
        catch(Exception ex)
        {
            Logger.getLogger(apmltaghandler.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
    }   
    
    /**
     * Creates JCodeModel model and returns it; makes the source file for output
     * 
     * @param sourcefile
     * @param apmlmodelfile
     * @return
     * @throws Exception 
     */
    private JCodeModel createsourcefile(File sourcefile, apmlmodelfile apmlmodelfile) throws Exception
    {       
        JCodeModel jcodemodel = new JCodeModel();                
        
        try
        {
            JDefinedClass classfile=null;
            JPackage jpackage=null;

            apmltaghandlerparameter param = new apmltaghandlerparameter(jcodemodel,jpackage,classfile,apmlmodelfile);
            
            //class file data (extends, implements, etc)
            this.dowritepackagename(param);
            this.dowriteclassname(param);            
            this.dowriteextends(param);
            this.dowriteimplements(param);
            
            //check for tags; move to JCM
            this.doautostarttag(param);
            this.doinittag(param); 
            this.doruntag(param);
            this.dostarttag(param);                         
            
            //check for children; move to JCM
            this.dolisteners(param);
            this.doobjects(param);
            
            //add inherited methods to JCM 
            this.addinterfacemethods(param);
            this.addsuperclassmethods(param);
            this.addtagmethods(param);                                     
            
            return jcodemodel;
        }
        catch(Exception ex)
        {
            Logger.getLogger(apmltaghandler.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }        
        
        throw new Exception("ApmlTagHandler::createJCodeModel: Unable to return a JCodeModel");
    }  
    
    /**
     * 
     * @param param 
     */
    private void doobjects(apmltaghandlerparameter param)
    {
        try
        {
            if(param.jcodemodel==null) 
                throw new InvalidParameterException("JCodeModel not set; unable to set package name");
            
            param.jpackage = param.jcodemodel._package(param.apmlmodelfile.apml_packagename);
        }
        catch(Exception ex)
        {
            Logger.getLogger(apmltaghandler.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }        
    }    
    
    /**
     * 
     * @param param 
     */
    private void dolisteners(apmltaghandlerparameter param)
    {
        try
        {
            if(param.jcodemodel==null) 
                throw new InvalidParameterException("JCodeModel not set; unable to set package name");
            
            param.jpackage = param.jcodemodel._package(param.apmlmodelfile.apml_packagename);
        }
        catch(Exception ex)
        {
            Logger.getLogger(apmltaghandler.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }        
    }
    
    /**
     * 
     * @param param 
     */
    private void dowritepackagename(apmltaghandlerparameter param)
    {
        try
        {
            if(param.jcodemodel==null) 
                throw new InvalidParameterException("JCodeModel not set; unable to set package name");
            
            param.jpackage = param.jcodemodel._package(param.apmlmodelfile.apml_packagename);
        }
        catch(Exception ex)
        {
            Logger.getLogger(apmltaghandler.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }        
    }
    
    /**
     * 
     * @param param 
     */
    private void dowriteclassname(apmltaghandlerparameter param)
    {
        try
        {     
            if(param.jpackage==null) 
                throw new InvalidParameterException("Package not set; unable to set class name");
                
            param.classfile = param.jpackage._class(param.apmlmodelfile.apml_classname);  
        }
        catch(NullPointerException | InvalidParameterException | JClassAlreadyExistsException ex)
        {
            Logger.getLogger(apmltaghandler.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }        
    }
    
    /**
     * 
     * @param param 
     */
    private void dowriteextends(apmltaghandlerparameter param)
    {
        try
        {
            if(param.classfile==null) 
                throw new InvalidParameterException("Classfile not set; unable to determine if Class extends another");
            
            if(param.apmlmodelfile.apml_extends==null) 
                throw new InvalidParameterException("No superclass reference found in param.apmlmodelfile.apml_extends");            
               
            param.classfile = param.classfile._extends(Class.forName(param.apmlmodelfile.apml_extends));
        }
        catch(NullPointerException | InvalidParameterException | ClassNotFoundException ex)
        {
            Logger.getLogger(apmltaghandler.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }        
    }
    
    /**
     * Intend here to add class definition id est Class1 implements Interface1 but not the associated stub methods
     * 
     * @param param 
     */
    private void dowriteimplements(apmltaghandlerparameter param)
    {      
            try
            {
                if(param.classfile==null) 
                    throw new InvalidParameterException("Classfile not set; unable to set interfaces for Class");
                
                if(param.apmlmodelfile.apml_implements==null || param.apmlmodelfile.apml_implements.length==0)
                    throw new InvalidParameterException("No interfaces found with param.apmlmodelfile.apml_implements");
                
                for(String implments : param.apmlmodelfile.apml_implements)
                {
                    try
                    {
                        param.classfile = param.classfile._implements(Class.forName(implments));
                    }
                    catch(ClassNotFoundException ex)
                    {
                        Logger.getLogger(apmltaghandler.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
                    }
                }
            }
            catch(NullPointerException | InvalidParameterException ex)
            {
                Logger.getLogger(apmltaghandler.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }        
    }
    
    /**
     * 
     * @param param 
     */
    private void doruntag(apmltaghandlerparameter param)
    {
            try
            {
                if(param.classfile==null) 
                    throw new InvalidParameterException("Classfile not set; unable to load interface methods for JCodeModel builder");
                
                if(param.apmlmodelfile.apml_run==null)
                    throw new InvalidParameterException("Run or runnable not set; unable to comply");
                
                if(param.apmlmodelfile.apml_run.equalsIgnoreCase("true"))                    
                    param.classfile = param.classfile._implements(Runnable.class);
            }
            catch(Exception ex)
            {
                Logger.getLogger(apmltaghandler.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }          
    }
    
    /**
     * 
     * @param param 
     */
    private void dostarttag(apmltaghandlerparameter param)
    {          
        try
        {
            if(param.classfile==null) 
                throw new InvalidParameterException("Classfile not set; unable to load interface methods for JCodeModel builder");
                
            if(param.apmlmodelfile.apml_start==null)
                throw new InvalidParameterException("Start or startable not set; unable to comply");
            
            if(param.apmlmodelfile.apml_start.equalsIgnoreCase("true"))
                param.classfile = param.classfile._implements(startable.class);
        }
        catch(Exception ex)
        {
            Logger.getLogger(apmltaghandler.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }           
    }
    
    /**
     * 
     * @param param 
     */
    private void addinterfacemethods(apmltaghandlerparameter param)
    {
        try
        {               
            if(param.classfile==null) 
                throw new InvalidParameterException("Classfile not set; unable to load interface methods for JCodeModel builder");            
                
            if(param.apmlmodelfile.apml_stdinterfaces==null || param.apmlmodelfile.apml_taginterfaces.length==0)
                throw new InvalidParameterException("No standard interfaces were found");                
                   
            for(String intrfacename : param.apmlmodelfile.apml_stdinterfaces)
            {
                try
                {
                    Class intrface = Class.forName(intrfacename);

                    Method[] methods = intrface.getMethods();

                    //
                    for(Method m: methods)
                    {                                      
                        switch(m.getModifiers())
                        {
                            case Modifier.ABSTRACT | Modifier.PUBLIC:
                                param.classfile.method(JMod.PUBLIC, m.getReturnType(), m.getName());
                                break;

                            case Modifier.ABSTRACT | Modifier.PROTECTED:                                            
                                param.classfile.method(JMod.PROTECTED, m.getReturnType(), m.getName());
                                break;                    

                            case Modifier.ABSTRACT | Modifier.NATIVE:
                                param.classfile.method(JMod.NATIVE, m.getReturnType(), m.getName());
                                break;   

                            case Modifier.ABSTRACT | Modifier.PRIVATE:
                                param.classfile.method(JMod.PRIVATE, m.getReturnType(), m.getName());
                                break;  

                            case Modifier.ABSTRACT | Modifier.INTERFACE:
                                System.err.println("Unknown origin detected; symmetry unbounded by this algorithm detected.");
                                break;

                            default: 
                                param.classfile.method(JMod.NONE, m.getReturnType(), m.getName()); 
                                break;
                        }                
                    }
                }
                catch(ClassNotFoundException ex)
                {
                    Logger.getLogger(apmltaghandler.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
                }
            }                 
        }
        catch(InvalidParameterException | NullPointerException | SecurityException ex)
        {
            Logger.getLogger(apmltaghandler.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }        
    }
    
    /**
     * 
     * @param param 
     */
    private void doautostarttag(apmltaghandlerparameter param)
    {
        try
        {
            if(param.classfile==null) 
                throw new InvalidParameterException("Classfile not set; unable to load interface methods for JCodeModel builder");
                
            if(param.apmlmodelfile.apml_autostart==null)
                throw new InvalidParameterException("No autostart tag found");
            
            param.classfile = param.classfile._implements(autostartable.class);
        }
        catch(Exception ex)
        {
            Logger.getLogger(apmltaghandler.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }         
    }
    
    /**
     * 
     * @param param 
     */
    private void doinittag(apmltaghandlerparameter param)
    {        
        try
        {
            if(param.classfile==null) 
                throw new InvalidParameterException("Classfile not set; unable to load interface methods for JCodeModel builder");
                
            if(param.apmlmodelfile.apml_initializable==null)
                throw new InvalidParameterException("No init tag found");
            
            param.classfile = param.classfile._implements(initializable.class);
        }
        catch(Exception ex)
        {
                Logger.getLogger(apmltaghandler.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }         
    }

    /**
     * 
     * 
     * @param param 
     */
    private void addsuperclassmethods(apmltaghandlerparameter param)
    {        
        try
        {
            if(param.classfile==null) 
                throw new InvalidParameterException("Classfile not set; unable to load superclass methods for JCodeModel builder");
                
            if(param.apmlmodelfile.apml_extends==null)
                throw new InvalidParameterException("No superclass found");
            
            Class superclass = Class.forName(param.apmlmodelfile.apml_extends);            

            //
            for (Method method : superclass.getMethods()) 
            {
                if(method==null) break;
                   
                switch (method.getModifiers()) 
                {
                    case Modifier.PUBLIC:
                        param.classfile.method(JMod.PUBLIC, method.getReturnType(), method.getName());
                        break;
            
                    case Modifier.PROTECTED:
                        param.classfile.method(JMod.PROTECTED, method.getReturnType(), method.getName());    
                        break;
                        
                        
                    case Modifier.NATIVE:                    
                        param.classfile.method(JMod.NATIVE, method.getReturnType(), method.getName());                        
                        break;
                        
                       
                    case Modifier.PRIVATE:                            
                        param.classfile.method(JMod.PRIVATE, method.getReturnType(), method.getName());                            
                        break;
                        
                        default:
                            param.classfile.method(JMod.NONE, method.getReturnType(), method.getName());
                            break;
                    }
            }                 
        }
        catch(InvalidParameterException | NullPointerException | ClassNotFoundException | SecurityException ex)
        {
            Logger.getLogger(apmltaghandler.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }        
    }
    
    /**
     * Parse apml for tags (init, start, run, autostart, etc.) and put this data into JCodeModel form for output
     * 
     * @param param Contains model information from APML parsing 
     */
    private void addtagmethods(apmltaghandlerparameter param)
    {
        try
        {
            Iterator<JClass> intrfaces;
                
            if(param.classfile==null) 
                throw new InvalidParameterException("Classfile not set; unable to load interface methods for JCodeModel builder");
            
            if(param.apmlmodelfile.apml_taginterfaces==null || param.apmlmodelfile.apml_taginterfaces.length==0)
                throw new InvalidParameterException("No tag interfaces found");
                
            intrfaces = param.classfile._implements();
                
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
                                param.classfile.method(JMod.PUBLIC, m.getReturnType(), m.getName());
                                break;
                                
                            case Modifier.ABSTRACT |  Modifier.PROTECTED:                                            
                                param.classfile.method(JMod.PROTECTED, m.getReturnType(), m.getName());
                                break;                    
                                
                            case Modifier.ABSTRACT |  Modifier.NATIVE:
                                param.classfile.method(JMod.NATIVE, m.getReturnType(), m.getName());
                                break;                        
                                
                            case Modifier.ABSTRACT |  Modifier.PRIVATE:
                                param.classfile.method(JMod.PRIVATE, m.getReturnType(), m.getName());
                                break;                                    
                                
                            default: 
                                param.classfile.method(JMod.NONE, m.getReturnType(), m.getName()); 
                                break;                                
                        }                
                    }                   
                }                 
        }
        catch(InvalidParameterException | NullPointerException | ClassNotFoundException | SecurityException ex)
        {
            Logger.getLogger(apmltaghandler.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }         
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
                System.err.println("fix this plz");
            }
            catch(Exception ex)
            {
                Logger.getLogger(apmltaghandler.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
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
            JCodeModel model;            
            
            apmlmodelpopulator apmlmodeler;                        
            sourcefilenamer sourcenamer;
                                    
            apmlmodeler = new apmlmodelpopulator(this.apmlfile, xpathstring);  
            sourcenamer = new sourcefilenamer(xpathstring);           
                                        
            if(apmlmodeler.apmlfiles.length != sourcenamer.sourcefiles.length) throw new Exception("Number of source files is different than APML files");
            
            //
            for(int i=0; i<sourcenamer.sourcefiles.length; i++)
            {                                     
                //
                model = this.createsourcefile(sourcenamer.sourcefiles[i],apmlmodeler.apmlfiles[i]);
                
                //
                File sourcedirs = new File(outputdir.getPath()+File.separator+new filegrepper().getpackagenameaspathname(sourcenamer.sourcefiles[i].getPath()));
                if(!sourcedirs.exists()) 
                    sourcedirs.mkdirs();
                                
                //
                File mandir = new File(outputdir.getPath()+File.separator+new filegrepper().getpackagenameaspathname(sourcenamer.sourcefiles[i].getPath()));
                if(!mandir.exists()) 
                    mandir.mkdirs();   
                
                //
                model.build(this.outputdir,System.err);
            }
        }
        catch(Exception ex)
        {
            Logger.getLogger(apmltaghandler.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }                  
    }     
}

/**
 * 
 * @author max rupplin @standard
 */
class apmltaghandlerparameter
{
    JCodeModel jcodemodel;
    JDefinedClass classfile;
    JPackage jpackage; 
    apmlmodelfile apmlmodelfile;
            
    public apmltaghandlerparameter(JCodeModel jcodemodel, JPackage jpackage, JDefinedClass classfile, apmlmodelfile apmlmodelfile)
    {
        this.jcodemodel = jcodemodel;
        this.classfile = classfile;
        this.jpackage = jpackage;
        this.apmlmodelfile = apmlmodelfile;
    }            
}