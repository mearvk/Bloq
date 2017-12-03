package org.events;

import java.awt.event.ActionEvent;
import java.io.ByteArrayInputStream;
import java.io.File;

public class SaveRunynDocumentEvent extends ActionEvent
{
	public ActionEvent event;

	public File fileRef;

	public ByteArrayInputStream byteRef;

	public SaveRunynDocumentEvent(Object source, int id, String command)
	{
		super(source, id, command);
	}

	public SaveRunynDocumentEvent(Object source, int id, String command, int modifiers)
	{
		super(source, id, command, modifiers);
	}

	public SaveRunynDocumentEvent(Object source, int id, String command, long when, int modifiers)
	{
		super(source, id, command, when, modifiers);
	}

	public SaveRunynDocumentEvent(SaveDocumentEvent event, File fileRef)
	{
		super(event.getSource(), event.getID(), "save_runyn_document_event");

		this.event = event;

		this.fileRef = fileRef;
	}

	public SaveRunynDocumentEvent(ActionEvent event, ByteArrayInputStream byteRef)
	{
		super(event.getSource(), event.getID(), "save_runyn_document_event");

		this.byteRef = byteRef;
	}

	public SaveRunynDocumentEvent(ByteArrayInputStream byteRef)
	{
		super(new Object(), 0, "save_runyn_document_event");

		this.byteRef = byteRef;
	}
}
