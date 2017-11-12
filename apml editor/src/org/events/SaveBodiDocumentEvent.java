package org.events;

import java.awt.event.ActionEvent;
import java.io.ByteArrayInputStream;
import java.io.File;

public class SaveBodiDocumentEvent extends ActionEvent
{
	public ActionEvent event;

	public File fileRef;

	public ByteArrayInputStream byteRef;

	public SaveBodiDocumentEvent(ActionEvent event, File fileRef)
	{
		super(event.getSource(), event.getID(), "save_bodi_document_event");

		this.event = event;
	}

	public SaveBodiDocumentEvent(Object object, Integer id, String command)
	{
		super(object, id, "save_bodi_document_event");
	}

	public SaveBodiDocumentEvent(ActionEvent event, ByteArrayInputStream byteRef)
	{
		super(event.getSource(), event.getID(), "save_bodi_document_event");

		this.byteRef = byteRef;
	}

	public SaveBodiDocumentEvent(ByteArrayInputStream byteRef)
	{
		super(new Object(), 0, "save_bodi_document_event");

		this.byteRef = byteRef;
	}
}
