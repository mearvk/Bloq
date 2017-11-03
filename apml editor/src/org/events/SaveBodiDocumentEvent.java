package org.events;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SaveBodiDocumentEvent extends ActionEvent
{
	JComponent source;

	public SaveBodiDocumentEvent(ActionEvent event)
	{
		super(event.getSource(), event.getID(), "save_bodi_document_event");

		this.source = source;
	}

	public SaveBodiDocumentEvent(Object object, Integer id, String command)
	{
		super(object, id, "save_bodi_document_event");
	}
}
