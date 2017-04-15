package apml.compilers.java.codemodel;

import apml.interfaces.Startable;

import apml.interfaces.Autostartable;

import apml.interfaces.Initializable;

import apml.helpers.Filegrepper;

import apml.modeling.Apmlimplement;

import apml.modeling.Apmlmodelfile;

import apml.system.bodi.Bodi;

import com.sun.codemodel.JClassAlreadyExistsException;

import com.sun.codemodel.JCodeModel;

import com.sun.codemodel.JDefinedClass;

import com.sun.codemodel.JMod;

import com.sun.codemodel.JPackage;

import com.sun.codemodel.JClass;

import com.sun.codemodel.JMethod;

import java.io.IOException;

import java.lang.reflect.Method;

import java.lang.reflect.Modifier;

import java.security.InvalidParameterException;

import java.util.ArrayList;

import java.util.Iterator;

import java.util.logging.FileHandler;

import java.util.logging.Level;

import java.util.logging.Logger;

import apml.annotations.BloqSys;

import java.lang.reflect.Parameter;


/**
 *
 * @author Max Rupplin
 * @since 03.28.2017
 */
public class Bloqjcmpopulator 
{
    private final Integer hash = 0x00888FE8;   
    
    /*--------------------------------------------------------------------------*/
          
    public static final Logger LOGGER = Logger.getLogger(Bloqjcmpopulator.class.getName()); 
    
    /*--------------------------------------------------------------------------*/
    
    public Bloqjcmpopulator()
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
            /*------------------------------------ Add base fields ---------------------------------------------------*/            
            
