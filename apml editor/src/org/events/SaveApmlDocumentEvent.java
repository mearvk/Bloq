package org.events;

import java.awt.event.ActionEvent;
import java.io.File;

public class SaveApmlDocumentEvent extends ActionEvent
{
	public SaveApmlDocumentEvent event;

	public File fileRef;

	public SaveApmlDocumentEvent(ActionEvent event, File fileRef)
	{
		super(event.getSource(), event.getID(), "save_apml_document_event");
	}

	public SaveApmlDocumentEvent(Object object, Integer id, String command)
	{
		super(object, id, "save_apml_document_event");
	}
}
