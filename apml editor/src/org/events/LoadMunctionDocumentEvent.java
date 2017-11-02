package org.events;

import java.awt.event.ActionEvent;
import java.io.File;

public class LoadMunctionDocumentEvent extends ActionEvent
{
	public File fileRef = null;

	public LoadMunctionDocumentEvent(ActionEvent event, File fileRef)
	{
		super(event.getSource(), event.getID(), "load_munction_document_event");

		this.fileRef = fileRef;
	}

	public File getFileRef()
	{
		return this.fileRef;
	}
}
