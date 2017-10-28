package org.events;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SaveApmlDocumentEvent extends ActionEvent
{
	JComponent source;

	public SaveApmlDocumentEvent(JComponent source)
	{
		super(source, 0, "save_apml_document_event");

		this.source = source;
	}

	public SaveApmlDocumentEvent(Object object, Integer id, String command)
	{
		super(object, id, "save_apml_document");
	}
}
