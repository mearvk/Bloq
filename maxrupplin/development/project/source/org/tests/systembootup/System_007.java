
package org.tests.systembootup;

import apml.system.Apmlsystem;

public class System_007 {

    protected Apmlsystem monitor;
    public final String bodi="//systems/null/undefined/6";
    public final String id="001";
    public final String tag="system";

    public System_007(final Apmlsystem monitor) {
        
	/*--------------- instantiation ----------------*/

        this.monitor = monitor;

        this.object_000 = new Object_007();

    }

    public System_007() {
        
	/*--------------- instantiation ----------------*/

        this.object_000 = new Object_007();

    }

    public Object callback(final Object event) {
        return null;

    }
    	

//TODO: finish adding support...
}
