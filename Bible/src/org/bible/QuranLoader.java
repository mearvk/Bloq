package org.bible;

import apml.system.bodi.Bodi;
import org.bible.events.LoadBibleTreeEvent;
import org.bible.widgets.JTree_Bible_000;
import org.bible.events.LoadBibleTreeEvent;
import org.bible.widgets.JTree_Bible_000;

import javax.xml.xpath.XPath;
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
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void run()
	{
		JTree_Bible_000 jtree;

		jtree = (JTree_Bible_000) Bodi.context("editor").pull("//editor/ui/jtree_quran_000");

		jtree.update(new LoadBibleTreeEvent(new ActionEvent(jtree, 0, "load_quran_tree_event"), this.file));

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
