
package org.tests.systembootup;

import apml.system.Apmlsystem;

public class System_003 {

    protected Apmlsystem monitor;
    public final String bodi="//systems/null/undefined/2";
    public final String id="001";
    public final String tag="system";

    public System_003(final Apmlsystem monitor) {
        
	/*--------------- instantiation ----------------*/

        this.monitor = monitor;

        this.object_000 = new Object_003();

    }

    public System_003() {
        
	/*--------------- instantiation ----------------*/

        this.object_000 = new Object_003();

    }

    public Object callback(final Object event) {
        return null;

    }
    	

//TODO: finish adding support...
}
