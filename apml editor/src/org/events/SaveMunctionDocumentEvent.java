package org.events;

import java.awt.event.ActionEvent;

public class SaveMunctionDocumentEvent extends ActionEvent
{
	public SaveMunctionDocumentEvent(Object source, int id, String command)
	{
		super(source, id, command);
	}

	public SaveMunctionDocumentEvent(Object source, int id, String command, int modifiers)
	{
		super(source, id, command, modifiers);
	}

	public SaveMunctionDocumentEvent(Object source, int id, String command, long when, int modifiers)
	{
		super(source, id, command, when, modifiers);
	}
}
