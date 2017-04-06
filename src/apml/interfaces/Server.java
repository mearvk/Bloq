package apml.interfaces;

import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Max Rupplin
 */
public interface Server
{
    public byte[] read();
    
    public byte[] write();
    
    public Socket connect(ServerSocket ss) throws Exception;
}
