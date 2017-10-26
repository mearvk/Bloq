package org.events;

import java.awt.event.ActionEvent;

public class TreeStructureUpdatedEvent extends ActionEvent
{
	public TreeStructureUpdatedEvent(Object source, int id, String command)
	{
		super(source, id, command);
	}
}
