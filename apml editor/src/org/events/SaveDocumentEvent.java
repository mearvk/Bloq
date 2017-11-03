package org.events;

import java.awt.event.ActionEvent;
import java.io.File;

public class SaveDocumentEvent extends ActionEvent
{
	public SaveDocumentEvent event;

	public File fileRef;

	public SaveDocumentEvent(ActionEvent event, File fileRef)
	{
		super(event.getSource(), event.getID(), "save_document_event");

		this.event = (SaveDocumentEvent)event;
	}

	public SaveDocumentEvent(Object object, Integer id, String command)
	{
		super(object, id, "save_document_event");
	}
}
