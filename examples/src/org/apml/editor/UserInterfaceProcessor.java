
package org.apml.editor;

import apml.modeling.Apmlobject;
import apml.system.Apmlbasesystem;

import java.awt.*;

public class UserInterfaceProcessor extends Apmlobject
{
    protected Apmlbasesystem monitor;

    public final String bodi="//apml/editor/{id}/processor";

    public final String id="processor";

    public final String tag="object";

    public UserInterfaceProcessor(final Apmlbasesystem monitor)
	{
		//

        this.monitor = monitor;
    }

    public UserInterfaceProcessor()
	{
		//
    }

    public void update(Event event, String target, String action)
    {
        //
    }
}