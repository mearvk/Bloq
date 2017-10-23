
package org.editor;

import apml.system.Apmlbasesystem;

import java.awt.*;

public class ModelInterfaceObserver
{
    public ModelInterfaceObserver observer = this;

    protected Apmlbasesystem monitor;

    public final String bodi="//events/editor/{id}/observer";

    public final String id="processor";

    public final String tag="object";

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
