package org.events;

import java.awt.event.ActionEvent;
import java.io.File;

public class LoadApmlDocumentEvent extends ActionEvent
{
	public File file = null;

	public LoadApmlDocumentEvent(File file)
	{
		super(null, 0, "load_apml_document_update");

		this.file = file;
	}

	public LoadApmlDocumentEvent(Object object, Integer id, String command, File file)
	{
		super(object, id, "load_apml_document_update");

		this.file = file;
	}
}
