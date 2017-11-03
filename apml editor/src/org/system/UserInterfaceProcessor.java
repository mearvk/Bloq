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
import java.io.*;

//

public class UserInterfaceProcessor extends Apmlobject
{
	public final String bodi = "//editor/ui/uiprocessor_000";
	public final String id = "processor_000";
	public final String tag = "object";
	public String last_loaded_file_url = null;

	//
	public File last_loaded_file = null;
	protected Apmlbasesystem monitor;

	//

	public UserInterfaceProcessor(final Apmlbasesystem monitor)
	{
		this.monitor = monitor;

		Bodi.context("editor").put(this.bodi, this);
	}

	public UserInterfaceProcessor()
	{
		Bodi.context("editor").put(this.bodi, this);
	}

	//

	public void update(ActionEvent event)
	{
		String action = event.getActionCommand();

		//

		switch (action)
		{
			case "open_document_event":

				new OpenDocumentRequest(this, event).run();

				break;

			case "build_ui_apml_request_event":

				new BuildApmlUserInterfaceRequest(this).run();

				break;

			case "build_apml_standalone_request_event":

				new BuildApmlStandaloneRequest(this).run();

				break;

			case "exit_program_event":

				new ExitProgramRequest(this, event).run();

				break;

			case "tree_structure_updated_event":

				new TreeStructureUpdatedRequest(this, event).run();

				break;

			case "save_apml_document_event":

				new SaveApmlDocumentRequest(this, event).run();

				break;

			case "load_apml_document_event":

				new LoadApmlDocumentRequest(this, event).run();

				break;

			case "load_apml_tree_event":

				new LoadApmlTreeRequest(this, event).run();

				break;

			case "load_bloq_document_event":

				new LoadBloqDocumentRequest(this, event).run();

				break;

			case "load_bloq_tree_event":

				new LoadBloqTreeRequest(this, event).run();

				break;

			case "document_loaded_event":

				new UpdateAllOnDocumentLoadedRequest(this, event).run();

				break;

			case "close_apml_document_event":

				new CloseApmlDocumentRequest(this, event).run();

				break;

			default:

				break;
		}
	}
}

class CloseApmlDocumentRequest
{
	public UserInterfaceProcessor processor;

	public CloseApmlDocumentEvent event;

	public CloseApmlDocumentRequest(UserInterfaceProcessor processor, ActionEvent event)
	{
		this.processor = processor;

		this.event = (CloseApmlDocumentEvent) event;
	}

	public void run()
	{
		RSTextPane_Apml_000 rstextpane_apml_000;

		rstextpane_apml_000 = (RSTextPane_Apml_000) Bodi.context("editor").pull("//editor/ui/rstextpane_apml_000");

		rstextpane_apml_000.setText("");
	}
}

class UpdateAllOnDocumentLoadedRequest
{
	public UserInterfaceProcessor processor;

	public DocumentLoadedEvent event;

	public UpdateAllOnDocumentLoadedRequest(UserInterfaceProcessor processor, ActionEvent event)
	{
		this.processor = processor;

		this.event = (DocumentLoadedEvent) event;
	}

	public void run()
	{
		processor.last_loaded_file_url = (event).getFileURL();

		processor.last_loaded_file = (event).getFileRef();
	}
}

class LoadBloqTreeRequest
{
	public UserInterfaceProcessor processor;

	public LoadBloqTreeEvent event;

	public LoadBloqTreeRequest(UserInterfaceProcessor processor, ActionEvent event)
	{
		this.processor = processor;

		this.event = (LoadBloqTreeEvent) event;
	}

	public void run()
	{
		JTree_Bloq_000 jtree_bloq_000;

		jtree_bloq_000 = (JTree_Bloq_000) Bodi.context("editor").pull("//editor/ui/jtree_bloq_000");

		jtree_bloq_000.init();

		jtree_bloq_000.update((LoadBloqTreeEvent) event);

		jtree_bloq_000.removenewlinetextnodes();
	}

}

