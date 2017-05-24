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
    
    public void go() //find a way to synch the unsecuredinput queue so delete doesn't actually delete fresh data
    {                                  
        while(running)
        {                        
            Bodiserverconnectioncontext connectioncontext = null;
            
            Connection network = this.getnextqueuedconnection();            
                                    
            String unsecuredinput = null;                                   
            
            try
            {
                unsecuredinput = network.inqueue.toString(); 
                
                if( network!=null && network.inputqueueisready() )
                {                                          
                    //up for redesign [will not catch malformed token]
                    Bodiconnection unsecuredbodiconnection = this.checkforexistingconnection(network.inqueue);                                        
                    
                    /*----------------------------------------------------------------------------------------------------------------------------------------------------------------*/                                                                                
                                        
                    if(unsecuredinput.startsWith("//handshake"))
                    {
                         connectioncontext = new Bodiserverconnectioncontext(this, HANDSHAKE, unsecuredinput, network, unsecuredbodiconnection);
                        
                         connectioncontext.processprotocol(connectioncontext);
                         
                         connectioncontext.processrequest(connectioncontext);     
                         
                         connectioncontext.processsesponse(connectioncontext);                                                  
                    }                    
                    
                    else if(unsecuredinput.startsWith("//close"))
                    {
                         connectioncontext = new Bodiserverconnectioncontext(this, CLOSE, unsecuredinput, network, unsecuredbodiconnection);
                        
                         connectioncontext.processprotocol(connectioncontext);
                        
                         connectioncontext.processrequest(connectioncontext);     
                         
                         connectioncontext.processsesponse(connectioncontext);         
                    }

                    else if(unsecuredinput.startsWith("//open"))
                    {
                         connectioncontext = new Bodiserverconnectioncontext(this, OPEN, unsecuredinput, network, unsecuredbodiconnection);
                        
                         connectioncontext.processprotocol(connectioncontext);
                        
                         connectioncontext.processrequest(connectioncontext);     
                         
                         connectioncontext.processsesponse(connectioncontext);         
                    }

                    else if(unsecuredinput.startsWith("//pull")) 
                    {
                         connectioncontext = new Bodiserverconnectioncontext(this, PULL, unsecuredinput, network, unsecuredbodiconnection);
                        
                         connectioncontext.processprotocol(connectioncontext);
                        
                         connectioncontext.processrequest(connectioncontext);     
                         
                         connectioncontext.processsesponse(connectioncontext);         
                    }
                                        
                    else if(unsecuredinput.startsWith("//put"))
                    {
                         connectioncontext = new Bodiserverconnectioncontext(this, PUT, unsecuredinput, network, unsecuredbodiconnection);
                        
                         connectioncontext.processprotocol(connectioncontext);                         
                         
                         connectioncontext.processrequest(connectioncontext);     
                         
                         connectioncontext.processsesponse(connectioncontext);                              
                    }
                    
                    else if(unsecuredinput.startsWith("//trade"))
                    {
                         connectioncontext = new Bodiserverconnectioncontext(this, TRADE, unsecuredinput, network, unsecuredbodiconnection);
                        
                         connectioncontext.processprotocol(connectioncontext);
                         
                         connectioncontext.processrequest(connectioncontext);     
                         
                         connectioncontext.processsesponse(connectioncontext);         
                    }        
                    
                    else
                    {
                         connectioncontext = new Bodiserverconnectioncontext(this, OTHER, unsecuredinput, network, unsecuredbodiconnection);
                        
                         connectioncontext.processprotocol(connectioncontext);
                         
                         connectioncontext.processrequest(connectioncontext);     
                         
                         connectioncontext.processsesponse(connectioncontext);                                                                 
                    }                                        
                }         
            }
            catch(NullPointerException exception)
            {
                //presume no connection found [for now]
            }            
            catch(InvalidSessionException exception)
            {
                try
                {
                    //connectioncontext.processprotocol(connectioncontext);
                    connectioncontext = new Bodiserverconnectioncontext(this, OTHER, "", network, new Bodiconnection());
                
                    connectioncontext.bodiconnection.operation = "//other";
                    
                    connectioncontext.bodiconnection.cause = "unrecognized protocol"; 
                    
                    connectioncontext.bodiconnection.message = "unable to complete request";                   
                    
                    connectioncontext.processsesponse(connectioncontext);                                                                 
                }
                catch(Exception e)
                {
                    //
                }
                
                //exception.printStackTrace(System.err);
            }
            catch(SecurityException exception) 
            {                
                exception.printStackTrace(System.err);
            }
            catch(Exception exception)
            {
                exception.printStackTrace(System.err);
            }              
            finally
            {                
                this.dohandlecleanup(connectioncontext, network, unsecuredinput);                                                
            }
        }
    }      
    
    public void dohandlecleanup(Bodiserverconnectioncontext connectioncontext, Connection network, String unsecuredinput)
    {
        try{ this.storewithbodi(connectioncontext, network); }catch(Exception e){}
                
        try{ connectioncontext.network.inqueue.delete(0, unsecuredinput.length()); }catch(Exception e){}
                
        try{ connectioncontext.input = ""; }catch(Exception e){}                                
                
        try{ this.sleepmillis(500l); /*oh boy oh boy let's rest half a secibd */ }catch(Exception e){}        
    }
    
    private Boolean storewithbodi(Bodiserverconnectioncontext connectioncontext, Connection connection) throws Exception
    {
        if(connectioncontext==null) return false;
        
        if(connection==null) return false;
        
        Bodi.context("//bodi/server/remote/bodiconnections").put(connectioncontext.bodiconnection.sessionid, connectioncontext.bodiconnection);

        Bodi.context("//bodi/server/remote/netconnections").put(connection, connection);  //connection.sessionid --> connection SVP ASAP
        
        return true;
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
                    
                    if(token==null || existingconnection.sessionid==null) continue;                        
                    
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
        
        else throw new InvalidSessionException("No session with matching sessionid found.");
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