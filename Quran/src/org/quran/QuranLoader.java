package org.quran;

import apml.system.bodi.Bodi;
import org.quran.events.LoadQuranTreeEvent;
import org.quran.widgets.JTree_Quran_000;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import java.awt.event.ActionEvent;
import java.io.File;

public class QuranLoader extends Thread
{
	public File file = null;

	public String fileRef = "/Users/mrupplin/IdeaProjects/bloq/Quran/lib/Dutch-15.xml";

	public XPath xpath;

	public QuranLoader()
	{
		try
		{
			this.file = new File(fileRef);

			//

			this.start();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void run()
	{
		JTree_Quran_000 jtree;

		jtree = (JTree_Quran_000) Bodi.context("editor").pull("//editor/ui/jtree_quran_000");

		jtree.update(new LoadQuranTreeEvent(new ActionEvent(jtree, 0, "load_quran_tree_event"), this.file));

		jtree.removenewlinetextnodes();
	}

	public void init()
	{
		try
		{
			this.file = new File(fileRef);

			//

			this.start();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
