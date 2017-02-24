package apml.compilers.java.codemodel;

import static java.nio.file.StandardCopyOption.*;
import apml.compilers.Stdcompiler;
import apml.helpers.Filegrepper;
import apml.modeling.Apmlmodelfile;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JPackage;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.File;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Jcmcompiler extends Stdcompiler
{        
    protected final Integer hash = 0x888fe8;
       
    protected static final Logger LOGGER = Logger.getLogger(Jcmcompiler.class.getName());    
    
    public Bloqapmlmanager bloqapmlmanager = new Bloqapmlmanager(); 
    
    public Bloqjcmmanager bloqjcmmanager = new Bloqjcmmanager();
    
    public Bloqfileguardian fileguardian = new Bloqfileguardian();
    
    public static void main(String...args) 
    {                          
        try
        {
            Jcmcompiler bloqcompiler = new Jcmcompiler();
            
            bloqcompiler.setapmlfiles(bloqcompiler.fileguardian.apmlxmlinputfile);
            
            bloqcompiler.settempfiles(bloqcompiler);
            
            bloqcompiler.setjcmfiles(bloqcompiler);         
            
            bloqcompiler.setsourcefiles(bloqcompiler);
            
            bloqcompiler.setmovejarfile();
            
            System.gc();
        }
        catch(Exception exception)
        {
            System.err.println(exception);
        }
    }
    
    public Jcmcompiler()
    {                        
        try
        {
            LOGGER.addHandler(new FileHandler("/home/oem/Desktop/apml/output/logging/Jcmcompiler.txt"));
            
            LOGGER.setUseParentHandlers(false);
            
            this.fileguardian.sourceoutdir                      = new File(fileguardian.basedirurl+fileguardian.srcdirurl);     
            
            this.fileguardian.buildoutdir                       = new File(fileguardian.basedirurl+fileguardian.builddirurl);
            
            this.fileguardian.manifestfiledir                   = new File(fileguardian.basedirurl+fileguardian.manifestdirurl);
            
            this.fileguardian.apmlxmlinputfile                  = new File(fileguardian.apmlinurl);
            
            if(!this.fileguardian.sourceoutdir.exists())        this.fileguardian.sourceoutdir.mkdirs();                  
            
            if(!this.fileguardian.buildoutdir.exists())         this.fileguardian.buildoutdir.mkdirs();                                             
            
            if(!this.fileguardian.manifestfiledir.exists())     this.fileguardian.manifestfiledir.mkdirs();
            
            if(!this.fileguardian.apmlxmlinputfile.exists())    throw new Exception("ApmlTagHandler::constructor:Could not find the system's APML file");
        }
        catch(Exception exception)
        {
            System.err.println(exception);
        }
    }          
    
    public void settempfiles(Jcmcompiler compiler)
    {
        try
        {
            compiler.quicktobytecode(compiler.bloqapmlmanager.apmlmodels);
            
            compiler.quicktobytecode(compiler.bloqapmlmanager.dynamiclistenermodels);
            
            compiler.quicktobytecode(compiler.bloqapmlmanager.listenermodels);
            
            compiler.quicktobytecode(compiler.bloqapmlmanager.objectmodels);
            
            compiler.quicktobytecode(compiler.bloqapmlmanager.subscribermodels);
            
            compiler.quicktobytecode(compiler.bloqapmlmanager.systemmodels);
        }
        catch(Exception exception)
        {
            LOGGER.log(Level.WARNING, exception.getMessage(), exception);
        }
    }
    
    public void setapmlfiles(File apmlxmlinputfile)
    {
        try
        {
            this.generateapmlmodelfiles(apmlxmlinputfile, "//apml");
            
            this.generateapmlmodelfiles(apmlxmlinputfile, "//dynamiclistener");
            
            this.generateapmlmodelfiles(apmlxmlinputfile, "//listener");    
            
            this.generateapmlmodelfiles(apmlxmlinputfile, "//object");   
            
            this.generateapmlmodelfiles(apmlxmlinputfile, "//subscriber");
            
            this.generateapmlmodelfiles(apmlxmlinputfile, "//system");        
        }
        catch(Exception exception)
        {
            LOGGER.log(Level.WARNING, exception.getMessage(), exception);
        }
    }    
    
    public void setjcmfiles(Jcmcompiler compiler)
    {
        try
        {
            compiler.generatejavamodelfiles(compiler.bloqapmlmanager.apmlmodels, "//apml");
            
            compiler.generatejavamodelfiles(compiler.bloqapmlmanager.dynamiclistenermodels, "//dynamiclistener");
            
            compiler.generatejavamodelfiles(compiler.bloqapmlmanager.listenermodels, "//listener");
            
            compiler.generatejavamodelfiles(compiler.bloqapmlmanager.objectmodels, "//object");
            
            compiler.generatejavamodelfiles(compiler.bloqapmlmanager.subscribermodels, "//subscriber");
            
            compiler.generatejavamodelfiles(compiler.bloqapmlmanager.systemmodels, "//system");         
        }
        catch(Exception exception)
        {
            LOGGER.log(Level.WARNING, exception.getMessage(), exception);
        }
    }
    
    public void quicktobytecode(ArrayList<Apmlmodelfile> apmlmodelfiles) 
    {
        for(Apmlmodelfile model: apmlmodelfiles) 
        {
            try
            {   
                JCodeModel jmodel;                
                
                Runtime runtime;
                
                String sourcepackagedir     = new Filegrepper().getpackagenameaspathname(model.packagename);
                
                String buildpackagedir      = new Filegrepper().getpackagenameaspathname(model.packagename);
                
                String pathname             = new Filegrepper().getpackagenameaspathname(model.packagename)+"/";
                
                String javac                = "javac "+fileguardian.basedirurl+fileguardian.tempsrcdirurl+pathname+model.classname+".java -d "+fileguardian.basedirurl+fileguardian.builddirurl;
                
                new File(fileguardian.basedirurl+fileguardian.tempsrcdirurl).mkdirs();
                
                new File(fileguardian.basedirurl+fileguardian.tempsrcdirurl+sourcepackagedir).mkdirs();               
                
                new File(fileguardian.basedirurl+fileguardian.builddirurl).mkdirs();
                
                new File(fileguardian.basedirurl+fileguardian.builddirurl+buildpackagedir).mkdirs();
                   
                jmodel = new JCodeModel();
                
                jmodel._package(model.packagename)._class(model.classname);
                
                jmodel.build(new File(fileguardian.basedirurl+fileguardian.tempsrcdirurl));
                
                runtime = Runtime.getRuntime();                                                
                
                runtime.exec(javac);
            }
            catch(Exception exception)
            {
                LOGGER.log(Level.WARNING, exception.getMessage(), exception);
            }
            finally
            {
                System.gc();
            }
        }
    }    
    
    public ArrayList<Apmlmodelfile> generateapmlmodelfiles(File apmlxmlfile, String apmltag)
    {        
        Apmlmodelpopulator apmlmodelpopulator = new Apmlmodelpopulator();
        
        ArrayList<Apmlmodelfile> apmlmodelfiles = null;
        
        try
        {
            switch(apmltag)
            {                    
                case "//apml": this.bloqapmlmanager.apmlmodels = apmlmodelfiles = apmlmodelpopulator.getapmlmodelfiles(apmlxmlfile, apmltag); break;
                
                case "//definitions": this.bloqapmlmanager.definitionmodels = apmlmodelfiles = apmlmodelpopulator.getapmlmodelfiles(apmlxmlfile, apmltag); break;                    
                
                case "//dynamiclistener": this.bloqapmlmanager.dynamiclistenermodels = apmlmodelfiles = apmlmodelpopulator.getapmlmodelfiles(apmlxmlfile, apmltag); break;                    
                
                case "//listener": this.bloqapmlmanager.listenermodels = apmlmodelfiles = apmlmodelpopulator.getapmlmodelfiles(apmlxmlfile, apmltag); break;                    
                
                case "//object": this.bloqapmlmanager.objectmodels = apmlmodelfiles = apmlmodelpopulator.getapmlmodelfiles(apmlxmlfile, apmltag); break;                    
                
                case "//subscriber": this.bloqapmlmanager.subscribermodels = apmlmodelfiles = apmlmodelpopulator.getapmlmodelfiles(apmlxmlfile, apmltag); break;                    
                
                case "//system": this.bloqapmlmanager.systemmodels = apmlmodelfiles = apmlmodelpopulator.getapmlmodelfiles(apmlxmlfile, apmltag); break;
            }            
        }
        catch(Exception exception)
        {
            LOGGER.log(Level.WARNING, exception.getMessage(), exception);
        }
        
        return apmlmodelfiles;
    }    
    
    public ArrayList<JCodeModel> generatejavamodelfiles(ArrayList<Apmlmodelfile> apmlmodelfiles, String apmltag)
    {
        Jcmmodelpopulator jcmmodelpopulator = new Jcmmodelpopulator();
        ArrayList<JCodeModel> jcmmodels_genericfiles = null;
                
        try
        {
            switch(apmltag)
            {
                case "//apml": this.bloqjcmmanager.apml = jcmmodels_genericfiles = jcmmodelpopulator.getjcmmodelfiles(apmlmodelfiles); break;
                    
                case "//definitions": this.bloqjcmmanager.definitions = jcmmodels_genericfiles = jcmmodelpopulator.getjcmmodelfiles(apmlmodelfiles); break;
                    
                case "//dynamiclistener": this.bloqjcmmanager.dynamiclisteners = jcmmodels_genericfiles = jcmmodelpopulator.getjcmmodelfiles(apmlmodelfiles); break;                    
                    
                case "//listener": this.bloqjcmmanager.listeners = jcmmodels_genericfiles = jcmmodelpopulator.getjcmmodelfiles(apmlmodelfiles); break;   
                    
                case "//object": this.bloqjcmmanager.objects = jcmmodels_genericfiles = jcmmodelpopulator.getjcmmodelfiles(apmlmodelfiles); break;                     
                    
                case "//subscriber": this.bloqjcmmanager.subscribers = jcmmodels_genericfiles = jcmmodelpopulator.getjcmmodelfiles(apmlmodelfiles); break;                    
                    
                case "//system": this.bloqjcmmanager.systems = jcmmodels_genericfiles = jcmmodelpopulator.getjcmmodelfiles(apmlmodelfiles); break;                    
            }                     
        }
        catch(Exception exception)
        {
            LOGGER.log(Level.WARNING, exception.getMessage(), exception);
        }
        
        return jcmmodels_genericfiles;
    }    
    
    public void writeallsourcetodisk()
    {
        try
        {
            this.compiletosource(this.bloqjcmmanager.apml);
            
            this.compiletosource(this.bloqjcmmanager.definitions);
            
            this.compiletosource(this.bloqjcmmanager.dynamiclisteners);            
            
            this.compiletosource(this.bloqjcmmanager.listeners);
            
            this.compiletosource(this.bloqjcmmanager.objects);
            
            this.compiletosource(this.bloqjcmmanager.subscribers);
            
            this.compiletosource(this.bloqjcmmanager.systems);
            
            try
            {
                if(new File(fileguardian.apmloutjarurl).exists())
                {
                    Files.copy(new File(fileguardian.apmlinjarurl).toPath(), new File(fileguardian.apmloutjarurl).toPath(), REPLACE_EXISTING );
                }
                else new File(fileguardian.apmloutjarurl).mkdirs();
                {
                    Files.copy(new File(fileguardian.apmlinjarurl).toPath(), new File(fileguardian.apmloutjarurl).toPath(), REPLACE_EXISTING );                
                }
            }
            catch(Exception exception)
            {
                LOGGER.log(Level.WARNING, exception.getMessage(), exception);
            }            
        }
        catch(Exception exception)
        {
            System.err.println(exception);
        }
    }   
    
    public void setsourcefiles(Jcmcompiler compiler)
    {
        compiler.compiletosource(compiler.bloqjcmmanager.apml);
        
        compiler.compiletosource(compiler.bloqjcmmanager.dynamiclisteners);                             
        
        compiler.compiletosource(compiler.bloqjcmmanager.listeners);                    
        
        compiler.compiletosource(compiler.bloqjcmmanager.objects);
        
        compiler.compiletosource(compiler.bloqjcmmanager.subscribers);                    
        
        compiler.compiletosource(compiler.bloqjcmmanager.systems);           
    }
        
    public void compiletosource(ArrayList<JCodeModel> jcmmodels)
    {        
        for(int i=0; i<jcmmodels.size(); i++)
        {
            try
            {                                
                Iterator<JPackage> packages = jcmmodels.get(i).packages();
                                
                while(packages.hasNext())
                {                    
                    JPackage jpackage = packages.next();                            
                                    
                    Iterator<JDefinedClass> classes = jpackage.classes();
                    
                    String pname = jpackage.name();
                    
                    while(classes.hasNext())
                    {                                              
                        String cname = classes.next().name();
                        
                        jcmmodels.get(i).build(new File(fileguardian.basedirurl+fileguardian.srcdirurl), System.err);           
                    }
                }
            }
            catch(Exception exception)
            {
                LOGGER.log(Level.WARNING, exception.getMessage(), exception);
            }                       
        }                
    }  
 
    public void setmovejarfile() throws Exception
    {
        if(new File(fileguardian.apmloutjarurl).exists())
        {
            Files.copy(new File(fileguardian.apmlinjarurl).toPath(),new File(fileguardian.apmloutjarurl).toPath(),REPLACE_EXISTING);
        }
        else new File(fileguardian.apmloutjarurl).mkdirs();
        {
            Files.copy(new File(fileguardian.apmlinjarurl).toPath(),new File(fileguardian.apmloutjarurl).toPath(),REPLACE_EXISTING);                
        }        
    }

    @Override
    public void compiletosource() 
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
