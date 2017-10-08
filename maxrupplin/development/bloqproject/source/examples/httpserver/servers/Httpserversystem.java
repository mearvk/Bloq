
package examples.httpserver.servers;

import apml.annotations.BloqSys;
import apml.interfaces.BasicSystemElement;
import apml.system.Apmlsystem;

public class Httpserversystem
    implements BasicSystemElement
{

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

    @BloqSys
    @Override
    public void run() {
        

    }

    @BloqSys
    @Override
    public void init() {
        

    }

    @BloqSys
    @Override
    public void start() {
        

    }

    @BloqSys
    @Override
    public void stop() {
        

    }

    @BloqSys
    @Override
    public void resume() {
        

    }

    @BloqSys
    @Override
    public void pause() {
        

    }

    @BloqSys
    @Override
    public void restart() {
        

    }

    @BloqSys
    @Override
    public void autostart() {
        

    }
    	

//TODO: finish adding support...
}
