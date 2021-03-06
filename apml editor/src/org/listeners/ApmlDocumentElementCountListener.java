package org.listeners;

import apml.system.bodi.Bodi;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ApmlDocumentElementCountListener implements DocumentListener
{
	public static Integer last_known_element_count = 0;

	public ApmlDocumentElementCountListener()
	{
		//bodi

		Bodi.context("system").put("//ui/listeners/apml_document_element_count_listener", this);

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
