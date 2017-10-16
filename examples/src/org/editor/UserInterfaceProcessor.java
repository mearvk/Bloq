
package org.editor;

import apml.modeling.Apmlobject;
import apml.system.Apmlbasesystem;
import apml.system.bodi.Bodi;
import org.events.CloseApmlDocumentEvent;
import org.events.LoadApmlDocumentEvent;
import org.widgets.JEditorPane_000;

import java.awt.event.ActionEvent;

public class UserInterfaceProcessor extends Apmlobject
{
    protected Apmlbasesystem monitor;

    public final String bodi="//events/editor/{id}/uiprocessor";

    public final String id="processor_000";

    public final String tag="object";

	//
    public UserInterfaceProcessor(final Apmlbasesystem monitor)
	{
        this.monitor = monitor;

        try
		{
			Bodi.setcontext("editor");

			Bodi.context("editor").put(this.bodi, this);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	//
    public UserInterfaceProcessor()
	{
		try
		{
			Bodi.setcontext("editor");

			Bodi.context("editor").put(this.bodi, this);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }

	//
    public void update(ActionEvent event)
    {
		String action = event.getActionCommand();

		switch(action)
		{
			case "load_apml_document_update":

				((JEditorPane_000)Apmlbasesystem.map.get("//ui/editor/jeditorpane_000")).loaddocument((LoadApmlDocumentEvent)event);

				break;

			case "close_apml_document_update":

				((JEditorPane_000)Apmlbasesystem.map.get("//ui/editor/jeditorpane_000")).closedocument((CloseApmlDocumentEvent)event);

				break;

			default:

				break;
		}
    }

	//
	public void update(ActionEvent event, String target, String action)
	{
		switch(action)
		{
			case "load_apml_document_update":

				((JEditorPane_000)Apmlbasesystem.map.get("//ui/editor/jeditorpane_000")).loaddocument((LoadApmlDocumentEvent)event); //target, action removed from parameter

				break;

			case "close_apml_document_update":

				((JEditorPane_000)Apmlbasesystem.map.get("//ui/editor/jeditorpane_000")).closedocument((CloseApmlDocumentEvent)event); //target, action removed from parameter

				break;

			default:

				break;
		}
	}
}
