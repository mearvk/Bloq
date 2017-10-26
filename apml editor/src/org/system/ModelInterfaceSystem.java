
package org.system;

import apml.modeling.Apmlsystem;
import apml.system.Apmlbasesystem;

public class ModelInterfaceSystem extends Apmlsystem
{
    public ModelInterfaceObserver object_000 = null;

    protected Apmlbasesystem monitor;

    public final String bodi="//events/system/{id}/modelinterfacesystem";

    public final String id="userinterfacesystem";

    public final String tag="system";

    //
    public ModelInterfaceSystem(final Apmlbasesystem monitor)
    {
        this.monitor = monitor;

        this.object_000 = new ModelInterfaceObserver();
    }

    //
    public ModelInterfaceSystem()
    {
        this.object_000 = new ModelInterfaceObserver();
    }
}
