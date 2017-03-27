package apml.compilers;

import apml.compilers.java.codemodel.Bloqapmlmanager;

import apml.compilers.java.codemodel.Bloqfileguardian;

import apml.compilers.java.codemodel.Bloqjcodemodelmanager;

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
    protected final Integer hash = 0x00888fe8;     
    
    public Bloqapmlmanager apmlmanager      = new Bloqapmlmanager(); 
    
    public Bloqjcodemodelmanager jcmmanager = new Bloqjcodemodelmanager();
    
    public Bloqfileguardian fileguardian    = new Bloqfileguardian();

    protected static final Logger LOGGER    = Logger.getLogger(Bloqcompiler.class.getName());     
    
    public Standardabstractapmlcompiler()
    {            
        try
        {
            LOGGER.addHandler(new FileHandler(this.fileguardian.loggingfileurl));
            
            LOGGER.setUseParentHandlers(false);
            
            this.fileguardian.buildoutdir       = new File(fileguardian.basedirurl+fileguardian.projectextensionurl+fileguardian.buildextensionurl);
            
            this.fileguardian.sourceoutdir      = new File(fileguardian.basedirurl+fileguardian.projectextensionurl+fileguardian.srcextensionurl);                             
            
            this.fileguardian.manifestdir       = new File(fileguardian.basedirurl+fileguardian.projectextensionurl+fileguardian.manifestextensionurl);
            
            this.fileguardian.libsdir           = new File(fileguardian.basedirurl+fileguardian.projectextensionurl+fileguardian.libsdir);
            
            this.fileguardian.loggingdir        = new File(fileguardian.basedirurl+fileguardian.projectextensionurl+fileguardian.loggingextensionurl);
            
            this.fileguardian.tempdir           = new File(fileguardian.basedirurl+fileguardian.projectextensionurl+fileguardian.tempdir);
            
            this.fileguardian.apmlinputfile     = new File(fileguardian.apmlinurl);                        
            
            if(!this.fileguardian.buildoutdir.exists())     this.fileguardian.buildoutdir.mkdirs();                                       
            
            if(!this.fileguardian.libsdir.exists())         this.fileguardian.libsdir.mkdirs();                                       
            
            if(!this.fileguardian.loggingdir.exists())      this.fileguardian.loggingdir.mkdirs();                                       
            
            if(!this.fileguardian.manifestdir.exists())     this.fileguardian.manifestdir.mkdirs();
            
            if(!this.fileguardian.sourceoutdir.exists())    this.fileguardian.sourceoutdir.mkdirs();                                               
            
            if(!this.fileguardian.tempdir.exists())         this.fileguardian.tempdir.mkdirs();
            
            if(!this.fileguardian.apmlinputfile.exists())   throw new Exception("ApmlTagHandler::constructor:Could not find the system's APML file");
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
    public abstract void setsourcefiles(Bloqjcodemodelmanager astmanager);  
}
