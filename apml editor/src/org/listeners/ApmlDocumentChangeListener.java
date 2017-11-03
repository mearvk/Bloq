package org.listeners;

import apml.system.bodi.Bodi;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;

public class ApmlDocumentChangeListener implements DocumentListener
{
	public static Integer DOM_element_count = 0;

	public ApmlDocumentChangeListener()
	{
		//bodi

		Bodi.context("system").put("//ui/listeners/ApmlDocumentChangeListener", this);
	}

	@Override
	public void insertUpdate(DocumentEvent e)
	{
		javax.swing.text.Document plain_doc;

		org.w3c.dom.Document xml_doc;

		Integer count;

		String text;

		//

		plain_doc = e.getDocument();

		count = plain_doc.getLength();

		try
		{
			text = plain_doc.getText(0, count);

			xml_doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(text.getBytes()));

			count = xml_doc.getDocumentElement().getChildNodes().getLength();

			System.out.println(count);
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}

		//
	}

	@Override
	public void removeUpdate(DocumentEvent e)
	{

	}

	@Override
	public void changedUpdate(DocumentEvent e)
	{

	}
}