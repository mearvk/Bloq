package org.quran.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.File;

public class ReloadQuranTreeEvent extends ActionEvent
{
	public File fileRef = null;

	public ByteArrayInputStream byteRef = null;

	public ReloadQuranTreeEvent(ActionEvent event, File fileRef)
	{
		super(event.getSource(), event.getID(), "reload_quran_tree_event");

		this.fileRef = fileRef;
	}

	public ReloadQuranTreeEvent(ActionEvent event, ByteArrayInputStream byteRef)
	{
		super(event.getSource(), event.getID(), "reload_quran_tree_event");

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