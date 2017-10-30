
package org.system;

import apml.compilers.java.codemodel.Bloqcompiler;
import apml.modeling.Apmlobject;
import apml.munction.Munction;
import apml.system.Apmlbasesystem;
import apml.system.bodi.Bodi;
import org.events.*;
import org.widgets.APMLGui;
import org.widgets.JTree_000;
import org.widgets.RSTextPane_000;

import javax.swing.*;
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

		Bodi.context("editor").put(this.bodi, this);
	}

	//
    public UserInterfaceProcessor()
	{
		Bodi.context("editor").put(this.bodi, this);
    }

	//
    public void update(ActionEvent event)
    {
		String action = event.getActionCommand();

		RSTextPane_000 editorpane;

		JTree_000 jtree;

		//

		Munction.registrar.register(0x0000001, Munction.instance.METHOD, "UserInterfaceProcessor.java", "UserInterfaceProcessor", "org.system", new byte[1]);

		switch(action)
		{


			case "build_apml_standalone_request_event":

				RSTextPane_000 textpane;

				String target_text;

				Bloqcompiler compiler;

				//

				textpane = (RSTextPane_000)Bodi.context("editor").pull("//editor/ui/rstextpane_000");

				target_text = textpane.getText();

				compiler =  new Bloqcompiler();

				//

				if(target_text==null || target_text.length()==0)
				{
					//Bodi.context("editor").pull();

					//

					JFileChooser input;

					input = new JFileChooser();

					input.setDialogTitle("Please Select APML Document");

					input.setFileSelectionMode(JFileChooser.FILES_ONLY);

					input.showOpenDialog((APMLGui)Bodi.context("editor").pull("//editor/ui/apmlgui"));

					//

					compiler.fileguardian.apmlinputfile = input.getSelectedFile();

					compiler.fileguardian.apmlfilename = input.getSelectedFile().getName() + "/";

					compiler.fileguardian.apmlinurl = input.getSelectedFile().getPath() + "/";

					//

					System.out.println("TODO: Please verify integrity of APML file");

					//

					JFileChooser output;

					output = new JFileChooser();

					output.setDialogTitle("Please Select Output Directory");

					output.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

					output.showSaveDialog((APMLGui)Bodi.context("editor").pull("//editor/ui/apmlgui"));

					//

					compiler.fileguardian.basedirurl = output.getSelectedFile().getPath().toString() + "/";

					compiler.fileguardian.apmlinurl = output.getSelectedFile().getPath()+"/in/";

					compiler.fileguardian.apmloutjarurl = output.getSelectedFile().getPath() + "/out/jar/";

					compiler.fileguardian.apmlinjarurl = output.getSelectedFile().getPath() + "/in/jar/";

					//

					compiler.fileguardian.apmlinputfile = output.getSelectedFile();

					compiler.fileguardian.apmlfilename = output.getSelectedFile().getName() + "/";

					compiler.fileguardian.apmlinurl = output.getSelectedFile().getPath() + "/";

					//

					compiler.setapmlfiles(compiler.fileguardian);

					compiler.settempfiles(compiler.inputmanager);

					compiler.setoutputfiles(compiler.inputmanager);

					compiler.setsourcefiles(compiler.outputmanager);

					compiler.writebytecode(compiler.inputmanager);

					try
					{
						compiler.writejarfile();
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
				else
				{
					JFileChooser output;

					output = new JFileChooser();

					output.setDialogTitle("Please Select Output Directory");

					output.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

					output.showSaveDialog((APMLGui)Bodi.context("editor").pull("//editor/ui/apmlgui"));

					//

					compiler.fileguardian.basedirurl = output.getSelectedFile().getPath().toString() + "/";

					compiler.fileguardian.apmlinurl = output.getSelectedFile().getPath()+"/in/";

					compiler.fileguardian.apmloutjarurl = output.getSelectedFile().getPath() + "/out/jar/";

					compiler.fileguardian.apmlinjarurl = output.getSelectedFile().getPath() + "/in/jar/";

					//

					compiler.setapmlfiles(compiler.fileguardian);

					compiler.settempfiles(compiler.inputmanager);

					compiler.setoutputfiles(compiler.inputmanager);

					compiler.setsourcefiles(compiler.outputmanager);

					compiler.writebytecode(compiler.inputmanager);
				}

				break;

			case "exit_program_event":

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