            try{this.addjcmpackagename(param);}         catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{this.addjcmclassname(param);}           catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}                                                
            
            try{this.addjcmextends(param);}             catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{this.addjcmimplements(param);}          catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
                        
            
            /*------------------------------------ Add bloq fields ---------------------------------------------------*/
            
            try{this.addjcmautostarttag(param);}        catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}                                                  
            
            try{this.addjcminittag(param);}             catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/} 
            
            try{this.addjcmlisteners(param);}           catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{this.addjcmobjects(param);}             catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}                        
            
            try{this.addjcmruntag(param);}              catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{this.addjcmstarttag(param);}            catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}                                                 
            
            try{this.addjcmsubscribers(param);}         catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{this.addjcmsystems(param);}             catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}                        
            
            /*--------------------------------------- Add methods -----------------------------------------------------*/
            
            try{this.doapmlspecificbloqs(param);}       catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{this.addjcmimplements(param);}          catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            try{this.addjcminterfacemethods(param);}    catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            //try{this.addjcmsuperclassmethods(param);}   catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            //try{this.addjcmtagmethods(param);}          catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}                                     
            
            //try{this.addjcmbodi(param);}                catch(Exception e){/*LOGGER.log(Level.WARNING, e.getMessage(), e);*/}
            
            /*--------------------------------------- Return JCM ------------------------------------------------------*/
                        
        }
        catch(Exception e)
        {
            /*LOGGER.log(Level.WARNING, e.getMessage(), e);*/
        }        
        
        
        
        return jcodemodel;               
    }   
    
    private void doapmlspecificbloqs(Bloqconvenienceparameter param)
    {
        Apmlmodelfile apmlmodelfile = param.apmlmodelfile;
        
        try
        {                 
            switch(apmlmodelfile.tagname.toLowerCase())
            {
                case "apml":            
                    doapmlbloqs(param);
                    break;
                    
                case "callback":            
                    docallbackbloqs(param);
                    break;                    
                
                case "dynamiclistener": 
                    dodynamiclistenerbloqs(param);
                    break;
                
                case "listener":        
                    dolistenerbloqs(param);
                    break;
                    
                case "object":        
                    doobjectbloqs(param);
                    break;                    
                
                case "subscriber":      
                    dosubscriberbloqs(param);
                    break;
                
                case "system":          
                    dosystembloqs(param);
                    break;                
            }
        }
        catch(Exception e)
        {
            //e.printStackTrace(System.err);
        }        
    }
    
    private void doapmlbloqs(Bloqconvenienceparameter param)
    {
        JDefinedClass theclass = param.classref;
                
        /*---------------------------------------------------------------------*/
        
        JMethod constructor1;
        
        constructor1 = param.classref.constructor(JMod.PUBLIC);                
        
        constructor1.param(JMod.FINAL, apml.system.Apmlsystem.class, "monitor");
        
        constructor1.body().directStatement("\n");
        
        constructor1.body().directStatement("this.monitor = monitor;\n");
        
        for(int i=0; i<param.apmlmodelfile.apmlsystems.size(); i++)
        {
            constructor1.body().directStatement("this.system_"+String.format("%03d", i)+" = new "+param.apmlmodelfile.apmlsystems.get(i).classname+"();\n");
        }
        
        /*---------------------------------------------------------------------*/
        
        JMethod constructor2;
        
        constructor2 = param.classref.constructor(JMod.PUBLIC);
        
        constructor2.body().directStatement("\n");                
        
        for(int i=0; i<param.apmlmodelfile.apmlsystems.size(); i++)
        {
            constructor2.body().directStatement("this.system_"+String.format("%03d", i)+" = new "+param.apmlmodelfile.apmlsystems.get(i).classname+"();\n");
        }        
        
        /*---------------------------------------------------------------------*/                                               
        
        JMethod callback;
        
        callback = param.classref.method(JMod.PUBLIC, java.lang.Object.class, "callback");
        
        callback.param(JMod.FINAL, java.lang.Object.class, "event");
        
        callback.body().directStatement("return null;\n");
        
        /*---------------------------------------------------------------------*/                                 
        
        theclass.field(JMod.PUBLIC | JMod.FINAL, java.lang.String.class, "bodi=\""+param.apmlmodelfile.bodi+"\"");
        
        theclass.field(JMod.PUBLIC | JMod.FINAL, java.lang.String.class, "id=\""+param.apmlmodelfile.id+"\"");
                
        theclass.field(JMod.PUBLIC | JMod.FINAL, java.lang.String.class, "tagname=\""+param.apmlmodelfile.tagname+"\"");
        
        /*---------------------------------------------------------------------*/
        
        param.classref.direct("\t\n\n//TODO: finish adding support...");
    }
    
    private void docallbackbloqs(Bloqconvenienceparameter param)
    {
        JDefinedClass theclass = param.classref;
                
        /*---------------------------------------------------------------------*/
        
        JMethod constructor1;
        
        constructor1 = param.classref.constructor(JMod.PUBLIC);                
        
        constructor1.param(JMod.FINAL, apml.system.Apmlsystem.class, "monitor");
        
        constructor1.body().directStatement("\n");
        
        constructor1.body().directStatement("this.monitor = monitor;\n");
        
        /*---------------------------------------------------------------------*/
        
        JMethod constructor2;
        
        constructor2 = param.classref.constructor(JMod.PUBLIC);
        
        constructor2.body().directStatement("\n");        
        
        /*---------------------------------------------------------------------*/                                               
        
        JMethod callback;
        
        callback = param.classref.method(JMod.PUBLIC, java.lang.Object.class, "callback");
        
        callback.param(JMod.FINAL, java.lang.Object.class, "event");
        
        callback.body().directStatement("return null;\n");
        
        /*---------------------------------------------------------------------*/                                 
        
        theclass.field(JMod.PUBLIC | JMod.FINAL, java.lang.String.class, "bodi=\""+param.apmlmodelfile.bodi+"\"");
        
        theclass.field(JMod.PUBLIC | JMod.FINAL, java.lang.String.class, "id=\""+param.apmlmodelfile.id+"\"");
                
        theclass.field(JMod.PUBLIC | JMod.FINAL, java.lang.String.class, "tagname=\""+param.apmlmodelfile.tagname+"\"");
        
        /*---------------------------------------------------------------------*/
        
        param.classref.direct("\t\n\n//TODO: finish adding support...");        
    }
    
    private void dodynamiclistenerbloqs(Bloqconvenienceparameter param)
    {
        JDefinedClass theclass = param.classref;
                
        /*---------------------------------------------------------------------*/
        
        JMethod constructor1;
        
        constructor1 = param.classref.constructor(JMod.PUBLIC);                
        
        constructor1.param(JMod.FINAL, apml.system.Apmlsystem.class, "monitor");
        
        constructor1.body().directStatement("\n");
        
        constructor1.body().directStatement("this.monitor = monitor;\n");
        
        /*---------------------------------------------------------------------*/
        
        JMethod constructor2;
        
        constructor2 = param.classref.constructor(JMod.PUBLIC);
        
        constructor2.body().directStatement("\n");        
        
        /*---------------------------------------------------------------------*/                                               
        
        JMethod callback;
        
        callback = param.classref.method(JMod.PUBLIC, java.lang.Object.class, "callback");
        
        callback.param(JMod.FINAL, java.lang.Object.class, "event");
        
        callback.body().directStatement("return null;\n");
        
        /*---------------------------------------------------------------------*/                   
        
        theclass.field(JMod.PUBLIC | JMod.FINAL, java.lang.String.class, "bodi=\""+param.apmlmodelfile.bodi+"\"");
        
        theclass.field(JMod.PUBLIC | JMod.FINAL, java.lang.String.class, "id=\""+param.apmlmodelfile.id+"\"");
                
        theclass.field(JMod.PUBLIC | JMod.FINAL, java.lang.String.class, "tagname=\""+param.apmlmodelfile.tagname+"\"");
        
        /*---------------------------------------------------------------------*/        
        
        param.classref.direct("\t\n\n//TODO: finish adding support...");
    }
    
    private void dolistenerbloqs(Bloqconvenienceparameter param)
    {                
        JDefinedClass theclass = param.classref;
        
        theclass._implements(java.awt.event.ActionListener.class);
                
        /*---------------------------------------------------------------------*/
        
        JMethod constructor1;
        
        constructor1 = param.classref.constructor(JMod.PUBLIC);                
        
        constructor1.param(JMod.FINAL, apml.system.Apmlsystem.class, "monitor");
        
        constructor1.body().directStatement("\n\t/*--------------- instantiation ----------------*/\n");
        
        constructor1.body().directStatement("this.monitor = monitor;\n");
        
        for(int i=0; i<param.apmlmodelfile.apmlsubscribers.size(); i++)
        {
            constructor1.body().directStatement("this.subscriber_"+String.format("%03d", i)+" = new "+param.apmlmodelfile.apmlsubscribers.get(i).classname+"();\n");
        }
        
        /*---------------------------------------------------------------------*/
        
        JMethod constructor2;
        
        constructor2 = param.classref.constructor(JMod.PUBLIC);    
        
        constructor2.body().directStatement("\n\t/*--------------- instantiation ----------------*/\n");
        
        for(int i=0; i<param.apmlmodelfile.apmlsubscribers.size(); i++)
        {
            constructor2.body().directStatement("this.subscriber_"+String.format("%03d", i)+" = new "+param.apmlmodelfile.apmlsubscribers.get(i).classname+"();\n");
        }        
        
        /*---------------------------------------------------------------------*/                                               
        
        JMethod callback;
        
        callback = param.classref.method(JMod.PUBLIC, java.lang.Object.class, "callback");
        
        callback.param(JMod.FINAL, java.lang.Object.class, "event");
        
        callback.body().directStatement("return null;\n");
        
        /*---------------------------------------------------------------------*/  
               
        theclass.field(JMod.PUBLIC | JMod.FINAL, java.lang.String.class, "bodi=\""+param.apmlmodelfile.bodi+"\"");
        
        theclass.field(JMod.PUBLIC | JMod.FINAL, java.lang.String.class, "id=\""+param.apmlmodelfile.id+"\"");
                
        theclass.field(JMod.PUBLIC | JMod.FINAL, java.lang.String.class, "tagname=\""+param.apmlmodelfile.tagname+"\"");
        
        /*---------------------------------------------------------------------*/        
        
        param.classref.direct("\t\n\n//TODO: finish adding support...");
    }
    
    private void doobjectbloqs(Bloqconvenienceparameter param)
    {
        JDefinedClass theclass = param.classref;
                
        /*---------------------------------------------------------------------*/
        
        JMethod constructor1;
        
        constructor1 = param.classref.constructor(JMod.PUBLIC);                
        
        constructor1.param(JMod.FINAL, apml.system.Apmlsystem.class, "monitor");
        
        constructor1.body().directStatement("\n\t/*--------------- instantiation ----------------*/\n"); 
        
        constructor1.body().directStatement("this.monitor = monitor;\n");
        
        for(int i=0; i<param.apmlmodelfile.apmllisteners.size(); i++)
        {
            constructor1.body().directStatement("this.listener_"+String.format("%03d", i)+" = new "+param.apmlmodelfile.apmllisteners.get(i).classname+"();\n");
        }                
        
        /*---------------------------------------------------------------------*/
        
        JMethod constructor2;
        
        constructor2 = param.classref.constructor(JMod.PUBLIC);
        
        constructor2.body().directStatement("\n\t/*--------------- instantiation ----------------*/\n"); 
        
        for(int i=0; i<param.apmlmodelfile.apmllisteners.size(); i++)
        {
            constructor2.body().directStatement("this.listener_"+String.format("%03d", i)+" = new "+param.apmlmodelfile.apmllisteners.get(i).classname+"();\n");
        }           
        
        /*---------------------------------------------------------------------*/                                               
        
        JMethod callback;
        
        callback = param.classref.method(JMod.PUBLIC, java.lang.Object.class, "callback");
        
        callback.param(JMod.FINAL, java.lang.Object.class, "event");
        
        callback.body().directStatement("return null;\n");
        
        /*---------------------------------------------------------------------*/         
        
        theclass.field(JMod.PUBLIC | JMod.FINAL, java.lang.String.class, "bodi=\""+param.apmlmodelfile.bodi+"\"");
        
        theclass.field(JMod.PUBLIC | JMod.FINAL, java.lang.String.class, "id=\""+param.apmlmodelfile.id+"\"");
                
        theclass.field(JMod.PUBLIC | JMod.FINAL, java.lang.String.class, "tagname=\""+param.apmlmodelfile.tagname+"\"");
        
        /*---------------------------------------------------------------------*/                       
        
        param.classref.direct("\t\n\n//TODO: finish adding support...");
    }    
    
    private void dosubscriberbloqs(Bloqconvenienceparameter param)
    {
        JDefinedClass theclass = param.classref;
                
        /*---------------------------------------------------------------------*/
        
        JMethod constructor1;
        
        constructor1 = param.classref.constructor(JMod.PUBLIC);                
        
        constructor1.param(JMod.FINAL, apml.system.Apmlsystem.class, "monitor");
        
        constructor1.body().directStatement("\n");
        
        constructor1.body().directStatement("this.monitor = monitor;\n");
        
        /*---------------------------------------------------------------------*/
        
        JMethod constructor2;
        
        constructor2 = param.classref.constructor(JMod.PUBLIC);
        
        constructor2.body().directStatement("\n");        
        
        /*---------------------------------------------------------------------*/                                               
        
        JMethod callback;
        
        callback = param.classref.method(JMod.PUBLIC, java.lang.Object.class, "callback");
        
        callback.param(JMod.FINAL, java.lang.Object.class, "event");
        
        callback.body().directStatement("return null;\n");
        
        /*---------------------------------------------------------------------*/                  
        
        theclass.field(JMod.PUBLIC | JMod.FINAL, java.lang.String.class, "bodi=\""+param.apmlmodelfile.bodi+"\"");
        
        theclass.field(JMod.PUBLIC | JMod.FINAL, java.lang.String.class, "id=\""+param.apmlmodelfile.id+"\"");
                
        theclass.field(JMod.PUBLIC | JMod.FINAL, java.lang.String.class, "tagname=\""+param.apmlmodelfile.tagname+"\"");
        
        /*---------------------------------------------------------------------*/                        
        
        param.classref.direct("\t\n\n//TODO: finish adding support...");
    }
    
    private void dosystembloqs(Bloqconvenienceparameter param)
    {
        JDefinedClass theclass = param.classref;
                
        /*---------------------------------------------------------------------*/
        
        JMethod constructor1;
        
        constructor1 = param.classref.constructor(JMod.PUBLIC);                
        
        constructor1.param(JMod.FINAL, apml.system.Apmlsystem.class, "monitor");        
        
        constructor1.body().directStatement("\n\t/*--------------- instantiation ----------------*/\n"); 
        
        constructor1.body().directStatement("this.monitor = monitor;\n");
        
        for(int i=0; i<param.apmlmodelfile.apmlobjects.size(); i++)
        {
            constructor1.body().directStatement("\tthis.object_"+String.format("%03d", i)+" = new "+param.apmlmodelfile.apmlobjects.get(i).classname+"();\n");
        }        
        
        /*---------------------------------------------------------------------*/
        
        JMethod constructor2;
        
        constructor2 = param.classref.constructor(JMod.PUBLIC);
        
        constructor2.body().directStatement("\n\t/*--------------- instantiation ----------------*/\n"); 
        
        for(int i=0; i<param.apmlmodelfile.apmlobjects.size(); i++)
        {
            constructor2.body().directStatement("\tthis.object_"+String.format("%03d", i)+" = new "+param.apmlmodelfile.apmlobjects.get(i).classname+"();\n");
        }        
        
        /*---------------------------------------------------------------------*/                                               
        
        JMethod callback;
        
        callback = param.classref.method(JMod.PUBLIC, java.lang.Object.class, "callback");
        
        callback.param(JMod.FINAL, java.lang.Object.class, "event");
        
        callback.body().directStatement("return null;\n");
        
        /*---------------------------------------------------------------------*/ 
               
        theclass.field(JMod.PUBLIC | JMod.FINAL, java.lang.String.class, "bodi=\""+param.apmlmodelfile.bodi+"\"");
        
        theclass.field(JMod.PUBLIC | JMod.FINAL, java.lang.String.class, "id=\""+param.apmlmodelfile.id+"\"");
                
        theclass.field(JMod.PUBLIC | JMod.FINAL, java.lang.String.class, "tagname=\""+param.apmlmodelfile.tagname+"\"");
        
        /*---------------------------------------------------------------------*/                
        
        param.classref.direct("\t\n\n//TODO: finish adding support...");
    }
    
    private void addjcmbodi(Bloqconvenienceparameter param)
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
        }
        catch(Exception e)
        {
            //e.printStackTrace(System.err);
        }        
    }      
    
    private void addjcmobjects(Bloqconvenienceparameter param)
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
            }
        }
        catch(Exception e)
        {
            //e.printStackTrace(System.err);
        }        
    }    
    
    private void addjcmlisteners(Bloqconvenienceparameter param)
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
            
                param.classref.field(JMod.PROTECTED, Class.forName(fullname), "listener_"+String.format("%03d",i));
                
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
    
    private void addjcmsubscribers(Bloqconvenienceparameter param)
    {
        try
        {
            if(param.jcodemodel==null)
            {
                throw new InvalidParameterException("JCodeModel not set; unable to set subscribers(s).");
            }
            
            if(param.apmlmodelfile.apmlsubscribers==null || param.apmlmodelfile.apmlsubscribers.size()==0)
            {
                throw new InvalidParameterException("Apmlsubscribers not set; unable to set subscriber(s).");
            }                
            
            //
            for(int i=0; i<param.apmlmodelfile.apmlsubscribers.size(); i++)
            {   
                String classname = new Filegrepper().getclassname(param.apmlmodelfile.apmlsubscribers.get(i).classname); 
                
                String packagename = param.apmlmodelfile.apmlsubscribers.get(i).packagename;
                
                String fullname = packagename+"."+classname;
            
                param.classref.field(JMod.PROTECTED, Class.forName(fullname), "subscriber_"+String.format("%03d",i));
                
                //param.classref.direct("\n\tprotected "+classname+" listener_"+String.format("%03d",i)+";\n");
            }            
        }
        catch(Exception e)
        {
            /*LOGGER.log(Level.WARNING, e.getMessage(), e);*/
        }
    }
    
    private void addjcmsystems(Bloqconvenienceparameter param)
    {
        try
        {
            if(param.jcodemodel==null)
            {
                throw new InvalidParameterException("JCodeModel not set; unable to set system(s).");
            }
            
            param.classref.field(JMod.PROTECTED, apml.system.Apmlsystem.class, "monitor");
            
            if(param.apmlmodelfile.apmlsystems==null)
            {
                throw new InvalidParameterException("Apmlsystems not set; unable to set system(s).");
            }                
            
            //
            for(int i=0; i<param.apmlmodelfile.apmlsystems.size(); i++)
            {   
                String classname = new Filegrepper().getclassname(param.apmlmodelfile.apmlsystems.get(i).classname); 
                
                String packagename = param.apmlmodelfile.apmlsystems.get(i).packagename;
                
                String fullname = packagename+"."+classname;
            
                param.classref.field(JMod.PROTECTED, Class.forName(fullname), "system_"+String.format("%03d",i));
                
                //param.classref.direct("\n\tprotected "+classname+" listener_"+String.format("%03d",i)+";\n");
            }            
        }
        catch(Exception e)
        {
            /*LOGGER.log(Level.WARNING, e.getMessage(), e);*/
        }        
    }
    
    private void addjcmpackagename(Bloqconvenienceparameter param)
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
    
    private void addjcmclassname(Bloqconvenienceparameter param)
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

    private void addjcmextends(Bloqconvenienceparameter param)
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
    
    private void addjcmimplements(Bloqconvenienceparameter param)
    {      
        try
        {
            if(param.classref==null) 
            {
                throw new InvalidParameterException("Classfile not set; unable to set interfaces for Class.");
            }                
                            
            Iterator itr = param.classref._implements();
                                    
            for(;itr.hasNext();)
            {               
                Class quickload = Class.forName(((JClass)itr.next()).fullName());
                
                for(Method method :quickload.getMethods())
                {                    
                    if(Modifier.isPublic(method.getModifiers()))
                    {                        
                        JMethod jcmmethod;
                        
                        jcmmethod = param.classref.method(JMod.PUBLIC, method.getReturnType(), method.getName());
                        
                        for(int k=0; k<method.getParameterCount(); k++)
                        {                    
                            Parameter parameter = method.getParameters()[k];
                            
                            jcmmethod.param(parameter.getType(), parameter.getName());
                            
                            jcmmethod.body().directStatement("\n");
                        }
                    }                                        
                }
            }
        }
        catch(NullPointerException | InvalidParameterException | ClassNotFoundException e)
        {
            /*LOGGER.log(Level.WARNING, e.getMessage(), e);*/
        }        
    }
    
    private void addjcmruntag(Bloqconvenienceparameter param)
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
    
    private void addjcmstarttag(Bloqconvenienceparameter param)
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
    
    private void addjcminterfacemethods(Bloqconvenienceparameter param)
    {
        try
        {               
            if(param.classref==null) 
            {
                throw new InvalidParameterException("Classfile not set; unable to load interface methods for JCodeModel builder.");            
            }                
            
            if(param.apmlmodelfile.apmlimplements==null) 
            {
                throw new InvalidParameterException("No interfaces found; unable to load interface methods for JCodeModel builder.");            
            }            
                   
            for(Apmlimplement _interface : param.apmlmodelfile.apmlimplements)
            {                                
                try
                {
                    Class interfacename = Class.forName(_interface.classname);
                    
                    param.classref._implements(interfacename);
                                        
                    for(Method m: interfacename.getMethods())
                    {                
                        JMethod method;
                        
                        switch(m.getModifiers())
                        {
                            
                            case Modifier.ABSTRACT | Modifier.PUBLIC:
                                
                                method = param.classref.method(JMod.PUBLIC, m.getReturnType(), m.getName());
                                method.body().directStatement("\n");                                      
                                method.annotate(BloqSys.class);
                                break;

                            case Modifier.ABSTRACT | Modifier.PROTECTED:                                            
                                
                                method = param.classref.method(JMod.PROTECTED, m.getReturnType(), m.getName());
                                method.body().directStatement("\n");
                                method.annotate(BloqSys.class);
                                break;                    

                            case Modifier.ABSTRACT | Modifier.NATIVE:
                                
                                method = param.classref.method(JMod.NATIVE, m.getReturnType(), m.getName());
                                method.body().directStatement("\n");
                                method.annotate(BloqSys.class);
                                break;   

                            case Modifier.ABSTRACT | Modifier.PRIVATE:
                                
                                method = param.classref.method(JMod.PRIVATE, m.getReturnType(), m.getName());
                                method.body().directStatement("\n");
                                method.annotate(BloqSys.class);
                                break;      

                            case Modifier.ABSTRACT | Modifier.INTERFACE:
                                
                                method = param.classref.method(JMod.PROTECTED, m.getReturnType(), m.getName());
                                method.body().directStatement("\n");
                                method.annotate(BloqSys.class);
                                break;      

                            default: 
                                
                                method = param.classref.method(JMod.NONE, m.getReturnType(), m.getName());
                                method.body().directStatement("\n");
                                method.annotate(BloqSys.class);
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
            //e.printStackTrace();
        }        
    }
    
    private void addjcmautostarttag(Bloqconvenienceparameter param)
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
    
    private void addjcminittag(Bloqconvenienceparameter param)
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

    private void addjcmsuperclassmethods(Bloqconvenienceparameter param)
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
    
    private void addjcmtagmethods(Bloqconvenienceparameter param)
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
