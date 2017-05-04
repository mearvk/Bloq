package apml.system.bodi.remote;

import apml.system.bodi.Bodi;

import static apml.system.bodi.remote.Bodiprotocol.*;
import java.util.ArrayList;

/**
 *
 * @author Max Rupplin
 */
public class Bodiremoteserver extends Protocolserver implements Runnable
{
    public static void main(String...args)
    {
        Bodiremoteserver server;
        
        server = new Bodiremoteserver("localhost", 8888);
        
        server.run();        
    }   
    
    public Bodi bodi;
    
    public ArrayList<Bodiconnection> bodiconnections;
    
    public Bodiremoteserver(String host, Integer port)
    {
        super(host, port);
        
        this.host = host;
        
        this.port = port;
        
        this.bodiconnections = new ArrayList<>();
    }
    
    public Bodiremoteserver(Integer port)
    {
        super(port);
        
        this.port = port;
        
        this.bodiconnections = new ArrayList<>();
    }
    
    @Override
    public void run()
    {
        while(running)
        {
            StringBuffer buffer = this.inqueue;
                    
            try
            {
                if( this.inqueue.toString().length()>0 && this.isdonereading==false )
                {                                        
                    if(this.inqueue.toString().startsWith("//handshake"))
                    {
                        this.parsebodi(Bodiprotocol.HANDSHAKE, buffer);
                    }
                    
                    if(this.inqueue.toString().startsWith("//close"))
                    {
                        this.parsebodi(Bodiprotocol.CLOSE, buffer);
                    }

                    if(this.inqueue.toString().startsWith("//open"))
                    {
                        this.parsebodi(Bodiprotocol.OPEN, buffer);
                    }

                    if(this.inqueue.toString().startsWith("//pull"))
                    {
                        this.parsebodi(Bodiprotocol.PULL, buffer);
                    }
                                        
                    if(this.inqueue.toString().startsWith("//put"))
                    {
                        this.parsebodi(Bodiprotocol.PUT, buffer);
                    }
                    
                    if(this.inqueue.toString().startsWith("//trade"))
                    {
                        this.parsebodi(Bodiprotocol.TRADE, buffer);
                    }                    
                }
                else
                {
                    this.sleepmillis(150l);
                }
            }
            catch(Exception e)
            {
                //e.printStackTrace();
            }
        }
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
    
    protected Object parsebodi(Bodiprotocol protocol, StringBuffer buffer) throws Exception
    {
        Bodiconnection acceptedconnection; 
        
        switch(protocol)
        {
            case HANDSHAKE: 
                acceptedconnection = this.checksessionid(bodiconnections, buffer, bodi);
                acceptedconnection.handshake(buffer, bodi);                     //request new connection id for persistent connection
                break;
            
            case CLOSE: 
                acceptedconnection = this.checksessionid(bodiconnections, buffer, bodi);
                acceptedconnection.close(buffer, bodi);                         //close a new request on existing connection
                break;
            
            case OPEN: 
                acceptedconnection = this.checksessionid(bodiconnections, buffer, bodi);
                acceptedconnection.open(buffer, bodi);                          //open a new request on existing connection
                break;
            
            case PULL: 
                acceptedconnection = this.checksessionid(bodiconnections, buffer, bodi);
                return acceptedconnection.pull(buffer, bodi);                   //return actual request as object                 
            
            case PUT: 
                acceptedconnection = this.checksessionid(bodiconnections, buffer, bodi);
                return acceptedconnection.put(buffer, bodi);                    //return boolean indicating if put was a success
            
            case TRADE: 
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
}
