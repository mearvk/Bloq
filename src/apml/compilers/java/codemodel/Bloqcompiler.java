package apml.compilers.java.codemodel;

import apml.compilers.Standardabstractapmlcompiler;
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
public class Bloqcompiler extends Standardabstractapmlcompiler
{
	public static final Logger LOGGER = Logger.getLogger(Bloqcompiler.class.getName());

    /*--------------------------------------------------------------------------*/
	private final Integer hash = 0x00888FE8;

    /*--------------------------------------------------------------------------*/

	public Bloqcompiler()
	{
		Bodi.setcontext("system");

		try
		{
			Bodi.context("system").put("bloqcompiler", this);
		}
		catch (Exception e)
		{

		}
        
        /*----------------------------------------------------------------------*/

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

	@Override
	public void setapmlfiles(Bloqfileguardian fileguardian)
	{
		try
		{
			this.inputmanager.dosetapmlfiles(fileguardian.apmlinputfile, "//driver");

			this.inputmanager.dosetapmlfiles(fileguardian.apmlinputfile, "//factory");

			this.inputmanager.dosetapmlfiles(fileguardian.apmlinputfile, "//events");

			this.inputmanager.dosetapmlfiles(fileguardian.apmlinputfile, "//callback");

			this.inputmanager.dosetapmlfiles(fileguardian.apmlinputfile, "//dynamiclistener");

			this.inputmanager.dosetapmlfiles(fileguardian.apmlinputfile, "//listener");

			this.inputmanager.dosetapmlfiles(fileguardian.apmlinputfile, "//object");

			this.inputmanager.dosetapmlfiles(fileguardian.apmlinputfile, "//subscriber");

			this.inputmanager.dosetapmlfiles(fileguardian.apmlinputfile, "//system");
		}
		catch (Exception e)
		{
			//LOGGER.log(Level.WARNING, e.getMessage(), e);

			e.printStackTrace();
		}
	}

	@Override
	public void settempfiles(Bloqinputmanager bloqapmlmanager)
	{
		try
		{
			this.inputmanager.dosettempfiles(bloqapmlmanager.apmlmodels);

			this.inputmanager.dosettempfiles(bloqapmlmanager.callbackmodels);

			this.inputmanager.dosettempfiles(bloqapmlmanager.drivermodels);

			this.inputmanager.dosettempfiles(bloqapmlmanager.dynamiclistenermodels);

			//this.inputmanager.dosettempfiles(bloqapmlmanager.eventmodels);

			//this.inputmanager.dosettempfiles(bloqapmlmanager.factorymodels);

			this.inputmanager.dosettempfiles(bloqapmlmanager.listenermodels);

			this.inputmanager.dosettempfiles(bloqapmlmanager.objectmodels);

			this.inputmanager.dosettempfiles(bloqapmlmanager.subscribermodels);

			this.inputmanager.dosettempfiles(bloqapmlmanager.systemmodels);
		}
		catch (Exception e)
		{
			//LOGGER.log(Level.WARNING, e.getMessage(), e);

			e.printStackTrace();
		}
	}

	@Override
	public void setoutputfiles(Bloqinputmanager bloqapmlmanager)
	{
		try
		{
			//this.outputmanager.dosetoutputfiles(bloqapmlmanager.callbackmodels, "//apml");

			this.outputmanager.dosetoutputfiles(bloqapmlmanager.callbackmodels, "//callback");

			this.outputmanager.dosetoutputfiles(bloqapmlmanager.drivermodels, "//driver");

			this.outputmanager.dosetoutputfiles(bloqapmlmanager.dynamiclistenermodels, "//dynamiclistener");

			this.outputmanager.dosetoutputfiles(bloqapmlmanager.apmlmodels, "//events");

			this.outputmanager.dosetoutputfiles(bloqapmlmanager.factorymodels, "//factory");

			this.outputmanager.dosetoutputfiles(bloqapmlmanager.listenermodels, "//listener");

			this.outputmanager.dosetoutputfiles(bloqapmlmanager.objectmodels, "//object");

			this.outputmanager.dosetoutputfiles(bloqapmlmanager.subscribermodels, "//subscriber");

			this.outputmanager.dosetoutputfiles(bloqapmlmanager.systemmodels, "//system");
		}
		catch (Exception e)
		{
			//LOGGER.log(Level.WARNING, e.getMessage(), e);

			e.printStackTrace();
		}
	}

	@Override
	public void setsourcefiles(Bloqoutputmanager bloqjcmmanager)
	{
		try
		{
			this.outputmanager.dosetsourcefiles(bloqjcmmanager.apmlmodels);

			this.outputmanager.dosetsourcefiles(bloqjcmmanager.callbackmodels);

			this.outputmanager.dosetsourcefiles(bloqjcmmanager.drivermodels);

			this.outputmanager.dosetsourcefiles(bloqjcmmanager.dynamiclistenermodels);

			//this.outputmanager.dosetsourcefiles(bloqjcmmanager.eventmodels);

			this.outputmanager.dosetsourcefiles(bloqjcmmanager.factorymodels);

			this.outputmanager.dosetsourcefiles(bloqjcmmanager.listenermodels);

			this.outputmanager.dosetsourcefiles(bloqjcmmanager.objectmodels);

			this.outputmanager.dosetsourcefiles(bloqjcmmanager.subscribermodels);

			this.outputmanager.dosetsourcefiles(bloqjcmmanager.systemmodels);
		}
		catch (Exception e)
		{
			//LOGGER.log(Level.WARNING, e.getMessage(), e);

			e.printStackTrace();
		}
	}

	public void writebytecode(Bloqinputmanager bloqapmlmanager)
	{
		try
		{
			this.outputmanager.dowriteclassfiles(bloqapmlmanager.apmlmodels);

			this.outputmanager.dowriteclassfiles(bloqapmlmanager.callbackmodels);

			this.outputmanager.dowriteclassfiles(bloqapmlmanager.drivermodels);

			this.outputmanager.dowriteclassfiles(bloqapmlmanager.dynamiclistenermodels);

			//this.outputmanager.dowriteclassfiles(bloqapmlmanager.eventmodels);

			this.outputmanager.dowriteclassfiles(bloqapmlmanager.factorymodels);

			this.outputmanager.dowriteclassfiles(bloqapmlmanager.listenermodels);

			this.outputmanager.dowriteclassfiles(bloqapmlmanager.objectmodels);

			this.outputmanager.dowriteclassfiles(bloqapmlmanager.subscribermodels);

			this.outputmanager.dowriteclassfiles(bloqapmlmanager.systemmodels);
		}
		catch (Exception e)
		{
			//LOGGER.log(Level.WARNING, e.getMessage(), e);

			e.printStackTrace();
		}
	}

	public void writejarfile() throws Exception
	{
		if (new File(fileguardian.apmlinjarurl).exists() && new File(fileguardian.apmloutjarurl).exists())
		{
			//Files.copy(new File(fileguardian.apmlinjarurl+fileguardian.apmlfilename).toPath(),new File(fileguardian.apmloutjarurl+fileguardian.apmlfilename).toPath(),REPLACE_EXISTING);
		}
		else
		{
			try
			{
				if (!new File(fileguardian.apmlinjarurl).exists())
				{
					new File(fileguardian.apmlinjarurl).mkdirs();
				}

				if (!new File(fileguardian.apmloutjarurl).exists())
				{
					new File(fileguardian.apmloutjarurl).mkdirs();
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				//Files.copy(new File(fileguardian.apmlinjarurl+fileguardian.apmlfilename).toPath(),new File(fileguardian.apmloutjarurl+fileguardian.apmlfilename).toPath(),REPLACE_EXISTING);
			}
		}
	}
}