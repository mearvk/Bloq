package org.events;

import java.awt.event.ActionEvent;
import java.io.File;

public class SaveMunctionDocumentEvent extends ActionEvent
{
	public ActionEvent event;

	public File fileRef;

	public SaveMunctionDocumentEvent(Object source, int id, String command)
	{
		super(source, id, command);
	}

	public SaveMunctionDocumentEvent(Object source, int id, String command, int modifiers)
	{
		super(source, id, command, modifiers);
	}

	public SaveMunctionDocumentEvent(Object source, int id, String command, long when, int modifiers)
	{
		super(source, id, command, when, modifiers);
	}

	public SaveMunctionDocumentEvent(SaveDocumentEvent event, File fileRef)
	{
		super(event.getSource(), event.getID(), "save_munction_document_event");

		this.event = event;

		this.fileRef = fileRef;
	}
}
