/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apml.compilers.java.codemodel;

import apml.helpers.Filegrepper;
import apml.modeling.Apmlmodelfile;
import apml.system.bodi.Bodi;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JPackage;
import java.io.File;

import java.util.ArrayList;
import java.util.Iterator;

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
    
    public ArrayList<JCodeModel> dosetoutputfiles(ArrayList<Apmlmodelfile> apmlmodelfiles, String apmltag)
    {
        Bloqpopulatorimpl jcmmodelpopulator = new Bloqpopulatorimpl();
        
        ArrayList<JCodeModel> jcmmodels_genericfiles = null;
                
        Bloqjcodemodelmanager jcmmanager = (Bloqjcodemodelmanager)Bodi.context("system").pull("bloqjcodemodelmanager");
        
        try
        {                        
            switch(apmltag)
            {
                case "//apml": jcmmanager.apmlmodels = jcmmodels_genericfiles = jcmmodelpopulator.getjcmmodelfiles(apmlmodelfiles); break;
                    
                case "//definitions": jcmmanager.definitionmodels = jcmmodels_genericfiles = jcmmodelpopulator.getjcmmodelfiles(apmlmodelfiles); break;
                    
                case "//dynamiclistener": jcmmanager.dynamiclistenermodels = jcmmodels_genericfiles = jcmmodelpopulator.getjcmmodelfiles(apmlmodelfiles); break;                    
                    
                case "//listener": jcmmanager.listenermodels = jcmmodels_genericfiles = jcmmodelpopulator.getjcmmodelfiles(apmlmodelfiles); break;   
                    
                case "//object": jcmmanager.objectmodels = jcmmodels_genericfiles = jcmmodelpopulator.getjcmmodelfiles(apmlmodelfiles); break;                     
                    
                case "//subscriber": jcmmanager.subscribermodels = jcmmodels_genericfiles = jcmmodelpopulator.getjcmmodelfiles(apmlmodelfiles); break;                    
                    
                case "//system": jcmmanager.systemmodels = jcmmodels_genericfiles = jcmmodelpopulator.getjcmmodelfiles(apmlmodelfiles); break;                    
            }                     
        }
        catch(Exception e)
        {
            /*LOGGER.log(Level.WARNING, e.getMessage(), e);*/
        }
        
        return jcmmodels_genericfiles;
    }    
    
    public void dosetsourcefiles(ArrayList<JCodeModel> jcmmodels)
    {        
        Bloqfileguardian fileguardian = (Bloqfileguardian)Bodi.context("system").pull("bloqfileguardian");
        
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
                        
                        jcmmodels.get(i).build(new File(fileguardian.basedirurl+fileguardian.projectextensionurl+fileguardian.srcextensionurl), System.err);           
                    }
                }
            }
            catch(Exception e)
            {
                /*LOGGER.log(Level.WARNING, e.getMessage(), e);*/
            }                       
        }                
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