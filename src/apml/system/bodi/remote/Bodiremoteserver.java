package apml.system.bodi.remote;

import apml.system.bodi.Bodi;

import java.util.ArrayList;

/**
 *
 * @author Max Rupplin
 */
public class Bodiremoteserver extends Basicserver
{       
    public ArrayList<Bodiconnection> bodiconnections = new ArrayList();    
    
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
    }
    
    public Bodiremoteserver(Integer port)
    {
        super("localhost", port);
        
        this.port = port;        
    }

    public void go()
    {                                  
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
                        bodiconnection = protocolhandler.parseprotocol(Bodiprotocol.HANDSHAKE, inconnection.inqueue, this, inconnection);
                                                
                        inconnection.outqueue.append("//handshake //sessionid="+bodiconnection.sessionid);
                                                                        
                        inconnection.haswriteready = true;
                        
                        inconnection.thread.outputlistenerthread.haswriteready = true;                        
                        
                        inconnection.inqueue.delete(0, inconnection.inqueue.length());
                    }
                    
                    if(inconnection.inqueue.toString().startsWith("//close"))
                    {
                        bodiconnection = protocolhandler.parseprotocol(Bodiprotocol.CLOSE, inconnection.inqueue, this, inconnection);
                        
                        inconnection.outqueue.append("//close");
                        
                        inconnection.haswriteready = true;
                        
                        inconnection.thread.outputlistenerthread.haswriteready = true;
                        
                        inconnection.inqueue.delete(0, inconnection.inqueue.length());
                    }

                    if(inconnection.inqueue.toString().startsWith("//open"))
                    {
                        bodiconnection = protocolhandler.parseprotocol(Bodiprotocol.OPEN, inconnection.inqueue, this, inconnection);
                        
                        inconnection.outqueue.append("//open");
                        
                        inconnection.haswriteready = true;
                        
                        inconnection.thread.outputlistenerthread.haswriteready = true;
                        
                        inconnection.inqueue.delete(0, inconnection.inqueue.length());
                    }

                    if(inconnection.inqueue.toString().startsWith("//pull"))
                    {
                        bodiconnection = protocolhandler.parseprotocol(Bodiprotocol.PULL, inconnection.inqueue, this, inconnection);
                        
                        inconnection.outqueue.append("//pull");
                        
                        inconnection.haswriteready = true;
                        
                        inconnection.thread.outputlistenerthread.haswriteready = true;
                        
                        inconnection.inqueue.delete(0, inconnection.inqueue.length());
                    }
                                        
                    if(inconnection.inqueue.toString().startsWith("//put"))
                    {
                        bodiconnection = protocolhandler.parseprotocol(Bodiprotocol.PUT, inconnection.inqueue, this, inconnection);
                        
                        inconnection.outqueue.append("//put");
                        
                        inconnection.haswriteready = true;
                        
                        inconnection.thread.outputlistenerthread.haswriteready = true;                  
                        
                        inconnection.inqueue.delete(0, inconnection.inqueue.length());
                    }
                    
                    if(inconnection.inqueue.toString().startsWith("//trade"))
                    {
                        bodiconnection = protocolhandler.parseprotocol(Bodiprotocol.TRADE, inconnection.inqueue, this, inconnection);
                        
                        inconnection.outqueue.append("//trade");
                        
                        inconnection.haswriteready = true;
                        
                        inconnection.thread.outputlistenerthread.haswriteready = true;
                        
                        inconnection.inqueue.delete(0, inconnection.inqueue.length());
                    }                    
                }
                else
                {
                    //this.sleepmillis(500l);
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