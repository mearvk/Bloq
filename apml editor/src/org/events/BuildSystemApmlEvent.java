package org.events;

import java.awt.event.ActionEvent;

public class BuildSystemApmlEvent extends ActionEvent
{

	public BuildSystemApmlEvent(Object source, int id, String command)
	{
		super(source, id, command);
	}
}
