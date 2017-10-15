
package org.apml.editor;

import apml.system.Apmlbasesystem;

public class ModelInterfaceObserver
{
    public ModelInterfaceObserver observer = this;

    protected Apmlbasesystem monitor;

    public final String bodi="//apml/editor/{id}/observer";

    public final String id="processor";

    public final String tag="object";

    public ModelInterfaceObserver(final Apmlbasesystem monitor)
    {
	    //

        this.monitor = monitor;
    }

    public ModelInterfaceObserver()
    {
	    //
    }
}
