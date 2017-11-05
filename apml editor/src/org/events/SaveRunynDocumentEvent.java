package org.events;

import java.awt.event.ActionEvent;

public class SaveRunynDocumentEvent extends ActionEvent
{

	public SaveRunynDocumentEvent(Object source, int id, String command)
	{
		super(source, id, command);
	}

	public SaveRunynDocumentEvent(Object source, int id, String command, int modifiers)
	{
		super(source, id, command, modifiers);
	}

	public SaveRunynDocumentEvent(Object source, int id, String command, long when, int modifiers)
	{
		super(source, id, command, when, modifiers);
	}
}
