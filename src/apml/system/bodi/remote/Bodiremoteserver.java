package apml.system.bodi.remote;

import apml.system.bodi.Bodi;

import static apml.system.bodi.remote.Bodiprotocol.*;

import java.util.Collection;

/**
 * Hello transactional service based pay-as-you-go internet (Bodi)
 * 
 * @author Max Rupplin
 */

public class Bodiremoteserver extends Basicserver //reserve keyword fortune at root for bodi and her creator Max R .mr .ok ss
{               
    public Integer hash = 0x008808ef;
    
    public Bodiprotocolhandler protocolhandler = new Bodiprotocolhandler();
    
    public Boolean running = true; 
    
    public Bodi bodi;           
    
    public static void main(String...args)
    {
        Bodiremoteserver server;
        
        server = new Bodiremoteserver("localhost", 8888);
        
        server.start();
        
        server.go();                       
    }       
    
    public Bodiremoteserver(String host, Integer port)
    {      
        super(host, port);
        
        if(host==null || port==null) throw new SecurityException("//bodi/connect");
        
        this.host = host;
        
        this.port = port;        
        
        Bodi.setcontext("//bodi/server/remote/bodiconnections");
        
        Bodi.setcontext("//bodi/server/remote/netconnections");
    }
    
    public Bodiremoteserver(Integer port)
    {
        super("localhost", port);
        
        if(port==null) throw new SecurityException("//bodi/connect");
        
        this.port = port;        
        
        Bodi.setcontext("//bodi/server/remote/bodiconnections");
        
        Bodi.setcontext("//bodi/server/remote/netconnections");        
    }
    
    public void go() //find a way to synch the input queue so delete doesn't actually delete fresh data
    {                                  
        while(running)
        {
            Connection network = this.getnextqueuedconnection();            
            
            try
            {
                if( network!=null && network.inputqueueisready() )
                {   
                    Bodiserverconnectioncontext connectioncontext = null;
                    
                    Bodiconnection unsecuredbodiconnection = this.checkforexistingconnection(network.inqueue);
                    
                    String input = network.inqueue.toString();
                    
                    /*----------------------------------------------------------------------------------------------------------------------------------------------------------------*/                                                                                
                                        
                    if(input.startsWith("//handshake"))
                    {
                         connectioncontext = new Bodiserverconnectioncontext(this, HANDSHAKE, input, network, unsecuredbodiconnection);
                        
                         connectioncontext.parseprotocol(connectioncontext);
                         
                         connectioncontext.processrequest(connectioncontext);     
                         
                         connectioncontext.processresponse(connectioncontext);                                                  
                    }                    
                    
                    else if(input.startsWith("//close"))
                    {
                         connectioncontext = new Bodiserverconnectioncontext(this, CLOSE, input, network, unsecuredbodiconnection);
                        
                         connectioncontext.parseprotocol(connectioncontext);
                        
                         connectioncontext.processrequest(connectioncontext);     
                         
                         connectioncontext.processresponse(connectioncontext);         
                    }

                    else if(input.startsWith("//open"))
                    {
                         connectioncontext = new Bodiserverconnectioncontext(this, OPEN, input, network, unsecuredbodiconnection);
                        
                         connectioncontext.parseprotocol(connectioncontext);
                        
                         connectioncontext.processrequest(connectioncontext);     
                         
                         connectioncontext.processresponse(connectioncontext);         
                    }

                    else if(input.startsWith("//pull")) 
                    {
                         connectioncontext = new Bodiserverconnectioncontext(this, PULL, input, network, unsecuredbodiconnection);
                        
                         connectioncontext.parseprotocol(connectioncontext);
                        
                         connectioncontext.processrequest(connectioncontext);     
                         
                         connectioncontext.processresponse(connectioncontext);         
                    }
                                        
                    else if(input.startsWith("//put"))
                    {
                         connectioncontext = new Bodiserverconnectioncontext(this, PUT, input, network, unsecuredbodiconnection);
                        
                         connectioncontext.parseprotocol(connectioncontext);                         
                         
                         connectioncontext.processrequest(connectioncontext);     
                         
                         connectioncontext.processresponse(connectioncontext);                              
                    }
                    
                    else if(input.startsWith("//trade"))
                    {
                         connectioncontext = new Bodiserverconnectioncontext(this, TRADE, input, network, unsecuredbodiconnection);
                        
                         connectioncontext.parseprotocol(connectioncontext);
                         
                         connectioncontext.processrequest(connectioncontext);     
                         
                         connectioncontext.processresponse(connectioncontext);         
                    }        
                    
                    else
                    {
                         connectioncontext = new Bodiserverconnectioncontext(this, OTHER, input, network, unsecuredbodiconnection);
                        
                         connectioncontext.parseprotocol(connectioncontext);
                         
                         connectioncontext.processrequest(connectioncontext);     
                         
                         connectioncontext.processresponse(connectioncontext);                                                                 
                    }
                    
                    this.storeconnectiondatawithbodi(connectioncontext, network);
                }
                else
                {
                    this.sleepmillis(500l); //oh boy oh boy let's rest half a day
                }                
            }
            catch(SecurityException se) //here we can receive security exceptions  
            {                
                se.printStackTrace();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }  
            finally
            {
                //
            }
        }
    }      
    
