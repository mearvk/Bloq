package org.events;

import java.awt.event.ActionEvent;
import java.io.File;

public class LoadBloqTreeEvent extends ActionEvent
{
	public File fileRef = null;

	public LoadBloqTreeEvent(ActionEvent event, File fileRef)
	{
		super(event.getSource(), event.getID(), "load_bloq_tree_event");

		this.fileRef = fileRef;
	}

	public File getFileRef()
	{
		return this.fileRef;
	}
}