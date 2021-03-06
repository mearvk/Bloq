package org.system;

import apml.modeling.Apmlsystem;
import apml.system.Apmlbasesystem;
import apml.system.bodi.Bodi;
import org.events.ReloadApmlTreeEvent;
import org.system.interfaces.BooleanRunnable;
import org.widgets.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.ByteArrayInputStream;

public class ModelInterfaceSystem extends Apmlsystem
{
	public final String bodi = "//editor/system/modelinterfacesystem";

	//

	public static final String static_bodi = "//editor/system/modelinterfacesystem";

	//

	public final String id = "userinterfacesystem";
	public final String tag = "system";
	public ModelInterfaceObserver object_000 = null;
	protected Apmlbasesystem monitor;

	//
	public ModelInterfaceSystem(final Apmlbasesystem monitor)
	{
		//instantiation

		//this.object_000 = new ModelInterfaceObserver();

		// bodi

		Bodi.context("editor").put("//editor/ui/observers/model_interface_system", this);

		// devolvement

		this.monitor = monitor;
	}

	//
	public ModelInterfaceSystem()
	{
		//instantiation

		//this.object_000 = new ModelInterfaceObserver();

		// bodi

		Bodi.context("editor").put("//editor/ui/observers/model_interface_system", this);
	}

	public static ModelInterfaceSystem ref()
	{
		return (ModelInterfaceSystem) Bodi.context("editor").pull(ModelInterfaceSystem.static_bodi);
	}

	public void update(ActionEvent event)
	{
		String action = event.getActionCommand();

		//

		switch (action)
		{
			case "apml_structure_updated_event":

				new ApmlStructureUpdateRequest().run();

				break;

			case "bloq_structure_updated_event":

				new BloqStructureUpdateRequest().run();

				break;

			case "bodi_structure_updated_event":

				new BodiStructureUpdateRequest().run();

				break;

			case "munction_structure_updated_event":

				new MunctionStructureUpdateRequest().run();

				break;

			case "runyn_structure_updated_event":

				new RunynStructureUpdateRequest().run();

				break;

			case "sprung_structure_updated_event":

				new SprungStructureUpdateRequest().run();

				break;

			case "falthruu_structure_updated_event":

				new FalthruuStructureUpdateRequest().run();

				break;
		}
	}
}

class GradeAsManifestFile implements BooleanRunnable
{
	@Override
	public Boolean run()
	{
		return null;
	}

	@Override
	public Boolean run(Object object)
	{
		return false;
	}

	@Override
	public Boolean ex_run() throws Exception
	{
		return null;
	}

	@Override
	public Boolean ex_run(Object object) throws Exception
	{
		return null;
	}
}

class ApmlStructureUpdateRequest implements Runnable
{
	public RSTextPane_Apml_000 textpane;

	public JTree_Apml_000 jtree;

	public JTree_Apml_000 jtree_prior;

	//

	ApmlStructureUpdateRequest()
	{
		this.textpane = (RSTextPane_Apml_000) Bodi.context("editor").pull("//editor/ui/rstextpane_apml_000");

		this.jtree = (JTree_Apml_000) Bodi.context("editor").pull("//editor/ui/jtree_apml_000");
	}

	@Override
	public void run()
	{
		ByteArrayInputStream bais;

		ReloadApmlTreeEvent reload_tree_event;

		//

		bais = new ByteArrayInputStream(this.textpane.getText().getBytes());

		reload_tree_event = new ReloadApmlTreeEvent(new ActionEvent(this, 0, "reload_apml_tree_event"), bais);

		//

		this.jtree.loadfrombytes(reload_tree_event);

		this.jtree.removenewlinetextnodes();

		//
	}
}

class BloqStructureUpdateRequest implements Runnable
{
	public RSTextPane_Bloq_000 textpane;

	public JTree_Bloq_000 jtree;

	BloqStructureUpdateRequest()
	{
		this.textpane = (RSTextPane_Bloq_000) Bodi.context("editor").pull("//editor/ui/rstextpane_bloq_000");

		this.jtree = (JTree_Bloq_000) Bodi.context("editor").pull("//editor/ui/jtree_bloq_000");
	}

	@Override
	public void run()
	{
		JOptionPane.showMessageDialog(null, "Number of elements or attributes has changed.");
	}
}

class BodiStructureUpdateRequest implements Runnable
{
	public RSTextPane_Bodi_000 textpane;

	public JTree_Bodi_000 jtree;

	BodiStructureUpdateRequest()
	{
		this.textpane = (RSTextPane_Bodi_000) Bodi.context("editor").pull("//editor/ui/rstextpane_bodi_000");

		this.jtree = (JTree_Bodi_000) Bodi.context("editor").pull("//editor/ui/jtree_bodi_000");
	}

	@Override
	public void run()
	{
		JOptionPane.showMessageDialog(null, "Number of elements or attributes has changed.");
	}
}

class MunctionStructureUpdateRequest implements Runnable
{
	public RSTextPane_Munction_000 textpane;

	public JTree_Munction_000 jtree;

	MunctionStructureUpdateRequest()
	{
		this.textpane = (RSTextPane_Munction_000) Bodi.context("editor").pull("//editor/ui/rstextpane_munction_000");

		this.jtree = (JTree_Munction_000) Bodi.context("editor").pull("//editor/ui/jtree_munction_000");
	}


	@Override
	public void run()
	{
		JOptionPane.showMessageDialog(null, "Number of elements or attributes has changed.");
	}
}

class RunynStructureUpdateRequest implements Runnable
{
	public RSTextPane_Runyn_000 textpane;

	public JTree_Runyn_000 jtree;

	RunynStructureUpdateRequest()
	{
		this.textpane = (RSTextPane_Runyn_000) Bodi.context("editor").pull("//editor/ui/rstextpane_runyn_000");

		this.jtree = (JTree_Runyn_000) Bodi.context("editor").pull("//editor/ui/jtree_runyn_000");
	}


	@Override
	public void run()
	{
		JOptionPane.showMessageDialog(null, "Number of elements or attributes has changed.");
	}
}

class SprungStructureUpdateRequest implements Runnable
{
	public RSTextPane_Sprung_000 textpane;

	public JTree_Sprung_000 jtree;

	SprungStructureUpdateRequest()
	{
		this.textpane = (RSTextPane_Sprung_000) Bodi.context("editor").pull("//editor/ui/rstextpane_sprung_000");

		this.jtree = (JTree_Sprung_000) Bodi.context("editor").pull("//editor/ui/jtree_apml_000");
	}

	@Override
	public void run()
	{
		JOptionPane.showMessageDialog(null, "Number of elements or attributes has changed.");
	}
}

class FalthruuStructureUpdateRequest implements Runnable
{
	public RSTextPane_Falthruu_000 textpane;

	public JTree_Falthruu_000 jtree;

	FalthruuStructureUpdateRequest()
	{
		this.textpane = (RSTextPane_Falthruu_000) Bodi.context("editor").pull("//editor/ui/rstextpane_falthruu_000");

		this.jtree = (JTree_Falthruu_000) Bodi.context("editor").pull("//editor/ui/jtree_falthruu_000");
	}

	@Override
	public void run()
	{
		JOptionPane.showMessageDialog(null, "Number of elements or attributes has changed.");
	}
}