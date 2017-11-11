package org.quran.events;

import java.awt.event.ActionEvent;
import java.io.ByteArrayInputStream;
import java.io.File;

public class LoadQuranTreeEvent extends ActionEvent
{
	public File fileRef = null;

	public ByteArrayInputStream byteRef = null;

	public LoadQuranTreeEvent(ActionEvent event, File fileRef)
	{
		super(event.getSource(), event.getID(), "load_quran_tree_event");

		this.fileRef = fileRef;
	}

	public LoadQuranTreeEvent(ActionEvent event, ByteArrayInputStream byteRef)
	{
		super(event.getSource(), event.getID(), "load_quran_tree_event");

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