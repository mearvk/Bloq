package org.events;

import java.awt.event.ActionEvent;
import java.io.File;

public class LoadRunynTreeEvent extends ActionEvent
{
	public File fileRef;

	public LoadRunynTreeEvent(ActionEvent event, File fileRef)
	{
		super(event.getSource(), event.getID(), "load_runyn_tree_event");

		this.fileRef = fileRef;
	}

	public File getFileRef()
	{
		return this.fileRef;
	}
}
