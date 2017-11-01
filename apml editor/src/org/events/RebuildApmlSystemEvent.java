package org.events;

import java.awt.event.ActionEvent;

public class RebuildApmlSystemEvent extends ActionEvent
{
	public RebuildApmlSystemEvent(ActionEvent ae)
	{
		super(ae.getSource(), ae.getID(), "build_apml_standalone_request_event");
	}

	public RebuildApmlSystemEvent(Object source, int id)
	{
		super(source, id, "build_apml_standalone_request_event");
	}
}
