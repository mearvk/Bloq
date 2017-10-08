
package examples.httpserver.servers;

import apml.system.Apmlsystem;

public class Httpserversystem {

    protected Apmlsystem monitor;
    public final String bodi="//systems/null/undefined/0";
    public final String id="Httpserversystem";
    public final String tag="system";

    public Httpserversystem(final Apmlsystem monitor) {
        
	/*--------------- instantiation ----------------*/

        this.monitor = monitor;

        this.object_000 = new Httpserver();

    }

    public Httpserversystem() {
        
	/*--------------- instantiation ----------------*/

        this.object_000 = new Httpserver();

    }

    public Object callback(final Object event) {
        return null;

    }
    	

//TODO: finish adding support...
}
