
package org.tests.systembootup;

import apml.system.Apmlsystem;

public class System_001 {

    protected Apmlsystem monitor;
    public final String bodi="//systems/null/undefined/0";
    public final String id="001";
    public final String tag="system";

    public System_001(final Apmlsystem monitor) {
        
	/*--------------- instantiation ----------------*/

        this.monitor = monitor;

        this.object_000 = new Object_001();

    }

    public System_001() {
        
	/*--------------- instantiation ----------------*/

        this.object_000 = new Object_001();

    }

    public Object callback(final Object event) {
        return null;

    }
    	

//TODO: finish adding support...
}
