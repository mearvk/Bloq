package org.quran;

import org.quran.widgets.JTree_Quran_000;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import java.io.File;

public class QuranLoader
{
	public File file = null;

	public String fileRef = "/Users/mrupplin/IdeaProjects/bloq/Quran/lib/Dutch-15.xml";

	public XPath xpath;

	public QuranLoader()
	{
		try
		{
			this.file = new File(fileRef);
		}
		catch(Exception e)
		{
			//
		}
	}

	public void init()
	{
		JTree_Quran_000 jtree;

		this.xpath = XPathFactory.newInstance().newXPath();
	}
}
