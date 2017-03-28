package apml.compilers;

import apml.compilers.java.codemodel.Bloqinputmanager;

import apml.compilers.java.codemodel.Bloqfileguardian;

import apml.compilers.java.codemodel.Bloqoutputmanager;

import java.io.File;

/**
 *
 * @author Max Rupplin
 */
public abstract class Standardabstractapmlcompiler 
{        
    protected final Integer hash = 0x00888fe8;     
        
    
    public Bloqfileguardian fileguardian    = new Bloqfileguardian(); //todo can we fix temporal dependency
    
    public Bloqinputmanager inputmanager      = new Bloqinputmanager(); 
    
    public Bloqoutputmanager outputmanager = new Bloqoutputmanager();               
    
    
    public Standardabstractapmlcompiler()
    {            
        try
        {                       
            /* --------------------------- New Files ---------------------------*/
            
            this.fileguardian.apmlinputfile         = new File(fileguardian.apmlinurl+fileguardian.apmlfilename);  
            
            this.fileguardian.buildoutdirfile       = new File(fileguardian.basedirurl+fileguardian.projectextensionurl+fileguardian.buildextensionurl);
            
            this.fileguardian.sourceoutdirfile      = new File(fileguardian.basedirurl+fileguardian.projectextensionurl+fileguardian.srcextensionurl);                             
            
            this.fileguardian.manifestdirfile       = new File(fileguardian.basedirurl+fileguardian.projectextensionurl+fileguardian.manifestextensionurl);
            
            this.fileguardian.libsdirfile           = new File(fileguardian.basedirurl+fileguardian.projectextensionurl+fileguardian.libsextensionurl);
            
            this.fileguardian.loggingdirfile        = new File(fileguardian.basedirurl+fileguardian.projectextensionurl+fileguardian.loggingextensionurl);
            
            this.fileguardian.tempdirfile           = new File(fileguardian.basedirurl+fileguardian.projectextensionurl+fileguardian.tempextensionurl);
            
            
            /* -------------------------- Mkdir calls --------------------------*/                      
            
            if(!this.fileguardian.buildoutdirfile.exists())     this.fileguardian.buildoutdirfile.mkdirs();                                       
            
            if(!this.fileguardian.libsdirfile.exists())         this.fileguardian.libsdirfile.mkdirs();                                       
            
            if(!this.fileguardian.loggingdirfile.exists())      this.fileguardian.loggingdirfile.mkdirs();                                       
            
            if(!this.fileguardian.manifestdirfile.exists())     this.fileguardian.manifestdirfile.mkdirs();
            
            if(!this.fileguardian.sourceoutdirfile.exists())    this.fileguardian.sourceoutdirfile.mkdirs();                                               
            
            if(!this.fileguardian.tempdirfile.exists())         this.fileguardian.tempdirfile.mkdirs();
            
            if(!this.fileguardian.apmlinputfile.exists())       throw new Exception("ApmlTagHandler::constructor:Could not find the system's APML file");
        }
        catch(Exception exception)
        {
            exception.printStackTrace(System.err);
        } 
    }
    
    /**
     * Read in the APML system specification file and store in Bloqinputmanager reference
     * 
     * @param fileguardian 
     */
    public abstract void setapmlfiles(Bloqfileguardian fileguardian);
    
    /**
     * Read partial specification of Java source code from APML system specification and produce temporary Java byte code [JCM requires for import inferencing]
     * 
     * @param apmlmanager 
     */
    public abstract void settempfiles(Bloqinputmanager apmlmanager);
    
    /**
     * Having read APML file and created all required temporary files we create the files/models for final output files
     * 
     * @param apmlmanager 
     */
    public abstract void setoutputfiles(Bloqinputmanager apmlmanager);
    
    /**
     * Handle outputting of files/models to disk, write out source models to computer hard drive
     * 
     * @param astmanager 
     */
    public abstract void setsourcefiles(Bloqoutputmanager astmanager);  
}
