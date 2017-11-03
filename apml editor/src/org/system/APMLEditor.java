package org.system;

import apml.drivers.Stdbloqdriver;
import apml.system.Apmlbasesystem;
import org.quran.QuranLoader;
import org.widgets.APMLGui;

public class APMLEditor extends apml.modeling.Apmlsystem
{
	public final String bodi = "//events/system/{id}/apmleditor";

	//
	public final String id = "apmleditor";
	public final String tag = "apml";

	//
	public Apmlbasesystem monitor;
	public String apmlfile = "apml_editor.xml";
	public String basedir = "/Users/mrupplin/IdeaProjects/bloq/maxrupplin/development/bloq/apml_editor";

	//

	//
	public APMLEditor(final Apmlbasesystem monitor)
	{
		this.monitor = monitor;
	}

	//
	public APMLEditor()
	{
		this(null);
	}

	public static void main(String... args)
	{
		Apmlbasesystem apmlsystem = new Apmlbasesystem("apml_editor.xml", "/Users/mrupplin/IdeaProjects/bloq/maxrupplin/development/bloq/apml_editor", new Stdbloqdriver());

		//

		apmlsystem.preload(APMLEditor.class, true);

		apmlsystem.preload(APMLGui.class, true);

		apmlsystem.preload(UserInterfaceProcessor.class, true);


		//

		apmlsystem.init();

		apmlsystem.start();

		apmlsystem.run();

		//

		apmlsystem.postload(QuranLoader.class, true);
	}
}
