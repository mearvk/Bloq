package org.events;

import java.awt.event.ActionEvent;
import java.io.File;

public class SaveFalthruuDocumentEvent extends ActionEvent
{
	public ActionEvent event;

	public File fileRef;

	public SaveFalthruuDocumentEvent(Object source, int id, String command)
	{
		super(source, id, command);
	}

	public SaveFalthruuDocumentEvent(Object source, int id, String command, int modifiers)
	{
		super(source, id, command, modifiers);
	}

	public SaveFalthruuDocumentEvent(Object source, int id, String command, long when, int modifiers)
	{
		super(source, id, command, when, modifiers);
	}

	public SaveFalthruuDocumentEvent(SaveDocumentEvent event, File fileRef)
	{
		super(event.getSource(), event.getID(), "save_falthruu_document_event");

		this.event = event;

		this.fileRef = fileRef;
	}
}
