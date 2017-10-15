package org.events;

import java.awt.event.ActionEvent;

public class LoadApmlDocumentEvent extends ActionEvent
{
	public LoadApmlDocumentEvent(Object object, Integer id, String command)
	{
		super(object, id, command);
	}
}
