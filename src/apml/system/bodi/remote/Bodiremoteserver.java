package apml.system.bodi.remote;

import apml.interfaces.BasicSystemElement;

import apml.system.bodi.Bodi;

import java.util.ArrayList;

/**
 *
 * @author Max Rupplin
 */
public class Bodiremoteserver extends Protocolserver implements Runnable, BasicSystemElement
{                
    public Bodi bodi;
    
    public ArrayList<Bodiconnection> bodiconnections;
    
    
    public static void main(String...args)
    {
        Bodiremoteserver server;
        
        server = new Bodiremoteserver("localhost", 8888);
        
        server.run();        
    }   
    
    
    public Bodiremoteserver(String host, Integer port)
    {
        super(host, port);
        
        this.host = host;
        
        this.port = port;
        
        this.bodiconnections = new ArrayList<>();
    }
    
    public Bodiremoteserver(Integer port)
    {
        super("localhost", port);
        
        this.port = port;
        
        this.bodiconnections = new ArrayList<>();
    }
    
    
    @Override
    public void run()
    {
        super.run();
        
        while(running)
        {
            StringBuffer buffer = this.inqueue;
                    
            try
            {
                if( inputqueueisready() )
                {                                        
                    if(this.inqueue.toString().startsWith("//handshake"))
                    {
                        this.parseprotocol(Bodiprotocol.HANDSHAKE, buffer);
                    }
                    
                    if(this.inqueue.toString().startsWith("//close"))
                    {
                        this.parseprotocol(Bodiprotocol.CLOSE, buffer);
                    }

                    if(this.inqueue.toString().startsWith("//open"))
                    {
                        this.parseprotocol(Bodiprotocol.OPEN, buffer);
                    }

                    if(this.inqueue.toString().startsWith("//pull"))
                    {
                        this.parseprotocol(Bodiprotocol.PULL, buffer);
                    }
                                        
                    if(this.inqueue.toString().startsWith("//put"))
                    {
                        this.parseprotocol(Bodiprotocol.PUT, buffer);
                    }
                    
                    if(this.inqueue.toString().startsWith("//trade"))
                    {
                        this.parseprotocol(Bodiprotocol.TRADE, buffer);
                    }                    
                }
                else
                {
                    this.sleepmillis(250l);
                }
            }
            catch(Exception e)
            {
                //e.printStackTrace();
            }
        }
    }
    
    private final Boolean inputqueueisready()
    {
        return this.inqueue.toString().length()>0 && this.isdonereading;
    }
    
    protected void sleepmillis(Long millis)
    {
        try
        {
            Thread.currentThread().sleep(150);
        }
        catch(InterruptedException ie)
        {
            return;
        }
        catch(Exception e)
        {
            //e.printStackTrace();
        }
        finally
        {
            //System.out.println("System in sleepmillis mode...");
        }        
    }
    
    @Override
    protected Object parseprotocol(String protocol, StringBuffer buffer) throws Exception
    {
        Bodiconnection acceptedconnection; 
        
        switch(protocol)
        {
            case Bodiprotocol.HANDSHAKE: 
                acceptedconnection = this.checksessionid(bodiconnections, buffer, bodi);
                acceptedconnection.handshake(buffer, bodi);                     //request new connection id for persistent connection
                break;
            
            case Bodiprotocol.CLOSE: 
                acceptedconnection = this.checksessionid(bodiconnections, buffer, bodi);
                acceptedconnection.close(buffer, bodi);                         //close a new request on existing connection
                break;
            
            case Bodiprotocol.OPEN: 
                acceptedconnection = this.checksessionid(bodiconnections, buffer, bodi);
                acceptedconnection.open(buffer, bodi);                          //open a new request on existing connection
                break;
            
            case Bodiprotocol.PULL: 
                acceptedconnection = this.checksessionid(bodiconnections, buffer, bodi);
                return acceptedconnection.pull(buffer, bodi);                   //return actual request as object                 
            
            case Bodiprotocol.PUT: 
                acceptedconnection = this.checksessionid(bodiconnections, buffer, bodi);
                return acceptedconnection.put(buffer, bodi);                    //return boolean indicating if put was a success
            
            case Bodiprotocol.TRADE: 
                acceptedconnection = this.checksessionid(bodiconnections, buffer, bodi);
                return acceptedconnection.trade(buffer, bodi);                  //trade bit coin for services etc. //hotswap one object for another //etc
        } 
        
        throw new NoValidConnectionFoundException("No valid connectino found.");
    }
    
    public Bodiconnection checksessionid(ArrayList<Bodiconnection> bodiconnections, StringBuffer buffer, Bodi bodi) throws Exception
    {
       String[] tokens = buffer.toString().split(":");
        
        for(String token : tokens)
        {          
            if(token.startsWith("//sessionid")) /* //sessionid = 1234567890 e.g. */
            {
                for(Bodiconnection incomingconnection : this.bodiconnections)
                {
                    for(String sessionid : incomingconnection.sessionids)
                    
                    if(token.trim().toLowerCase().equalsIgnoreCase(sessionid))
                    {
                        return incomingconnection;                        
                    }
                }
            }
        }
        
        throw new NoValidConnectionFoundException("No valid connection found");
    }

    @Override
    public void autostart()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void init()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void pause()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void restart()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void start()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void stop()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
