package apml.compilers;

import apml.compilers.java.codemodel.Bloqapmlmanager;

import apml.compilers.java.codemodel.Bloqfileguardian;

import apml.compilers.java.codemodel.Bloqjcmmanager;

import apml.compilers.java.codemodel.Bloqcompiler;

import java.io.File;

import java.util.logging.FileHandler;

import java.util.logging.Logger;

/**
 *
 * @author Max Rupplin
 */
public abstract class Standardabstractapmlcompiler 
{        
    protected final Integer hash = 0x0888fe8;     
    
    public Bloqapmlmanager apmlmanager = new Bloqapmlmanager(); 
    
    public Bloqjcmmanager jcmmanager = new Bloqjcmmanager();
    
    public Bloqfileguardian fileguardian = new Bloqfileguardian();

    protected static final Logger LOGGER = Logger.getLogger(Bloqcompiler.class.getName());     
    
    public Standardabstractapmlcompiler()
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
            exception.printStackTrace(System.err);
        } 
    }
    
    /**
     * Read in the APML system specification file and store in Bloqapmlmanager reference
     * 
     * @param fileguardian 
     */
    public abstract void setapmlfiles(Bloqfileguardian fileguardian);
    
    /**
     * Read partial specification of Java source code from APML system specification and produce temporary Java byte code [JCM requires for import inferencing]
     * 
     * @param apmlmanager 
     */
    public abstract void settempfiles(Bloqapmlmanager apmlmanager);
    
    /**
     * Having read APML file and created all required temporary files we create the files/models for final output files
     * 
     * @param apmlmanager 
     */
    public abstract void setoutputfiles(Bloqapmlmanager apmlmanager);
    
    /**
     * Handle outputting of files/models to disk, write out source models to computer hard drive
     * 
     * @param astmanager 
     */
    public abstract void setsourcefiles(Bloqjcmmanager astmanager);  
}
