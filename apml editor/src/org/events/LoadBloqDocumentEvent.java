package org.events;

import java.awt.event.ActionEvent;
import java.io.File;

public class LoadBloqDocumentEvent extends ActionEvent
{
	public File fileRef = null;

	public LoadBloqDocumentEvent(ActionEvent event, File fileRef)
	{
		super(event.getSource(), event.getID(), "load_bloq_document_event");

		this.fileRef = fileRef;
	}

	public File getFileRef()
	{
		return this.fileRef;
	}
}
