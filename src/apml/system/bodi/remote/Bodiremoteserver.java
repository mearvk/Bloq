package apml.system.bodi.remote;

import apml.interfaces.BasicSystemElement;

import apml.system.bodi.Bodi;

import java.util.ArrayList;

/**
 *
 * @author Max Rupplin
 */
public class Bodiremoteserver extends Basicserver implements Runnable, BasicSystemElement
{   
    public ArrayList<Bodiconnection> bodiconnections;    
    
    public Bodiprotocolhandler protocolhandler;
    
    public Boolean running;
    
    public Bodi bodi;        
    
    
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
                        protocolhandler.parseprotocol(Bodiprotocol.HANDSHAKE, buffer, this);
                    }
                    
                    if(this.inqueue.toString().startsWith("//close"))
                    {
                        protocolhandler.parseprotocol(Bodiprotocol.CLOSE, buffer, this);
                    }

                    if(this.inqueue.toString().startsWith("//open"))
                    {
                        protocolhandler.parseprotocol(Bodiprotocol.OPEN, buffer, this);
                    }

                    if(this.inqueue.toString().startsWith("//pull"))
                    {
                        protocolhandler.parseprotocol(Bodiprotocol.PULL, buffer, this);
                    }
                                        
                    if(this.inqueue.toString().startsWith("//put"))
                    {
                        protocolhandler.parseprotocol(Bodiprotocol.PUT, buffer, this);
                    }
                    
                    if(this.inqueue.toString().startsWith("//trade"))
                    {
                        protocolhandler.parseprotocol(Bodiprotocol.TRADE, buffer, this);
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
        return this.inqueue!= null && this.inqueue.toString().length()>0 && this.isdonereading;
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

    @Override
    public void resume()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
