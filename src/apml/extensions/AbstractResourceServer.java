package apml.extensions;

import apml.system.bodi.Bodi;

import java.util.Collection;

import java.util.Objects;

/**
 * Implementation of a session aware AbstractResourceServer
 * 
 * @author Max Rupplin
 */

public abstract class AbstractResourceServer extends AbstractBaseServer         
{                       
    public Integer hash = 0x008808EF;                   
    
    public String resourcecontext = "//bodi/systems/"+this.hashCode()+"/resource/connections";
    
    public String networkcontext = "//bodi/systems/"+this.hashCode()+"/network/connections";
    
    /**
     * 
     * @param args 
     */
    public static void main(String...args)
    {
        
    }       
    
    /**
     * 
     * @param host
     * @param port 
     */
    public AbstractResourceServer(String host, Integer port)
    {      
        super(host, port);
        
        if(host==null || port==null) throw new SecurityException("//bodi/connect");
        
        this.host = host;
        
        this.port = port;        
        
        Bodi.setcontext(resourcecontext);
        
        Bodi.setcontext(networkcontext);
    }
    
    /**
     * 
     * @param port 
     */
    public AbstractResourceServer(Integer port)
    {
        super("localhost", port);
        
        if(port==null) throw new SecurityException("//bodi/connect");
        
        this.port = port;        
        
        Bodi.setcontext(resourcecontext);
        
        Bodi.setcontext(networkcontext);       
    }
    
    /**
     * Attend new connections and resource requests
     */
    @Override
    public void attend() 
    {                                  
        while(running)
        {                                                
            ServerContext servercontext = null;
            
            NetworkContext networkcontext = this.getattendantnetworkcontext();                         
                        
            //
            
            try
            {                
                if( this.dovalidatenetworkcontext(networkcontext) ) //
                {                                                                                
                    servercontext = new ServerContext(this, networkcontext, this.pollstoredresourcesessions(networkcontext));
                    
                    if( this.dovalidateresourcecontext(servercontext) ) 
                    {                                                                                                                      
                        this.processprotocol(servercontext);
                        
                        this.processrequest(servercontext);
                        
                        this.processsesponse(servercontext);
                    }  
                    else
                    {
                        //fail this
                    }                    
                }                                                   
            }
            catch(SecurityException exception) 
            {                                                
                exception.printStackTrace(System.err);
                
                //
            }            
            catch(NullPointerException exception)
            {
                exception.printStackTrace(System.err);
                
                //
            }            
            catch(Exception exception)
            {
                exception.printStackTrace(System.err);
                
                //make sure we care for protocol specific error messages here too
            }              
            finally
            {                
                //
                
                this.finalizenetworkcontext(networkcontext);     
                
                //
                
                this.finalizeresourcecontext(servercontext);
                
                //
                
                this.sleepmillis(500L);
                
                //
                
                System.gc();
                
                //
            }
        }
    }
    
    /**
     * That network context is non-null, has a value to be read in the InputQueue and is still connected to a client.
     * 
     * @param networkcontext The network context containing the network and the associated the input queue.
     * 
     * @return TRUE is underlying network context is not null, has some ready input and the network connection (socket) is not disconnected but connected and open; FALSE otherwise.
     */
    public Boolean dovalidatenetworkcontext(NetworkContext networkcontext)
    {
        return networkcontext!=null && networkcontext.inputqueueisready() && networkcontext.issocketconnected();
    }
    
    /**
     * We care that only valid resources requests get introduced into the server and her routing procedure.
     * 
     * @param servercontext The server context containing the network packet and the resourcecontext for inspection.
     * 
     * @return TRUE if ResourceContext is valid by these requirements; FALSE otherwise.
     */
    public abstract Boolean dovalidateresourcecontext(ServerContext servercontext);
    
    /**
     * Clear me
     * 
     * @param servercontext 
     */
    public void finalizeresourcecontext(ServerContext servercontext)
    {
        if(servercontext==null) return; 
        
        servercontext.resourcecontext.value = "";
        
        servercontext.resourcecontext.result = "";
        
        servercontext.resourcecontext.message = "";
        
        servercontext.resourcecontext.value = null;
        
        servercontext.resourcecontext.result = null;
        
        servercontext.resourcecontext.message = null;        
    }
    
    /**
     * Clear cyclical buffers 
     * 
     * @param networkcontext 
     */
    public void finalizenetworkcontext(NetworkContext networkcontext)
    {                
        if(networkcontext==null) return;
        
        if(networkcontext.inqueue==null) return;
            
        try
        { 
            networkcontext.inqueue.delete(0, networkcontext.inqueue.length());                         
        }
        catch(Exception e)
        {
            e.printStackTrace(System.err);
        } 
    }
    
