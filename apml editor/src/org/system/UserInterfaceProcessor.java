
package org.system;

import apml.modeling.Apmlobject;
import apml.system.Apmlbasesystem;
import apml.system.bodi.Bodi;
import org.events.CloseApmlDocumentEvent;
import org.events.LoadApmlDocumentEvent;
import org.events.SaveApmlDocumentEvent;
import org.events.TreeStructureUpdatedEvent;
import org.widgets.JTree_000;
import org.widgets.RSTextPane_000;

import java.awt.event.ActionEvent;

public class UserInterfaceProcessor extends Apmlobject
{
    protected Apmlbasesystem monitor;

    public final String bodi="//events/system/{id}/uiprocessor";

    public final String id="processor_000";

    public final String tag="object";

	//
    public UserInterfaceProcessor(final Apmlbasesystem monitor)
	{
        this.monitor = monitor;

        try
		{
			Bodi.setcontext("system");

			Bodi.context("system").put(this.bodi, this);
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
			Bodi.setcontext("system");

			Bodi.context("system").put(this.bodi, this);
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

			case "tree_structure_updated_event":

				editorpane = (RSTextPane_000)Bodi.context("editor").pull("//editor/ui/rstextpane_000");

				editorpane.updatedocumentbasedontreechange((TreeStructureUpdatedEvent) event);

				//

			case "save_apml_document":

				editorpane = (RSTextPane_000)Bodi.context("editor").pull("//editor/ui/rstextpane_000");

				editorpane.savedocument((SaveApmlDocumentEvent)event);

				//

				jtree = (JTree_000)Bodi.context("editor").pull("//editor/ui/jpanel_003");

				jtree.init();

				jtree.update((LoadApmlDocumentEvent)event);

				jtree.removenewlinetextnodes();

				break;

			case "load_apml_document_update":

				editorpane = (RSTextPane_000)Bodi.context("editor").pull("//editor/ui/rstextpane_000");

				editorpane.loaddocument((LoadApmlDocumentEvent)event);

				//

				jtree = (JTree_000)Bodi.context("editor").pull("//editor/ui/jtree_000");

				jtree.init();

				jtree.update((LoadApmlDocumentEvent)event);

				jtree.removenewlinetextnodes();

				break;

			case "close_apml_document_update":

				editorpane = (RSTextPane_000)Bodi.context("editor").pull("//editor/ui/rstextpane_000");

				editorpane.closedocument((CloseApmlDocumentEvent)event);

				//

				jtree = (JTree_000)Bodi.context("editor").pull("//editor/ui/jtree_000");

				jtree.init();

				jtree.update((CloseApmlDocumentEvent)event);

				jtree.removenewlinetextnodes();

				break;

			default:

				break;
		}
    }
}
