
package org.system;

import apml.compilers.java.codemodel.Bloqcompiler;
import apml.modeling.Apmlobject;
import apml.system.Apmlbasesystem;
import apml.system.bodi.Bodi;
import apml.ui.compilers.java.Uicompiler;
import org.events.*;
import org.widgets.*;

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

		RSTextPane_000 textpane;

		Uicompiler ui_compiler;

		Bloqcompiler bloq_compiler;

		JFileChooser chooser;

		JFileChooser output;

		JFileChooser input;

		JTree_000 jtree;

		//

		//Munction.registrar.register(0x0000001, Munction.instance.METHOD, "UserInterfaceProcessor.java", "UserInterfaceProcessor", "org.system", new byte[1]);

		switch(action)
		{
			case "build_ui_apml_request_event":

				String target_text;

				//

				JPanel_001 jpanel_001;

				jpanel_001 = (JPanel_001)Bodi.context("editor").pull("//editor/ui/jpanel_apml_001");

				target_text = jpanel_001.rstextpane.getText();

				ui_compiler =  new Uicompiler();

				//

				if(target_text==null || target_text.length()==0)
				{

					//JFileChooser input;

					input = new JFileChooser();

					input.setDialogTitle("Please Select UI APML Document");

					input.setFileSelectionMode(JFileChooser.FILES_ONLY);

					input.showOpenDialog((APMLGui) Bodi.context("editor").pull("//editor/ui/apmlgui"));

					//

					this.last_loaded_file = ui_compiler.fileguardian.xmlin = input.getSelectedFile();

					this.last_loaded_file_url = input.getSelectedFile().getName();

				}

				//

				//JFileChooser output;

				output = new JFileChooser();

				output.setDialogTitle("Please Select Output Directory");

				output.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

				output.showSaveDialog((APMLGui)Bodi.context("editor").pull("//editor/ui/apmlgui"));

				//

				ui_compiler.fileguardian.outputdir = output.getSelectedFile();

				//

				ui_compiler.dohandleinputfiles(ui_compiler.inputmanager);

				ui_compiler.dohandleoutputfiles(ui_compiler.outputmanager);

				//

				break;

			case "build_apml_standalone_request_event":

				//

				textpane = (RSTextPane_000)Bodi.context("editor").pull("//editor/ui/rstextpane_000");

				target_text = textpane.getText();

				bloq_compiler =  new Bloqcompiler();

				//

				if(target_text==null || target_text.length()==0)
				{
					//

					//JFileChooser input;

					input = new JFileChooser();

					input.setDialogTitle("Please Select APML Document");

					input.setFileSelectionMode(JFileChooser.FILES_ONLY);

					input.showOpenDialog((APMLGui)Bodi.context("editor").pull("//editor/ui/apmlgui"));

					//

					this.last_loaded_file = bloq_compiler.fileguardian.apmlinputfile = input.getSelectedFile();

					this.last_loaded_file_url = bloq_compiler.fileguardian.apmlfilename = input.getSelectedFile().getName();

					//

					System.out.println("TODO: Please verify integrity of APML file");

					//

					//JFileChooser output;

					output = new JFileChooser();

					output.setDialogTitle("Please Select Output Directory");

					output.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

					output.showSaveDialog((APMLGui)Bodi.context("editor").pull("//editor/ui/apmlgui"));

					//

					bloq_compiler.fileguardian.basedirurl = output.getSelectedFile().getPath().toString() + "/";

					bloq_compiler.fileguardian.apmloutjarurl = output.getSelectedFile().getPath() + "/out/jar/";

					bloq_compiler.fileguardian.apmlinjarurl = output.getSelectedFile().getPath() + "/in/jar/";

					bloq_compiler.fileguardian.apmlinurl = output.getSelectedFile().getPath()+"/in/";

					//

					bloq_compiler.setapmlfiles(bloq_compiler.fileguardian);

					bloq_compiler.settempfiles(bloq_compiler.inputmanager);

					bloq_compiler.setoutputfiles(bloq_compiler.inputmanager);

					bloq_compiler.setsourcefiles(bloq_compiler.outputmanager);

					bloq_compiler.writebytecode(bloq_compiler.inputmanager);

					try
					{
						bloq_compiler.writejarfile();
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
				else
				{
					//JFileChooser output;

					output = new JFileChooser();

					output.setDialogTitle("Please Select Output Directory");

					output.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

					output.showSaveDialog((APMLGui)Bodi.context("editor").pull("//editor/ui/apmlgui"));

					//

					bloq_compiler.fileguardian.basedirurl = output.getSelectedFile().getPath().toString() + "/";

					bloq_compiler.fileguardian.apmlinurl = output.getSelectedFile().getPath()+"/in/";

					bloq_compiler.fileguardian.apmloutjarurl = output.getSelectedFile().getPath() + "/out/jar/";

					bloq_compiler.fileguardian.apmlinjarurl = output.getSelectedFile().getPath() + "/in/jar/";

					//

					if(last_loaded_file==null || this.last_loaded_file_url == null || this.last_loaded_file_url.length()==0)
					{
						JPanel_002 jpanel_002;

						jpanel_002 = (JPanel_002)Bodi.context("editor").pull("//editor/ui/jpanel_002");

						jpanel_002.status.setText("Unable to determine existing file for APML input.  Double check.");
					}

					bloq_compiler.fileguardian.apmlinputfile = this.last_loaded_file;

					bloq_compiler.fileguardian.apmlfilename = this.last_loaded_file_url;

					//

					bloq_compiler.setapmlfiles(bloq_compiler.fileguardian);

					bloq_compiler.settempfiles(bloq_compiler.inputmanager);

					bloq_compiler.setoutputfiles(bloq_compiler.inputmanager);

					bloq_compiler.setsourcefiles(bloq_compiler.outputmanager);

					bloq_compiler.writebytecode(bloq_compiler.inputmanager);
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

				jpanel_001 = (JPanel_001)Bodi.context("editor").pull("//editor/ui/jpanel_apml_001");

				jpanel_001.rstextpane.savedocument((SaveApmlDocumentEvent)event);

				//

				break;

			case "load_apml_document_event":

				//JPanel_001 jpanel_001;

				jpanel_001 = (JPanel_001)Bodi.context("editor").pull("//editor/ui/jpanel_apml_001");

				jpanel_001.rstextpane.loaddocument((LoadApmlDocumentEvent)event);

				break;

				//

			case "load_apml_tree_event":

				JPanel_000 jpanel_000;

				jpanel_000 = (JPanel_000)Bodi.context("editor").pull("//editor/ui/jtree_apml_000");

				jpanel_000.jtree_000.init();

				jpanel_000.jtree_000.update((LoadApmlDocumentEvent)event);

				jpanel_000.jtree_000.removenewlinetextnodes();

				break;

			case "document_loaded_event":

				this.last_loaded_file_url = ((DocumentLoadedEvent)event).getFileURL();

				this.last_loaded_file = ((DocumentLoadedEvent)event).getFileRef();

				break;

			case "close_apml_document_event":

				jpanel_001 = (JPanel_001)Bodi.context("editor").pull("//editor/ui/jpanel_apml_001");

				jpanel_001.rstextpane.closedocument((CloseApmlDocumentEvent)event);

				//

				break;

			default:

				break;
		}
    }
}
