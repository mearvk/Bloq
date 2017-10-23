package org.events;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class CloseApmlDocumentEvent extends ActionEvent
{
	JComponent source;

	public CloseApmlDocumentEvent(JComponent source)
	{
		super(source, 0, "close_apml_document_update");

		this.source = source;
	}

	public CloseApmlDocumentEvent(Object object, Integer id, String command)
	{
		super(object, id, "close_apml_document_update");
	}
}
