package org.events;

import java.awt.event.ActionEvent;
import java.io.File;

public class LoadApmlTreeEvent extends ActionEvent
{
	public File fileRef = null;

	public LoadApmlTreeEvent(ActionEvent event, File fileRef)
	{
		super(event.getSource(), event.getID(), "load_apml_tree_event");

		this.fileRef = fileRef;
	}

	public File getFileRef()
	{
		return this.fileRef;
	}
}