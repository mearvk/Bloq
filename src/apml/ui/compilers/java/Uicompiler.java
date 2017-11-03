package apml.ui.compilers.java;

import apml.system.bodi.Bodi;
import com.sun.codemodel.JCodeModel;

import java.util.ArrayList;

/**
 * Primary class for actually running Bloq UI compilation; Localdriver class may call this class.
 *
 * @author Max Rupplin
 * @version Bloq 1.0
 * @since 04.30.2017
 */
public class Uicompiler
{
	protected final Integer hash = 0x00888FE8;

	//

	public Uifileguardian fileguardian = new Uifileguardian();

	public Uiinputmanager inputmanager = new Uiinputmanager(this);

	public Uioutputmanager outputmanager = new Uioutputmanager(this);

	/**
	 *
	 */
	public Uicompiler()
	{
		Bodi.setcontext("//bodi/version");

		try
		{
			Bodi.context("//bodi/version").put("version", "1.05 - Bodi Remote Version");

			Bodi.context("//bodi/version").put("since", "4/4/2017");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Handles the input files (APML/XML to JCM source) and moving from interpretive to actual JCM files for output finalization
	 *
	 * @param inputmanager Storage pointer for input objects
	 */
	public void dohandleinputfiles(Uiinputmanager inputmanager)
	{
		for (String xpathmodel : this.inputmanager.xpathstrings.list)
		{
			inputmanager.generatemodels(xpathmodel);
		}
	}

	/**
	 * Handles the output of the Bloq UI system; JCM files to actual output (persistent files on disk usually).
	 *
	 * @param outputmanager Storage pointer for output objects
	 */
	public void dohandleoutputfiles(Uioutputmanager outputmanager)
	{
		for (ArrayList<JCodeModel> jcmmodel : this.inputmanager.jcmmodels.list)
		{
			outputmanager.generatefiles(jcmmodel);
		}
	}
}