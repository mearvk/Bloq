package apml.system.bodi.remote;

/**
 *
 * @author Max Rupplin
 */
public class Bodiprotocolhandler
{ 
    protected Bodiconnection parseprotocol( String protocol, StringBuffer buffer, Bodiremoteserver server, Connection connection ) throws Exception
    {               
        if(connection!=null)
        {
            switch(protocol)
            {                
                //
                case Bodiprotocol.CLOSE:        
                    return new Bodiconnection(server, connection).close(buffer);                                                 

                //
                case Bodiprotocol.HANDSHAKE:    
                    return new Bodiconnection(server, connection).handshake(buffer);                                                                

                //
                case Bodiprotocol.OPEN:         
                    return new Bodiconnection(server, connection).open(buffer);                                        

                //
                case Bodiprotocol.PULL:         
                    return new Bodiconnection(server, connection).pull(buffer);                                    

                //
                case Bodiprotocol.PUT:          
                    return new Bodiconnection(server, connection).put(buffer);                    

                //
                case Bodiprotocol.TRADE:        
                    return new Bodiconnection(server, connection).trade(buffer);                  
            }                        
        }  
        
        throw new NoValidConnectionFoundException("No valid connection found.");
    } 
}
