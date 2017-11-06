package org.events;

import java.awt.event.ActionEvent;
import java.io.File;

public class SaveBloqDocumentEvent extends ActionEvent
{
	public ActionEvent event;

	public File fileRef;

	public SaveBloqDocumentEvent(Object source, int id, String command)
	{
		super(source, id, command);
	}

	public SaveBloqDocumentEvent(Object source, int id, String command, int modifiers)
	{
		super(source, id, command, modifiers);
	}

	public SaveBloqDocumentEvent(Object source, int id, String command, long when, int modifiers)
	{
		super(source, id, command, when, modifiers);
	}

	public SaveBloqDocumentEvent(SaveDocumentEvent event, File fileRef)
	{
		super(event.getSource(), event.getID(), "save_bloq_document_event");

		this.event = event;

		this.fileRef = fileRef;
	}
}
