package apml.compilers.java.codemodel;

import static java.nio.file.StandardCopyOption.*;

import apml.compilers.Stdcompiler;
import apml.helpers.Filegrepper;
import apml.modeling.Apmlmodelfile;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JPackage;
import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Max Rupplin
 * @since 02.02.2017
 * @version 1.00
 * @serial 0x888fe8 /mr /ss /ok
 */
public class Jcmcompiler extends Stdcompiler
{        
    protected final Integer hash = 0x888fe8;
    
    public ArrayList<Apmlmodelfile> apmlmodelfiles_apml;    
    public ArrayList<Apmlmodelfile> apmlmodelfiles_definitions;
    public ArrayList<Apmlmodelfile> apmlmodelfiles_dynamiclisteners;
    public ArrayList<Apmlmodelfile> apmlmodelfiles_listeners;
    public ArrayList<Apmlmodelfile> apmlmodelfiles_objects;
    public ArrayList<Apmlmodelfile> apmlmodelfiles_subscribers;
    public ArrayList<Apmlmodelfile> apmlmodelfiles_systems;
    
    public ArrayList<JCodeModel> jcmmodelfiles_apml;
    public ArrayList<JCodeModel> jcmmodelfiles_definitions;
    public ArrayList<JCodeModel> jcmmodelfiles_dynamiclisteners;
    public ArrayList<JCodeModel> jcmmodelfiles_listeners;
    public ArrayList<JCodeModel> jcmmodelfiles_objects;
    public ArrayList<JCodeModel> jcmmodelfiles_subscribers;
    public ArrayList<JCodeModel> jcmmodelfiles_systems;    
    
    protected File manifestfile;
    protected File manifestdir;    
    protected File sourcedir;   
    protected File apmlxmlfile;
    
    protected static final String APMLINJAR = "/home/oem/NetBeansProjects/APML/dist/APML.jar";
    protected static final String APMLOUTJAR = "/home/oem/Desktop/apml/output/libs/APML.jar";
    protected static final String APMLIN = "/home/oem/NetBeansProjects/APML/src/apml/examples/echoserver/server/echoserver.xml";
    //protected static final String APMLIN = "/home/oem/NetBeansProjects/APML/src/apml/examples/httpserver/server/httpserver.xml";
    //protected static final String APMLIN = "/home/oem/NetBeansProjects/APML/src/apml/examples/jgpserver/server/jgpserver.xml";
        
    protected static final String BASEDIR = "/home/oem/Desktop/apml/output/echo/";
    protected static final String BUILDDIR = "build/";
    protected static final String SRCDIR = "source/";
    protected static final String TEMPSRCDIR = "temp/";
    protected static final String MANIFESTDIR = "manifest/";
    protected static final String MANIFESTFILE = "/home/oem/Desktop/apml/output/manifest/manifest.txt";
    
