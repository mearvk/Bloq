package org.events;

import java.awt.event.ActionEvent;
import java.io.File;

public class SaveBodiDocumentEvent extends ActionEvent
{
	public ActionEvent event;

	public File fileRef;

	public SaveBodiDocumentEvent(ActionEvent event, File fileRef)
	{
		super(event.getSource(), event.getID(), "save_bodi_document_event");

		this.event = event;
	}

	public SaveBodiDocumentEvent(Object object, Integer id, String command)
	{
		super(object, id, "save_bodi_document_event");
	}
}
