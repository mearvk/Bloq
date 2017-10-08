
package examples.httpserver.servers;

import apml.annotations.BloqExtension;
import apml.annotations.BloqSys;
import apml.extensions.AbstractResourceServer;
import apml.interfaces.BasicSystemElement;
import apml.system.Apmlsystem;

public class Httpserver
    extends AbstractResourceServer
    implements BasicSystemElement
{

    protected Apmlsystem monitor;
    public final String bodi="//systems/null/undefined/0";
    public final String id="Httpserver";
    public final String tag="object";

    public Httpserver(final Apmlsystem monitor) {
        
	/*--------------- instantiation ----------------*/

        this.monitor = monitor;

    }

    public Httpserver() {
        
	/*--------------- instantiation ----------------*/

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

    @BloqExtension
    @Override
    public Boolean dovalidateresourcecontext() {
        

    }

    @BloqExtension
    @Override
    public void processprotocol() {
        

    }

    @BloqExtension
    @Override
    public void processrequest() {
        

    }

    @BloqExtension
    @Override
    public void processsesponse() {
        

    }
    	

//TODO: finish adding support...
}
