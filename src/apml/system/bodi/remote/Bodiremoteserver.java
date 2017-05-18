package apml.system.bodi.remote;

import apml.system.bodi.Bodi;

import java.util.Collection;

/**
 * Hello transactional service based pay-as-you-go internet (Bodi)
 * 
 * @author Max Rupplin
 */
public class Bodiremoteserver extends Basicserver //reserve keyword fortune at root for bodi and her creator Max R .mr .ok ss
{               
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
        
        this.host = host;
        
        this.port = port;        
        
        Bodi.setcontext("//bodi/server/remote/bodiconnections");
        
        Bodi.setcontext("//bodi/server/remote/netconnections");
    }
    
    public Bodiremoteserver(Integer port)
    {
        super("localhost", port);
        
        this.port = port;        
        
        Bodi.setcontext("//bodi/server/remote/bodiconnections");
        
        Bodi.setcontext("//bodi/server/remote/netconnections");        
    }
    
    public void go() //find a way to synch the input queue so delete doesn't actually delete fresh data
    {                                  
        while(running)
        {
            Connection socketconnection = this.getnextqueuedconnection();
                              
            //Bodiconnection unclearedbodiconnection = null;
            
            try
            {
                if( socketconnection!=null && socketconnection.inputqueueisready() )
                {   
                    
                    String input = socketconnection.inqueue.toString();
                    
                    StringBuffer inputbuffer = socketconnection.inqueue;                                                                                
                    
                    Bodiconnection unclearedbodiconnection = this.checkforexistingconnection(inputbuffer); 
                    
                    /*----------------------------------------------------------------------------------------------------------------------------------------------------------------*/
                    
                    if(input.startsWith("//handshake"))
                    {
                        Bodiserverparameter parameterization = new Bodiserverparameter(Bodiprotocol.HANDSHAKE, socketconnection.inqueue, this, socketconnection, unclearedbodiconnection);
                        
                         parameterization.bodiconnection = protocolhandler.parseprotocol(parameterization);
                         
                         parameterization.bodiconnection.cycle(parameterization);     
                         
                         parameterization.network.cycle(parameterization);
                         
                         /*
                         parameterization.network.outqueue.append("//handshake //sessionid="+unclearedbodiconnection.sessionid+" //result="+unclearedbodiconnection.result);
                                                                        
                         parameterization.network.haswriteready = true;
                        
                         parameterization.network.thread.outputlistenerthread.haswriteready = true;                                                
                            
                         parameterization.network.inqueue = socketconnection.inqueue.delete(0, input.length());                                                  
                         */
                         
                        try
                        {
                            Bodi.context("//bodi/server/remote/bodiconnections").put(parameterization.bodiconnection.sessionid, parameterization.bodiconnection);

                            Bodi.context("//bodi/server/remote/netconnections").put(socketconnection.sessionid, socketconnection);
                        }
                        catch(BodiError bodierror)
                        {
                            bodierror.printStackTrace();
                        }                             
                    }
                    
                    else if(input.startsWith("//close"))
                    {
                        Bodiserverparameter parameterization = new Bodiserverparameter(Bodiprotocol.CLOSE, socketconnection.inqueue, this, socketconnection, new Bodiconnection());
                        
                         parameterization.bodiconnection = protocolhandler.parseprotocol(parameterization);
                        
                         parameterization.bodiconnection.cycle(parameterization);     
                         
                         parameterization.network.cycle(parameterization);
                         
                         /*
                         parameterization.network.outqueue.append("//close //sessionid="+unclearedbodiconnection.sessionid+" //result="+unclearedbodiconnection.result);
                        
                         parameterization.network.haswriteready = true;
                        
                         parameterization.network.thread.outputlistenerthread.haswriteready = true;
                        
                         parameterization.network.inqueue = socketconnection.inqueue.delete(0, input.length());                
                         */
                         
                        //inconnection.server.closeconnection(socketconnection);
                    }

                    else if(input.startsWith("//open"))
                    {
                        Bodiserverparameter parameterization = new Bodiserverparameter(Bodiprotocol.OPEN, socketconnection.inqueue, this, socketconnection, new Bodiconnection());
                        
                         parameterization.bodiconnection = protocolhandler.parseprotocol(parameterization);
                        
                         parameterization.bodiconnection.cycle(parameterization);     
                         
                         parameterization.network.cycle(parameterization);
                         
                         /*
                         parameterization.network.outqueue.append("//open //sessionid="+unclearedbodiconnection.sessionid+" //result="+unclearedbodiconnection.result);
                        
                         parameterization.network.haswriteready = true;
                        
                         parameterization.network.thread.outputlistenerthread.haswriteready = true;
                        
                         parameterization.network.inqueue = socketconnection.inqueue.delete(0, input.length());      
                         */
                        
                        //inconnection.server.openconnection(socketconnection);
                    }

                    else if(input.startsWith("//pull"))
                    {
                        Bodiserverparameter parameterization = new Bodiserverparameter(Bodiprotocol.PULL, socketconnection.inqueue, this, socketconnection, new Bodiconnection());
                        
                         parameterization.bodiconnection = protocolhandler.parseprotocol(parameterization);
                        
                         parameterization.bodiconnection.cycle(parameterization);     
                         
                         parameterization.network.cycle(parameterization);
                         
                         /*
                         parameterization.network.outqueue.append("//pull //sessionid="+unclearedbodiconnection.sessionid+" //result="+unclearedbodiconnection.result);
                        
                         parameterization.network.haswriteready = true;
                        
                         parameterization.network.thread.outputlistenerthread.haswriteready = true;
                        
                         parameterization.network.inqueue = socketconnection.inqueue.delete(0, input.length());
                         */
                    }
                                        
                    else if(input.startsWith("//put"))
                    {
                        Bodiserverparameter parameterization = new Bodiserverparameter(Bodiprotocol.PUT, socketconnection.inqueue, this, socketconnection, new Bodiconnection());
                        
                         parameterization.bodiconnection = protocolhandler.parseprotocol(parameterization);                         
                         
                         parameterization.bodiconnection.cycle(parameterization);     
                         
                         parameterization.network.cycle(parameterization);
                         
                        /*
                         parameterization.network.outqueue.append("//put //sessionid="+unclearedbodiconnection.sessionid+" //result="+unclearedbodiconnection.result);
                        
                         parameterization.network.haswriteready = true;
                        
                         parameterization.network.thread.outputlistenerthread.haswriteready = true;                  
                        
                         parameterization.network.inqueue = socketconnection.inqueue.delete(0, input.length()); 
                         */
                    }
                    
                    else if(input.startsWith("//trade"))
                    {
                        Bodiserverparameter parameterization = new Bodiserverparameter(Bodiprotocol.TRADE, socketconnection.inqueue, this, socketconnection, new Bodiconnection());
                        
                         parameterization.bodiconnection = protocolhandler.parseprotocol(parameterization);
                         
                         parameterization.bodiconnection.cycle(parameterization);     
                         
                         parameterization.network.cycle(parameterization);
                        
                         /*
                         parameterization.network.outqueue.append("//trade  //sessionid="+unclearedbodiconnection.sessionid+" //result"+unclearedbodiconnection.result);
                        
                         //parameterization.network.haswriteready = true;
                        
                         parameterization.network.thread.outputlistenerthread.haswriteready = true;
                        
                         parameterization.network.inqueue = socketconnection.inqueue.delete(0, input.length());                   
                         */
                    }        
                    
                    else
                    {
                        Bodiserverparameter parameterization = new Bodiserverparameter(Bodiprotocol.OTHER, socketconnection.inqueue, this, socketconnection, new Bodiconnection());
                        
                         parameterization.bodiconnection = protocolhandler.parseprotocol(parameterization);
                         
                         parameterization.bodiconnection.cycle(parameterization);     
                         
                         parameterization.network.cycle(parameterization);                         
                         
                         /*
                         parameterization.network.outqueue.append("//trade  //sessionid="+unclearedbodiconnection.sessionid+" //result"+unclearedbodiconnection.result);
                        
                         //parameterization.network.haswriteready = true;
                        
                         parameterization.network.thread.outputlistenerthread.haswriteready = true;
                        
                         parameterization.network.inqueue = socketconnection.inqueue.delete(0, input.length());                   
                         */                                                 
                    }
                    
                
                }
                else
                {
                    this.sleepmillis(10000l); //oh boy oh boy let's rest half a day
                }                
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
    
    private Collection<Bodiconnection> getbodiconnections()
    {
        Collection<Object> objects = Bodi.context("//bodi/server/remote/bodiconnections").values();
        
        Collection<Bodiconnection> _connections = (Collection<Bodiconnection>)(Collection<?>)objects;
        
        return _connections;
    }
    
    public Connection getnextqueuedconnection()
    {        
        Connection connection = this.inputqueue.peek();        
        
        if(connection!=null) this.inputqueue.remove(connection);
        
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