package org.events;

import java.awt.event.ActionEvent;
import java.io.ByteArrayInputStream;
import java.io.File;

public class LoadApmlTreeEvent extends ActionEvent
{
	public File fileRef = null;

	public ByteArrayInputStream byteRef = null;

	public LoadApmlTreeEvent(ActionEvent event, File fileRef)
	{
		super(event.getSource(), event.getID(), "load_apml_tree_event");

		this.fileRef = fileRef;
	}

	public LoadApmlTreeEvent(ActionEvent event, ByteArrayInputStream byteRef)
	{
		super(event.getSource(), event.getID(), "load_apml_tree_event");

		this.byteRef = byteRef;
	}

	public File getFileRef()
	{
		return this.fileRef;
	}

	public ByteArrayInputStream getByteRef()
	{
		return this.byteRef;
	}
}