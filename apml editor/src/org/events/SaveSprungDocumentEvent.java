package org.events;

import java.awt.event.ActionEvent;
import java.io.ByteArrayInputStream;
import java.io.File;

public class SaveSprungDocumentEvent extends ActionEvent
{
	public ActionEvent event;

	public File fileRef;

	public ByteArrayInputStream byteRef;

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

	public SaveSprungDocumentEvent(ActionEvent event, ByteArrayInputStream byteRef)
	{
		super(event.getSource(), event.getID(), "save_sprung_document_event");

		this.byteRef = byteRef;
	}

	public SaveSprungDocumentEvent(ByteArrayInputStream byteRef)
	{
		super(new Object(), 0, "save_sprung_document_event");

		this.byteRef = byteRef;
	}
}
