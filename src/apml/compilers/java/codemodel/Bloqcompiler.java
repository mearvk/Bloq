package apml.compilers.java.codemodel;

import static java.nio.file.StandardCopyOption.*;

import apml.compilers.Standardabstractapmlcompiler;

import apml.helpers.Filegrepper;

import apml.modeling.Apmlmodelfile;

import com.sun.codemodel.JCodeModel;

import com.sun.codemodel.JDefinedClass;

import com.sun.codemodel.JPackage;

import java.nio.file.Files;

import java.util.ArrayList;

import java.util.Iterator;

import java.io.File;

/**
 * 
 * @author Max Rupplin
 */
public class Bloqcompiler extends Standardabstractapmlcompiler
{        
    protected final Integer hash = 0x888fe8;                  
               
    public Bloqcompiler()
    {                        
        //moved to common abstract compiler
    }          
        
    @Override
    public void setapmlfiles(Bloqfileguardian fileguardian)
    {
        try
        {            
            this.dosetapmlfiles(fileguardian.apmlxmlinputfile, "//apml");
            
            this.dosetapmlfiles(fileguardian.apmlxmlinputfile, "//dynamiclistener");
            
            this.dosetapmlfiles(fileguardian.apmlxmlinputfile, "//listener");    
            
            this.dosetapmlfiles(fileguardian.apmlxmlinputfile, "//object");   
            
            this.dosetapmlfiles(fileguardian.apmlxmlinputfile, "//subscriber");
            
            this.dosetapmlfiles(fileguardian.apmlxmlinputfile, "//system");        
        }
        catch(Exception exception)
        {
            exception.printStackTrace(System.err);
        }
    }    
    
    @Override
    public void settempfiles(Bloqapmlmanager bloqapmlmanager)
    {
        try
        {
            this.dosettempfiles(bloqapmlmanager.apmlmodels);
            
            this.dosettempfiles(bloqapmlmanager.dynamiclistenermodels);
            
            this.dosettempfiles(bloqapmlmanager.listenermodels);
            
            this.dosettempfiles(bloqapmlmanager.objectmodels);
            
            this.dosettempfiles(bloqapmlmanager.subscribermodels);
            
            this.dosettempfiles(bloqapmlmanager.systemmodels);
        }
        catch(Exception exception)
        {
            exception.printStackTrace(System.err);
        }
    }
        
    @Override
    public void setoutputfiles(Bloqapmlmanager bloqapmlmanager)
    {        
        try
        {                        
            this.dosetoutputfiles(bloqapmlmanager.apmlmodels, "//apml");
            
            this.dosetoutputfiles(bloqapmlmanager.dynamiclistenermodels, "//dynamiclistener");
            
            this.dosetoutputfiles(bloqapmlmanager.listenermodels, "//listener");
            
            this.dosetoutputfiles(bloqapmlmanager.objectmodels, "//object");
            
            this.dosetoutputfiles(bloqapmlmanager.subscribermodels, "//subscriber");
            
            this.dosetoutputfiles(bloqapmlmanager.systemmodels, "//system");         
        }
        catch(Exception exception)
        {
            exception.printStackTrace(System.err);
        }
    }
    
    @Override
    public void setsourcefiles(Bloqjcodemodelmanager bloqjcmmanager)
    {
        this.dosetsourcefiles(bloqjcmmanager.apmlmodels);
        
        this.dosetsourcefiles(bloqjcmmanager.dynamiclistenermodels);                             
        
        this.dosetsourcefiles(bloqjcmmanager.listenermodels);                    
        
        this.dosetsourcefiles(bloqjcmmanager.objectmodels);
        
        this.dosetsourcefiles(bloqjcmmanager.subscribermodels);                    
        
        this.dosetsourcefiles(bloqjcmmanager.systemmodels);           
    }    
    
    private ArrayList<Apmlmodelfile> dosetapmlfiles(File apmlxmlfile, String apmltag)
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
            exception.printStackTrace(System.err);
        }
        
        return apmlmodelfiles;
    }  
    
    private ArrayList<JCodeModel> dosetoutputfiles(ArrayList<Apmlmodelfile> apmlmodelfiles, String apmltag)
    {
        Bloqpopulatorimpl jcmmodelpopulator = new Bloqpopulatorimpl();
        
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
            exception.printStackTrace(System.err);
        }
        
        return jcmmodels_genericfiles;
    }    
    
    private void dosetsourcefiles(ArrayList<JCodeModel> jcmmodels)
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
                exception.printStackTrace(System.err);
            }                       
        }                
    }    
    
    private void dosettempfiles(ArrayList<Apmlmodelfile> apmlmodelfiles) 
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
                exception.printStackTrace(System.err);
            }
            finally
            {
                System.gc();
            }
        }
    }                    
    
    public void dowritesourcetoharddrive()
    {
        try
        {
            this.dosetsourcefiles(this.jcmmanager.apmlmodels);
            
            this.dosetsourcefiles(this.jcmmanager.definitionmodels);
            
            this.dosetsourcefiles(this.jcmmanager.dynamiclistenermodels);            
            
            this.dosetsourcefiles(this.jcmmanager.listenermodels);
            
            this.dosetsourcefiles(this.jcmmanager.objectmodels);
            
            this.dosetsourcefiles(this.jcmmanager.subscribermodels);
            
            this.dosetsourcefiles(this.jcmmanager.systemmodels);
            
            try
            {
                if(new File(fileguardian.apmloutjarurl).exists())
                {
                    Files.copy(new File(fileguardian.apmlinjarurl+fileguardian.apmlfilename).toPath(), new File(fileguardian.apmloutjarurl+fileguardian.apmlfilename).toPath(), REPLACE_EXISTING );
                }
                else new File(fileguardian.apmloutjarurl).mkdirs();
                {
                    Files.copy(new File(fileguardian.apmlinjarurl+fileguardian.apmlfilename).toPath(), new File(fileguardian.apmloutjarurl+fileguardian.apmlfilename).toPath(), REPLACE_EXISTING );                
                }
            }
            catch(Exception exception)
            {
                exception.printStackTrace(System.err);
            }            
        }
        catch(Exception exception)
        {
            exception.printStackTrace(System.err);
        }
    }       
          
    public void setjarfile() throws Exception
    {
        if(new File(fileguardian.apmloutjarurl).exists())
        {
            Files.copy(new File(fileguardian.apmlinjarurl+fileguardian.apmlfilename).toPath(),new File(fileguardian.apmloutjarurl+fileguardian.apmlfilename).toPath(),REPLACE_EXISTING);
        }
        else new File(fileguardian.apmloutjarurl).mkdirs();
        {
            Files.copy(new File(fileguardian.apmlinjarurl+fileguardian.apmlfilename).toPath(),new File(fileguardian.apmloutjarurl+fileguardian.apmlfilename).toPath(),REPLACE_EXISTING);                
        }        
    }
}

class Localdriver
{    
    public static void main(String...args) 
    {                          
        try
        {
            Bloqcompiler bloqcompiler = new Bloqcompiler();
            
            bloqcompiler.setapmlfiles(bloqcompiler.fileguardian);
            
            bloqcompiler.settempfiles(bloqcompiler.apmlmanager);
            
            bloqcompiler.setoutputfiles(bloqcompiler.apmlmanager);         
            
            bloqcompiler.setsourcefiles(bloqcompiler.jcmmanager);
            
            bloqcompiler.setjarfile();
            
            System.gc();
        }
        catch(Exception exception)
        {
            exception.printStackTrace(System.err);
        }
    }
}