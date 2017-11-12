package org.events;

import java.awt.event.ActionEvent;
import java.io.ByteArrayInputStream;
import java.io.File;

public class SaveApmlDocumentEvent extends ActionEvent
{
	public SaveApmlDocumentEvent event;

	public File fileRef;

	public ByteArrayInputStream byteRef;

	public SaveApmlDocumentEvent(ActionEvent event, File fileRef)
	{
		super(event.getSource(), event.getID(), "save_apml_document_event");

		this.fileRef = fileRef;
	}

	public SaveApmlDocumentEvent(ActionEvent event, ByteArrayInputStream byteRef)
	{
		super(event.getSource(), event.getID(), "save_apml_document_event");

		this.byteRef = byteRef;
	}

	public SaveApmlDocumentEvent(ByteArrayInputStream byteRef)
	{
		super(new Object(), 0, "save_apml_document_event");

		this.byteRef = byteRef;
	}

	public SaveApmlDocumentEvent(Object object, Integer id, String command)
	{
		super(object, id, "save_apml_document_event");
	}
}
