package apml.compilers.java.codemodel;

import static java.nio.file.StandardCopyOption.*;

import apml.compilers.Standardabstractapmlcompiler;

import apml.system.bodi.Bodi;

import java.nio.file.Files;

import java.io.File;

import java.io.IOException;

import java.util.logging.FileHandler;

import java.util.logging.Level;

/**
 * 
 * @author Max Rupplin
 */
public class Bloqcompiler extends Standardabstractapmlcompiler
{        
    private final Integer hash = 0x00888fe8;                    
               
    public Bloqcompiler()
    {                        
        Bodi.setcontext("system");
        
        Bodi.context("system").put("bloqcompiler", this);
        
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
        
    @Override
    public void setapmlfiles(Bloqfileguardian fileguardian)
    {
        try
        {            
            this.apmlmanager.dosetapmlfiles(fileguardian.apmlinputfile, "//apml");
            
            this.apmlmanager.dosetapmlfiles(fileguardian.apmlinputfile, "//dynamiclistener");
            
            this.apmlmanager.dosetapmlfiles(fileguardian.apmlinputfile, "//listener");    
            
            this.apmlmanager.dosetapmlfiles(fileguardian.apmlinputfile, "//object");   
            
            this.apmlmanager.dosetapmlfiles(fileguardian.apmlinputfile, "//subscriber");
            
            this.apmlmanager.dosetapmlfiles(fileguardian.apmlinputfile, "//system");        
        }
        catch(Exception e)
        {
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        }
    }    
    
    @Override
    public void settempfiles(Bloqapmlmanager bloqapmlmanager)
    {
        try
        {
            this.apmlmanager.dosettempfiles(bloqapmlmanager.apmlmodels);
            
            this.apmlmanager.dosettempfiles(bloqapmlmanager.dynamiclistenermodels);
            
            this.apmlmanager.dosettempfiles(bloqapmlmanager.listenermodels);
            
            this.apmlmanager.dosettempfiles(bloqapmlmanager.objectmodels);
            
            this.apmlmanager.dosettempfiles(bloqapmlmanager.subscribermodels);
            
            this.apmlmanager.dosettempfiles(bloqapmlmanager.systemmodels);
        }
        catch(Exception e)
        {
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        }
    }
        
    @Override
    public void setoutputfiles(Bloqapmlmanager bloqapmlmanager)
    {        
        try
        {                        
            this.jcmmanager.dosetoutputfiles(bloqapmlmanager.apmlmodels, "//apml");
            
            this.jcmmanager.dosetoutputfiles(bloqapmlmanager.dynamiclistenermodels, "//dynamiclistener");
            
            this.jcmmanager.dosetoutputfiles(bloqapmlmanager.listenermodels, "//listener");
            
            this.jcmmanager.dosetoutputfiles(bloqapmlmanager.objectmodels, "//object");
            
            this.jcmmanager.dosetoutputfiles(bloqapmlmanager.subscribermodels, "//subscriber");
            
            this.jcmmanager.dosetoutputfiles(bloqapmlmanager.systemmodels, "//system");         
        }
        catch(Exception e)
        {
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        }
    }
    
    @Override
    public void setsourcefiles(Bloqjcodemodelmanager bloqjcmmanager)
    {
        try
        {
            this.jcmmanager.dosetsourcefiles(bloqjcmmanager.apmlmodels);

            this.jcmmanager.dosetsourcefiles(bloqjcmmanager.dynamiclistenermodels);                             

            this.jcmmanager.dosetsourcefiles(bloqjcmmanager.listenermodels);                    

            this.jcmmanager.dosetsourcefiles(bloqjcmmanager.objectmodels);

            this.jcmmanager.dosetsourcefiles(bloqjcmmanager.subscribermodels);                    

            this.jcmmanager.dosetsourcefiles(bloqjcmmanager.systemmodels);           
        }
        catch(Exception e)
        {
            LOGGER.log(Level.WARNING, e.getMessage(), e);
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
        catch(Exception e)
        {
            Bloqcompiler.LOGGER.log(Level.WARNING, e.getMessage(), e);
        }
    }
}