package org.listeners;

import apml.system.bodi.Bodi;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.widgets.APMLGui;

import javax.swing.*;
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

		APMLGui apmlgui;

		//

		element = this.textarea.getDocument().getDefaultRootElement();

		apmlgui = (APMLGui) Bodi.context("editor").pull("//editor/ui/apmlgui");

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