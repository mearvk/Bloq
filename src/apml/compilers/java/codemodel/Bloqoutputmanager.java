package apml.compilers.java.codemodel;

import apml.helpers.Filegrepper;
import apml.modeling.Apmlmodelfile;
import apml.system.bodi.Bodi;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JPackage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Max Rupplin
 * @since 03.28.2017
 */
public class Bloqoutputmanager
{
	public static final Logger LOGGER = Logger.getLogger(Bloqoutputmanager.class.getName());

    /*--------------------------------------------------------------------------*/
	private final Integer hash = 0x00888fe8;
	public ArrayList<JCodeModel> factorymodels;
	public ArrayList<JCodeModel> apmlmodels;
	public ArrayList<JCodeModel> callbackmodels;
	public ArrayList<JCodeModel> definitionmodels;
	public ArrayList<JCodeModel> dynamiclistenermodels;
	public ArrayList<JCodeModel> listenermodels;
	public ArrayList<JCodeModel> objectmodels;
	public ArrayList<JCodeModel> subscribermodels;
    
    /*--------------------------------------------------------------------------*/
	public ArrayList<JCodeModel> systemmodels;
    
    /*--------------------------------------------------------------------------*/

	//
	public Bloqoutputmanager()
	{
		Bodi.setcontext("system");

		try
		{
			Bodi.context("system").put("bloqjcodemodelmanager", this);
		}
		catch (Exception e)
		{

		}

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

	//
	public ArrayList<JCodeModel> dosetoutputfiles(ArrayList<Apmlmodelfile> apmlmodelfiles, String apmltag)
	{
		Bloqjcmpopulator jcmmodelpopulator = new Bloqjcmpopulator();

		ArrayList<JCodeModel> jcmmodels_genericfiles = null;

		Bloqoutputmanager jcmmanager = (Bloqoutputmanager) Bodi.context("system").pull("bloqjcodemodelmanager");

		try
		{
			switch (apmltag)
			{
				case "//events":
					jcmmanager.apmlmodels = jcmmodels_genericfiles = jcmmodelpopulator.getjcmmodelfiles(apmlmodelfiles);
					break;

				case "//callback":
					jcmmanager.callbackmodels = jcmmodels_genericfiles = jcmmodelpopulator.getjcmmodelfiles(apmlmodelfiles);
					break;

				case "//definitions":
					jcmmanager.definitionmodels = jcmmodels_genericfiles = jcmmodelpopulator.getjcmmodelfiles(apmlmodelfiles);
					break;

				case "//dynamiclistener":
					jcmmanager.dynamiclistenermodels = jcmmodels_genericfiles = jcmmodelpopulator.getjcmmodelfiles(apmlmodelfiles);
					break;

				case "//listener":
					jcmmanager.listenermodels = jcmmodels_genericfiles = jcmmodelpopulator.getjcmmodelfiles(apmlmodelfiles);
					break;

				case "//object":
					jcmmanager.objectmodels = jcmmodels_genericfiles = jcmmodelpopulator.getjcmmodelfiles(apmlmodelfiles);
					break;

				case "//subscriber":
					jcmmanager.subscribermodels = jcmmodels_genericfiles = jcmmodelpopulator.getjcmmodelfiles(apmlmodelfiles);
					break;

				case "//system":
					jcmmanager.systemmodels = jcmmodels_genericfiles = jcmmodelpopulator.getjcmmodelfiles(apmlmodelfiles);
					break;
			}
		}
		catch (Exception e)
		{
            /*LOGGER.log(Level.WARNING, e.getMessage(), e);*/
		}

		return jcmmodels_genericfiles;
	}

	//
	public void dosetsourcefiles(ArrayList<JCodeModel> jcmmodels)
	{
		if (jcmmodels == null)
			return;

		Bloqfileguardian fileguardian = (Bloqfileguardian) Bodi.context("system").pull("bloqfileguardian");

		for (int i = 0; i < jcmmodels.size(); i++)
		{
			try
			{
				Iterator<JPackage> packages = jcmmodels.get(i).packages();

				while (packages.hasNext())
				{
					JPackage jpackage = packages.next();

					Iterator<JDefinedClass> classes = jpackage.classes();

					String pname = jpackage.name();

					while (classes.hasNext())
					{
						String cname = classes.next().name();

						//

						if (!new File(fileguardian.basedirurl + fileguardian.projectextensionurl + fileguardian.srcextensionurl).exists())
						{
							new File(fileguardian.basedirurl + fileguardian.projectextensionurl + fileguardian.srcextensionurl).mkdirs();
						}

						//

						jcmmodels.get(i).build(new File(fileguardian.basedirurl + fileguardian.projectextensionurl + fileguardian.srcextensionurl), System.out);
					}
				}
			}
			catch (Exception e)
			{
                /*LOGGER.log(Level.WARNING, e.getMessage(), e);*/

				e.printStackTrace();
			}
		}
	}

	//
	public void dowriteclassfiles(ArrayList<Apmlmodelfile> apmlmodelfiles)
	{
		if (apmlmodelfiles == null)
			return;

		Bloqfileguardian fileguardian = (Bloqfileguardian) Bodi.context("system").pull("bloqfileguardian");

		for (int i = 0; i < apmlmodelfiles.size(); i++)
		{
			Apmlmodelfile model = apmlmodelfiles.get(i);

			try
			{
				String pathname = new Filegrepper().getpackagenameaspathname(model.packagename) + "/";

				//

				String javac = "javac " + fileguardian.basedirurl + fileguardian.projectextensionurl + fileguardian.tempextensionurl + pathname + model.classname + ".java -d " + fileguardian.basedirurl + fileguardian.projectextensionurl + fileguardian.buildextensionurl;

				//

				Runtime runtime;

				runtime = Runtime.getRuntime();

				runtime.exec(javac);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				System.gc();
			}
		}
	}
}