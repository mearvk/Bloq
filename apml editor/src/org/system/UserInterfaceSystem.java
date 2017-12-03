package org.system;

import apml.modeling.Apmlsystem;
import apml.system.Apmlbasesystem;

public class UserInterfaceSystem extends Apmlsystem
{
	public final String bodi = "//editor/system/userinterfacesystem";

	public final String id = "userinterfacesystem";

	public final String tag = "system";

	//

	public UserInterfaceSystem userInterfaceSystem = null;

	public UserInterfaceProcessor userInterfaceProcessor = null;

	//

	protected Apmlbasesystem monitor;

	//
	public UserInterfaceSystem()
	{
		this.userInterfaceSystem = new UserInterfaceSystem();

		this.userInterfaceProcessor = new UserInterfaceProcessor();
	}

	//
	public UserInterfaceSystem(final Apmlbasesystem monitor)
	{
		this.monitor = monitor;

		this.userInterfaceSystem = new UserInterfaceSystem();

		this.userInterfaceProcessor = new UserInterfaceProcessor();
	}
}
