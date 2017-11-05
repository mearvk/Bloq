package org.events;

import java.awt.event.ActionEvent;

public class SaveBloqDocumentEvent extends ActionEvent
{

	public SaveBloqDocumentEvent(Object source, int id, String command)
	{
		super(source, id, command);
	}

	public SaveBloqDocumentEvent(Object source, int id, String command, int modifiers)
	{
		super(source, id, command, modifiers);
	}

	public SaveBloqDocumentEvent(Object source, int id, String command, long when, int modifiers)
	{
		super(source, id, command, when, modifiers);
	}
}
