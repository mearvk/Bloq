package apml.system.bodi.remote;

/**
 *
 * @author Max Rupplin
 */
public class Bodiprotocolhandler
{ 
    protected Bodiconnection parseprotocol( String protocol, StringBuffer buffer, Bodiremoteserver server ) throws Exception
    {
        Bodiconnection connection = server.checksessionid(server.bodiconnections, buffer, server.bodi);        
        
        switch(protocol)
        {                
            //
            case Bodiprotocol.CLOSE:        
                return connection.close(buffer, server);                                                 
         
            //
            case Bodiprotocol.HANDSHAKE:    
                return connection.handshake(buffer, server);                                                                
                
            //
            case Bodiprotocol.OPEN:         
                return connection.open(buffer, server);                                        
                
            //
            case Bodiprotocol.PULL:         
                return connection.pull(buffer, server);                                    
                            
            //
            case Bodiprotocol.PUT:          
                return connection.put(buffer, server);                    
            
            //
            case Bodiprotocol.TRADE:        
                return connection.trade(buffer, server);                  
        } 
        
        throw new NoValidConnectionFoundException("No valid connection found.");
    } 
}
