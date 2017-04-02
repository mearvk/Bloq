package apml.compilers.java.codemodel;

import apml.interfaces.Startable;

import apml.interfaces.Autostartable;

import apml.interfaces.Initializable;

import apml.helpers.Filegrepper;

import apml.modeling.Apmlmodelfile;

import apml.system.bodi.Bodi;

import com.sun.codemodel.JClassAlreadyExistsException;

import com.sun.codemodel.JCodeModel;

import com.sun.codemodel.JDefinedClass;

import com.sun.codemodel.JMod;

import com.sun.codemodel.JPackage;

import com.sun.codemodel.JClass;

import java.io.IOException;

import java.lang.reflect.Method;

import java.lang.reflect.Modifier;

import java.security.InvalidParameterException;

import java.util.ArrayList;

import java.util.Iterator;

import java.util.logging.FileHandler;

import java.util.logging.Level;

import java.util.logging.Logger;

/**
 *
 * @author Max Rupplin
 * @since 03.28.2017
 */
public class BloqJCMpopulator 
{
    private final Integer hash = 0x00888fe8;   
    
    /*--------------------------------------------------------------------------*/
          
    public static final Logger LOGGER = Logger.getLogger(BloqJCMpopulator.class.getName()); 
    
    /*--------------------------------------------------------------------------*/
    
    public BloqJCMpopulator()
    {
        Bodi.setcontext("system");
        
        Bodi.context("system").put("bloqpopulatorimpl", this);   
        
        /*----------------------------------------------------------------------*/
        
        Bloqfileguardian fileguardian = (Bloqfileguardian)Bodi.context("system").pull("bloqfileguardian");
        
        try
        {
            LOGGER.addHandler(new FileHandler(fileguardian.loggingfileurl+fileguardian.loggingfilename));
            
            LOGGER.setUseParentHandlers(false);            
        }
        catch(IOException | SecurityException e)
        {
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        }
    }            
    
    private JCodeModel makeindividualjcodemodelinstance(Apmlmodelfile apmlmodelfile) throws Exception
    {       
        JCodeModel jcodemodel = new JCodeModel();                

        JDefinedClass classfile = null;
            
        JPackage jpackage = null;            
            
        Bloqconvenienceparameter param = new Bloqconvenienceparameter(jcodemodel, jpackage, classfile, apmlmodelfile);                
        
        try            
        {            
            /*------------------------------------ Add class fields ---------------------------------------------------*/
            
            try{this.jcmpackagename(param);}        catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{this.jcmclassname(param);}          catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}            
            
            try{this.jcmextends(param);}            catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{this.jcmimplements(param);}         catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{this.jcmbodi(param);}               catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{this.jcmautostarttag(param);}       catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{this.jcminittag(param);}            catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/} 
            
