package org.events;

import java.awt.event.ActionEvent;
import java.io.File;

public class LoadFalthruuTreeEvent extends ActionEvent
{
	public File fileRef;

	public LoadFalthruuTreeEvent(ActionEvent event, File fileRef)
	{
		super(event.getSource(), event.getID(), "load_bodi_tree_event");

		this.fileRef = fileRef;
	}

	public File getFileRef()
	{
		return this.fileRef;
	}
}
