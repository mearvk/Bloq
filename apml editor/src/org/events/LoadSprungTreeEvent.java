package org.events;

import java.awt.event.ActionEvent;
import java.io.File;

public class LoadSprungTreeEvent extends ActionEvent
{
	public File fileRef;

	public LoadSprungTreeEvent(ActionEvent event, File fileRef)
	{
		super(event.getSource(), event.getID(), "load_sprung_tree_event");

		this.fileRef = fileRef;
	}

	public File getFileRef()
	{
		return this.fileRef;
	}
}
