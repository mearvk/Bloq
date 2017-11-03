package apml.compilers.java.codemodel;

import apml.system.bodi.Bodi;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Max Rupplin
 * @since 03.28.2017
 */
public class Bloqfileguardian
{
	public static final Logger LOGGER = Logger.getLogger(Bloqfileguardian.class.getName());

	//
	private final Integer hash = 0x00888FE8;

	//
	public String apmljarfilename = "APML.jar";                                                                            //name of the APML jar file to be written

	// File Instances
	public String apmlfilename = "apml_editor.xml";                                                                        //name of the APML source file
	public String loggingfilename = "logging.txt";                                                                        //logging output file
	public String manifestfilename = "manifest.txt";                                                                    //manifest output file
	public String apmlinjarurl = "/Users/mrupplin/IdeaProjects/bloq/maxrupplin/development/bloq/in/jar/";                //where project jar file is supposed to exist
	public String apmloutjarurl = "/Users/mrupplin/IdeaProjects/bloq/maxrupplin/development/bloq/out/";                    //where project jar file is supposed to be written
	public String apmlinurl = "/Users/mrupplin/IdeaProjects/bloq/maxrupplin/development/bloq/apml_editor/in/";        //APML source file
	public String basedirurl = "/Users/mrupplin/IdeaProjects/bloq/maxrupplin/development/bloq/apml_editor/";            //where .xml or .events file would be
	public String loggingfileurl = "/Users/mrupplin/IdeaProjects/bloq/maxrupplin/development/logging/";                    //base logging URL for files

	// File Names
	public String manifestfileurl = "/Users/mrupplin/IdeaProjects/bloq/maxrupplin/development/manifest/";                //manifest URL for files
	public String buildextensionurl = "build/";                                                                            //output directory for .class files
	public String libsextensionurl = "libs/";                                                                            //output directory for libs files
	public String loggingextensionurl = "logging/";                                                                        //output directory for logging files

	// Strings & URLs
	public String manifestextensionurl = "manifest/";                                                                    //manifest extension for manifest documents
	public String projectextensionurl = "project/";                                                                        //project extension directory
	public String srcextensionurl = "source/";                                                                        //output directory for final .java files
	public String tempextensionurl = "temp/";                                                                            //output directory for temporary files (partial builds, etc.)
	public File apmlinputfile = null;                                                                                    //file for events (an xml) system specification
	public File buildoutdirfile = null;                                                                                    //file dir for bytecode

	// Files Types
	public File libsdirfile = null;                                                                                        //libs dir for byte code
	public File loggingdirfile = null;                                                                                        //logging dir for byte code
	public File manifestfile = null;                                                                                        //file for manifest
	public File manifestdirfile = null;                                                                                        //file dir for manifest
	public File sourceoutdirfile = null;                                                                                    //file dir for source code
	public File tempdirfile = null;                                                                                            //temporary dir for byte code

	public Bloqfileguardian()
	{
		Bodi.setcontext("system");

		try
		{
			Bodi.context("system").put("bloqfileguardian", this);
		}
		catch (Exception e)
		{

		}

		//

		Bloqfileguardian fileguardian = (Bloqfileguardian) Bodi.context("system").pull("bloqfileguardian");

		try
		{
			LOGGER.addHandler(new FileHandler(fileguardian.loggingfileurl + fileguardian.loggingfilename));

			LOGGER.setUseParentHandlers(false);
		}
		catch (IOException e)
		{
			LOGGER.log(Level.WARNING, e.getMessage(), e);
		}
	}
}