    private Boolean storeconnectiondatawithbodi(Bodiserverconnectioncontext connectioncontext, Connection connection) throws Exception
    {
        Bodi.context("//bodi/server/remote/bodiconnections").put(connectioncontext.bodiconnection.sessionid, connectioncontext.bodiconnection);

        Bodi.context("//bodi/server/remote/netconnections").put(connection, connection);  //connection.sessionid --> connection SVP ASAP
        
        return false;
    }
    
    private Collection<Bodiconnection> getbodiconnections()
    {
        Collection<Object> objects = Bodi.context("//bodi/server/remote/bodiconnections").values();
        
        Collection<Bodiconnection> _connections = (Collection<Bodiconnection>)(Collection<?>)objects;
        
        return _connections;
    }
    
    public Connection getnextqueuedconnection()
    {        
        Connection connection = this.connectionqueue.peek();        
        
        if(connection!=null) this.connectionqueue.remove(connection);
        
        return connection;
    }    
    
    protected void sleepmillis(Long millis) throws Exception
    {
        Thread.currentThread().sleep(millis);
    }    
    
    protected Bodiconnection checkforexistingconnection(StringBuffer buffer) throws Exception
    {
        if(buffer==null) return null;
        
        //if(buffer.toString().startsWith("//handshake")) return null; //workaround tmp fix etc
            
        String[] tokens = buffer.toString().split(" ");
        
        for(String token : tokens)
        {          
            if(token.trim().startsWith("//sessionid"))
            {
                for(Bodiconnection existingconnection : this.getbodiconnections())
                {
                    token = token.trim().replace("//sessionid=", "");
                    
                    int receivedsessionid = Integer.parseInt(token);
                    
                    int existingsessionid = existingconnection.sessionid;
                    
                    if(receivedsessionid == existingsessionid)
                    {
                        return existingconnection;
                    }
                }
            }
        }
        
        if(buffer.toString().startsWith("//handshake")) 
        {
            return new Bodiconnection();
        }
        
        else throw new Exception("No session with sessionid found.");
    }
    
    public Boolean isvalidsessionid(Bodiconnection connection)
    {
        if(connection==null) return false;
        
        if(connection.sessionid==null) return false;
        
        for(Bodiconnection existingconnection : this.getbodiconnections())
        {
            if(existingconnection.sessionid == connection.sessionid)
            {
                return true;
            }
        }
        
        return false;
    }  
    
    public Boolean isvalidsession(Bodiconnection connection)
    {
        if(connection==null) return false;
        
        if(connection.sessionid==null) return false;
        
        if(connection.ttl<=0) return false;
        
        for(Bodiconnection existingconnection : this.getbodiconnections())
        {
            if(existingconnection.sessionid == connection.sessionid)
            {
                return connection.ttl > 0;                
            }
        }
        
        return false;
    }    

    @Override
    public void write(byte[] bytes)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public byte[] read()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}