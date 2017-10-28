package org.events;

import java.awt.event.ActionEvent;
import java.io.File;

public class LoadApmlDocumentEvent extends ActionEvent
{
	public File fileRef = null;

	public LoadApmlDocumentEvent(ActionEvent event, File file)
	{
		super(event.getSource(), event.getID(), "load_apml_document_event");

		this.fileRef = fileRef;
	}

	public File getFileRef()
	{
		return this.fileRef;
	}
}
