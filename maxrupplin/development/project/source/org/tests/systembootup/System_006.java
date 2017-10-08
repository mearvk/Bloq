
package org.tests.systembootup;

import apml.system.Apmlsystem;

public class System_006 {

    protected Apmlsystem monitor;
    public final String bodi="//systems/null/undefined/5";
    public final String id="001";
    public final String tag="system";

    public System_006(final Apmlsystem monitor) {
        
	/*--------------- instantiation ----------------*/

        this.monitor = monitor;

        this.object_000 = new Object_006();

    }

    public System_006() {
        
	/*--------------- instantiation ----------------*/

        this.object_000 = new Object_006();

    }

    public Object callback(final Object event) {
        return null;

    }
    	

//TODO: finish adding support...
}
