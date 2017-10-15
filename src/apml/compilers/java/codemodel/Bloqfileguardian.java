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
 * @since 03.28.2017
 */
public class Bloqfileguardian 
{
    private final Integer hash = 0x00888fe8;
    
    /*--------------------------------------------------------------------------*/
    
    public static final Logger LOGGER = Logger.getLogger(Bloqfileguardian.class.getName());
    
    /*--------------------------------------------------------------------------*/
    
    public Bloqfileguardian()
    {      
        Bodi.setcontext("system");
        
        try
        {
            Bodi.context("system").put("bloqfileguardian", this);
        }
        catch(Exception e)
        {
            
        }
        
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

	public final String apmljarfilename = "APML.jar";                                                                //name of the APML jar file to be written
	public final String apmlfilename = "apml_editor.xml";                                                                //APML source file
	public final String loggingfilename = "logging.txt";                                                                //logging output file
	public final String manifestfilename = "manifest.txt";                                                            //manifest output file
	public final String apmlinjarurl = "/Users/mrupplin/IdeaProjects/bloq/maxrupplin/development/bloq/in/jar/";        //where project jar file is supposed to exist
	public final String apmloutjarurl = "/Users/mrupplin/IdeaProjects/bloq/maxrupplin/development/bloq/out/";            //where project jar file is supposed to be written
	public final String apmlinurl = "/Users/mrupplin/IdeaProjects/bloq/maxrupplin/development/bloq/apml_editor/in/";                //APML source file
	public final String basedirurl = "/Users/mrupplin/IdeaProjects/bloq/maxrupplin/development/bloq/apml_editor/";                    //where .xml or .apml file would be

	/* ------------------------------ File Names ------------------------------- */
	public final String loggingfileurl = "/Users/mrupplin/IdeaProjects/bloq/maxrupplin/development/logging/";        //base logging URL for files
	public final String manifestfileurl = "/Users/mrupplin/IdeaProjects/bloq/maxrupplin/development/manifest/";        //manifest URL for files
	public final String buildextensionurl = "build/";                                                                //output directory for .class files
	public final String libsextensionurl = "libs/";                                                                    //output directory for libs files

	/* -------------------------- Strings & URLs ------------------------------- */
	public final String loggingextensionurl = "logging/";                                                            //output directory for logging files
	public final String manifestextensionurl = "manifest/";                                                            //manifest extension for manifest documents
	public final String projectextensionurl = "project/";                                                            //project extension directory
	public final String srcextensionurl = "source/";                                                                    //output directory for final .java files
	public final String tempextensionurl = "temp/";                                                                    //output directory for temporary files (partial builds, etc.)
	public File apmlinputfile = null;                                                                                //file for apml (an xml) system specification

	/* ------------------------------------------------------------------------- */
	public File buildoutdirfile = null;                                                                                //file dir for bytecode
	public File libsdirfile = null;                                                                                    //libs dir for byte code
	public File loggingdirfile = null;                                                                                //logging dir for byte code
	public File manifestfile = null;                                                                                    //file for manifest
	public File manifestdirfile = null;                                                                                //file dir for manifest
	public File sourceoutdirfile = null;                                                                                //file dir for source code
	public File tempdirfile = null;                                                                                    //temporary dir for byte code
}
