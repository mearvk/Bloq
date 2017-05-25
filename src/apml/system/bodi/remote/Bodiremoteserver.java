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
            Networkconnectioncontext networkcontext = this.pollqueuednetworkconnections();                                      
            
            try
            {                
                if( this.trysecurenetwork(networkcontext) ) //
                {
                    
                    Bodiservercontext bodiservercontext = new Bodiservercontext(this, networkcontext);                                        
                    
                    bodiservercontext.networkcontext = networkcontext;                    
                    
                    bodiservercontext.bodicontext = this.pollstoredbodisessions(networkcontext);
                    
                    
                    if( this.trysecurebodiconnection(bodiservercontext) ) //
                    {

                        bodiservercontext.inputbuffer = new StringBuffer(bodiservercontext.networkcontext.inqueue);
                        
                        bodiservercontext.inputstring = new StringBuffer(bodiservercontext.networkcontext.inqueue).toString();                                                                                                

                        
                        if(bodiservercontext.inputstring.startsWith(HANDSHAKE))
                        {
                            bodiservercontext = new Bodiservercontext(this, HANDSHAKE, bodiservercontext);

                            bodiservercontext.processprotocol(bodiservercontext);

                            bodiservercontext.processrequest(bodiservercontext);     

                            bodiservercontext.processsesponse(bodiservercontext);     
                            
                            //
                            
                            this.trystorecontextstobodhi(bodiservercontext, networkcontext);
                        }                    

                        else if(bodiservercontext.inputstring.startsWith(CLOSE))
                        {
                            bodiservercontext = new Bodiservercontext(this, CLOSE, bodiservercontext);

                            bodiservercontext.processprotocol(bodiservercontext);

                            bodiservercontext.processrequest(bodiservercontext);     

                            bodiservercontext.processsesponse(bodiservercontext);         
                            
                            //
                            
                            this.trystorecontextstobodhi(bodiservercontext, networkcontext);
                        }

                        else if(bodiservercontext.inputstring.startsWith(OPEN))
                        {
                            bodiservercontext = new Bodiservercontext(this, OPEN, bodiservercontext);

                            bodiservercontext.processprotocol(bodiservercontext);

                            bodiservercontext.processrequest(bodiservercontext);     

                            bodiservercontext.processsesponse(bodiservercontext);      
                            
                            //
                            
                            this.trystorecontextstobodhi(bodiservercontext, networkcontext);
                        }

                        else if(bodiservercontext.inputstring.startsWith(PULL)) 
                        {
                            bodiservercontext = new Bodiservercontext(this, PULL, bodiservercontext);

                            bodiservercontext.processprotocol(bodiservercontext);

                            bodiservercontext.processrequest(bodiservercontext);     

                            bodiservercontext.processsesponse(bodiservercontext);         
                            
                            //
                            
                            this.trystorecontextstobodhi(bodiservercontext, networkcontext);
                        }

                        else if(bodiservercontext.inputstring.startsWith(PUT))
                        {
                            bodiservercontext = new Bodiservercontext(this, PUT, bodiservercontext);

                            bodiservercontext.processprotocol(bodiservercontext);                         

                            bodiservercontext.processrequest(bodiservercontext);     

                            bodiservercontext.processsesponse(bodiservercontext);                              
                            
                            //
                            
                            this.trystorecontextstobodhi(bodiservercontext, networkcontext);
                        }

                        else if(bodiservercontext.inputstring.startsWith(TRADE))
                        {
                            bodiservercontext = new Bodiservercontext(this, TRADE, bodiservercontext);

                            bodiservercontext.processprotocol(bodiservercontext);

                            bodiservercontext.processrequest(bodiservercontext);     

                            bodiservercontext.processsesponse(bodiservercontext);         
                            
                            //
                            
                            this.trystorecontextstobodhi(bodiservercontext, networkcontext);
                        }        

                        else
                        {
                            bodiservercontext = new Bodiservercontext(this, OTHER, "", bodiservercontext.networkcontext, new Bodiconnection());

                            bodiservercontext.processsesponse(bodiservercontext);                                                                 
                        }                        
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
            catch(InvalidSessionException exception)
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
                this.trypurgeinputbuffer(networkcontext);           
                
                //
                
                this.sleepmillis(500L);
                
                //
            }
        }
    }      
    
    public Boolean trysecurenetwork(Networkconnectioncontext networkcontext)
    {
        return networkcontext!=null && networkcontext.inputqueueisready() && networkcontext.issocketconnected();
    }
    
    public Boolean trysecurebodiconnection(Bodiservercontext bodiservercontext)
    {
        return 
            
            bodiservercontext!=null && 
                
            bodiservercontext.bodicontext!=null &&                             
            
            bodiservercontext.packet!=null &&                    
            
            (
                bodiservercontext.packet.startsWith("//handshake") ||
                
                bodiservercontext.packet.startsWith("//close") ||
                
                bodiservercontext.packet.startsWith("//open") ||
                
                bodiservercontext.packet.startsWith("//pull") ||
                
                bodiservercontext.packet.startsWith("//put") ||
                
                bodiservercontext.packet.startsWith("//trade")
            );
    }    
    
    public void trypurgeinputbuffer(Networkconnectioncontext networkcontext)
    {                
        try
        { 
            networkcontext.inqueue.delete(0, networkcontext.inqueue.length()); 
        }
        catch(Exception e){} 
    }
    
    private Boolean trystorecontextstobodhi(Bodiservercontext connectioncontext, Networkconnectioncontext connection) throws Exception
    {
        if(connectioncontext==null) return false;
        
        if(connection==null) return false;
        
        Bodi.context("//bodi/server/remote/bodiconnections").put(connectioncontext.bodicontext.sessionid, connectioncontext.bodicontext);

        Bodi.context("//bodi/server/remote/netconnections").put(connection, connection);  //connection.sessionid --> connection SVP ASAP
        
        return true;
    }
    
    private Collection<Bodiconnection> getbodiconnections()
    {
        Collection<Object> objects = Bodi.context("//bodi/server/remote/bodiconnections").values();
        
        Collection<Bodiconnection> _connections = (Collection<Bodiconnection>)(Collection<?>)objects;
        
        return _connections;
    }
    
    /**
     * 
     * @return 
     */
    public Networkconnectioncontext pollqueuednetworkconnections()
    {        
        Networkconnectioncontext connection = this.connectionqueue.peek();        
        
        if(connection!=null) this.connectionqueue.remove(connection);
        
        return connection;
    }    
    
    protected void sleepmillis(Long millis) 
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
    
    protected Bodiconnection pollstoredbodisessions(Networkconnectioncontext networkcontext) throws Exception
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