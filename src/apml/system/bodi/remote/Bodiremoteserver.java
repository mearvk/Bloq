package apml.system.bodi.remote;

import apml.system.bodi.Bodi;

import java.util.ArrayList;

/**
 * Hello transactional service based pay-as-you-go internet (Bodi)
 * 
 * @author Max Rupplin
 */
public class Bodiremoteserver extends Basicserver
{       
    public ArrayList<Bodiconnection> bodiconnections = new ArrayList();    
//    
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
    
    public void go() //find a way to synch the input queue so delete doesn't actually delete fresh data
    {                                  
        while(running)
        {
            Connection socketconnection = this.getnextqueuedconnection();
            
            Bodiconnection bodiconnection = null;
                    
            try
            {
                if( socketconnection!=null && socketconnection.inputqueueisready() )
                {                                        
                    String input = socketconnection.inqueue.toString();
                    
                    if(input.startsWith("//handshake"))
                    {
                        bodiconnection = protocolhandler.parseprotocol(Bodiprotocol.HANDSHAKE, socketconnection.inqueue, this, socketconnection);
                                                
                        socketconnection.outqueue.append("//handshake //sessionid="+bodiconnection.sessionid+" //result="+bodiconnection.result);
                                                                        
                        socketconnection.haswriteready = true;
                        
                        socketconnection.thread.outputlistenerthread.haswriteready = true;                                                
                            
                        socketconnection.inqueue.delete(0, input.length()); 
                    }
                    
                    if(input.startsWith("//close"))
                    {
                        bodiconnection = protocolhandler.parseprotocol(Bodiprotocol.CLOSE, socketconnection.inqueue, this, socketconnection);
                        
                        socketconnection.outqueue.append("//close //sessionid="+bodiconnection.sessionid+" //result="+bodiconnection.result);
                        
                        socketconnection.haswriteready = true;
                        
                        socketconnection.thread.outputlistenerthread.haswriteready = true;
                        
                        socketconnection.inqueue.delete(0, input.length());                
                        
                        //inconnection.server.closeconnection(socketconnection);
                    }

                    if(input.startsWith("//open"))
                    {
                        bodiconnection = protocolhandler.parseprotocol(Bodiprotocol.OPEN, socketconnection.inqueue, this, socketconnection);
                        
                        socketconnection.outqueue.append("//open //sessionid="+bodiconnection.sessionid+" //result="+bodiconnection.result);
                        
                        socketconnection.haswriteready = true;
                        
                        socketconnection.thread.outputlistenerthread.haswriteready = true;
                        
                        socketconnection.inqueue.delete(0, input.length());      
                        
                        //inconnection.server.openconnection(socketconnection);
                    }

                    if(input.startsWith("//pull"))
                    {
                        bodiconnection = protocolhandler.parseprotocol(Bodiprotocol.PULL, socketconnection.inqueue, this, socketconnection);
                        
                        socketconnection.outqueue.append("//pull //sessionid="+bodiconnection.sessionid+" //result="+bodiconnection.result);
                        
                        socketconnection.haswriteready = true;
                        
                        socketconnection.thread.outputlistenerthread.haswriteready = true;
                        
                        socketconnection.inqueue.delete(0, input.length());                   
                    }
                                        
                    if(input.startsWith("//put"))
                    {
                        bodiconnection = protocolhandler.parseprotocol(Bodiprotocol.PUT, socketconnection.inqueue, this, socketconnection);
                        
                        socketconnection.outqueue.append("//put //sessionid="+bodiconnection.sessionid+" //result="+bodiconnection.result);
                        
                        socketconnection.haswriteready = true;
                        
                        socketconnection.thread.outputlistenerthread.haswriteready = true;                  
                        
                        socketconnection.inqueue.delete(0, input.length()); 
                    }
                    
                    if(input.startsWith("//trade"))
                    {
                        bodiconnection = protocolhandler.parseprotocol(Bodiprotocol.TRADE, socketconnection.inqueue, this, socketconnection);
                        
                        socketconnection.outqueue.append("//trade  //sessionid="+bodiconnection.sessionid+" //result"+bodiconnection.result);
                        
                        socketconnection.haswriteready = true;
                        
                        socketconnection.thread.outputlistenerthread.haswriteready = true;
                        
                        socketconnection.inqueue.delete(0, input.length());                   
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
        }
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
       String[] tokens = buffer.toString().split(" ");
        
        for(String token : tokens)
        {          
            if(token.trim().startsWith("//sessionid"))
            {
                for(Bodiconnection existingconnection : this.bodiconnections)
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
        
        throw new Exception("No session with sessionid found.");
    }
    
    public Boolean isvalidsessionid(Bodiconnection connection)
    {
        if(connection==null) return false;
        
        if(connection.sessionid==null) return false;
        
        for(Bodiconnection existingconnection : this.bodiconnections)
        {
            if(existingconnection.sessionid == connection.sessionid)
            {
                return true;
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