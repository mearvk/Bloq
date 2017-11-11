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
			// general requests //

			case "open_document_event":

				new OpenDocumentRequest(this, event).run();

				break;

			case "save_document_event":

				new SaveDocumentRequest(this, event).run();

				break;

			case "exit_program_event":

				new ExitProgramRequest(this, event).run();

				break;

			case "tree_structure_updated_event":

				new TreeStructureUpdatedRequest(this, event).run();

				break;

			// apml //

			case "build_ui_apml_request_event":

				new BuildApmlUserInterfaceRequest(this).run();

				break;

			case "build_apml_standalone_request_event":

				new BuildApmlStandaloneRequest(this).run();

				break;

			case "close_apml_document_event":

				new CloseApmlDocumentRequest(this, event).run();

				break;

			case "load_apml_document_event":

				new LoadApmlDocumentRequest(this, event).run();

				break;

			case "load_apml_tree_event":

				new LoadApmlTreeRequest(this, event).run();

				break;

			case "reload_apml_tree_event":

				new ReloadApmlTreeRequest(this, event).run();

				break;

			case "save_apml_document_event":

				new SaveApmlDocumentRequest(this, event).run();

				break;

			// bodi //

			case "load_bodi_document_event":

				new LoadBodiDocumentRequest(this, event).run();

				break;

			case "load_bodi_tree_event":

				new LoadBodiTreeRequest(this, event).run();

				break;

			case "save_bodi_document_event":

				new SaveBodiDocumentRequest(this, event).run();

				break;

			// bloq //

			case "load_bloq_document_event":

				new LoadBloqDocumentRequest(this, event).run();

				break;

			case "load_bloq_tree_event":

				new LoadBloqTreeRequest(this, event).run();

				break;

			case "save_bloq_document_event":

				new SaveBloqDocumentRequest(this, event).run();

				break;

			// sprung //

			case "load_sprung_document_event":

				new LoadSprungDocumentRequest(this, event).run();

				break;

			case "load_sprung_tree_event":

				new LoadSprungTreeRequest(this, event).run();

				break;

			case "save_sprung_document_event":

				new SaveSprungDocumentRequest(this, event).run();

				break;

			// runyn //

			case "load_runyn_document_event":

				new LoadRunynDocumentRequest(this, event).run();

				break;

			case "load_runyn_tree_event":

				new LoadRunynTreeRequest(this, event).run();

				break;

			case "save_runyn_document_event":

				new SaveRunynDocumentRequest(this, event).run();

				break;

			// falthruu //

			case "load_falthruu_document_event":

				new LoadFalthruuDocumentRequest(this, event).run();

				break;

			case "load_falthruu_tree_event":

				new LoadFalthruuTreeRequest(this, event).run();

				break;

			case "save_falthruu_document_event":

				new SaveFalthruuDocumentRequest(this, event).run();

				break;

			// munction_analysis //

			case "load_munction_document_event":

				new LoadMunctionDocumentRequest(this, event).run();

				break;

			case "load_munction_tree_event":

				new LoadMunctionTreeRequest(this, event).run();

				break;

			case "save_munction_document_event":

				new SaveMunctionDocumentRequest(this, event).run();

				break;

			//

			case "document_loaded_event":

				new UpdateAllOnDocumentLoadedRequest(this, event).run();

				break;

			//

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

//end apml

//start general case

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

class LoadBodiTreeRequest
{
	public UserInterfaceProcessor processor;

	public LoadBodiTreeEvent event;

	public LoadBodiTreeRequest(UserInterfaceProcessor processor, ActionEvent event)
	{
		this.processor = processor;

		this.event = (LoadBodiTreeEvent)event;
	}

	public void run()
	{
		JTree_Bodi_000 jtree_bodi_000;

		jtree_bodi_000 = (JTree_Bodi_000) Bodi.context("editor").pull("//editor/ui/jtree_bodi_000");

		jtree_bodi_000.init();

		jtree_bodi_000.update(event);

		jtree_bodi_000.removenewlinetextnodes();
	}
}

