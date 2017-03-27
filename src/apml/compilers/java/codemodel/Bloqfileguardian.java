package apml.compilers.java.codemodel;

//import apml.compilers.Bloqabstractfileguardian;

import java.io.File;

/**
 *
 * @author Max Rupplin
 */
public class Bloqfileguardian 
{
    /* ---------------------------- File Instances ----------------------------- */
    
    public File apmlxmlinputfile = null; //file for ampl (xml) system specification
    
    public File buildoutdir = null; //file dir for bytecode
    
    public File manifestfile = null; //file for manifest 
    
    public File manifestfiledir = null; //file dir for manifest
    
    public File sourceoutdir = null; //file dir for source code
            
    /* ---------------------------- Strings & URLs ----------------------------- */
    
    public final String apmlfilename = "APML.jar"; //name of the APML jar file to be written
    
    public final String apmlinjarurl = "/home/oem/NetBeansProjects/APML/dist/"; //where project jar file is supposed to exist
    
    public final String apmloutjarurl = "/home/oem/Desktop/Apml/output/libs/"; //where project jar file is supposed to be written
    
    public final String apmlinurl = "/home/oem/NetBeansProjects/APML/src/apml/examples/echoserver/server/echoserver.xml"; //APML source file 
    
    public final String basedirurl = "/home/oem/Desktop/Apml/output/"; //where .xml or .apml file would be
    
    public final String projectdirurl = "echo/"; //project extension directory
    
    public final String builddirurl = "build/"; //output directory for .class files
    
    public final String srcdirurl = "source/"; //output directory for final .java files
    
    public final String tempsrcdirurl = "temp/"; //output directory for temporary files (partial builds, etc.)
    
    public final String manifestdirurl = "manifest/"; //manifest extension for manifest documents
    
    public final String manifestfileurl = "/home/oem/Desktop/apml/output/manifest/manifest.txt"; //manifest URL for files
}
