package org.events;

import java.awt.event.ActionEvent;

public class SaveSprungDocumentEvent extends ActionEvent
{
	public SaveSprungDocumentEvent(Object source, int id, String command)
	{
		super(source, id, command);
	}

	public SaveSprungDocumentEvent(Object source, int id, String command, int modifiers)
	{
		super(source, id, command, modifiers);
	}

	public SaveSprungDocumentEvent(Object source, int id, String command, long when, int modifiers)
	{
		super(source, id, command, when, modifiers);
	}
}
