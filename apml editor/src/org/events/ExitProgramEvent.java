package org.events;

import java.awt.event.ActionEvent;

public class ExitProgramEvent extends ActionEvent
{
	public ActionEvent event;

	public ExitProgramEvent(ActionEvent event)
	{
		super(event.getSource(), event.getID(), "exit_program_event");

		this.event = event;
	}
}
