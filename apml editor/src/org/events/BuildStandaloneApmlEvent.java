package org.events;

import java.awt.event.ActionEvent;

public class BuildStandaloneApmlEvent extends ActionEvent
{

	public BuildStandaloneApmlEvent(Object source, int id, String command)
	{
		super(source, id, command);
	}
}
