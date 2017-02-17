package apml.compilers.java.codemodel;

import apml.helpers.Filegrepper;
import apml.modeling.Apmlmodelfile;
import apml.modeling.Apmlobject;
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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import apml.interfaces.Startable;
import apml.interfaces.Autostartable;
import apml.interfaces.Initializable;

/**
 *
 * @author Max Rupplin
 */
public class Jcmmodelpopulator 
{
    protected File manifestfile;
    protected File manifestdir;    
    protected File outputdir;   
    protected File apmlfile;
    
    protected static final String APMLIN = "/home/oem/Desktop/apml/apml/echoserver.xml";
    protected static final String SOURCEOUTDIR = "/home/oem/Desktop/apml/output";
    protected static final String MANIFESTDIR = "/home/oem/Desktop/apml/output/manifest";
    protected static final String MANIFESTFILE = "/home/oem/Desktop/apml/output/manifest/manifest.txt";
    
   /**
     * Makes the source file for output
     * 
     * @param sourcefile
     * @param apmlmodelfile
     * @return
     * @throws Exception 
     */
    private JCodeModel makeindividualjcodemodelinstance(Apmlmodelfile apmlmodelfile) throws Exception
    {       
        JCodeModel jcodemodel = new JCodeModel();                
        
        try
        {
            Apmltaghandlerparameter param;
            JDefinedClass classfile=null;
            JPackage jpackage=null;            
            
            param = new Apmltaghandlerparameter(jcodemodel,jpackage,classfile,apmlmodelfile);
            
            //         
            try{this.jcmpackagename(param);}        catch(Exception e){e.printStackTrace(System.err);}
            try{this.jcmclassname(param);}          catch(Exception e){e.printStackTrace(System.err);}            
            try{this.jcmextends(param);}            catch(Exception e){e.printStackTrace(System.err);}
            try{this.jcmimplements(param);}         catch(Exception e){e.printStackTrace(System.err);}
            try{this.jcmbndi(param);}               catch(Exception e){e.printStackTrace(System.err);}
            
            //
            try{this.jcmautostarttag(param);}       catch(Exception e){e.printStackTrace(System.err);}
            try{this.jcminittag(param);}            catch(Exception e){e.printStackTrace(System.err);} 
            try{this.jcmruntag(param);}             catch(Exception e){e.printStackTrace(System.err);}
            try{this.jcmstarttag(param);}           catch(Exception e){e.printStackTrace(System.err);}                         
            
            //
            try{this.jcmlisteners(param);}          catch(Exception e){e.printStackTrace(System.err);}
            try{this.jcmobjects(param);}            catch(Exception e){e.printStackTrace(System.err);}
            
            //
            try{this.addinterfacemethods(param);}   catch(Exception e){e.printStackTrace(System.err);}
            try{this.addsuperclassmethods(param);}  catch(Exception e){e.printStackTrace(System.err);}
            try{this.addtagmethods(param);}         catch(Exception e){e.printStackTrace(System.err);}                                     
            
            return jcodemodel;
        }
        catch(Exception ex)
        {
            Logger.getLogger(Jcmcompiler.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }        
        
        throw new Exception("ApmlTagHandler::createJCodeModel: Unable to return a JCodeModel.");
    }  
    
    /**
     * 
     * @param param 
     */
    private void jcmbndi(Apmltaghandlerparameter param)
    {
        try
        {
            if(param.jcodemodel==null) 
                throw new InvalidParameterException("JCodeModel not set; unable to set BNDI value.");

            param.classfile.field(JMod.PUBLIC | JMod.FINAL, java.lang.String.class, "bndi=\""+param.apmlmodelfile.bndi+"\"");                          
        }
        catch(Exception ex)
        {
            Logger.getLogger(Jcmcompiler.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }        
    }      
    
    /**
     * 
     * @param param 
     */
    private void jcmobjects(Apmltaghandlerparameter param)
    {
        try
        {
            if(param.jcodemodel==null) 
                throw new InvalidParameterException("JCodeModel not set; unable to get child object(s).");
            
            ArrayList<Apmlobject> objects = param.apmlmodelfile.apmlobjects;            
            for(int i=0; i<objects.size(); i++)
            {
//TODO kindly param.apmlmodelfile.builddir.replace() shouldn't be called; replace with working set or knowledge. /ok                
                String fullclassname = param.apmlmodelfile.builddir.replace("systems", "objects")+"."+new Filegrepper().getclassname(param.apmlmodelfile.apmlobjects.get(i).classname);                                                             
                
                param.classfile.field(JMod.PROTECTED, Class.forName(fullclassname), "object_"+String.format("%03d", i));                               
            }
        }
        catch(Exception ex)
        {
            Logger.getLogger(Jcmcompiler.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }        
    }    
    
    /**
     * 
     * @param param 
     */
    private void jcmlisteners(Apmltaghandlerparameter param)
    {
        try
        {
            if(param.jcodemodel==null) 
                throw new InvalidParameterException("JCodeModel not set; unable to set listener(s).");
            
            //param.jpackage = param.jcodemodel._package(param.apmlmodelfile.packagename); 
        }
        catch(Exception ex)
        {
            Logger.getLogger(Jcmcompiler.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }        
    }
    
    /**
     * 
     * @param param 
     */
    private void jcmpackagename(Apmltaghandlerparameter param)
    {
        try
        {
            if(param.jcodemodel==null) 
                throw new InvalidParameterException("JCodeModel not set; unable to set package name.");            

            param.jpackage = param.jcodemodel._package(param.apmlmodelfile.sourcedir);
        }
        catch(Exception ex)
        {
            Logger.getLogger(Jcmcompiler.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }        
    }
    
    /**
     * 
     * @param param 
     */
    private void jcmclassname(Apmltaghandlerparameter param)
    {
        try
        {     
            if(param.jpackage==null) 
                throw new InvalidParameterException("Package not set; unable to set class name.");
                
            if(param.apmlmodelfile.classname==null)
                throw new InvalidParameterException("No classname found.");
            
            param.classfile = param.jpackage._class(param.apmlmodelfile.classname);  
        }
        catch(NullPointerException | InvalidParameterException | JClassAlreadyExistsException ex)
        {
            Logger.getLogger(Jcmcompiler.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }        
    }
    
    /**
     * 
     * @param param 
     */
    private void jcmextends(Apmltaghandlerparameter param)
    {
        try
        {
            if(param.classfile==null) 
                throw new InvalidParameterException("Classfile not set; unable to determine if Class extends another.");
            
            if(param.apmlmodelfile.superclass==null) 
                throw new InvalidParameterException("No superclass reference found in param.apmlmodelfile.apml_extends.");            
               
            param.classfile = param.classfile._extends(Class.forName(param.apmlmodelfile.superclass));      
        }
        catch(NullPointerException | InvalidParameterException | ClassNotFoundException ex)
        {
            Logger.getLogger(Jcmcompiler.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }        
    }
    
    /**
     * Intend here to add class definition id est Class1 implements Interface1 but not the associated stub methods
     * 
     * @param param 
     */
    private void jcmimplements(Apmltaghandlerparameter param)
    {      
            try
            {
                if(param.classfile==null) 
                    throw new InvalidParameterException("Classfile not set; unable to set interfaces for Class.");
                
                if(param.apmlmodelfile.implementors==null || param.apmlmodelfile.implementors.length==0)
                    throw new InvalidParameterException("No interfaces found with param.apmlmodelfile.apml_implements.");
                
                for(String implments : param.apmlmodelfile.implementors)        
                {
                    try
                    {
                        param.classfile = param.classfile._implements(Class.forName(implments));
                    }
                    catch(ClassNotFoundException ex)
                    {
                        Logger.getLogger(Jcmcompiler.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
                    }
                }
            }
            catch(NullPointerException | InvalidParameterException ex)
            {
                Logger.getLogger(Jcmcompiler.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }        
    }
    
    /**
     * 
     * @param param 
     */
    private void jcmruntag(Apmltaghandlerparameter param)
    {
            try
            {
                if(param.classfile==null) 
                    throw new InvalidParameterException("Classfile not set; unable to load interface methods for JCodeModel builder.");
                
                if(param.apmlmodelfile.run==null)
                    throw new InvalidParameterException("Run or runnable not set; unable to comply.");
                
                if(param.apmlmodelfile.run.equalsIgnoreCase("true"))                    
                    param.classfile = param.classfile._implements(Runnable.class);
            }
            catch(Exception ex)
            {
                Logger.getLogger(Jcmcompiler.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }          
    }
    
    /**
     * 
     * @param param 
     */
    private void jcmstarttag(Apmltaghandlerparameter param)
    {          
        try
        {
            if(param.classfile==null) 
                throw new InvalidParameterException("Classfile not set; unable to load interface methods for JCodeModel builder.");
                
            if(param.apmlmodelfile.start==null)
                throw new InvalidParameterException("Start or startable not set; unable to comply.");
            
            if(param.apmlmodelfile.start.equalsIgnoreCase("true"))
                param.classfile = param.classfile._implements(Startable.class);
        }
        catch(Exception ex)
        {
            Logger.getLogger(Jcmcompiler.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }           
    }
    
    /**
     * 
     * @param param 
     */
    private void addinterfacemethods(Apmltaghandlerparameter param)
    {
        try
        {               
            if(param.classfile==null) 
                throw new InvalidParameterException("Classfile not set; unable to load interface methods for JCodeModel builder.");            
                
            if(param.apmlmodelfile.stdinterfaces==null || param.apmlmodelfile.stdinterfaces.length==0)
                throw new InvalidParameterException("No standard interfaces were found.");                
                   
            for(String intrfacename : param.apmlmodelfile.stdinterfaces)
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
                    Logger.getLogger(Jcmcompiler.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
                }
            }                 
        }
        catch(InvalidParameterException | NullPointerException | SecurityException ex)
        {
            Logger.getLogger(Jcmcompiler.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }        
    }
    
    /**
     * 
     * @param param 
     */
    private void jcmautostarttag(Apmltaghandlerparameter param)
    {
        try
        {
            if(param.classfile==null) 
                throw new InvalidParameterException("Classfile not set; unable to load interface methods for JCodeModel builder.");
                
            if(param.apmlmodelfile.autostart==null)
                throw new InvalidParameterException("No autostart tag found");
            
            param.classfile = param.classfile._implements(Autostartable.class);
        }
        catch(Exception ex)
        {
            Logger.getLogger(Jcmcompiler.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }         
    }
    
    /**
     * 
     * @param param 
     */
    private void jcminittag(Apmltaghandlerparameter param)
    {        
        try
        {
            if(param.classfile==null) 
                throw new InvalidParameterException("Classfile not set; unable to load interface methods for JCodeModel builder.");
                
            if(param.apmlmodelfile.init==null)
                throw new InvalidParameterException("No init tag found.");
            
            param.classfile = param.classfile._implements(Initializable.class);
        }
        catch(Exception ex)
        {
                Logger.getLogger(Jcmcompiler.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }         
    }

    /**
     * 
     * 
     * @param param 
     */
    private void addsuperclassmethods(Apmltaghandlerparameter param)
    {        
        try
        {
            if(param.classfile==null) 
                throw new InvalidParameterException("Classfile not set; unable to load superclass methods for JCodeModel builder.");
                
            if(param.apmlmodelfile.superclass==null)
                throw new InvalidParameterException("No superclass found.");
            
            Class superclass = Class.forName(param.apmlmodelfile.superclass);
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
            Logger.getLogger(Jcmcompiler.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }        
    }
    
    /**
     * Parse apml for tags (init, start, run, autostart, etc.) and put this data into JCodeModel form for output
     * 
     * @param param Contains model information from APML parsing 
     */
    private void addtagmethods(Apmltaghandlerparameter param)
    {
        try
        {
            Iterator<JClass> intrfaces;
                
            if(param.classfile==null) 
                throw new InvalidParameterException("Classfile not set; unable to load interface methods for JCodeModel builder.");
            
            if(param.apmlmodelfile.taginterfaces==null || param.apmlmodelfile.taginterfaces.length==0)
                throw new InvalidParameterException("No tag interfaces found.");
                
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
            Logger.getLogger(Jcmcompiler.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }         
    }            
    
    /**
     * 
     * @param apmlmodels
     * @return Returns JCodeModel array from Apmlmodelfile conversion process
     */
    public ArrayList<JCodeModel> getjcmmodelfiles(ArrayList<Apmlmodelfile> apmlmodels)
    {
        ArrayList<JCodeModel> jcmmodels = new ArrayList();
        
        for(int i=0;i<apmlmodels.size();i++)
        {            
            try
            {
                jcmmodels.add(this.makeindividualjcodemodelinstance(apmlmodels.get(i)));
            }
            catch(Exception e)
            {
                e.printStackTrace(System.err);
            }
        }              
        
        return jcmmodels;
    }
}
