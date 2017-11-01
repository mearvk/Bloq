package org.events;

import java.awt.event.ActionEvent;

public class BuildApmlUisubsystemEvent extends ActionEvent
{
	public BuildApmlUisubsystemEvent(ActionEvent ae)
	{
		super(ae.getSource(), ae.getID(), "build_ui_apml_request_event");
	}

	public BuildApmlUisubsystemEvent(Object source, int id)
	{
		super(source, id, "build_ui_apml_request_event");
	}
}
