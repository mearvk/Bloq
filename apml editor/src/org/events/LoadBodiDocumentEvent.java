package org.events;

import java.awt.event.ActionEvent;
import java.io.File;

public class LoadBodiDocumentEvent extends ActionEvent
{
	public File fileRef = null;

	public LoadBodiDocumentEvent(ActionEvent event, File fileRef)
	{
		super(event.getSource(), event.getID(), "load_bodi_document_event");

		this.fileRef = fileRef;
	}

	public File getFileRef()
	{
		return this.fileRef;
	}
}
