package org.events;

import java.awt.event.ActionEvent;

public class CloseApmlDocumentEvent extends ActionEvent
{
	public CloseApmlDocumentEvent()
	{
		super(null, 0, "close_apml_document_update");

		//
	}

	public CloseApmlDocumentEvent(Object object, Integer id, String command)
	{
		super(object, id, "close_apml_document_update");

		//
	}
}