            try{this.jcmruntag(param);}             catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{this.jcmstarttag(param);}           catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}                         
            
            try{this.jcmlisteners(param);}          catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{this.jcmsubscribers(param);}        catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{this.jcmobjects(param);}            catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            /*--------------------------------------- Add methods -----------------------------------------------------*/
            
            //try{this.addsimplemethods(param);}         catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{this.addinterfacemethods(param);}   catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{this.addsuperclassmethods(param);}  catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{this.addtagmethods(param);}         catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}                                     
            
            /*--------------------------------------- Return JCM ------------------------------------------------------*/
                        
        }
        catch(Exception e)
        {
            /*LOGGER.log(Level.WARNING, e.getMessage(), e);*/
        }        
        
        try
        {                 
            switch(apmlmodelfile.tagname.toLowerCase())
            {
                case "apml":            
                    doapmlfinishing(param);
                    break;
                
                case "dynamiclistener": 
                    dodynamiclistenerfinishing(param);
                    break;
                
                case "listener":        
                    dolistenerfinishing(param);
                    break;
                    
                case "object":        
                    doobjectfinishing(param);
                    break;                    
                
                case "subscriber":      
                    dosubscriberfinishing(param);
                    break;
                
                case "system":          
                    dosystemfinishing(param);
                    break;                
            }
        }
        catch(Exception e)
        {
            //
        }        
        
        return jcodemodel;
        
        //throw new Exception("ApmlTagHandler::createJCodeModel: Unable to return a JCodeModel.");        
    }  
    
    private void doapmlfinishing(Bloqconvenienceparameter param)
    {
        param.classref.constructor(JMod.PUBLIC);                
        
        param.classref.method(JMod.PUBLIC, java.lang.Object.class, "dosetter");
        
        param.classref.direct("//todo finish adding support");
    }
    
    private void dodynamiclistenerfinishing(Bloqconvenienceparameter param)
    {
        param.classref.constructor(JMod.PUBLIC);                
        
        param.classref.method(JMod.PUBLIC, java.lang.Object.class, "dosetter");
        
        param.classref.direct("//todo finish adding support");
    }
    
    private void dolistenerfinishing(Bloqconvenienceparameter param)
    {
        param.classref.constructor(JMod.PUBLIC);                
        
        param.classref.method(JMod.PUBLIC, java.lang.Object.class, "dosetter");
        
        param.classref.direct("//todo finish adding support");
    }
    
    private void doobjectfinishing(Bloqconvenienceparameter param)
    {
        param.classref.constructor(JMod.PUBLIC);                
        
        param.classref.method(JMod.PUBLIC, java.lang.Object.class, "dosetter");
        
        param.classref.direct("//todo finish adding support");
    }    
    
    private void dosubscriberfinishing(Bloqconvenienceparameter param)
    {
        param.classref.constructor(JMod.PUBLIC);                
        
        param.classref.method(JMod.PUBLIC, java.lang.Object.class, "dosetter");
        
        param.classref.direct("//todo finish adding support");
    }
    
    private void dosystemfinishing(Bloqconvenienceparameter param)
    {
        param.classref.constructor(JMod.PUBLIC);                
        
        param.classref.method(JMod.PUBLIC, java.lang.Object.class, "dosetter");
        
        param.classref.direct("//todo finish adding support");
    }
    
    private void jcmbodi(Bloqconvenienceparameter param)
    {
        try
        {
            if(param.jcodemodel==null) 
            {
                throw new InvalidParameterException("JCodeModel not set; unable to set BODI value.");           
            }
            
            if(param.apmlmodelfile.bodi==null) 
            {
                throw new InvalidParameterException("BODI value not properly set; unable to set BODI value.");           
            }
                            
            param.classref.field(JMod.PUBLIC | JMod.FINAL, java.lang.String.class, "bodi=\""+param.apmlmodelfile.bodi+"\"");                          
            
            //param.classref.direct("\tprotected String bodi=\""+param.apmlmodelfile.bodi+"\";\n");
        }
        catch(Exception e)
        {
            /*LOGGER.log(Level.WARNING, e.getMessage(), e);*/
        }        
    }      
    
    private void jcmobjects(Bloqconvenienceparameter param)
    {
        try
        {
            if(param.jcodemodel==null)
            {    
                throw new InvalidParameterException("JCodeModel not set; unable to set child object(s).");
            }
            
            if(param.apmlmodelfile.apmlobjects==null)
            {    
                throw new InvalidParameterException("No child objects set; unable to set child object(s).");
            }            
                       
            for(int i=0; i<param.apmlmodelfile.apmlobjects.size(); i++) //for each child object
            {   
                String classname = new Filegrepper().getclassname(param.apmlmodelfile.apmlobjects.get(i).classname);                                                             
                
                String packagename = param.apmlmodelfile.apmlobjects.get(i).packagename;
                
                String fullname = packagename+"."+classname;
                                                
                param.classref.field(JMod.PROTECTED, Class.forName(fullname), "object_"+String.format("%03d", i));                               
                
                //param.classref.direct("\n\tprotected "+classname+" object_"+String.format("%03d", i)+";\n");
            }
        }
        catch(Exception e)
        {
            /*LOGGER.log(Level.WARNING, e.getMessage(), e);*/
        }        
    }    
    
    private void jcmlisteners(Bloqconvenienceparameter param)
    {
        try
        {
            if(param.jcodemodel==null) 
            {
                throw new InvalidParameterException("JCodeModel not set; unable to set listener(s).");
            }
            
            if(param.apmlmodelfile.apmllisteners==null)
            {
                throw new InvalidParameterException("Apmllisteners not set; unable to set listener(s).");
            }
            
            //
            for(int i=0; i<param.apmlmodelfile.apmllisteners.size(); i++)
            {   
                String classname = new Filegrepper().getclassname(param.apmlmodelfile.apmllisteners.get(i).classname); 
                
                String packagename = param.apmlmodelfile.apmllisteners.get(i).packagename;
                
                String fullname = packagename+"."+classname;
            
                param.classref.field(JMod.PROTECTED, Class.forName(fullname), "listeners_"+String.format("%03d",i));
                
                //
                /*for(int j=0; j<param.apmlmodelfile.apmlsubscribers.size(); j++)
                {
                    param.classref.direct("\tprivate final class Apmllistener\n");
                    
                    param.classref.direct("\t{\n");
                    
                    param.classref.direct("\t//to be implemented\n");
                    
                    param.classref.direct("\t}\n");
                }*/
                
                //param.classref.direct("\n\tprotected "+classname+" listener_"+String.format("%03d",i)+";\n");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace(); //LOGGER.log(Level.WARNING, e.getMessage(), e);            
        }        
    }
    
    private void jcmsubscribers(Bloqconvenienceparameter param)
    {
        try
        {
            if(param.jcodemodel==null)
            {
                throw new InvalidParameterException("JCodeModel not set; unable to set subscribers(s).");
            }
            
            if(param.apmlmodelfile.subscribers==null)
            {
                throw new InvalidParameterException("Apmlsubscribers not set; unable to set subscriber(s).");
            }                
            
            //
            for(int i=0; i<param.apmlmodelfile.apmlsubscribers.size(); i++)
            {   
                String classname = new Filegrepper().getclassname(param.apmlmodelfile.apmlsubscribers.get(i).classname); 
                
                String packagename = param.apmlmodelfile.apmlsubscribers.get(i).packagename;
                
                String fullname = packagename+"."+classname;
            
                param.classref.field(JMod.PROTECTED, Class.forName(fullname), "subscribers_"+String.format("%03d",i));
                
                //param.classref.direct("\n\tprotected "+classname+" listener_"+String.format("%03d",i)+";\n");
            }            
        }
        catch(Exception e)
        {
            /*LOGGER.log(Level.WARNING, e.getMessage(), e);*/
        }
    }
    
    private void jcmpackagename(Bloqconvenienceparameter param)
    {
        try
        {
            if(param.jcodemodel==null) 
            {
                throw new InvalidParameterException("JCodeModel not set; unable to set package name.");            
            }
            
            if(param.apmlmodelfile.packagename==null) 
            {
                throw new InvalidParameterException("No package name found; unable to set package name.");            
            }            
            
            param.jpackage = param.jcodemodel._package(param.apmlmodelfile.packagename);
        }
        catch(Exception e)
        {
            /*LOGGER.log(Level.WARNING, e.getMessage(), e);*/
        }        
    }
    
    private void jcmclassname(Bloqconvenienceparameter param)
    {
        try
        {     
            if(param.jpackage==null) 
            {
                throw new InvalidParameterException("Package not set; unable to set class name.");
            }
                
            if(param.apmlmodelfile.classname==null)
            {
                throw new InvalidParameterException("No classname found.");
            }
            
            param.classref = param.jpackage._class(param.apmlmodelfile.classname);  
        }
        catch(NullPointerException | InvalidParameterException | JClassAlreadyExistsException e)
        {
            /*LOGGER.log(Level.WARNING, e.getMessage(), e);*/
        }        
    }

    private void jcmextends(Bloqconvenienceparameter param)
    {
        try
        {
            if(param.classref==null) 
            {
                throw new InvalidParameterException("Classfile not set; unable to determine if Class extends another.");
            }
            
            if(param.apmlmodelfile.superclass==null) 
            {
                throw new InvalidParameterException("No superclass reference found in param.apmlmodelfile.apml_extends.");            
            }
               
            param.classref = param.classref._extends(Class.forName(param.apmlmodelfile.superclass));      
        }
        catch(NullPointerException | InvalidParameterException | ClassNotFoundException e)
        {
            /*LOGGER.log(Level.WARNING, e.getMessage(), e);*/
        }        
    }
    
    private void jcmimplements(Bloqconvenienceparameter param)
    {      
        try
        {
            if(param.classref==null) 
            {
                throw new InvalidParameterException("Classfile not set; unable to set interfaces for Class.");
            }
                
            if(param.apmlmodelfile.implementors==null || param.apmlmodelfile.implementors.length==0)
            {
                throw new InvalidParameterException("No interfaces found with param.apmlmodelfile.apml_implements.");
            }
                
            for(String implments : param.apmlmodelfile.implementors)        
            {
                try
                {
                    param.classref = param.classref._implements(Class.forName(implments));
                }
                catch(ClassNotFoundException ex)
                {
                    Logger.getLogger(Bloqcompiler.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
                }
            }
        }
        catch(NullPointerException | InvalidParameterException e)
        {
            /*LOGGER.log(Level.WARNING, e.getMessage(), e);*/
        }        
    }
    
    private void jcmruntag(Bloqconvenienceparameter param)
    {
        try
        {
            if(param.classref==null) 
            {
                throw new InvalidParameterException("Classfile not set; unable to load interface methods for JCodeModel builder.");
            }
                
            if(param.apmlmodelfile.run==null)
            {
                throw new InvalidParameterException("Run or runnable not set; unable to comply.");
            }
                
            if(param.apmlmodelfile.run.equalsIgnoreCase("true"))                    
            {
                param.classref = param.classref._implements(Runnable.class);
            }
        }
        catch(Exception e)
        {
            /*LOGGER.log(Level.WARNING, e.getMessage(), e);*/
        }          
    }
    
    private void jcmstarttag(Bloqconvenienceparameter param)
    {          
        try
        {
            if(param.classref==null) 
            {
                throw new InvalidParameterException("Classfile not set; unable to load interface methods for JCodeModel builder.");
            }
                
            if(param.apmlmodelfile.start==null)
            {
                throw new InvalidParameterException("Start or startable not set; unable to comply.");
            }
            
            if(param.apmlmodelfile.start.equalsIgnoreCase("true"))
            {
                param.classref = param.classref._implements(Startable.class);
            }
        }
        catch(Exception e)
        {
            /*LOGGER.log(Level.WARNING, e.getMessage(), e);*/
        }           
    }
    
    private void addinterfacemethods(Bloqconvenienceparameter param)
    {
        try
        {               
            if(param.classref==null) 
            {
                throw new InvalidParameterException("Classfile not set; unable to load interface methods for JCodeModel builder.");            
            }
                
            if(param.apmlmodelfile.stdinterfaces==null || param.apmlmodelfile.stdinterfaces.length==0)
            {
                throw new InvalidParameterException("No standard interfaces were found.");                
            }
                   
            for(String intrfacename : param.apmlmodelfile.stdinterfaces)
            {
                try
                {
                    Class intrface = Class.forName(intrfacename);

                    Method[] methods = intrface.getMethods();

                    for(Method m: methods)
                    {                                      
                        switch(m.getModifiers())
                        {
                            case Modifier.ABSTRACT | Modifier.PUBLIC:
                                param.classref.method(JMod.PUBLIC, m.getReturnType(), m.getName());
                                break;

                            case Modifier.ABSTRACT | Modifier.PROTECTED:                                            
                                param.classref.method(JMod.PROTECTED, m.getReturnType(), m.getName());
                                break;                    

                            case Modifier.ABSTRACT | Modifier.NATIVE:
                                param.classref.method(JMod.NATIVE, m.getReturnType(), m.getName());
                                break;   

                            case Modifier.ABSTRACT | Modifier.PRIVATE:
                                param.classref.method(JMod.PRIVATE, m.getReturnType(), m.getName());
                                break;  

                            case Modifier.ABSTRACT | Modifier.INTERFACE:
                                System.err.println("Unknown origin detected; symmetry unbounded by this algorithm detected.");
                                break;

                            default: 
                                param.classref.method(JMod.NONE, m.getReturnType(), m.getName()); 
                                break;
                        }                
                    }
                }
                catch(ClassNotFoundException e)
                {
                    /*LOGGER.log(Level.WARNING, e.getMessage(), e);*/
                }
            }                 
        }
        catch(InvalidParameterException | NullPointerException | SecurityException e)
        {
            /*LOGGER.log(Level.WARNING, e.getMessage(), e);*/
        }        
    }
    
    private void jcmautostarttag(Bloqconvenienceparameter param)
    {
        try
        {
            if(param.classref==null) 
            {
                throw new InvalidParameterException("Classfile not set; unable to load interface methods for JCodeModel builder.");
            }
                
            if(param.apmlmodelfile.autostart==null)
            {
                throw new InvalidParameterException("No autostart tag found");
            }
            
            param.classref = param.classref._implements(Autostartable.class);
        }
        catch(Exception e)
        {
            /*LOGGER.log(Level.WARNING, e.getMessage(), e);*/
        }         
    }
    
    private void jcminittag(Bloqconvenienceparameter param)
    {        
        try
        {
            if(param.classref==null) 
            {
                throw new InvalidParameterException("Classfile not set; unable to load interface methods for JCodeModel builder.");
            }
                
            if(param.apmlmodelfile.init==null)
            {
                throw new InvalidParameterException("No init tag found.");
            }
            
            param.classref = param.classref._implements(Initializable.class);
        }
        catch(Exception e)
        {
            /*LOGGER.log(Level.WARNING, e.getMessage(), e);*/
        }         
    }

    private void addsuperclassmethods(Bloqconvenienceparameter param)
    {        
        try
        {
            if(param.classref==null) 
            {
                throw new InvalidParameterException("Classfile not set; unable to load superclass methods for JCodeModel builder.");
            }
                
            if(param.apmlmodelfile.superclass==null)
            {
                throw new InvalidParameterException("No superclass found.");
            }
            
            Class superclass = Class.forName(param.apmlmodelfile.superclass);
            for (Method method : superclass.getMethods()) 
            {
                if(method==null) break;
                   
                switch (method.getModifiers()) 
                {
                    case Modifier.PUBLIC:
                        param.classref.method(JMod.PUBLIC, method.getReturnType(), method.getName());
                        break;
            
                    case Modifier.PROTECTED:
                        param.classref.method(JMod.PROTECTED, method.getReturnType(), method.getName());    
                        break;
                                                
                    case Modifier.NATIVE:                    
                        param.classref.method(JMod.NATIVE, method.getReturnType(), method.getName());                        
                        break;                        
                       
                    case Modifier.PRIVATE:                            
                        param.classref.method(JMod.PRIVATE, method.getReturnType(), method.getName());                            
                        break;
                        
                    default:
                        param.classref.method(JMod.NONE, method.getReturnType(), method.getName());
                        break;
                    }
            }                 
        }
        catch(InvalidParameterException | NullPointerException | ClassNotFoundException | SecurityException e)
        {
            /*LOGGER.log(Level.WARNING, e.getMessage(), e);*/
        }        
    }
    
    private void addtagmethods(Bloqconvenienceparameter param)
    {
        try
        {
            Iterator<JClass> intrfaces;
                
            if(param.classref==null) 
            {
                throw new InvalidParameterException("Classfile not set; unable to load interface methods for JCodeModel builder.");
            }
            
            if(param.apmlmodelfile.taginterfaces==null || param.apmlmodelfile.taginterfaces.length==0)
            {
                throw new InvalidParameterException("No tag interfaces found.");
            }
                
            intrfaces = param.classref._implements();
                
            for(;intrfaces.hasNext();)
            {
                JClass class_ = intrfaces.next();                    
                Class intrface = Class.forName(class_.fullName());
                    
                    Method[] methods = intrface.getMethods();

                    for(Method m: methods)
                    {                
                        switch(m.getModifiers())
                        {
                            case Modifier.ABSTRACT |  Modifier.PUBLIC:
                                param.classref.method(JMod.PUBLIC, m.getReturnType(), m.getName());
                                break;
                                
                            case Modifier.ABSTRACT |  Modifier.PROTECTED:                                            
                                param.classref.method(JMod.PROTECTED, m.getReturnType(), m.getName());
                                break;                    
                                
                            case Modifier.ABSTRACT |  Modifier.NATIVE:
                                param.classref.method(JMod.NATIVE, m.getReturnType(), m.getName());
                                break;                        
                                
                            case Modifier.ABSTRACT |  Modifier.PRIVATE:
                                param.classref.method(JMod.PRIVATE, m.getReturnType(), m.getName());
                                break;                                    
                                
                            default: 
                                param.classref.method(JMod.NONE, m.getReturnType(), m.getName()); 
                                break;                                
                        }                
                    }                   
                }                 
        }
        catch(InvalidParameterException | NullPointerException | ClassNotFoundException | SecurityException e)
        {
            /*LOGGER.log(Level.WARNING, e.getMessage(), e);*/
        }         
    }            
    
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
                /*LOGGER.log(Level.WARNING, e.getMessage(), e);*/
            }
        }              
        
        return jcmmodels;
    }
}