class LoadBloqDocumentRequest
{
	public UserInterfaceProcessor processor;

	public LoadBloqDocumentEvent event;

	public LoadBloqDocumentRequest(UserInterfaceProcessor processor, ActionEvent event)
	{
		this.processor = processor;

		this.event = (LoadBloqDocumentEvent) event;
	}

	public void run()
	{
		JPanel_001 jpanel_001;

		jpanel_001 = (JPanel_001) Bodi.context("editor").pull("//editor/ui/jpanel_bloq_001");

		this.loaddocument(event);
	}

	private void loaddocument(LoadBloqDocumentEvent event)
	{
		RSTextPane_Bloq_000 rstextpane_bloq_000;

		rstextpane_bloq_000 = (RSTextPane_Bloq_000) Bodi.context("editor").pull("//editor/ui/rstextpane_bloq_000");

				try
				{
					BufferedReader reader = new BufferedReader(new FileReader(event.getFileRef()));

					String line = null;

					String buffer = new String();

					//

					while ((line = reader.readLine()) != null)
					{
						buffer = buffer + line + "\n";
					}

					//

					rstextpane_bloq_000.setText(buffer + "\n");

					rstextpane_bloq_000.setCaretPosition(000);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
	}
}

class LoadApmlTreeRequest
{
	public UserInterfaceProcessor processor;

	public LoadApmlTreeEvent event;

	public LoadApmlTreeRequest(UserInterfaceProcessor processor, ActionEvent event)
	{
		this.processor = processor;

		this.event = (LoadApmlTreeEvent) event;
	}

	public void run()
	{
		JTree_Apml_000 jtree_apml_000;

		jtree_apml_000 = (JTree_Apml_000) Bodi.context("editor").pull("//editor/ui/jtree_apml_000");

		jtree_apml_000.init();

		jtree_apml_000.update((LoadApmlTreeEvent) event);

		jtree_apml_000.removenewlinetextnodes();
	}
}

class ExitProgramRequest
{
	public UserInterfaceProcessor processor;

	public ExitProgramEvent event;

	public ExitProgramRequest(UserInterfaceProcessor processor, ActionEvent event)
	{
		this.processor = processor;

		this.event = (ExitProgramEvent) event;
	}

	public void run()
	{
		System.exit(0);
	}
}

class LoadApmlDocumentRequest
{
	public UserInterfaceProcessor processor;

	public LoadApmlDocumentEvent event;

	public LoadApmlDocumentRequest(UserInterfaceProcessor processor, ActionEvent event)
	{
		this.processor = processor;

		this.event = (LoadApmlDocumentEvent) event;
	}

	public void run()
	{
		this.loaddocument(event);
	}