    /**
     * Store session objects with a Bodi carekeeper
     * 
     * @param connectioncontext
     * @param connection
     * @return
     * @throws Exception 
     */    
    public Boolean trystorecontextstobodhi(ServerContext connectioncontext, NetworkContext connection) throws Exception
    {
        if(connectioncontext==null) return false;
        
        if(connection==null) return false;
        
        if(connection.socket==null) return false;
        
        Bodi.context(resourcecontext).put(connectioncontext.resourcecontext.sessionid.toString(), connectioncontext.resourcecontext);
        
        Bodi.context(networkcontext).put(connection.socket.getInetAddress().toString(), connection);
        
        return true;
    }
    
    /**
     * Returns the Collection of Bodiconnections from the Bodi server context.
     * 
     * @return The Collection of Bodiconnections from the Bodi server context.
     */
    public Collection<ResourceContext> getresourceconnections()
    {
        Collection<Object> objects = Bodi.context(resourcecontext).values();
        
        Collection<ResourceContext> _connections = (Collection<ResourceContext>)(Collection<?>)objects;
        
        return _connections;
    }
    
    /**
     * Returns the next network connection from InputQueue from BasicServer class to AbstractResourceServer
     * 
     * @return That particular NetworkContext or null if none found queued
     */
    public NetworkContext getattendantnetworkcontext()
    {        
        NetworkContext connection = this.connectionqueue.peek();        
        
        if(connection!=null) this.connectionqueue.remove(connection);
        
        return connection;
    }    
    
    /**
     * Talk to system; have it rest millis number of milliseconds before returning to task.
     * 
     * @param millis Rest this many milliseconds and no longer.
     */
    public void sleepmillis(Long millis) 
    {
        try
        {
            Thread.currentThread().sleep(millis);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }    
    
    /**
     * 
     * @param networkcontext
     * @return
     * @throws Exception 
     */
    public ResourceContext pollstoredresourcesessions(NetworkContext networkcontext) throws Exception
    {
        if(networkcontext==null) throw new SecurityException("//bodi/connect");
        
        if(networkcontext.inqueue==null) throw new SecurityException("//bodi/connect");        
                    
        String[] tokens = networkcontext.inqueue.toString().split(" ");                
        
        for(String token : tokens)
        {          
            if(token.trim().startsWith("//sessionid"))
            {
                for(ResourceContext existingconnection : this.getresourceconnections())
                {
                    token = token.trim().replace("//sessionid=", "");
                    
                    if(token==null || existingconnection.sessionid==null) continue;                        
                    
                    int receivedsessionid = Integer.parseInt(token);
                    
                    int existingsessionid = existingconnection.sessionid;
                    
                    if(receivedsessionid == existingsessionid)
                    {
                        if(existingconnection.ttl>0)
                            
                        return existingconnection;
                        
                        else return null;                            
                    }
                }
            }
        }
        
        return new ResourceContext();                
    }
    
    /**
     * 
     * @param connection
     * @return 
     */
    public Boolean isvalidsessionid(ResourceContext connection)
    {
        if(connection==null) return false;
        
        if(connection.sessionid==null) return false;
        
        for(ResourceContext existingconnection : this.getresourceconnections())
        {
            if(Objects.equals(existingconnection.sessionid, connection.sessionid))
            {
                return true;
            }
        }
        
        return false;
    }  
    
    /**
     * 
     * @param connection
     * @return 
     */
    public Boolean isvalidsession(ResourceContext connection)
    {
        if(connection==null) return false;
        
        if(connection.sessionid==null) return false;
        
        if(connection.ttl<=0) return false;
        
        for(ResourceContext existingconnection : this.getresourceconnections())
        {
            if(Objects.equals(existingconnection.sessionid, connection.sessionid))
            {                
                return connection.ttl > 0;                
            }
        }
        
        return false;
    }    

    /**
     * 
     * @param bytes 
     */
    @Override
    public void write(byte[] bytes)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * 
     * @return 
     */
    @Override
    public byte[] read()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * 
     * @param connectioncontext
     * @throws Exception 
     */
    public abstract void processprotocol(ServerContext connectioncontext) throws Exception;

    /**
     * 
     * @param connectioncontext
     * @throws Exception 
     */
    public abstract void processrequest(ServerContext connectioncontext) throws Exception;
    
    /**
     * 
     * @param connectioncontext
     * @throws Exception 
     */
    public abstract void processsesponse(ServerContext connectioncontext) throws Exception;
}