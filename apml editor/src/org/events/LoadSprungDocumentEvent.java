package org.events;

import java.awt.event.ActionEvent;
import java.io.File;

public class LoadSprungDocumentEvent extends ActionEvent
{
	public File fileRef = null;

	public LoadSprungDocumentEvent(ActionEvent event, File fileRef)
	{
		super(event.getSource(), event.getID(), "load_sprung_document_event");

		this.fileRef = fileRef;
	}

	public File getFileRef()
	{
		return this.fileRef;
	}
}
