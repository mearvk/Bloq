package org.events;

import java.awt.event.ActionEvent;
import java.io.File;

public class LoadRunynDocumentEvent extends ActionEvent
{
	public File fileRef = null;

	public LoadRunynDocumentEvent(ActionEvent event, File fileRef)
	{
		super(event.getSource(), event.getID(), "load_runyn_document_event");

		this.fileRef = fileRef;
	}

	public File getFileRef()
	{
		return this.fileRef;
	}
}
