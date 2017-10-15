
package org.apml.editor;

import apml.drivers.Stdbloqdriver;
import apml.system.Apmlbasesystem;
import org.widgets.APMLGui;

public class APMLEditor extends apml.modeling.Apmlsystem
{
    public Apmlbasesystem monitor;

    public final String bodi="//apml/editor/{id}/apmleditor";

    public final String id="apmleditor";

    public final String tag="apml";

    public static void main(String...args)
	{
		Apmlbasesystem apmlsystem = new Apmlbasesystem("apml_editor.xml", "/build", new Stdbloqdriver());

		//

		apmlsystem._load(new APMLEditor(), true);

		apmlsystem._load(new APMLGui(), true);

		//

		apmlsystem.init();

		apmlsystem.start();

		apmlsystem.run();
	}

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

	@Override
	public void initialize()
	{

	}

	@Override
	public void start()
	{

	}

	@Override
	public void run()
	{

	}

	@Override
	public void autostart()
	{

	}

	@Override
	public void init()
	{

	}

	@Override
	public void pause()
	{

	}

	@Override
	public void restart()
	{

	}

	@Override
	public void resume()
	{

	}

	@Override
	public void stop()
	{

	}
}
