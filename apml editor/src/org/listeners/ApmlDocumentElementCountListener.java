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

		Bodi.context("system").put("//ui/listeners/ApmlDocumentElementCountListener", this);
	}

	@Override
	public void insertUpdate(DocumentEvent e)
	{

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