	private void loaddocument(LoadApmlDocumentEvent event)
	{
		RSTextPane_Apml_000 rstextpane_apml_000;

		rstextpane_apml_000 = (RSTextPane_Apml_000) Bodi.context("editor").pull("//editor/ui/rstextpane_apml_000");

		//

		try
		{
			BufferedReader reader = new BufferedReader(new FileReader(event.getFileRef()));

			String line = null;

			String buffer = new String();

			//

			while ((line = reader.readLine()) != null)
			{
				buffer = buffer + line + "\n";
			}

			//

			rstextpane_apml_000.setText(buffer + "\n");

			rstextpane_apml_000.setCaretPosition(000);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}

class TreeStructureUpdatedRequest
{
	public UserInterfaceProcessor processor;

	public TreeStructureUpdatedEvent event;

	public TreeStructureUpdatedRequest(UserInterfaceProcessor processor, ActionEvent event)
	{
		this.processor = processor;

		this.event = (TreeStructureUpdatedEvent) event;
	}

	public void run()
	{
		RSTextPane_000 rstextpane_000;

		rstextpane_000 = (RSTextPane_000) Bodi.context("editor").pull("//editor/ui/rstextpane_000");

		rstextpane_000.processtreechange((TreeStructureUpdatedEvent) event);
	}
}

class SaveApmlDocumentRequest
{
	public UserInterfaceProcessor processor;

	public SaveApmlDocumentEvent event;

	public APMLGui apmlgui;

	public RSTextPane_Apml_000 rstextpane_apml_000;

	//

	public SaveApmlDocumentRequest(UserInterfaceProcessor processor, ActionEvent event)
	{
		this.processor = processor;

		this.event = (SaveApmlDocumentEvent) event;

		this.apmlgui = (APMLGui) Bodi.context("editor").pull("//editor/ui/apmlgui");

		this.rstextpane_apml_000 = (RSTextPane_Apml_000) Bodi.context("editor").pull("//editor/ui/rstextpane_apml_000");
	}

	public void run()
	{
		this.savedocument(event);
	}

	//
	private void savedocument(SaveApmlDocumentEvent event)
	{
		try
		{
			JFileChooser chooser = new JFileChooser();

			int retval = chooser.showSaveDialog(this.apmlgui);

			if (retval == JFileChooser.APPROVE_OPTION)
			{
				File file = chooser.getSelectedFile();

				BufferedWriter writer;

				writer = new BufferedWriter(new FileWriter(file));

				writer.write(this.rstextpane_apml_000.getText(), 0, this.rstextpane_apml_000.getText().length());

				writer.flush();

				writer.close();
			}

			JOptionPane.showMessageDialog(this.apmlgui, "Document successfully saved.");
		}
		catch (Exception e)
		{
			e.printStackTrace();

			JOptionPane.showMessageDialog(this.apmlgui, "Error saving document.");
		}
	}
}

class OpenDocumentRequest
{
	private UserInterfaceProcessor processor;

	private OpenDocumentEvent event;

	private Element element;

	private String type;

	private Document document;

	private XPath xpath;

	private NodeList nodes;

	private JTabbedPane_000 jtabbedpane_000;

	//

	public OpenDocumentRequest(UserInterfaceProcessor processor, ActionEvent event)
	{
		this.processor = processor;

		this.event = (OpenDocumentEvent) event;
	}

	public void run()
	{
		APMLGui apmlgui = (APMLGui) Bodi.context("editor").pull("//editor/ui/apmlgui");

		try
		{
			this.document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(event.getFileRef());

			this.xpath = XPathFactory.newInstance().newXPath();

			this.nodes = Xpathquick.evaluate(this.document, this.xpath, "//project[@type]");

			//

			if (this.nodes.getLength() == 0)
				JOptionPane.showMessageDialog(apmlgui, "Unable to locate any document type; make sure you have a project type set ( //apml/project[@type] ).");

			if (this.nodes.getLength() > 1)
				JOptionPane.showMessageDialog(apmlgui, "Unable to locate unambiguous document type; make sure you have a single project type set ( //apml/project[@type] ).");

			//

			element = (Element) this.nodes.item(0);

			type = element.getAttribute("type");

			event.fileType = type.trim().toLowerCase();

			//

			switch (event.fileType)
			{
				case "apml":
					break;

				case "bloq":
					break;

				case "bodi":
					break;

				case "munction":
					break;

				case "runyn":
					break;

				case "sprung":
					break;

				case "falthruu":
					break;

				default:

					JOptionPane.showMessageDialog(apmlgui, "Unknown file type; no can help.");
					return;
			}

			//

			JOptionPane.showMessageDialog(apmlgui, "Document type located as [" + event.fileType + "].");

			//

			this.processor = (UserInterfaceProcessor) Bodi.context("editor").pull("//editor/ui/uiprocessor_000");

			//

			switch (event.fileType)
			{
				case "apml":

					this.processor.update(new LoadApmlDocumentEvent(event, event.fileRef));

					this.processor.update(new LoadApmlTreeEvent(event, event.fileRef));

					//

					this.jtabbedpane_000 = (JTabbedPane_000) Bodi.context("editor").pull("//editor/ui/jtabbedpane_000");

					this.jtabbedpane_000.setSelectedIndex(0);

					break;

				case "bloq":

					this.processor.update(new LoadBloqDocumentEvent(event, event.fileRef));

					this.processor.update(new LoadBloqTreeEvent(event, event.fileRef));

					//

					this.jtabbedpane_000 = (JTabbedPane_000) Bodi.context("editor").pull("//editor/ui/jtabbedpane_000");

					this.jtabbedpane_000.setSelectedIndex(1);

					break;

				case "bodi":

					this.processor.update(new LoadBodiDocumentEvent(event, event.fileRef));

					//processor.update(new LoadBodiTreeEvent(this,this.fileRef));

					break;

				case "munction":

					this.processor.update(new LoadMunctionDocumentEvent(event, event.fileRef));

					//processor.update(new LoadMunctionTreeEvent(this,this.fileRef));

					break;

				case "runyn":

					this.processor.update(new LoadRunynDocumentEvent(event, event.fileRef));

					//processor.update(new LoadRunynTreeEvent(this,this.fileRef));

					break;

				case "sprung":

					this.processor.update(new LoadSprungDocumentEvent(event, event.fileRef));

					//processor.update(new LoadSprungTreeEvent(this,this.fileRef));

					break;

				case "falthruu":

					this.processor.update(new LoadFalthruuDocumentEvent(event, event.fileRef));

					//processor.update(new LoadFalthruuTreeEvent(this,this.fileRef));

					break;

				default:
					JOptionPane.showMessageDialog(apmlgui, "Unknown file type; no can help.");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}
}

class BuildApmlUserInterfaceRequest
{
	private UserInterfaceProcessor processor;

	private JPanel_001 jpanel_001 = (JPanel_001) Bodi.context("editor").pull("//editor/ui/jpanel_apml_001");

	private Uicompiler ui_compiler = new Uicompiler();

	private JFileChooser output = new JFileChooser();

	private JFileChooser input = new JFileChooser();

	private String target_text;

	public BuildApmlUserInterfaceRequest(UserInterfaceProcessor processor)
	{
		this.processor = processor;
	}

	public void run()
	{

		jpanel_001 = (JPanel_001) Bodi.context("editor").pull("//editor/ui/jpanel_apml_001");

		this.target_text = jpanel_001.rstextpane.getText();

		this.ui_compiler = new Uicompiler();

		//

		if (this.target_text == null || this.target_text.length() == 0)
		{
			this.input.setDialogTitle("Please Select UI APML Document");

			this.input.setFileSelectionMode(JFileChooser.FILES_ONLY);

			this.input.showOpenDialog((APMLGui) Bodi.context("editor").pull("//editor/ui/apmlgui"));

			//

			processor.last_loaded_file = this.ui_compiler.fileguardian.xmlin = this.input.getSelectedFile();

			processor.last_loaded_file_url = this.input.getSelectedFile().getName();
		}

		//

		this.output = new JFileChooser();

		this.output.setDialogTitle("Please Select Output Directory");

		this.output.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		this.output.showSaveDialog((APMLGui) Bodi.context("editor").pull("//editor/ui/apmlgui"));

		//

		this.ui_compiler.fileguardian.outputdir = this.output.getSelectedFile();

		//

		this.ui_compiler.dohandleinputfiles(this.ui_compiler.inputmanager);

		this.ui_compiler.dohandleoutputfiles(this.ui_compiler.outputmanager);
	}
}

class BuildApmlStandaloneRequest
{
	private UserInterfaceProcessor processor;

	private APMLGui apmlgui = (APMLGui) Bodi.context("editor").pull("//editor/ui/apmlgui");

	private JPanel_001 jpanel_001 = (JPanel_001) Bodi.context("editor").pull("//editor/ui/jpanel_apml_001");

	private Bloqcompiler bloq_compiler = new Bloqcompiler();

	private JFileChooser output = new JFileChooser();

	private JFileChooser input = new JFileChooser();

	private String target_text;

	public BuildApmlStandaloneRequest(UserInterfaceProcessor processor)
	{
		this.processor = processor;
	}

	public void run()
	{
		//

		this.target_text = jpanel_001.rstextpane.getText();

		//

		this.bloq_compiler = new Bloqcompiler();

		//

		if (this.target_text == null || this.target_text.length() == 0)
		{
			//

			this.input = new JFileChooser();

			this.input.setDialogTitle("Please Select APML Document");

			this.input.setFileSelectionMode(JFileChooser.FILES_ONLY);

			this.input.showOpenDialog((APMLGui) Bodi.context("editor").pull("//editor/ui/apmlgui"));

			//

			processor.last_loaded_file = this.bloq_compiler.fileguardian.apmlinputfile = this.input.getSelectedFile();

			processor.last_loaded_file_url = this.bloq_compiler.fileguardian.apmlfilename = this.input.getSelectedFile().getName();

			//

			System.out.println("TODO: Please verify integrity of APML file");

			//

			this.output.setDialogTitle("Please Select Output Directory");

			this.output.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

			this.output.showSaveDialog((APMLGui) Bodi.context("editor").pull("//editor/ui/apmlgui"));

			//

			this.bloq_compiler.fileguardian.basedirurl = this.output.getSelectedFile().getPath().toString() + "/";

			this.bloq_compiler.fileguardian.apmloutjarurl = this.output.getSelectedFile().getPath() + "/out/jar/";

			this.bloq_compiler.fileguardian.apmlinjarurl = this.output.getSelectedFile().getPath() + "/in/jar/";

			this.bloq_compiler.fileguardian.apmlinurl = this.output.getSelectedFile().getPath() + "/in/";

			//

			this.bloq_compiler.setapmlfiles(this.bloq_compiler.fileguardian);

			this.bloq_compiler.settempfiles(this.bloq_compiler.inputmanager);

			this.bloq_compiler.setoutputfiles(this.bloq_compiler.inputmanager);

			this.bloq_compiler.setsourcefiles(this.bloq_compiler.outputmanager);

			this.bloq_compiler.writebytecode(this.bloq_compiler.inputmanager);

			try
			{
				this.bloq_compiler.writejarfile();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			this.output = new JFileChooser();

			this.output.setDialogTitle("Please Select Output Directory");

			this.output.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

			this.output.showSaveDialog((APMLGui) Bodi.context("editor").pull("//editor/ui/apmlgui"));

			//

			this.bloq_compiler.fileguardian.basedirurl = this.output.getSelectedFile().getPath().toString() + "/";

			this.bloq_compiler.fileguardian.apmlinurl = this.output.getSelectedFile().getPath() + "/in/";

			this.bloq_compiler.fileguardian.apmloutjarurl = this.output.getSelectedFile().getPath() + "/out/jar/";

			this.bloq_compiler.fileguardian.apmlinjarurl = this.output.getSelectedFile().getPath() + "/in/jar/";

			//

			processor.last_loaded_file = this.bloq_compiler.fileguardian.apmlinputfile;

			processor.last_loaded_file_url = this.bloq_compiler.fileguardian.apmlfilename;

			if (processor.last_loaded_file == null || processor.last_loaded_file_url == null || processor.last_loaded_file_url.length() == 0)
			{
				JOptionPane.showMessageDialog(apmlgui, "Unable to determing existing file for APML input. Double check.");
			}

			//

			this.bloq_compiler.setapmlfiles(this.bloq_compiler.fileguardian);

			this.bloq_compiler.settempfiles(this.bloq_compiler.inputmanager);

			this.bloq_compiler.setoutputfiles(this.bloq_compiler.inputmanager);

			this.bloq_compiler.setsourcefiles(this.bloq_compiler.outputmanager);

			this.bloq_compiler.writebytecode(this.bloq_compiler.inputmanager);

			//

			JOptionPane.showMessageDialog(apmlgui, "Compilation successful; check folder for files.");
		}
	}
}