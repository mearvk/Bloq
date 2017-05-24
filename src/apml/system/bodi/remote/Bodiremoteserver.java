package apml.system.bodi.remote;

import apml.system.bodi.Bodi;

import static apml.system.bodi.remote.Bodiprotocol.*;

import java.util.Collection;

import java.util.Objects;

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
            Bodiserverconnectioncontext bodiconnectioncontext = null;
            
            Networkconnectioncontext networkcontext = this.pollqueuednetworkconnections();                                      
            
            try
            {                
                if( this.networkiscleared(networkcontext) )
                {
                    Bodiconnection unsecuredbodiconnection = this.pollqueuedbodiconnections(networkcontext);
                    
                    String unsecuredinput = networkcontext.inqueue.toString();
                    
                    /*----------------------------------------------------------------------------------------------------------------------------------------------------------------*/                                                                                
                                        
                    if(unsecuredinput.startsWith("//handshake"))
                    {
                        bodiconnectioncontext = new Bodiserverconnectioncontext(this, HANDSHAKE, unsecuredinput, networkcontext, unsecuredbodiconnection);
                        
                        bodiconnectioncontext.processprotocol(bodiconnectioncontext);
                         
                        bodiconnectioncontext.processrequest(bodiconnectioncontext);     
                         
                        bodiconnectioncontext.processsesponse(bodiconnectioncontext);                                                  
                    }                    
                    
                    else if(unsecuredinput.startsWith("//close"))
                    {
                        bodiconnectioncontext = new Bodiserverconnectioncontext(this, CLOSE, unsecuredinput, networkcontext, unsecuredbodiconnection);
                        
                        bodiconnectioncontext.processprotocol(bodiconnectioncontext);
                        
                        bodiconnectioncontext.processrequest(bodiconnectioncontext);     
                         
                        bodiconnectioncontext.processsesponse(bodiconnectioncontext);         
                    }

                    else if(unsecuredinput.startsWith("//open"))
                    {
                        bodiconnectioncontext = new Bodiserverconnectioncontext(this, OPEN, unsecuredinput, networkcontext, unsecuredbodiconnection);
                        
                        bodiconnectioncontext.processprotocol(bodiconnectioncontext);
                        
                        bodiconnectioncontext.processrequest(bodiconnectioncontext);     
                         
                        bodiconnectioncontext.processsesponse(bodiconnectioncontext);         
                    }

                    else if(unsecuredinput.startsWith("//pull")) 
                    {
                        bodiconnectioncontext = new Bodiserverconnectioncontext(this, PULL, unsecuredinput, networkcontext, unsecuredbodiconnection);
                        
                        bodiconnectioncontext.processprotocol(bodiconnectioncontext);
                        
                        bodiconnectioncontext.processrequest(bodiconnectioncontext);     
                         
                        bodiconnectioncontext.processsesponse(bodiconnectioncontext);         
                    }
                                        
                    else if(unsecuredinput.startsWith("//put"))
                    {
                        bodiconnectioncontext = new Bodiserverconnectioncontext(this, PUT, unsecuredinput, networkcontext, unsecuredbodiconnection);
                        
                        bodiconnectioncontext.processprotocol(bodiconnectioncontext);                         
                         
                        bodiconnectioncontext.processrequest(bodiconnectioncontext);     
                         
                        bodiconnectioncontext.processsesponse(bodiconnectioncontext);                              
                    }
                    
                    else if(unsecuredinput.startsWith("//trade"))
                    {
                        bodiconnectioncontext = new Bodiserverconnectioncontext(this, TRADE, unsecuredinput, networkcontext, unsecuredbodiconnection);
                        
                        bodiconnectioncontext.processprotocol(bodiconnectioncontext);
                         
                        bodiconnectioncontext.processrequest(bodiconnectioncontext);     
                         
                        bodiconnectioncontext.processsesponse(bodiconnectioncontext);         
                    }        
                    
                    else
                    {
                        bodiconnectioncontext = new Bodiserverconnectioncontext(this, OTHER, "", networkcontext, new Bodiconnection());
                        
                        bodiconnectioncontext.bodiconnection.cause = "unrecognized protocol"; 
                    
                        bodiconnectioncontext.bodiconnection.message = "unable to complete request";  
                         
                        bodiconnectioncontext.processsesponse(bodiconnectioncontext);                                                                 
                    }                                        
                }         
            }
            catch(NullPointerException exception)
            {
                exception.printStackTrace(System.err);
                
                //
            }            
            catch(InvalidSessionException exception)
            {
                exception.printStackTrace(System.err);
                
                //
            }
            catch(SecurityException exception) 
            {                                                
                exception.printStackTrace(System.err);
                
                //
            }
            catch(Exception exception)
            {
                exception.printStackTrace(System.err);
                
                //                                
            }              
            finally
            {                
                this.dohandlecleanup(bodiconnectioncontext, networkcontext);                                                
            }
        }
    }      
    
    public Boolean networkiscleared(Networkconnectioncontext networkcontext)
    {
        return networkcontext!=null && networkcontext.inputqueueisready();
    }
    
    public void dohandlecleanup(Bodiserverconnectioncontext connectioncontext, Networkconnectioncontext network)
    {
        try
        { 
            this.storewithbodi(connectioncontext, network); 
        }
        catch(Exception e){}
                
        try
        { 
            connectioncontext.network.inqueue.delete(0, connectioncontext.network.inqueue.length()); 
        }
        catch(Exception e){}
                
        try
        { 
            connectioncontext.input = ""; 
        }
        catch(Exception e){}                                
                
        try
        { 
            this.sleepmillis(500l); /*oh boy oh boy let's rest half a day ; yawn; yawn */ 
        }
        catch(Exception e){}        
    }
    
    private Boolean storewithbodi(Bodiserverconnectioncontext connectioncontext, Networkconnectioncontext connection) throws Exception
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
    
    public Networkconnectioncontext pollqueuednetworkconnections()
    {        
        Networkconnectioncontext connection = this.connectionqueue.peek();        
        
        if(connection!=null) this.connectionqueue.remove(connection);
        
        return connection;
    }    
    
    protected void sleepmillis(Long millis) throws Exception
    {
        Thread.currentThread().sleep(millis);
    }    
    
    protected Bodiconnection pollqueuedbodiconnections(Networkconnectioncontext networkcontext) throws Exception
    {
        if(networkcontext==null) throw new SecurityException("//bodi/connect");
        
        if(networkcontext.inqueue==null) throw new SecurityException("//bodi/connect");        
                    
        String[] tokens = networkcontext.inqueue.toString().split(" ");                
        
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
                        if(existingconnection.ttl>0)
                            
                        return existingconnection;
                        
                        else return null;                            
                    }
                }
            }
        }
        
        if(networkcontext.inqueue.toString().startsWith("//handshake")) 
        {
            return new Bodiconnection();
        }
        
        else return null;
    }
    
    public Boolean isvalidsessionid(Bodiconnection connection)
    {
        if(connection==null) return false;
        
        if(connection.sessionid==null) return false;
        
        for(Bodiconnection existingconnection : this.getbodiconnections())
        {
            if(Objects.equals(existingconnection.sessionid, connection.sessionid))
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
            if(Objects.equals(existingconnection.sessionid, connection.sessionid))
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