class LoadBodiDocumentRequest
{
	public UserInterfaceProcessor processor;

	public LoadBodiDocumentEvent event;

	public LoadBodiDocumentRequest(UserInterfaceProcessor processor, ActionEvent event)
	{
		this.processor = processor;

		this.event = (LoadBodiDocumentEvent) event;
	}

	public void run()
	{
		this.loaddocument(event);
	}

	private void loaddocument(LoadBodiDocumentEvent event)
	{
		RSTextPane_Bodi_000 rstextpane_bodi_000;

		rstextpane_bodi_000 = (RSTextPane_Bodi_000) Bodi.context("editor").pull("//editor/ui/rstextpane_bodi_000");

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

			rstextpane_bodi_000.setText(buffer + "\n");

			rstextpane_bodi_000.setCaretPosition(000);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
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

		jtree_bloq_000.update(event);

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

class SaveBloqDocumentRequest
{
	public UserInterfaceProcessor processor;

	public SaveBloqDocumentEvent event;

	public APMLGui apmlgui;

	public RSTextPane_Bloq_000 rstextpane_bloq_000;

	public SaveBloqDocumentRequest(UserInterfaceProcessor processor, ActionEvent event)
	{
		this.processor = processor;

		this.event = (SaveBloqDocumentEvent) event;

		this.apmlgui = (APMLGui) Bodi.context("editor").pull("//editor/ui/apmlgui");

		this.rstextpane_bloq_000 = (RSTextPane_Bloq_000) Bodi.context("editor").pull("//editor/ui/rstextpane_bloq_000");
	}

	public void run()
	{
		this.savedocument(event);
	}

	//
	private void savedocument(SaveBloqDocumentEvent event)
	{
		try
		{
			String text = this.rstextpane_bloq_000.getText();

			if(text==null || text.length()==0) throw new Exception("No data");

			//

			JFileChooser chooser = new JFileChooser();

			int retval = chooser.showSaveDialog(this.apmlgui);

			if (retval == JFileChooser.APPROVE_OPTION)
			{
				File file = chooser.getSelectedFile();

				BufferedWriter writer;

				writer = new BufferedWriter(new FileWriter(file));

				writer.write(this.rstextpane_bloq_000.getText(), 0, this.rstextpane_bloq_000.getText().length());

				writer.flush();

				writer.close();
			}
			else throw new Exception("Aborted.");

			JOptionPane.showMessageDialog(this.apmlgui, "Document successfully saved.");
		}
		catch (Exception e)
		{
			e.printStackTrace();

			if(e.getMessage()!=null && e.getMessage().contains("Aborted"))
				JOptionPane.showMessageDialog(this.apmlgui, "Document was not saved.");
			else if(e.getMessage()!=null && e.getMessage().contains("No data"))
				JOptionPane.showMessageDialog(this.apmlgui, "No data loaded; document was not saved.");
			else
				JOptionPane.showMessageDialog(this.apmlgui, "Error saving document.");
		}
	}
}

class LoadFalthruuDocumentRequest
{
	public UserInterfaceProcessor processor;

	public LoadFalthruuDocumentEvent event;

	public LoadFalthruuDocumentRequest(UserInterfaceProcessor processor, ActionEvent event)
	{
		this.processor = processor;

		this.event = (LoadFalthruuDocumentEvent) event;
	}

	public void run()
	{
		this.loaddocument(event);
	}

	private void loaddocument(LoadFalthruuDocumentEvent event)
	{
		RSTextPane_Falthruu_000 rstextpane_falthruu_000;

		rstextpane_falthruu_000 = (RSTextPane_Falthruu_000) Bodi.context("editor").pull("//editor/ui/rstextpane_falthruu_000");

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

			rstextpane_falthruu_000.setText(buffer + "\n");

			rstextpane_falthruu_000.setCaretPosition(000);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}

class LoadFalthruuTreeRequest
{
	public UserInterfaceProcessor processor;

	public LoadFalthruuTreeEvent event;

	public LoadFalthruuTreeRequest(UserInterfaceProcessor processor, ActionEvent event)
	{
		this.processor = processor;

		this.event = (LoadFalthruuTreeEvent) event;
	}

	public void run()
	{
		JTree_Falthruu_000 jtree_falthruu_000;

		jtree_falthruu_000 = (JTree_Falthruu_000) Bodi.context("editor").pull("//editor/ui/jtree_falthruu_000");

		jtree_falthruu_000.init();

		jtree_falthruu_000.update(event);

		jtree_falthruu_000.removenewlinetextnodes();
	}
}

class SaveFalthruuDocumentRequest
{
	public UserInterfaceProcessor processor;

	public SaveFalthruuDocumentEvent event;

	public APMLGui apmlgui;

	public RSTextPane_Falthruu_000 rstextpane_falthruu_000;

	public SaveFalthruuDocumentRequest(UserInterfaceProcessor processor, ActionEvent event)
	{
		this.processor = processor;

		this.event = (SaveFalthruuDocumentEvent) event;

		this.apmlgui = (APMLGui) Bodi.context("editor").pull("//editor/ui/apmlgui");

		this.rstextpane_falthruu_000 = (RSTextPane_Falthruu_000) Bodi.context("editor").pull("//editor/ui/rstextpane_falthruu_000");
	}

	public void run()
	{
		this.savedocument(event);
	}

	//
	private void savedocument(SaveFalthruuDocumentEvent event)
	{
		try
		{
			String text = this.rstextpane_falthruu_000.getText();

			if(text==null || text.length()==0) throw new Exception("No data");

			//

			JFileChooser chooser = new JFileChooser();

			int retval = chooser.showSaveDialog(this.apmlgui);

			if (retval == JFileChooser.APPROVE_OPTION)
			{
				File file = chooser.getSelectedFile();

				BufferedWriter writer;

				writer = new BufferedWriter(new FileWriter(file));

				writer.write(this.rstextpane_falthruu_000.getText(), 0, this.rstextpane_falthruu_000.getText().length());

				writer.flush();

				writer.close();
			}
			else throw new Exception("Aborted.");

			JOptionPane.showMessageDialog(this.apmlgui, "Document successfully saved.");
		}
		catch (Exception e)
		{
			e.printStackTrace();

			if(e.getMessage()!=null && e.getMessage().contains("Aborted"))
				JOptionPane.showMessageDialog(this.apmlgui, "Document was not saved.");
			else if(e.getMessage()!=null && e.getMessage().contains("No data"))
				JOptionPane.showMessageDialog(this.apmlgui, "No data loaded; document was not saved.");
			else
				JOptionPane.showMessageDialog(this.apmlgui, "Error saving document.");
		}
	}
}

class LoadMunctionDocumentRequest
{
	public UserInterfaceProcessor processor;

	public LoadMunctionDocumentEvent event;

	public LoadMunctionDocumentRequest(UserInterfaceProcessor processor, ActionEvent event)
	{
		this.processor = processor;

		this.event = (LoadMunctionDocumentEvent) event;
	}

	public void run()
	{
		this.loaddocument(event);
	}

	private void loaddocument(LoadMunctionDocumentEvent event)
	{
		RSTextPane_Munction_000 rstextpane_munction_000;

		rstextpane_munction_000 = (RSTextPane_Munction_000) Bodi.context("editor").pull("//editor/ui/rstextpane_munction_000");

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

			rstextpane_munction_000.setText(buffer + "\n");

			rstextpane_munction_000.setCaretPosition(000);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}

class LoadMunctionTreeRequest
{
	public UserInterfaceProcessor processor;

	public LoadMunctionTreeEvent event;

	public LoadMunctionTreeRequest(UserInterfaceProcessor processor, ActionEvent event)
	{
		this.processor = processor;

		this.event = (LoadMunctionTreeEvent) event;
	}

	public void run()
	{
		JTree_Munction_000 jtree_munction_000;

		jtree_munction_000 = (JTree_Munction_000) Bodi.context("editor").pull("//editor/ui/jtree_munction_000");

		jtree_munction_000.init();

		jtree_munction_000.update(event);

		jtree_munction_000.removenewlinetextnodes();
	}
}

class SaveMunctionDocumentRequest
{
	public UserInterfaceProcessor processor;

	public SaveMunctionDocumentEvent event;

	public APMLGui apmlgui;

	public RSTextPane_Munction_000 rstextpane_munction_000;

	public SaveMunctionDocumentRequest(UserInterfaceProcessor processor, ActionEvent event)
	{
		this.processor = processor;

		this.event = (SaveMunctionDocumentEvent) event;

		this.apmlgui = (APMLGui) Bodi.context("editor").pull("//editor/ui/apmlgui");

		this.rstextpane_munction_000 = (RSTextPane_Munction_000) Bodi.context("editor").pull("//editor/ui/rstextpane_munction_000");
	}


	public void run()
	{
		this.savedocument(event);
	}

	//
	private void savedocument(SaveMunctionDocumentEvent event)
	{
		try
		{
			String text = this.rstextpane_munction_000.getText();

			if(text==null || text.length()==0) throw new Exception("No data");

			//

			JFileChooser chooser = new JFileChooser();

			int retval = chooser.showSaveDialog(this.apmlgui);

			if (retval == JFileChooser.APPROVE_OPTION)
			{
				File file = chooser.getSelectedFile();

				BufferedWriter writer;

				writer = new BufferedWriter(new FileWriter(file));

				writer.write(this.rstextpane_munction_000.getText(), 0, this.rstextpane_munction_000.getText().length());

				writer.flush();

				writer.close();
			}
			else throw new Exception("Aborted.");

			JOptionPane.showMessageDialog(this.apmlgui, "Document successfully saved.");
		}
		catch (Exception e)
		{
			e.printStackTrace();

			if(e.getMessage()!=null && e.getMessage().contains("Aborted"))
				JOptionPane.showMessageDialog(this.apmlgui, "Document was not saved.");
			else if(e.getMessage()!=null && e.getMessage().contains("No data"))
				JOptionPane.showMessageDialog(this.apmlgui, "No data loaded; document was not saved.");
			else
				JOptionPane.showMessageDialog(this.apmlgui, "Error saving document.");
		}
	}
}

class LoadRunynDocumentRequest
{
	public UserInterfaceProcessor processor;

	public LoadRunynDocumentEvent event;

	public LoadRunynDocumentRequest(UserInterfaceProcessor processor, ActionEvent event)
	{
		this.processor = processor;

		this.event = (LoadRunynDocumentEvent) event;
	}

	public void run()
	{
		this.loaddocument(event);
	}

	private void loaddocument(LoadRunynDocumentEvent event)
	{
		RSTextPane_Runyn_000 rstextpane_runyn_000;

		rstextpane_runyn_000 = (RSTextPane_Runyn_000) Bodi.context("editor").pull("//editor/ui/rstextpane_runyn_000");

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

			rstextpane_runyn_000.setText(buffer + "\n");

			rstextpane_runyn_000.setCaretPosition(000);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}

class LoadRunynTreeRequest
{
	public UserInterfaceProcessor processor;

	public LoadRunynTreeEvent event;

	public LoadRunynTreeRequest(UserInterfaceProcessor processor, ActionEvent event)
	{
		this.processor = processor;

		this.event = (LoadRunynTreeEvent) event;
	}

	public void run()
	{
		JTree_Runyn_000 jtree_runyn_000;

		jtree_runyn_000 = (JTree_Runyn_000) Bodi.context("editor").pull("//editor/ui/jtree_runyn_000");

		jtree_runyn_000.init();

		jtree_runyn_000.update(event);

		jtree_runyn_000.removenewlinetextnodes();
	}
}

class SaveRunynDocumentRequest
{
	public UserInterfaceProcessor processor;

	public SaveRunynDocumentEvent event;

	public APMLGui apmlgui;

	public RSTextPane_Runyn_000 rstextpane_runyn_000;

	public SaveRunynDocumentRequest(UserInterfaceProcessor processor, ActionEvent event)
	{
		this.processor = processor;

		this.event = (SaveRunynDocumentEvent) event;

		this.apmlgui = (APMLGui) Bodi.context("editor").pull("//editor/ui/apmlgui");

		this.rstextpane_runyn_000 = (RSTextPane_Runyn_000) Bodi.context("editor").pull("//editor/ui/rstextpane_runyn_000");
	}


	public void run()
	{
		this.savedocument(event);
	}

	//
	private void savedocument(SaveRunynDocumentEvent event)
	{
		try
		{
			String text = this.rstextpane_runyn_000.getText();

			if(text==null || text.length()==0) throw new Exception("No data");

			//

			JFileChooser chooser = new JFileChooser();

			int retval = chooser.showSaveDialog(this.apmlgui);

			if (retval == JFileChooser.APPROVE_OPTION)
			{
				File file = chooser.getSelectedFile();

				BufferedWriter writer;

				writer = new BufferedWriter(new FileWriter(file));

				writer.write(this.rstextpane_runyn_000.getText(), 0, this.rstextpane_runyn_000.getText().length());

				writer.flush();

				writer.close();
			}
			else throw new Exception("Aborted.");

			JOptionPane.showMessageDialog(this.apmlgui, "Document successfully saved.");
		}
		catch (Exception e)
		{
			e.printStackTrace();

			if(e.getMessage()!=null && e.getMessage().contains("Aborted"))
				JOptionPane.showMessageDialog(this.apmlgui, "Document was not saved.");
			else if(e.getMessage()!=null && e.getMessage().contains("No data"))
				JOptionPane.showMessageDialog(this.apmlgui, "No data loaded; document was not saved.");
			else
				JOptionPane.showMessageDialog(this.apmlgui, "Error saving document.");
		}
	}
}

class LoadSprungDocumentRequest
{
	public UserInterfaceProcessor processor;

	public LoadSprungDocumentEvent event;

	public LoadSprungDocumentRequest(UserInterfaceProcessor processor, ActionEvent event)
	{
		this.processor = processor;

		this.event = (LoadSprungDocumentEvent) event;
	}

	public void run()
	{
		this.loaddocument(event);
	}

	private void loaddocument(LoadSprungDocumentEvent event)
	{
		RSTextPane_Sprung_000 rstextpane_sprung_000;

		rstextpane_sprung_000 = (RSTextPane_Sprung_000) Bodi.context("editor").pull("//editor/ui/rstextpane_sprung_000");

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

			rstextpane_sprung_000.setText(buffer + "\n");

			rstextpane_sprung_000.setCaretPosition(000);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}

class LoadSprungTreeRequest
{
	public UserInterfaceProcessor processor;

	public LoadSprungTreeEvent event;

	public LoadSprungTreeRequest(UserInterfaceProcessor processor, ActionEvent event)
	{
		this.processor = processor;

		this.event = (LoadSprungTreeEvent) event;
	}

	public void run()
	{
		JTree_Sprung_000 jtree_sprung_000;

		jtree_sprung_000 = (JTree_Sprung_000) Bodi.context("editor").pull("//editor/ui/jtree_sprung_000");

		jtree_sprung_000.init();

		jtree_sprung_000.update(event);

		jtree_sprung_000.removenewlinetextnodes();
	}
}

class SaveSprungDocumentRequest
{
	public UserInterfaceProcessor processor;

	public SaveSprungDocumentEvent event;

	public APMLGui apmlgui;

	public RSTextPane_Sprung_000 rstextpane_sprung_000;

	public SaveSprungDocumentRequest(UserInterfaceProcessor processor, ActionEvent event)
	{
		this.processor = processor;

		this.event = (SaveSprungDocumentEvent) event;

		this.apmlgui = (APMLGui) Bodi.context("editor").pull("//editor/ui/apmlgui");

		this.rstextpane_sprung_000 = (RSTextPane_Sprung_000) Bodi.context("editor").pull("//editor/ui/rstextpane_sprung_000");
	}

	public void run()
	{
		this.savedocument(event);
	}

	//
	private void savedocument(SaveSprungDocumentEvent event)
	{
		try
		{
			String text = this.rstextpane_sprung_000.getText();

			if(text==null || text.length()==0) throw new Exception("No data");

			//

			JFileChooser chooser = new JFileChooser();

			int retval = chooser.showSaveDialog(this.apmlgui);

			if (retval == JFileChooser.APPROVE_OPTION)
			{
				File file = chooser.getSelectedFile();

				BufferedWriter writer;

				writer = new BufferedWriter(new FileWriter(file));

				writer.write(this.rstextpane_sprung_000.getText(), 0, this.rstextpane_sprung_000.getText().length());

				writer.flush();

				writer.close();
			}
			else throw new Exception("Aborted.");

			JOptionPane.showMessageDialog(this.apmlgui, "Document successfully saved.");
		}
		catch (Exception e)
		{
			e.printStackTrace();

			if(e.getMessage()!=null && e.getMessage().contains("Aborted"))
				JOptionPane.showMessageDialog(this.apmlgui, "Document was not saved.");
			else if(e.getMessage()!=null && e.getMessage().contains("No data"))
				JOptionPane.showMessageDialog(this.apmlgui, "No data loaded; document was not saved.");
			else
				JOptionPane.showMessageDialog(this.apmlgui, "Error saving document.");
		}
	}
}

class SaveBodiDocumentRequest
{
	public UserInterfaceProcessor processor;

	public SaveBodiDocumentEvent event;

	public APMLGui apmlgui;

	public RSTextPane_Bodi_000 rstextpane_bodi_000;

	//

	public SaveBodiDocumentRequest(UserInterfaceProcessor processor, ActionEvent event)
	{
		this.processor = processor;

		this.event = (SaveBodiDocumentEvent) event;

		this.apmlgui = (APMLGui) Bodi.context("editor").pull("//editor/ui/apmlgui");

		this.rstextpane_bodi_000 = (RSTextPane_Bodi_000) Bodi.context("editor").pull("//editor/ui/rstextpane_bodi_000");
	}

	public void run()
	{
		this.savedocument(event);
	}

	//
	private void savedocument(SaveBodiDocumentEvent event)
	{
		try
		{
			String text = this.rstextpane_bodi_000.getText();

			if(text==null || text.length()==0) throw new Exception("No data");

			//

			JFileChooser chooser = new JFileChooser();

			int retval = chooser.showSaveDialog(this.apmlgui);

			if (retval == JFileChooser.APPROVE_OPTION)
			{
				File file = chooser.getSelectedFile();

				BufferedWriter writer;

				writer = new BufferedWriter(new FileWriter(file));

				writer.write(this.rstextpane_bodi_000.getText(), 0, this.rstextpane_bodi_000.getText().length());

				writer.flush();

				writer.close();
			}
			else throw new Exception("Aborted.");

			JOptionPane.showMessageDialog(this.apmlgui, "Document successfully saved.");
		}
		catch (Exception e)
		{
			e.printStackTrace();

			if(e.getMessage()!=null && e.getMessage().contains("Aborted"))
				JOptionPane.showMessageDialog(this.apmlgui, "Document was not saved.");
			else if(e.getMessage()!=null && e.getMessage().contains("No data"))
				JOptionPane.showMessageDialog(this.apmlgui, "No data loaded; document was not saved.");
			else
				JOptionPane.showMessageDialog(this.apmlgui, "Error saving document.");
		}
	}
}

class ReloadApmlTreeRequest
{
	public UserInterfaceProcessor processor;

	public ReloadApmlTreeEvent event;

	public ReloadApmlTreeRequest(UserInterfaceProcessor processor, ActionEvent event)
	{
		this.processor = processor;

		this.event = (ReloadApmlTreeEvent) event;
	}

	public void run()
	{
		JTree_Apml_000 jtree_apml_000;

		jtree_apml_000 = (JTree_Apml_000) Bodi.context("editor").pull("//editor/ui/jtree_apml_000");

		jtree_apml_000.init();

		jtree_apml_000._update((ReloadApmlTreeEvent) event);

		jtree_apml_000.removenewlinetextnodes();
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
			String text = this.rstextpane_apml_000.getText();

			if(text==null || text.length()==0) throw new Exception("No data");

			//

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
			else throw new Exception("Aborted.");

			JOptionPane.showMessageDialog(this.apmlgui, "Document successfully saved.");
		}
		catch (Exception e)
		{
			e.printStackTrace();

			if(e.getMessage()!=null && e.getMessage().contains("Aborted"))
				JOptionPane.showMessageDialog(this.apmlgui, "Document was not saved.");
			else if(e.getMessage()!=null && e.getMessage().contains("No data"))
				JOptionPane.showMessageDialog(this.apmlgui, "No data loaded; document was not saved.");
			else
				JOptionPane.showMessageDialog(this.apmlgui, "Error saving document.");
		}
	}
}

class BuildApmlUserInterfaceRequest
{
	private UserInterfaceProcessor processor;

	private JPanel_Apml_001 jpanel_001 = (JPanel_Apml_001) Bodi.context("editor").pull("//editor/ui/jpanel_apml_001");

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

		jpanel_001 = (JPanel_Apml_001) Bodi.context("editor").pull("//editor/ui/jpanel_apml_001");

		this.target_text = jpanel_001.rstextpane.getText();

		this.ui_compiler = new Uicompiler();

		//

		if (this.target_text == null || this.target_text.length() == 0)
		{
			this.input.setDialogTitle("Please Select UI APML Document");

			this.input.setFileSelectionMode(JFileChooser.FILES_ONLY);

			this.input.showOpenDialog((APMLGui) Bodi.context("editor").pull("//editor/ui/apmlgui"));

			//

			this.processor.last_loaded_file = this.ui_compiler.fileguardian.xmlin = this.ui_compiler.inputmanager.file = this.input.getSelectedFile();

			this.processor.last_loaded_file_url = this.input.getSelectedFile().getName();
		}
		else
		{
			try
			{
				FileWriter writer;

				File file;

				writer = new FileWriter((file=new File("./tmp")));

				writer.write(this.target_text, 0, this.target_text.length());

				writer.flush();

				writer.close();

				this.ui_compiler.inputmanager.file = file;

				//

				this.processor.last_loaded_file = this.ui_compiler.fileguardian.xmlin = this.ui_compiler.inputmanager.file = file;

				this.processor.last_loaded_file_url = file.getPath();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
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

	private JPanel_Apml_001 jpanel_001 = (JPanel_Apml_001) Bodi.context("editor").pull("//editor/ui/jpanel_apml_001");

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

		rstextpane_000.processtreechange(event);
	}
}

class SaveDocumentRequest
{
	private UserInterfaceProcessor processor;

	private SaveDocumentEvent event;

	private APMLGui apmlgui;

	private JTabbedPane_000 jtabbedpane;

	//

	public SaveDocumentRequest(UserInterfaceProcessor processor, ActionEvent event)
	{
		this.processor = processor;

		this.event = (SaveDocumentEvent) event;
	}

	public void run()
	{
		//

		this.processor = (UserInterfaceProcessor) Bodi.context("editor").pull("//editor/ui/uiprocessor_000");

		this.apmlgui = (APMLGui) Bodi.context("editor").pull("//editor/ui/apmlgui");

		this.jtabbedpane =  (JTabbedPane_000)Bodi.context("editor").pull("//editor/ui/jtabbedpane_000");

		//

		try
		{
			Integer selected_tab = this.jtabbedpane.getSelectedIndex();

			//

			switch (selected_tab)
			{
				//apml
				case 0:

					this.processor.update(new SaveApmlDocumentEvent(event, event.fileRef));

					break;

				//bloq
				case 1:

					this.processor.update(new SaveBloqDocumentEvent(event, event.fileRef));

					break;

				//bodi
				case 2:

					this.processor.update(new SaveBodiDocumentEvent(event, event.fileRef));

					break;

				//munction_analysis
				case 3:

					this.processor.update(new SaveMunctionDocumentEvent(event, event.fileRef));

					break;

				//runyn
				case 4:

					this.processor.update(new SaveRunynDocumentEvent(event, event.fileRef));

					break;

				//sprung
				case 5:

					this.processor.update(new SaveSprungDocumentEvent(event, event.fileRef));

					break;

				//falthruu
				case 6:

					this.processor.update(new SaveFalthruuDocumentEvent(event, event.fileRef));

					break;

				default:

					JOptionPane.showMessageDialog(apmlgui, "Unknown file type; no can help."); break;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
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
				JOptionPane.showMessageDialog(apmlgui, "Unable to locate any document type; make sure you have a project type set ( //project[@type] ).");

			if (this.nodes.getLength() > 1)
				JOptionPane.showMessageDialog(apmlgui, "Unable to locate unambiguous document type; make sure you have a single project type set ( //project[@type] ).");

			//

			element = (Element) this.nodes.item(0);

			type = element.getAttribute("type");

			event.fileType = type.trim().toLowerCase();

			//

			switch (event.fileType)
			{
				case "apml":

					JOptionPane.showMessageDialog(apmlgui, "Apml™ Document Loading...");

					break;

				case "bloq":

					JOptionPane.showMessageDialog(apmlgui, "Bloq™ Document Loading...");

					break;

				case "bodi":

					JOptionPane.showMessageDialog(apmlgui, "Bodi™ Document Loading...");

					break;

				case "munction":

					JOptionPane.showMessageDialog(apmlgui, "Munction™ Document Loading...");

					break;

				case "runyn":

					JOptionPane.showMessageDialog(apmlgui, "Runyn™ Document Loading...");

					break;

				case "sprung":

					JOptionPane.showMessageDialog(apmlgui, "Sprung™ Document Loading...");

					break;

				case "falthruu":

					JOptionPane.showMessageDialog(apmlgui, "Falthruu™ Document Loading...");

					break;

				default:

					JOptionPane.showMessageDialog(apmlgui, "Unknown file type; unable to help.");

					return;
			}

			//



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

					this.processor.update(new LoadBodiTreeEvent(event,event.fileRef));

					//

					this.jtabbedpane_000 = (JTabbedPane_000) Bodi.context("editor").pull("//editor/ui/jtabbedpane_000");

					this.jtabbedpane_000.setSelectedIndex(2);

					break;

				case "munction":

					this.processor.update(new LoadMunctionDocumentEvent(event, event.fileRef));

					this.processor.update(new LoadMunctionTreeEvent(event,event.fileRef));

					//

					this.jtabbedpane_000 = (JTabbedPane_000) Bodi.context("editor").pull("//editor/ui/jtabbedpane_000");

					this.jtabbedpane_000.setSelectedIndex(3);

					break;

				case "runyn":

					this.processor.update(new LoadRunynDocumentEvent(event, event.fileRef));

					this.processor.update(new LoadRunynTreeEvent(event,event.fileRef));

					//

					this.jtabbedpane_000 = (JTabbedPane_000) Bodi.context("editor").pull("//editor/ui/jtabbedpane_000");

					this.jtabbedpane_000.setSelectedIndex(4);

					break;

				case "sprung":

					this.processor.update(new LoadSprungDocumentEvent(event, event.fileRef));

					this.processor.update(new LoadSprungTreeEvent(event,event.fileRef));

					//

					this.jtabbedpane_000 = (JTabbedPane_000) Bodi.context("editor").pull("//editor/ui/jtabbedpane_000");

					this.jtabbedpane_000.setSelectedIndex(5);

					break;

				case "falthruu":

					this.processor.update(new LoadFalthruuDocumentEvent(event, event.fileRef));

					this.processor.update(new LoadFalthruuTreeEvent(event,event.fileRef));

					//

					this.jtabbedpane_000 = (JTabbedPane_000) Bodi.context("editor").pull("//editor/ui/jtabbedpane_000");

					this.jtabbedpane_000.setSelectedIndex(6);

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

