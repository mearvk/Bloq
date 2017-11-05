package org.events;

import java.awt.event.ActionEvent;

public class SaveFalthruuDocumentEvent extends ActionEvent
{
	public SaveFalthruuDocumentEvent(Object source, int id, String command)
	{
		super(source, id, command);
	}

	public SaveFalthruuDocumentEvent(Object source, int id, String command, int modifiers)
	{
		super(source, id, command, modifiers);
	}

	public SaveFalthruuDocumentEvent(Object source, int id, String command, long when, int modifiers)
	{
		super(source, id, command, when, modifiers);
	}
}