    public static void main(String...args) 
    {                          
        try
        {
            //
            Jcmcompiler compiler = new Jcmcompiler();
            
            //
            compiler.getapmlmodelfiles(compiler.apmlxmlfile, "//apml");
            compiler.getapmlmodelfiles(compiler.apmlxmlfile, "//dynamiclistener");
            compiler.getapmlmodelfiles(compiler.apmlxmlfile, "//listener");    
            compiler.getapmlmodelfiles(compiler.apmlxmlfile, "//object");   
            compiler.getapmlmodelfiles(compiler.apmlxmlfile, "//subscriber");
            compiler.getapmlmodelfiles(compiler.apmlxmlfile, "//system");
              
            //
            compiler.quicktobytecode(compiler.apmlmodelfiles_apml);
            compiler.quicktobytecode(compiler.apmlmodelfiles_dynamiclisteners);
            compiler.quicktobytecode(compiler.apmlmodelfiles_listeners);
            compiler.quicktobytecode(compiler.apmlmodelfiles_objects);
            compiler.quicktobytecode(compiler.apmlmodelfiles_subscribers);
            compiler.quicktobytecode(compiler.apmlmodelfiles_systems);
            
            //
            compiler.getjavamodelfiles(compiler.apmlmodelfiles_apml, "//apml");
            compiler.getjavamodelfiles(compiler.apmlmodelfiles_dynamiclisteners, "//dynamiclistener");
            compiler.getjavamodelfiles(compiler.apmlmodelfiles_listeners, "//listener");
            compiler.getjavamodelfiles(compiler.apmlmodelfiles_objects, "//object");
            compiler.getjavamodelfiles(compiler.apmlmodelfiles_subscribers, "//subscriber");
            compiler.getjavamodelfiles(compiler.apmlmodelfiles_systems, "//system");    
            
            //
            compiler.compiletosource(compiler.jcmmodelfiles_apml);
            compiler.compiletosource(compiler.jcmmodelfiles_dynamiclisteners);                             
            compiler.compiletosource(compiler.jcmmodelfiles_listeners);                    
            compiler.compiletosource(compiler.jcmmodelfiles_objects);
            compiler.compiletosource(compiler.jcmmodelfiles_subscribers);                    
            compiler.compiletosource(compiler.jcmmodelfiles_systems);   
                        
            //
            compiler.writeapmlbackingjartodisk();
            
            //
            System.gc();
        }
        catch(Exception ex)
        {
            //Logger.getLogger(Jcmcompiler.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
    }    
    
    //
    public Jcmcompiler()
    {
        try
        {
            this.sourcedir = new File(BASEDIR+SRCDIR);            
            if(!this.sourcedir.exists())
                this.sourcedir.mkdirs();  
            
            this.sourcedir = new File(BASEDIR+BUILDDIR);            
            if(!this.sourcedir.exists())
                this.sourcedir.mkdirs();            
            
            this.manifestdir = new File(BASEDIR+MANIFESTDIR);            
            if(!this.manifestdir.exists())
                this.manifestdir.mkdirs();   
            
            this.apmlxmlfile = new File(Jcmcompiler.APMLIN);
            if(!this.apmlxmlfile.exists())
                throw new Exception("ApmlTagHandler::constructor:Could not find the system's APML file");     
        }
        catch(Exception ex)
        {
            Logger.getLogger(Jcmcompiler.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
    }      
    
    /**
     * Quickly make bytecode (.class) files for JCM reference in compiling final Java Source files.
     * 
     * @param apmlmodelfiles Ready, whole APML files for reference; ok, go.
     */
    public void quicktobytecode(ArrayList<Apmlmodelfile> apmlmodelfiles) 
    {
        for(Apmlmodelfile model: apmlmodelfiles) 
        {
            try
            {   
                JCodeModel jmodel;                
                Runtime runtime;
                
                String sourcepackagedir = new Filegrepper().getpackagenameaspathname(model.packagename);
                String buildpackagedir = new Filegrepper().getpackagenameaspathname(model.packagename);
                String pathname = new Filegrepper().getpackagenameaspathname(model.packagename)+"/";
                String runme = "javac "+BASEDIR+TEMPSRCDIR+pathname+model.classname+".java -d "+BASEDIR+BUILDDIR;
                
                new File(BASEDIR+TEMPSRCDIR).mkdirs();
                new File(BASEDIR+TEMPSRCDIR+sourcepackagedir).mkdirs();               
                
                new File(BASEDIR+BUILDDIR).mkdirs();
                new File(BASEDIR+BUILDDIR+buildpackagedir).mkdirs();
                   
                jmodel = new JCodeModel();
                jmodel._package(model.packagename)._class(model.classname);
                jmodel.build(new File(BASEDIR+TEMPSRCDIR),System.out);
                
                runtime = Runtime.getRuntime();                                                
                runtime.exec(runme);
            }
            catch(Exception e)
            {
                //e.printStackTrace(System.err);
            }
            finally
            {
                System.gc();
            }
        }
    }
    
    //
    public ArrayList<Apmlmodelfile> getapmlmodelfiles(File apmlxmlfile, String apmltag)
    {        
        Apmlmodelpopulator apmlmodelpopulator = new Apmlmodelpopulator();
        ArrayList<Apmlmodelfile> apmlmodels_genericfiles = null;
        
        try
        {
            switch(apmltag)
            {                    
                case "//apml":
                    this.apmlmodelfiles_apml = apmlmodels_genericfiles = apmlmodelpopulator.getapmlmodelfiles(apmlxmlfile, apmltag);
                    break; 
                    
                case "//definitions":
                    this.apmlmodelfiles_definitions = apmlmodels_genericfiles = apmlmodelpopulator.getapmlmodelfiles(apmlxmlfile, apmltag);
                    break; 
                    
                case "//dynamiclistener":
                    this.apmlmodelfiles_dynamiclisteners = apmlmodels_genericfiles = apmlmodelpopulator.getapmlmodelfiles(apmlxmlfile, apmltag);
                    break;    
                    
                case "//listener":
                    this.apmlmodelfiles_listeners = apmlmodels_genericfiles = apmlmodelpopulator.getapmlmodelfiles(apmlxmlfile, apmltag);
                    break;  
                    
                case "//object":
                    this.apmlmodelfiles_objects = apmlmodels_genericfiles = apmlmodelpopulator.getapmlmodelfiles(apmlxmlfile, apmltag);
                    break;                      
                    
                case "//subscriber":
                    this.apmlmodelfiles_subscribers = apmlmodels_genericfiles = apmlmodelpopulator.getapmlmodelfiles(apmlxmlfile, apmltag);
                    break;                    
                    
                case "//system":
                    this.apmlmodelfiles_systems = apmlmodels_genericfiles = apmlmodelpopulator.getapmlmodelfiles(apmlxmlfile, apmltag);
                    break;                    
            }            
        }
        catch(Exception e)
        {
            //e.printStackTrace(System.err);
        }
        
        return apmlmodels_genericfiles;
    }    
    
    //
    public ArrayList<JCodeModel> getjavamodelfiles(ArrayList<Apmlmodelfile> apmlmodelfiles, String apmltag)
    {
        Jcmmodelpopulator jcmmodelpopulator = new Jcmmodelpopulator();
        ArrayList<JCodeModel> jcmmodels_genericfiles = null;
                
        try
        {
            switch(apmltag)
            {
                case "//apml": 
                    this.jcmmodelfiles_apml = jcmmodels_genericfiles = jcmmodelpopulator.getjcmmodelfiles(apmlmodelfiles);
                    break;
                    
                case "//definitions": 
                    this.jcmmodelfiles_definitions = jcmmodels_genericfiles = jcmmodelpopulator.getjcmmodelfiles(apmlmodelfiles);
                    break;
                    
                case "//dynamiclistener":
                    this.jcmmodelfiles_dynamiclisteners = jcmmodels_genericfiles = jcmmodelpopulator.getjcmmodelfiles(apmlmodelfiles);
                    break;                    
                    
                case "//listener":
                    this.jcmmodelfiles_listeners = jcmmodels_genericfiles = jcmmodelpopulator.getjcmmodelfiles(apmlmodelfiles);
                    break;   
                    
                case "//object":
                    this.jcmmodelfiles_objects = jcmmodels_genericfiles = jcmmodelpopulator.getjcmmodelfiles(apmlmodelfiles);
                    break;                     
                    
                case "//subscriber":
                    this.jcmmodelfiles_subscribers = jcmmodels_genericfiles = jcmmodelpopulator.getjcmmodelfiles(apmlmodelfiles);
                    break;                    
                    
                case "//system":
                    this.jcmmodelfiles_systems = jcmmodels_genericfiles = jcmmodelpopulator.getjcmmodelfiles(apmlmodelfiles);
                    break;                    
            }                     
        }
        catch(Exception e)
        {
            e.printStackTrace(System.err);
        }
        
        return jcmmodels_genericfiles;
    }    
    
    //
    public void writeallsourcetodisk()
    {
        try
        {
            this.compiletosource(this.jcmmodelfiles_apml);
            this.compiletosource(this.jcmmodelfiles_definitions);
            this.compiletosource(this.jcmmodelfiles_dynamiclisteners);            
            this.compiletosource(this.jcmmodelfiles_listeners);
            this.compiletosource(this.jcmmodelfiles_objects);
            this.compiletosource(this.jcmmodelfiles_subscribers);
            this.compiletosource(this.jcmmodelfiles_systems);
            
            try
            {
                if(new File(Jcmcompiler.APMLOUTJAR).exists())
                {
                    Files.copy(new File(Jcmcompiler.APMLINJAR).toPath(),new File(Jcmcompiler.APMLOUTJAR).toPath(),REPLACE_EXISTING);
                }
                else new File(Jcmcompiler.APMLOUTJAR).mkdirs();
                {
                    Files.copy(new File(Jcmcompiler.APMLINJAR).toPath(),new File(Jcmcompiler.APMLOUTJAR).toPath(),REPLACE_EXISTING);                
                }
            }
            catch(Exception e){e.printStackTrace(System.err);}            
        }
        catch(Exception e)
        {
            e.printStackTrace(System.err);
        }
    }   
        
    //
    public void compiletosource(ArrayList<JCodeModel> jcmmodels)
    {        
        for(int i=0; i<jcmmodels.size(); i++)
        {
            try
            {                                
                Iterator<JPackage> packages = jcmmodels.get(i).packages();
                                
                while(packages.hasNext()) //do every package
                {                    
                    JPackage jpackage = packages.next();                            
                                    
                    Iterator<JDefinedClass> classes = jpackage.classes();
                    
                    while(classes.hasNext()) //do every class
                    {                                              
                        jcmmodels.get(i).build(new File(BASEDIR+SRCDIR), System.err);           
                    }
                }
            }
            catch(Exception e){e.printStackTrace(System.err);}                       
        }                
    }  

    //    
    public void writeapmlbackingjartodisk() throws Exception
    {
        if(new File(Jcmcompiler.APMLOUTJAR).exists())
        {
            Files.copy(new File(Jcmcompiler.APMLINJAR).toPath(),new File(Jcmcompiler.APMLOUTJAR).toPath(),REPLACE_EXISTING);
        }
        else new File(Jcmcompiler.APMLOUTJAR).mkdirs();
        {
            Files.copy(new File(Jcmcompiler.APMLINJAR).toPath(),new File(Jcmcompiler.APMLOUTJAR).toPath(),REPLACE_EXISTING);                
        }        
    }

    //
    @Override
    public void compiletosource() 
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
