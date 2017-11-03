package org.system;

import apml.system.Apmlbasesystem;

import java.awt.*;

public class ModelInterfaceObserver
{
	public final String bodi = "//events/system/{id}/observer";
	public final String id = "processor";
	public final String tag = "object";
	public ModelInterfaceObserver observer = this;
	protected Apmlbasesystem monitor;

	//
	public ModelInterfaceObserver(final Apmlbasesystem monitor)
	{
		this.monitor = monitor;
	}

	//
	public ModelInterfaceObserver()
	{

	}

	//
	public void update(Event event, String target, String action)
	{

	}
}




