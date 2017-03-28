package apml.compilers.java.codemodel;

import static java.nio.file.StandardCopyOption.*;

import apml.compilers.Standardabstractapmlcompiler;

import apml.system.bodi.Bodi;

import java.nio.file.Files;

import java.io.File;
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
            this.apmlmanager.dosetoutputfiles(bloqapmlmanager.apmlmodels, "//apml");
            
            this.apmlmanager.dosetoutputfiles(bloqapmlmanager.dynamiclistenermodels, "//dynamiclistener");
            
            this.apmlmanager.dosetoutputfiles(bloqapmlmanager.listenermodels, "//listener");
            
            this.apmlmanager.dosetoutputfiles(bloqapmlmanager.objectmodels, "//object");
            
            this.apmlmanager.dosetoutputfiles(bloqapmlmanager.subscribermodels, "//subscriber");
            
            this.apmlmanager.dosetoutputfiles(bloqapmlmanager.systemmodels, "//system");         
        }
        catch(Exception e)
        {
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        }
    }
    
    @Override
    public void setsourcefiles(Bloqjcodemodelmanager bloqjcmmanager)
    {
        this.apmlmanager.dosetsourcefiles(bloqjcmmanager.apmlmodels);
        
        this.apmlmanager.dosetsourcefiles(bloqjcmmanager.dynamiclistenermodels);                             
        
        this.apmlmanager.dosetsourcefiles(bloqjcmmanager.listenermodels);                    
        
        this.apmlmanager.dosetsourcefiles(bloqjcmmanager.objectmodels);
        
        this.apmlmanager.dosetsourcefiles(bloqjcmmanager.subscribermodels);                    
        
        this.apmlmanager.dosetsourcefiles(bloqjcmmanager.systemmodels);           
    }    
    
                   
    
    public void dowritesourcetoharddrive()
    {
        try
        {
            this.apmlmanager.dosetsourcefiles(this.jcmmanager.apmlmodels);
            
            this.apmlmanager.dosetsourcefiles(this.jcmmanager.definitionmodels);
            
            this.apmlmanager.dosetsourcefiles(this.jcmmanager.dynamiclistenermodels);            
            
            this.apmlmanager.dosetsourcefiles(this.jcmmanager.listenermodels);
            
            this.apmlmanager.dosetsourcefiles(this.jcmmanager.objectmodels);
            
            this.apmlmanager.dosetsourcefiles(this.jcmmanager.subscribermodels);
            
            this.apmlmanager.dosetsourcefiles(this.jcmmanager.systemmodels);
            
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
            catch(Exception e)
            {
                LOGGER.log(Level.WARNING, e.getMessage(), e);
            }            
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