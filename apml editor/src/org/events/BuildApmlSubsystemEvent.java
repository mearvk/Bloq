package org.events;

import java.awt.event.ActionEvent;

public class BuildApmlSubsystemEvent extends ActionEvent
{
	public BuildApmlSubsystemEvent(ActionEvent ae)
	{
		super(ae.getSource(), ae.getID(), "build_apml_standalone_request_event");
	}

	public BuildApmlSubsystemEvent(Object source, int id)
	{
		super(source, id, "build_apml_standalone_request_event");
	}
}
