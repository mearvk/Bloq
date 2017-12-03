package org.system;

import apml.drivers.Stdbloqdriver;
import apml.system.Apmlbasesystem;
import apml.system.bodi.Bodi;
import apml.system.bodi.Bodicontext;
import org.helpers.Preloadable;
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

		Bodi.context("editor").put(this.bodi, this);
	}

	//
	public APMLEditor()
	{
		this.monitor = null;

		Bodi.context("editor").put(this.bodi, this);
	}

	public static void main(String... args)
	{
		Apmlbasesystem apmlsystem = new Apmlbasesystem("apml_editor.xml", "/Users/mrupplin/IdeaProjects/bloq/maxrupplin/development/bloq/apml_editor", new Stdbloqdriver());

		//

		Bodicontext context;

		context = Bodi.context("editor");

		apmlsystem.preloadables.add(new Preloadable(APMLEditor.class, true, 0L, 0L));

		//context = Bodi.context("editor");

		apmlsystem.preloadables.add(new Preloadable(APMLGui.class, true, 0L, 0L));

		//context = Bodi.context("editor");

		apmlsystem.preloadables.add(new Preloadable(UserInterfaceProcessor.class, true, 0L, 0L));

		//context = Bodi.context("editor");

		apmlsystem.preloadables.add(new Preloadable(ModelInterfaceSystem.class, true, 0L, 0L));

		//context = Bodi.context("editor");

		apmlsystem.preloadables.add(new Preloadable(ModelInterfaceObserver.class, true, 0L, 0L));

		//context = Bodi.context("editor");

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
