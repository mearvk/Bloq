
package org.tests.systembootup;

import apml.system.Apmlsystem;

public class System_002 {

    protected Apmlsystem monitor;
    public final String bodi="//systems/null/undefined/1";
    public final String id="001";
    public final String tag="system";

    public System_002(final Apmlsystem monitor) {
        
	/*--------------- instantiation ----------------*/

        this.monitor = monitor;

        this.object_000 = new Object_002();

    }

    public System_002() {
        
	/*--------------- instantiation ----------------*/

        this.object_000 = new Object_002();

    }

    public Object callback(final Object event) {
        return null;

    }
    	

//TODO: finish adding support...
}
