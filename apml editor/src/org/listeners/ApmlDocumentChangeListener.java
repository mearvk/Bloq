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

		Bodi.context("system").put("//ui/listeners/apml_document_change_listener", this);

		//

		/* Go check ModelInterfaceObserver for the day */
	}

	@Override
	public void insertUpdate(DocumentEvent e)
	{
		//
	}

	@Override
	public void removeUpdate(DocumentEvent e)
	{
		//
	}

	@Override
	public void changedUpdate(DocumentEvent e)
	{
		//
	}
}