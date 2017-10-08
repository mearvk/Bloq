
package org.tests.systembootup;

import apml.system.Apmlsystem;

public class System_004 {

    protected Apmlsystem monitor;
    public final String bodi="//systems/null/undefined/3";
    public final String id="001";
    public final String tag="system";

    public System_004(final Apmlsystem monitor) {
        
	/*--------------- instantiation ----------------*/

        this.monitor = monitor;

        this.object_000 = new Object_004();

    }

    public System_004() {
        
	/*--------------- instantiation ----------------*/

        this.object_000 = new Object_004();

    }

    public Object callback(final Object event) {
        return null;

    }
    	

//TODO: finish adding support...
}
