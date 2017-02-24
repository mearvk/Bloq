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

/**
 * 
 * @author max rupplin
 */
public class Jcmcompiler extends Stdcompiler
{        
    protected final Integer hash = 0x888fe8;
       
    protected Logger logger = Logger.getLogger(Jcmcompiler.class.getName());    
    
    public Bloqapmlmanager apmlmanager = new Bloqapmlmanager(); 
    
    public Bloqjcmmanager jcmmanager = new Bloqjcmmanager();
    
    public Bloqfileguardian fileguardian = new Bloqfileguardian();
    
    public static void main(String...args) 
    {                          
        try
        {
            Jcmcompiler bloqcompiler = new Jcmcompiler();
            
            bloqcompiler.setapmlfiles(bloqcompiler.fileguardian);
            
            bloqcompiler.settempfiles(bloqcompiler.apmlmanager);
            
            bloqcompiler.setjcmfiles(bloqcompiler.apmlmanager);         
            
            bloqcompiler.setsourcefiles(bloqcompiler.jcmmanager);
            
            bloqcompiler.setjarfile();
            
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
            logger.addHandler(new FileHandler("/home/oem/Desktop/apml/output/logging/Jcmcompiler.txt"));
            
            logger.setUseParentHandlers(false);
            
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
    
    public void settempfiles(Bloqapmlmanager bloqapmlmanager)
    {
        try
        {
            this.quicktobytecode(bloqapmlmanager.apmlmodels);
            
            this.quicktobytecode(bloqapmlmanager.dynamiclistenermodels);
            
            this.quicktobytecode(bloqapmlmanager.listenermodels);
            
            this.quicktobytecode(bloqapmlmanager.objectmodels);
            
            this.quicktobytecode(bloqapmlmanager.subscribermodels);
            
            this.quicktobytecode(bloqapmlmanager.systemmodels);
        }
        catch(Exception exception)
        {
            logger.log(Level.WARNING, exception.getMessage(), exception);
        }
    }
    
    public void setapmlfiles(Bloqfileguardian fileguardian)
    {
        try
        {
            this.generateapmlmodelfiles(fileguardian.apmlxmlinputfile, "//apml");
            
            this.generateapmlmodelfiles(fileguardian.apmlxmlinputfile, "//dynamiclistener");
            
            this.generateapmlmodelfiles(fileguardian.apmlxmlinputfile, "//listener");    
            
            this.generateapmlmodelfiles(fileguardian.apmlxmlinputfile, "//object");   
            
            this.generateapmlmodelfiles(fileguardian.apmlxmlinputfile, "//subscriber");
            
            this.generateapmlmodelfiles(fileguardian.apmlxmlinputfile, "//system");        
        }
        catch(Exception exception)
        {
            logger.log(Level.WARNING, exception.getMessage(), exception);
        }
    }    
    
    public void setjcmfiles(Bloqapmlmanager bloqapmlmanager)
    {        
        try
        {                        
            this.generatejavamodelfiles(bloqapmlmanager.apmlmodels, "//apml");
            
            this.generatejavamodelfiles(bloqapmlmanager.dynamiclistenermodels, "//dynamiclistener");
            
            this.generatejavamodelfiles(bloqapmlmanager.listenermodels, "//listener");
            
            this.generatejavamodelfiles(bloqapmlmanager.objectmodels, "//object");
            
            this.generatejavamodelfiles(bloqapmlmanager.subscribermodels, "//subscriber");
            
            this.generatejavamodelfiles(bloqapmlmanager.systemmodels, "//system");         
        }
        catch(Exception exception)
        {
            logger.log(Level.WARNING, exception.getMessage(), exception);
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
                logger.log(Level.WARNING, exception.getMessage(), exception);
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
                case "//apml": this.apmlmanager.apmlmodels = apmlmodelfiles = apmlmodelpopulator.getapmlmodelfiles(apmlxmlfile, apmltag); break;
                
                case "//definitions": this.apmlmanager.definitionmodels = apmlmodelfiles = apmlmodelpopulator.getapmlmodelfiles(apmlxmlfile, apmltag); break;                    
                
                case "//dynamiclistener": this.apmlmanager.dynamiclistenermodels = apmlmodelfiles = apmlmodelpopulator.getapmlmodelfiles(apmlxmlfile, apmltag); break;                    
                
                case "//listener": this.apmlmanager.listenermodels = apmlmodelfiles = apmlmodelpopulator.getapmlmodelfiles(apmlxmlfile, apmltag); break;                    
                
                case "//object": this.apmlmanager.objectmodels = apmlmodelfiles = apmlmodelpopulator.getapmlmodelfiles(apmlxmlfile, apmltag); break;                    
                
                case "//subscriber": this.apmlmanager.subscribermodels = apmlmodelfiles = apmlmodelpopulator.getapmlmodelfiles(apmlxmlfile, apmltag); break;                    
                
                case "//system": this.apmlmanager.systemmodels = apmlmodelfiles = apmlmodelpopulator.getapmlmodelfiles(apmlxmlfile, apmltag); break;
            }            
        }
        catch(Exception exception)
        {
            logger.log(Level.WARNING, exception.getMessage(), exception);
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
                case "//apml": this.jcmmanager.apmlmodels = jcmmodels_genericfiles = jcmmodelpopulator.getjcmmodelfiles(apmlmodelfiles); break;
                    
                case "//definitions": this.jcmmanager.definitionmodels = jcmmodels_genericfiles = jcmmodelpopulator.getjcmmodelfiles(apmlmodelfiles); break;
                    
                case "//dynamiclistener": this.jcmmanager.dynamiclistenermodels = jcmmodels_genericfiles = jcmmodelpopulator.getjcmmodelfiles(apmlmodelfiles); break;                    
                    
                case "//listener": this.jcmmanager.listenermodels = jcmmodels_genericfiles = jcmmodelpopulator.getjcmmodelfiles(apmlmodelfiles); break;   
                    
                case "//object": this.jcmmanager.objectmodels = jcmmodels_genericfiles = jcmmodelpopulator.getjcmmodelfiles(apmlmodelfiles); break;                     
                    
                case "//subscriber": this.jcmmanager.subscribermodels = jcmmodels_genericfiles = jcmmodelpopulator.getjcmmodelfiles(apmlmodelfiles); break;                    
                    
                case "//system": this.jcmmanager.systemmodels = jcmmodels_genericfiles = jcmmodelpopulator.getjcmmodelfiles(apmlmodelfiles); break;                    
            }                     
        }
        catch(Exception exception)
        {
            logger.log(Level.WARNING, exception.getMessage(), exception);
        }
        
        return jcmmodels_genericfiles;
    }    
    
