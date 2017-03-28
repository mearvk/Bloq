package apml.compilers.java.codemodel;

import static apml.compilers.java.codemodel.Bloqpopulatorimpl.LOGGER;

import apml.helpers.Filegrepper;

import apml.modeling.Apmlmodelfile;

import apml.system.bodi.Bodi;

import com.sun.codemodel.JCodeModel;

import java.io.File;

import java.io.IOException;

import java.util.ArrayList;

import java.util.logging.FileHandler;

import java.util.logging.Level;

/**
 *
 * @author Max Rupplin
 */
public class Bloqapmlmanager
{   
    private final Integer hash = 0x00888fe8;  
    
    public ArrayList<Apmlmodelfile> apmlmodels;  
    
    public ArrayList<Apmlmodelfile> definitionmodels;
    
    public ArrayList<Apmlmodelfile> dynamiclistenermodels;
    
    public ArrayList<Apmlmodelfile> listenermodels;
    
    public ArrayList<Apmlmodelfile> objectmodels;
    
    public ArrayList<Apmlmodelfile> subscribermodels;
    
    public ArrayList<Apmlmodelfile> systemmodels;
    
    
    public Bloqapmlmanager()
    {
        Bodi.setcontext("system");
        
        Bodi.context("system").put("bloqapmlmanager", this);
        
        /*----------------------------------------------------------------------*/
        
        Bloqfileguardian fileguardian = (Bloqfileguardian)Bodi.context("system").pull("bloqfileguardian");
        
        try
        {
            LOGGER.addHandler(new FileHandler(fileguardian.loggingfileurl+fileguardian.loggingfilename));
            
            LOGGER.setUseParentHandlers(false);            
        }
        catch(IOException e)
        {
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        }        
    }
    
    
    public ArrayList<Apmlmodelfile> dosetapmlfiles(File apmlxmlfile, String apmltag)
    {        
        Apmlmodelpopulator apmlmodelpopulator = new Apmlmodelpopulator();
        
        ArrayList<Apmlmodelfile> apmlmodelfiles = null;
        
        try
        {
            switch(apmltag)
            {                    
                case "//apml": this.apmlmodels = apmlmodelfiles = apmlmodelpopulator.getapmlmodelfiles(apmlxmlfile, apmltag); break;
                
                case "//definitions": this.definitionmodels = apmlmodelfiles = apmlmodelpopulator.getapmlmodelfiles(apmlxmlfile, apmltag); break;                    
                
                case "//dynamiclistener": this.dynamiclistenermodels = apmlmodelfiles = apmlmodelpopulator.getapmlmodelfiles(apmlxmlfile, apmltag); break;                    
                
                case "//listener": this.listenermodels = apmlmodelfiles = apmlmodelpopulator.getapmlmodelfiles(apmlxmlfile, apmltag); break;                    
                
                case "//object": this.objectmodels = apmlmodelfiles = apmlmodelpopulator.getapmlmodelfiles(apmlxmlfile, apmltag); break;                    
                
                case "//subscriber": this.subscribermodels = apmlmodelfiles = apmlmodelpopulator.getapmlmodelfiles(apmlxmlfile, apmltag); break;                    
                
                case "//system": this.systemmodels = apmlmodelfiles = apmlmodelpopulator.getapmlmodelfiles(apmlxmlfile, apmltag); break;
            }            
        }
        catch(Exception e)
        {
            /*LOGGER.log(Level.WARNING, e.getMessage(), e);*/
        }
        
        return apmlmodelfiles;
    }  
    
    
    
    public void dosettempfiles(ArrayList<Apmlmodelfile> apmlmodelfiles) 
    {
        Bloqfileguardian fileguardian = (Bloqfileguardian)Bodi.context("system").pull("bloqfileguardian");
        
        for(Apmlmodelfile model: apmlmodelfiles) 
        {
            try
            {   
                JCodeModel jmodel;                
                
                Runtime runtime;
                
                String sourcepackagedir     = new Filegrepper().getpackagenameaspathname(model.packagename);
                
                String buildpackagedir      = new Filegrepper().getpackagenameaspathname(model.packagename);
                
                String pathname             = new Filegrepper().getpackagenameaspathname(model.packagename)+"/";
                
                String javac                = "javac "+fileguardian.basedirurl+fileguardian.tempextensionurl+pathname+model.classname+".java -d "+fileguardian.basedirurl+fileguardian.buildextensionurl;
                
                new File(fileguardian.basedirurl+fileguardian.tempextensionurl).mkdirs();
                
                new File(fileguardian.basedirurl+fileguardian.tempextensionurl+sourcepackagedir).mkdirs();               
                
                new File(fileguardian.basedirurl+fileguardian.buildextensionurl).mkdirs();
                
                new File(fileguardian.basedirurl+fileguardian.buildextensionurl).mkdirs();
                   
                jmodel = new JCodeModel();
                
                jmodel._package(model.packagename)._class(model.classname);
                
                jmodel.build(new File(fileguardian.basedirurl+fileguardian.tempextensionurl));
                
                runtime = Runtime.getRuntime();                                                
                
                runtime.exec(javac);
            }
            catch(Exception e)
            {
                /*LOGGER.log(Level.WARNING, e.getMessage(), e);*/
            }
            finally
            {
                System.gc();
            }
        }
    }     
}