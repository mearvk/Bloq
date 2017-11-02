package org.events;

import java.awt.event.ActionEvent;
import java.io.File;

public class LoadFalthruuDocumentEvent extends ActionEvent
{
	public File fileRef = null;

	public LoadFalthruuDocumentEvent(ActionEvent event, File fileRef)
	{
		super(event.getSource(), event.getID(), "load_falthruu_document_event");

		this.fileRef = fileRef;
	}

	public File getFileRef()
	{
		return this.fileRef;
	}
}