    public void dowritesourcetoharddrive()
    {
        try
        {
            this.dosetfinalsource(this.jcmmanager.apmlmodels);
            
            this.dosetfinalsource(this.jcmmanager.definitionmodels);
            
            this.dosetfinalsource(this.jcmmanager.dynamiclistenermodels);            
            
            this.dosetfinalsource(this.jcmmanager.listenermodels);
            
            this.dosetfinalsource(this.jcmmanager.objectmodels);
            
            this.dosetfinalsource(this.jcmmanager.subscribermodels);
            
            this.dosetfinalsource(this.jcmmanager.systemmodels);
            
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
                logger.log(Level.WARNING, exception.getMessage(), exception);
            }            
        }
        catch(Exception exception)
        {
            System.err.println(exception);
        }
    }   
    
    public void setsourcefiles(Bloqjcmmanager bloqjcmmanager)
    {
        dosetfinalsource(bloqjcmmanager.apmlmodels);
        
        dosetfinalsource(bloqjcmmanager.dynamiclistenermodels);                             
        
        dosetfinalsource(bloqjcmmanager.listenermodels);                    
        
        dosetfinalsource(bloqjcmmanager.objectmodels);
        
        dosetfinalsource(bloqjcmmanager.subscribermodels);                    
        
        dosetfinalsource(bloqjcmmanager.systemmodels);           
    }
        
    public void dosetfinalsource(ArrayList<JCodeModel> jcmmodels)
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
                logger.log(Level.WARNING, exception.getMessage(), exception);
            }                       
        }                
    }  
 
    public void setjarfile() throws Exception
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
