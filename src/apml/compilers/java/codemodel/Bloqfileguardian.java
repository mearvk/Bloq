package apml.compilers.java.codemodel;

import apml.system.bodi.Bodi;

import java.io.File;

import java.io.IOException;

import java.util.logging.FileHandler;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Max Rupplin
 */
public class Bloqfileguardian 
{
    private final Integer hash = 0x00888fe8;
    
    
    public static final Logger LOGGER = Logger.getLogger(Bloqfileguardian.class.getName());
    
    
    public Bloqfileguardian()
    {      
        Bodi.setcontext("system");
        
        Bodi.context("system").put("bloqfileguardian", this);
        
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
            
    /* ---------------------------- File Instances ----------------------------- */
    
    public File apmlinputfile = null; //file for ampl (xml) system specification
    
    public File buildoutdirfile = null; //file dir for bytecode
    
    public File libsdirfile = null; //libs dir for byte code
    
    public File loggingdirfile = null; //logging dir for byte code    
    
    public File manifestfile = null; //file for manifest 
    
    public File manifestdirfile = null; //file dir for manifest
    
    public File sourceoutdirfile = null; //file dir for source code
    
    public File tempdirfile = null; //temporary dir for byte code   
            
    /* ---------------------------- Strings & URLs ----------------------------- */
    
    public final String apmljarfilename = "APML.jar"; //name of the APML jar file to be written
    
    public final String apmlfilename = "echoserver.xml"; //APML source file
    
    public final String loggingfilename = "logging.txt";
    
    public final String manifestfilename = "manifest.txt";        
    
    /* ------------------------------------------------------------------------- */
    
    public final String apmlinjarurl = "/home/oem/NetBeansProjects/APML/dist/"; //where project jar file is supposed to exist
    
    public final String apmloutjarurl = "/home/oem/Desktop/Apml/output/libs/"; //where project jar file is supposed to be written    
    
    public final String apmlinurl = "/home/oem/NetBeansProjects/APML/src/apml/examples/echoserver/server/"; //APML source file 
    
    public final String basedirurl = "/home/oem/Desktop/Apml/output/"; //where .xml or .apml file would be
    
    public final String loggingfileurl = "/home/oem/Desktop/Apml/output/echo/logging/"; //base logging URL for files
    
    public final String manifestfileurl = "/home/oem/Desktop/Apml/output/manifest/"; //manifest URL for files      
    
    /* ------------------------------------------------------------------------- */
    
    public final String buildextensionurl = "build/"; //output directory for .class files
                
    public final String libsextensionurl = "libs/"; //output directory for libs files        
    
    public final String loggingextensionurl = "logging/"; //output directory for logging files                
    
    public final String manifestextensionurl = "manifest/"; //manifest extension for manifest documents          
    
    public final String projectextensionurl = "echo/"; //project extension directory   
    
    public final String srcextensionurl = "source/"; //output directory for final .java files
    
    public final String tempextensionurl = "temp/"; //output directory for temporary files (partial builds, etc.)
}
