package apml.system.bodi.remote;

import apml.interfaces.BasicSystemElement;

import apml.system.bodi.Bodi;

import java.util.ArrayList;

import java.util.Queue;

/**
 *
 * @author Max Rupplin
 */
public class Bodiremoteserver extends Basicserver implements Runnable, BasicSystemElement
{   
    public ArrayList<Bodiconnection> bodiconnections;    
    
    public Bodiprotocolhandler protocolhandler;
    
    public Boolean running = true; //start at false?
    
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
            Connection inconnection = this.getnextqueuedconnection();
            
            Bodiconnection bodiconnection = null;
                    
            try
            {
                if( inconnection!=null && inconnection.inputqueueisready() )
                {                                        
                    if(inconnection.inqueue.toString().startsWith("//handshake"))
                    {
                        bodiconnection = protocolhandler.parseprotocol(Bodiprotocol.HANDSHAKE, inconnection.inqueue, this);
                        
                        inconnection.outqueue.append("//handshake //sessionid="+bodiconnection.sessionid);
                        
                        inconnection.haswriteready = true;
                        
                        inconnection.thread.outputlistenerthrread.haswriteready = true;
                    }
                    
                    if(inconnection.inqueue.toString().startsWith("//close"))
                    {
                        bodiconnection = protocolhandler.parseprotocol(Bodiprotocol.CLOSE, inconnection.inqueue, this);
                        
                        inconnection.outqueue.append("//close");
                        
                        inconnection.haswriteready = true;
                        
                        inconnection.thread.outputlistenerthrread.haswriteready = true;
                    }

                    if(inconnection.inqueue.toString().startsWith("//open"))
                    {
                        bodiconnection = protocolhandler.parseprotocol(Bodiprotocol.OPEN, inconnection.inqueue, this);
                        
                        inconnection.outqueue.append("//open");
                        
                        inconnection.haswriteready = true;
                        
                        inconnection.thread.outputlistenerthrread.haswriteready = true;
                    }

                    if(inconnection.inqueue.toString().startsWith("//pull"))
                    {
                        bodiconnection = protocolhandler.parseprotocol(Bodiprotocol.PULL, inconnection.inqueue, this);
                        
                        inconnection.outqueue.append("//pull");
                        
                        inconnection.haswriteready = true;
                        
                        inconnection.thread.outputlistenerthrread.haswriteready = true;
                    }
                                        
                    if(inconnection.inqueue.toString().startsWith("//put"))
                    {
                        bodiconnection = protocolhandler.parseprotocol(Bodiprotocol.PUT, inconnection.inqueue, this);
                        
                        inconnection.outqueue.append("//put");
                        
                        inconnection.haswriteready = true;
                        
                        inconnection.thread.outputlistenerthrread.haswriteready = true;                  
                    }
                    
                    if(inconnection.inqueue.toString().startsWith("//trade"))
                    {
                        bodiconnection = protocolhandler.parseprotocol(Bodiprotocol.TRADE, inconnection.inqueue, this);
                        
                        inconnection.outqueue.append("//trade");
                        
                        inconnection.haswriteready = true;
                        
                        inconnection.thread.outputlistenerthrread.haswriteready = true;
                    }                    
                }
                else
                {
                    this.sleepmillis(250l);
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }    
    
    public Connection getnextqueuedconnection()
    {
        Connection connection;
        
        connection = this.inputqueue.peek();
        
        if(connection==null) return null;
        
        this.inputqueue.remove(connection);
        
        return connection;
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
    
    protected Bodiconnection checksessionid(ArrayList<Bodiconnection> bodiconnections, StringBuffer buffer, Bodi bodi) throws Exception
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

class Inputqueue 
{
    public Queue<Connection> queue;
    
    public void add(Connection connection)
    {
        this.queue.add(connection);
    }
    
    public void remove(Connection connection)
    {
        this.queue.remove(connection);
    }
    
    public Connection peek()
    {
        return this.queue.peek();
    }
}