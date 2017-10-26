package org.listeners;

import org.widgets.RSTextPane_000;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Element;

public class LineCountDocumentListener implements DocumentListener
{

	public RSTextPane_000 textarea;

	public LineCountDocumentListener(RSTextPane_000 textarea)
	{
		this.textarea = textarea;
	}

	@Override
	public void insertUpdate(DocumentEvent e)
	{
		Element element;

		element = this.textarea.getDocument().getDefaultRootElement();

		//
	}

	@Override
	public void removeUpdate(DocumentEvent e)
	{
		Element element;

		element = this.textarea.getDocument().getDefaultRootElement();

		//
	}

	@Override
	public void changedUpdate(DocumentEvent e)
	{
		Element element;

		element = this.textarea.getDocument().getDefaultRootElement();

		//
	}
}