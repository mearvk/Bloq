
package org.system;

import apml.modeling.Apmlobject;
import apml.system.Apmlbasesystem;
import apml.system.bodi.Bodi;
import org.events.*;
import org.widgets.JTree_000;
import org.widgets.RSTextPane_000;

import java.awt.event.ActionEvent;
import java.io.File;

public class UserInterfaceProcessor extends Apmlobject
{
    protected Apmlbasesystem monitor;

    public final String bodi="//editor/ui/uiprocessor_000";

    public final String id="processor_000";

    public final String tag="object";

    //

    public String last_loaded_file_url = null;

    public File last_loaded_file = null;

    //

	//
    public UserInterfaceProcessor(final Apmlbasesystem monitor)
	{
        this.monitor = monitor;

        try
		{
			Bodi.setcontext("editor");

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
			Bodi.setcontext("editor");

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

				editorpane.processtreechange((TreeStructureUpdatedEvent) event);

				//

			case "save_apml_document_event":

				editorpane = (RSTextPane_000)Bodi.context("editor").pull("//editor/ui/rstextpane_000");

				editorpane.savedocument((SaveApmlDocumentEvent)event);

				//

				jtree = (JTree_000)Bodi.context("editor").pull("//editor/ui/jpanel_003");

				jtree.init();

				jtree.update((LoadApmlDocumentEvent)event);

				jtree.removenewlinetextnodes();

				break;

			case "load_apml_document_event":

				editorpane = (RSTextPane_000)Bodi.context("editor").pull("//editor/ui/rstextpane_000");

				editorpane.loaddocument((LoadApmlDocumentEvent)event);

				//

				jtree = (JTree_000)Bodi.context("editor").pull("//editor/ui/jtree_000");

				jtree.init();

				jtree.update((LoadApmlDocumentEvent)event);

				jtree.removenewlinetextnodes();

				break;

			case "document_loaded_event":

				this.last_loaded_file_url = ((DocumentLoadedEvent)event).getFileURL();

				this.last_loaded_file = ((DocumentLoadedEvent)event).getFileRef();

				break;

			case "close_apml_document_event":

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
