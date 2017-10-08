
package examples.httpserver.servers;

import apml.annotations.BloqExtension;
import apml.extensions.AbstractResourceServer;
import apml.system.Apmlsystem;

public class Httpserver
    extends AbstractResourceServer
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
