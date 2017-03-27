package apml.compilers.java.codemodel;

//import apml.compilers.Bloqabstractfileguardian;

import java.io.File;

/**
 *
 * @author Max Rupplin
 */
public class Bloqfileguardian 
{
    private final Integer hash = 0x00888fe8;  
    
    /* ---------------------------- File Instances ----------------------------- */
    
    public File apmlinputfile = null; //file for ampl (xml) system specification
    
    public File buildoutdir = null; //file dir for bytecode
    
    public File manifestfile = null; //file for manifest 
    
    public File manifestdir = null; //file dir for manifest
    
    public File sourceoutdir = null; //file dir for source code
    
    public File tempdir = null; //temporary dir for byte code
    
    public File libsdir = null; //libs dir for byte code
    
    public File loggingdir = null; //logging dir for byte code
            
    /* ---------------------------- Strings & URLs ----------------------------- */
    
    public final String apmlfilename = "APML.jar"; //name of the APML jar file to be written
    
    public final String apmlinjarurl = "/home/oem/NetBeansProjects/APML/dist/"; //where project jar file is supposed to exist
    
    public final String apmloutjarurl = "/home/oem/Desktop/Apml/output/libs/"; //where project jar file is supposed to be written
    
    public final String apmlinurl = "/home/oem/NetBeansProjects/APML/src/apml/examples/echoserver/server/echoserver.xml"; //APML source file 
    
    public final String basedirurl = "/home/oem/Desktop/Apml/output/"; //where .xml or .apml file would be
    
    public final String builddirurl = "build/"; //output directory for .class files
    
    public final String projectdirurl = "echo/"; //project extension directory        
    
    public final String libsdirurl = "libs/"; //output directory for libs files
    
    public final String loggingfileurl = "/home/oem/Desktop/Apml/output/logging/logging.txt"; //base logging URL for files
    
    public final String loggingdirurl = "logging/"; //output directory for logging files
    
    public final String srcdirurl = "source/"; //output directory for final .java files
    
    public final String tempsrcdirurl = "temp/"; //output directory for temporary files (partial builds, etc.)
    
    public final String manifestdirurl = "manifest/"; //manifest extension for manifest documents
    
    public final String manifestfileurl = "/home/oem/Desktop/Apml/output/manifest/manifest.txt"; //manifest URL for files        
}
