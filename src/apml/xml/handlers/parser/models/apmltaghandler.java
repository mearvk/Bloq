package apml.xml.handlers.parser.models;

import apml.helpers.filegrepper;
import apml.interfaces.Autostartable;
import apml.interfaces.Initializable;
import apml.interfaces.Startable;
import apml.modeling.apmlmodelfile;
import apml.xml.handlers.parser.models.io.apmlmodelpopulator;
import apml.xml.handlers.parser.models.io.sourcefilenamer;
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
        catch(Exception ex)
        {
            Logger.getLogger(apmltaghandler.class.getName()).log(Level.SEVERE, null, ex);
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
            sth.convertapmltagstosource("//definitions");
            
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
            JDefinedClass classfile=null;
            JPackage jpackage=null;

            apmltaghandlerparameter param = new apmltaghandlerparameter(jcodemodel,jpackage,classfile,apmlmodelfile);
            
            dopackagename(param);
            doclassname(param);            
            doextends(param);
            doimplements(param);
            dorun(param);
            dostart(param);
            doautostart(param);
            doinit(param);
            dosuperclass(param);
            dotaginterfaces(param);
            dostdinterfaces(param);
            
            return jcodemodel;
        }
        catch(Exception ex)
        {
            Logger.getLogger(apmltaghandler.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }        
        
        throw new Exception("ApmlTagHandler::createJCodeModel: Unable to return a JCodeModel");
    }    
    
    private void dopackagename(apmltaghandlerparameter param)
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
    
    private void doclassname(apmltaghandlerparameter param)
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
    
    private void doextends(apmltaghandlerparameter param)
    {
        try
        {
            if(param.classfile==null) 
                throw new InvalidParameterException("Classfile not set; unable to determine if Class extends another");
               
            param.classfile = param.classfile._extends(Class.forName(param.apmlmodelfile.apml_extends));
        }
        catch(NullPointerException | InvalidParameterException | ClassNotFoundException ex)
        {
            Logger.getLogger(apmltaghandler.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }        
    }
    
    private void doimplements(apmltaghandlerparameter param)
    {
            //
            try
            {
                //
                if(param.classfile==null) 
                    throw new InvalidParameterException("Classfile not set; unable to set interfaces for Class");
                
                param.classfile = param.classfile._implements(Class.forName(param.apmlmodelfile.apml_interface));
            }
            catch(NullPointerException | InvalidParameterException | ClassNotFoundException ex)
            {
                Logger.getLogger(apmltaghandler.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }        
    }
    
    private void dorun(apmltaghandlerparameter param)
    {
            try
            {
                if(param.classfile==null) 
                    throw new InvalidParameterException("Classfile not set; unable to load interface methods for JCodeModel builder");
                
                if(param.apmlmodelfile.apml_run!=null)
                    param.classfile = param.classfile._implements(Runnable.class);
            }
            catch(Exception ex)
            {
                Logger.getLogger(apmltaghandler.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }          
    }
    
    private void dostart(apmltaghandlerparameter param)
    {          
        try
        {
            //
            if(param.classfile==null) 
                throw new InvalidParameterException("Classfile not set; unable to load interface methods for JCodeModel builder");
                
            if(param.apmlmodelfile.apml_start!=null)
                param.classfile = param.classfile._implements(Startable.class);
        }
        catch(Exception ex)
        {
            Logger.getLogger(apmltaghandler.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }           
    }
    
    private void dostdinterfaces(apmltaghandlerparameter param)
    {
        try
        {
            Iterator<JClass> intrfaces;
                
            if(param.classfile==null) 
                    throw new InvalidParameterException("Classfile not set; unable to load interface methods for JCodeModel builder");
                
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
                            System.err.println("Unknown origin detected; symmetry unbounded by this algorithm./detected..");
                            break;
                                
                        default: 
                            param.classfile.method(JMod.NONE, m.getReturnType(), m.getName()); 
                            break;
                    }                
                }                    
            }                 
        }
        catch(NullPointerException | ClassNotFoundException | SecurityException ex)
        {
            Logger.getLogger(apmltaghandler.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }        
    }
    
    private void doautostart(apmltaghandlerparameter param)
    {
        try
        {
            if(param.classfile==null) 
                throw new InvalidParameterException("Classfile not set; unable to load interface methods for JCodeModel builder");
                
            if(param.apmlmodelfile.apml_autostart!=null)
                param.classfile = param.classfile._implements(Autostartable.class);
        }
        catch(Exception ex)
        {
            Logger.getLogger(apmltaghandler.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }         
    }
    
    private void doinit(apmltaghandlerparameter param)
    {        
        try
        {
            if(param.classfile==null) 
                throw new InvalidParameterException("Classfile not set; unable to load interface methods for JCodeModel builder");
                
            if(param.apmlmodelfile.apml_initializable!=null)
                param.classfile = param.classfile._implements(Initializable.class);
        }
        catch(Exception ex)
        {
                Logger.getLogger(apmltaghandler.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }         
    }

    private void dosuperclass(apmltaghandlerparameter param)
    {        
        try
        {
            if(param.classfile==null) 
                throw new InvalidParameterException("Classfile not set; unable to load superclass methods for JCodeModel builder");
                
            Class superclass = Class.forName(param.apmlmodelfile.apml_extends);
            
            Method[] methods = superclass.getMethods();

            //
            for (Method method : methods) 
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
        catch(NullPointerException | ClassNotFoundException | SecurityException ex)
        {
            Logger.getLogger(apmltaghandler.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }        
    }
    
    private void dotaginterfaces(apmltaghandlerparameter param)
    {
        try
        {
            Iterator<JClass> intrfaces;
                
            if(param.classfile==null) 
                throw new InvalidParameterException("Classfile not set; unable to load interface methods for JCodeModel builder");
                
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
        catch(NullPointerException | ClassNotFoundException | SecurityException ex)
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
            JCodeModel model;            
            
            apmlmodelpopulator apmlmodeler;                        
            sourcefilenamer sourcenamer;
                                    
            apmlmodeler = new apmlmodelpopulator(this.apmlfile, xpathstring);  
            sourcenamer = new sourcefilenamer(xpathstring);           
                                                                  
            //
            for(int i=0; i<sourcenamer.sourcefiles.length; i++)
            {                                     
                //
                model = this.createjcodemodel(sourcenamer.sourcefiles[i],apmlmodeler.apmlfiles[i]);
                
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
            Logger.getLogger(apmltaghandler.class.getName()).log(Level.SEVERE, null, ex);
        }                  
    }     
}

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