package org.events;

import java.awt.event.ActionEvent;
import java.io.File;

public class RequestBodiPragramAnalysisEvent extends ActionEvent
{
	public ActionEvent event;

	public File fileRef;

	public RequestBodiPragramAnalysisEvent(Object source, int id, String command)
	{
		super(source, id, command);
	}

	public RequestBodiPragramAnalysisEvent(Object source, int id, String command, int modifiers)
	{
		super(source, id, command, modifiers);
	}

	public RequestBodiPragramAnalysisEvent(Object source, int id, String command, long when, int modifiers)
	{
		super(source, id, command, when, modifiers);
	}
}
