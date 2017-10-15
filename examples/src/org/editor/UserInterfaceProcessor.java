
package org.editor;

import apml.modeling.Apmlobject;
import apml.system.Apmlbasesystem;

import java.awt.event.ActionEvent;

public class UserInterfaceProcessor extends Apmlobject
{
    protected Apmlbasesystem monitor;

    public final String bodi="//events/editor/{id}/processor";

    public final String id="processor";

    public final String tag="object";

	//
    public UserInterfaceProcessor(final Apmlbasesystem monitor)
	{
        this.monitor = monitor;
    }

	//
    public UserInterfaceProcessor()
	{

    }

	//
    public void update(ActionEvent event, String target, String action)
    {
		switch(action)
		{
			case "load_apml_document_update":

				break;

			default:

				break;
		}
    }

    //
	public void update(ActionEvent event)
	{
		switch(event.getActionCommand())
		{
			case "load_apml_document_update":

				break;

			default:

				break;
		}
	}
}
