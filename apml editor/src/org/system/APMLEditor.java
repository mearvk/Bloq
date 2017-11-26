package org.system;

import apml.drivers.Stdbloqdriver;
import apml.system.Apmlbasesystem;
import org.bible.BibleLoader;
import org.quran.QuranLoader;
import org.widgets.APMLGui;

public class APMLEditor extends apml.modeling.Apmlsystem
{
	public final String bodi = "//editor/ui/apmleditor";

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

		apmlsystem.preload(APMLEditor.class, true, 0L, 4000L);

		apmlsystem.preload(APMLGui.class, true, 0L, 4000L);

		apmlsystem.preload(UserInterfaceProcessor.class, true, 0L,1000L);

		apmlsystem.preload(ModelInterfaceSystem.class, true,0L,1000L);

		apmlsystem.preload(ModelInterfaceObserver.class, true,0L, 1000L);

		//

		apmlsystem.init();

		apmlsystem.start();

		apmlsystem.run();

		//

		//apmlsystem.postload(QuranLoader.class, true);

		//

		//apmlsystem.postload(BibleLoader.class, true);
	}
}
