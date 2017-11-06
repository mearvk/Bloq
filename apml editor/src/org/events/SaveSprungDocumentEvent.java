package org.events;

import java.awt.event.ActionEvent;
import java.io.File;

public class SaveSprungDocumentEvent extends ActionEvent
{
	public ActionEvent event;

	public File fileRef;

	public SaveSprungDocumentEvent(Object source, int id, String command)
	{
		super(source, id, command);
	}

	public SaveSprungDocumentEvent(Object source, int id, String command, int modifiers)
	{
		super(source, id, command, modifiers);
	}

	public SaveSprungDocumentEvent(Object source, int id, String command, long when, int modifiers)
	{
		super(source, id, command, when, modifiers);
	}

	public SaveSprungDocumentEvent(SaveDocumentEvent event, File fileRef)
	{
		super(event.getSource(), event.getID(), "save_sprung_document_event");

		this.event = event;

		this.fileRef = fileRef;
	}
}
