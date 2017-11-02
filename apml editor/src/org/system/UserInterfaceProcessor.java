
package org.system;

import apml.compilers.java.codemodel.Bloqcompiler;
import apml.modeling.Apmlobject;
import apml.system.Apmlbasesystem;
import apml.system.bodi.Bodi;
import apml.ui.compilers.java.Uicompiler;
import apml.xpath.helpers.Xpathquick;
import org.events.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.widgets.*;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import java.awt.event.ActionEvent;
import java.io.File;

public class UserInterfaceProcessor extends Apmlobject
{
    protected Apmlbasesystem monitor;

    public final String bodi= "//editor/ui/uiprocessor_000";

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

		Document document;

		XPath xpath;

		NodeList nodes;

		JTabbedPane_000 jTabbedPane_000;

		//

		//Munction.registrar.register(0x0000001, Munction.instance.METHOD, "UserInterfaceProcessor.java", "UserInterfaceProcessor", "org.system", new byte[1]);

		switch(action)
		{
			case "open_document_event":

				//

				try
				{
					OpenDocumentEvent opendocumentevent;

					opendocumentevent = (OpenDocumentEvent)event;

					document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(opendocumentevent.getFileRef());

					xpath = XPathFactory.newInstance().newXPath();

					nodes = Xpathquick.evaluate(document, xpath,"//project[@type]");

					//

					APMLGui apmlgui;

					apmlgui = (APMLGui)Bodi.context("editor").pull("//editor/ui/apmlgui");

					//

					if(nodes.getLength()==0)
						JOptionPane.showMessageDialog(apmlgui, "Unable to locate any document type; make sure you have a project type set ( //apml/project[@type] ).");

					if(nodes.getLength()>1)
						JOptionPane.showMessageDialog(apmlgui, "Unable to locate unambiguous document type; make sure you have a single project type set ( //apml/project[@type] ).");


					Element element;

					element = (Element)nodes.item(0);

					String type;

					type = element.getAttribute("type");

					opendocumentevent.fileType = type.trim().toLowerCase();

					switch(opendocumentevent.fileType)
					{
						case "apml": break;

						case "bloq": break;

						case "bodi": break;

						case "munction": break;

						case "runyn": break;

						case "sprung": break;

						case "falthruu": break;

						default:

							JOptionPane.showMessageDialog(apmlgui, "Unknown file type; no can help.");

							return;
					}

					JOptionPane.showMessageDialog(apmlgui, "Document type located as ["+opendocumentevent.fileType+"].");

					//

					UserInterfaceProcessor processor;

					processor = (UserInterfaceProcessor)Bodi.context("editor").pull("//editor/ui/uiprocessor_000");

					//

					switch(opendocumentevent.fileType)
					{
						case "apml":

							processor.update(new LoadApmlDocumentEvent(opendocumentevent, opendocumentevent.fileRef));

							processor.update(new LoadApmlTreeEvent(opendocumentevent, opendocumentevent.fileRef));

							//

							//JTabbedPane_000 jTabbedPane_000;

							jTabbedPane_000 = (JTabbedPane_000)Bodi.context("editor").pull("//editor/ui/jtabbedpane_000");

							jTabbedPane_000.setSelectedIndex(0);

							break;

						case "bloq":

							processor.update(new LoadBloqDocumentEvent(opendocumentevent,opendocumentevent.fileRef));

							processor.update(new LoadBloqTreeEvent(opendocumentevent,opendocumentevent.fileRef));

							//

							//JTabbedPane_000 jTabbedPane_000;

							jTabbedPane_000 = (JTabbedPane_000)Bodi.context("editor").pull("//editor/ui/jtabbedpane_000");

							jTabbedPane_000.setSelectedIndex(1);

							break;

						case "bodi":

							processor.update(new LoadBodiDocumentEvent(opendocumentevent,opendocumentevent.fileRef));

							//processor.update(new LoadBodiTreeEvent(this,this.fileRef));

							break;

						case "munction":

							processor.update(new LoadMunctionDocumentEvent(opendocumentevent,opendocumentevent.fileRef));

							//processor.update(new LoadMunctionTreeEvent(this,this.fileRef));

							break;

						case "runyn":

							processor.update(new LoadRunynDocumentEvent(opendocumentevent,opendocumentevent.fileRef));

							//processor.update(new LoadRunynTreeEvent(this,this.fileRef));

							break;

						case "sprung":

							processor.update(new LoadSprungDocumentEvent(opendocumentevent,opendocumentevent.fileRef));

							//processor.update(new LoadSprungTreeEvent(this,this.fileRef));

							break;

						case "falthruu":

							processor.update(new LoadFalthruuDocumentEvent(opendocumentevent,opendocumentevent.fileRef));

							//processor.update(new LoadFalthruuTreeEvent(this,this.fileRef));

							break;

						default: JOptionPane.showMessageDialog(apmlgui, "Unknown file type; no can help.");
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}

				break;

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

				jpanel_001 = (JPanel_001)Bodi.context("editor").pull("//editor/ui/jpanel_apml_001");

				target_text = jpanel_001.rstextpane.getText();

				//

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

					this.last_loaded_file = bloq_compiler.fileguardian.apmlinputfile;

					this.last_loaded_file_url = bloq_compiler.fileguardian.apmlfilename;

					if(last_loaded_file==null || this.last_loaded_file_url == null || this.last_loaded_file_url.length()==0)
					{
						APMLGui apmlgui;

						apmlgui = (APMLGui)Bodi.context("editor").pull("//editor/ui/apmlgui");

						JOptionPane.showMessageDialog(apmlgui, "Unable to determing existing file for APML input. Double check.");
					}

					//

					bloq_compiler.setapmlfiles(bloq_compiler.fileguardian);

					bloq_compiler.settempfiles(bloq_compiler.inputmanager);

					bloq_compiler.setoutputfiles(bloq_compiler.inputmanager);

					bloq_compiler.setsourcefiles(bloq_compiler.outputmanager);

					bloq_compiler.writebytecode(bloq_compiler.inputmanager);

					//

					APMLGui apmlgui;

					apmlgui = (APMLGui)Bodi.context("editor").pull("//editor/ui/apmlgui");

					JOptionPane.showMessageDialog(apmlgui, "Compilation successful; check folder for files.");
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

				JTree_Apml_000 jtree_apml_000;

				jtree_apml_000 = (JTree_Apml_000)Bodi.context("editor").pull("//editor/ui/jtree_apml_000");

				jtree_apml_000.init();

				jtree_apml_000.update((LoadApmlTreeEvent)event);

				jtree_apml_000.removenewlinetextnodes();

				break;

			case "load_bloq_document_event":

				//JPanel_001 jpanel_001;

				jpanel_001 = (JPanel_001)Bodi.context("editor").pull("//editor/ui/jpanel_bloq_001");

				jpanel_001.rstextpane.loaddocument((LoadApmlDocumentEvent)event);

				break;

			//

			case "load_bloq_tree_event":

				JTree_Bloq_000 jtree_bloq_000;

				jtree_bloq_000 = (JTree_Bloq_000)Bodi.context("editor").pull("//editor/ui/jtree_bloq_000");

				jtree_bloq_000.init();

				jtree_bloq_000.update((LoadBloqTreeEvent)event);

				jtree_bloq_000.removenewlinetextnodes();

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
