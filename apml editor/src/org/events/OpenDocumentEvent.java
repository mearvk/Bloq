package org.events;

import java.awt.event.ActionEvent;
import java.io.File;

public class OpenDocumentEvent extends ActionEvent
{
	public File fileRef = null;

	public String fileType = "";



	//

	public OpenDocumentEvent(ActionEvent event, File fileRef)
	{
		super(event.getSource(), event.getID(), "open_document_event");

		this.fileRef = fileRef;



		//
	}

	public File getFileRef()
	{
		return this.fileRef;
	}
}
