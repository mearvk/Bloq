package org.events;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class CloseBodiDocumentEvent extends ActionEvent
{
	JComponent source;

	public CloseBodiDocumentEvent(JComponent source)
	{
		super(source, 0, "close_bodi_document_event");

		this.source = source;
	}

	public CloseBodiDocumentEvent(Object object, Integer id, String command)
	{
		super(object, id, "close_bodi_document_event");
	}
}