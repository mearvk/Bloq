
package org.editor;

import apml.modeling.Apmlobject;
import apml.system.Apmlbasesystem;
import apml.system.bodi.Bodi;
import org.events.CloseApmlDocumentEvent;
import org.events.LoadApmlDocumentEvent;
import org.widgets.JEditorPane_000;
import org.widgets.JTree_000;
import org.widgets.RSTextPane_000;

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

		RSTextPane_000 editorpane;

		JTree_000 jtree;


		switch(action)
		{
			case "exit_program":

				System.exit(0);

				break;

			case "save_apml_document":

				editorpane = (RSTextPane_000)Bodi.context("editor").pull("//ui/editor/jeditorpane_000");

				//editorpane.savedocument((SaveApmlDocumentEvent)event);

				//

				jtree = (JTree_000)Bodi.context("editor").pull("//ui/editor/jpanel_003");

				jtree.initTree();

				jtree.updatetree((LoadApmlDocumentEvent)event);

				jtree.removenewlinetextnodes();

				break;

			case "load_apml_document_update":

				editorpane = (RSTextPane_000)Bodi.context("editor").pull("//ui/editor/rstextpane_000");

				editorpane.loaddocument((LoadApmlDocumentEvent)event);

				//

				jtree = (JTree_000)Bodi.context("editor").pull("//ui/editor/jtree_000");

				jtree.initTree();

				jtree.updatetree((LoadApmlDocumentEvent)event);

				jtree.removenewlinetextnodes();

				break;

			case "close_apml_document_update":

				editorpane = (RSTextPane_000)Bodi.context("editor").pull("//ui/editor/jeditorpane_000");

				//editorpane.closedocument((CloseApmlDocumentEvent)event);

				//

				jtree = (JTree_000)Bodi.context("editor").pull("//ui/editor/jtree_000");

				jtree.initTree();

				jtree.updatetree((CloseApmlDocumentEvent)event);

				jtree.removenewlinetextnodes();

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
