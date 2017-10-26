
package org.system;

import apml.modeling.Apmlsystem;
import apml.system.Apmlbasesystem;

public class UserInterfaceSystem extends Apmlsystem
{
    public UserInterfaceSystem object_000 = null;

    protected Apmlbasesystem monitor;

    public final String bodi="//events/system/{id}/userinterfacesystem";

    public final String id="userinterfacesystem";

    public final String tag="system";

    //
    public UserInterfaceSystem(final Apmlbasesystem monitor)
	{
        this.monitor = monitor;

        this.object_000 = new UserInterfaceSystem();
    }

    //
    public UserInterfaceSystem()
    {
        this.object_000 = new UserInterfaceSystem();
    }
}
