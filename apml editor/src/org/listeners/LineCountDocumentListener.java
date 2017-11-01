package org.listeners;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Element;

public class LineCountDocumentListener implements DocumentListener
{

	public RSyntaxTextArea textarea;

	public LineCountDocumentListener(RSyntaxTextArea textarea)
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