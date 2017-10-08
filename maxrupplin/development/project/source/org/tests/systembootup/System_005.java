
package org.tests.systembootup;

import apml.system.Apmlsystem;

public class System_005 {

    protected Apmlsystem monitor;
    public final String bodi="//systems/null/undefined/4";
    public final String id="001";
    public final String tag="system";

    public System_005(final Apmlsystem monitor) {
        
	/*--------------- instantiation ----------------*/

        this.monitor = monitor;

        this.object_000 = new Object_005();

    }

    public System_005() {
        
	/*--------------- instantiation ----------------*/

        this.object_000 = new Object_005();

    }

    public Object callback(final Object event) {
        return null;

    }
    	

//TODO: finish adding support...
}
