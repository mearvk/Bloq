package apml.compilers.java.codemodel;

import static java.nio.file.StandardCopyOption.*;

import apml.compilers.Standardabstractapmlcompiler;

import apml.system.bodi.Bodi;

import java.nio.file.Files;

import java.io.File;

import java.io.IOException;

import java.util.logging.FileHandler;

import java.util.logging.Level;

import java.util.logging.Logger;

/**
 * 
 * @author Max Rupplin
 * @since 03.28.2017
 */
public class Bloqcompiler extends Standardabstractapmlcompiler
{        
    private final Integer hash = 0x00888fe8;  
    
    /*--------------------------------------------------------------------------*/
    
    public static final Logger LOGGER = Logger.getLogger(Bloqcompiler.class.getName());
    
    /*--------------------------------------------------------------------------*/           
    
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
            this.inputmanager.dosetapmlfiles(fileguardian.apmlinputfile, "//apml");
            
            this.inputmanager.dosetapmlfiles(fileguardian.apmlinputfile, "//dynamiclistener");
            
            this.inputmanager.dosetapmlfiles(fileguardian.apmlinputfile, "//listener");    
            
            this.inputmanager.dosetapmlfiles(fileguardian.apmlinputfile, "//object");   
            
            this.inputmanager.dosetapmlfiles(fileguardian.apmlinputfile, "//subscriber");
            
            this.inputmanager.dosetapmlfiles(fileguardian.apmlinputfile, "//system");        
        }
        catch(Exception e)
        {
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        }
    }    
    
    @Override
    public void settempfiles(Bloqinputmanager bloqapmlmanager)
    {
        try
        {
            this.inputmanager.dosettempfiles(bloqapmlmanager.apmlmodels);
            
            this.inputmanager.dosettempfiles(bloqapmlmanager.dynamiclistenermodels);
            
            this.inputmanager.dosettempfiles(bloqapmlmanager.listenermodels);
            
            this.inputmanager.dosettempfiles(bloqapmlmanager.objectmodels);
            
            this.inputmanager.dosettempfiles(bloqapmlmanager.subscribermodels);
            
            this.inputmanager.dosettempfiles(bloqapmlmanager.systemmodels);
        }
        catch(Exception e)
        {
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        }
    }
        
    @Override
    public void setoutputfiles(Bloqinputmanager bloqapmlmanager)
    {        
        try
        {                        
            this.outputmanager.dosetoutputfiles(bloqapmlmanager.apmlmodels, "//apml");
            
            this.outputmanager.dosetoutputfiles(bloqapmlmanager.dynamiclistenermodels, "//dynamiclistener");
            
            this.outputmanager.dosetoutputfiles(bloqapmlmanager.listenermodels, "//listener");
            
            this.outputmanager.dosetoutputfiles(bloqapmlmanager.objectmodels, "//object");
            
            this.outputmanager.dosetoutputfiles(bloqapmlmanager.subscribermodels, "//subscriber");
            
            this.outputmanager.dosetoutputfiles(bloqapmlmanager.systemmodels, "//system");         
        }
        catch(Exception e)
        {
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        }
    }
    
    @Override
    public void setsourcefiles(Bloqoutputmanager bloqjcmmanager)
    {
        try
        {
            this.outputmanager.dosetsourcefiles(bloqjcmmanager.apmlmodels);

            this.outputmanager.dosetsourcefiles(bloqjcmmanager.dynamiclistenermodels);                             

            this.outputmanager.dosetsourcefiles(bloqjcmmanager.listenermodels);                    

            this.outputmanager.dosetsourcefiles(bloqjcmmanager.objectmodels);

            this.outputmanager.dosetsourcefiles(bloqjcmmanager.subscribermodels);                    

            this.outputmanager.dosetsourcefiles(bloqjcmmanager.systemmodels);           
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
            
            bloqcompiler.settempfiles(bloqcompiler.inputmanager);
            
            bloqcompiler.setoutputfiles(bloqcompiler.inputmanager);         
            
            bloqcompiler.setsourcefiles(bloqcompiler.outputmanager);
            
            bloqcompiler.setjarfile();
            
            System.gc();
        }
        catch(Exception e)
        {
            Bloqcompiler.LOGGER.log(Level.WARNING, e.getMessage(), e);
        }
    }
}