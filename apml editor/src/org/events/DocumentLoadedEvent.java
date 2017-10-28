package org.events;

import java.awt.event.ActionEvent;
import java.io.File;

public class DocumentLoadedEvent extends ActionEvent
{
	public String fileURL = "";

	public File fileRef;

	//

	public DocumentLoadedEvent(ActionEvent event, String fileURL)
	{
		super(event.getSource(), event.getID(), "document_loaded_event");

		this.fileURL = fileURL;
	}

	public DocumentLoadedEvent(ActionEvent event, File fileRef)
	{
		super(event.getSource(), event.getID(), "document_loaded_event");

		this.fileRef = fileRef;
	}

	public String getFileURL()
	{
		return this.fileURL;
	}

	public File getFileRef()
	{
		return this.fileRef;
	}
}
