package apml.extensions.servers.threading;

import apml.extensions.NetworkContext;


/**
 *
 * @author Max Rupplin
 */
public class Listenerthread extends Thread
{
    public Integer hash = 0x008808EF;
    
    public NetworkContext networkcontext;
    
    public Boolean running = true;
    
    public Inputlistenerthread inputlistenerthrread;
    
    public Outputlistenerthread outputlistenerthread;
        
    /**
     * 
     * @param connection 
     */
    public Listenerthread(NetworkContext connection)
    {
        if(connection==null) throw new SecurityException("//bodi/connect");
        
        this.networkcontext = connection;
                
        //
        
        this.setName("Listenerthread");
        
        //
        
        this.inputlistenerthrread = new Inputlistenerthread(this);
        
        this.inputlistenerthrread.start();        
                
        //
        
        this.outputlistenerthread = new Outputlistenerthread(this);                
        
        this.outputlistenerthread.start();
    }
    
    /**
     * 
     */
    @Override
    public void run()
    {        
        try
        {
            while(running)            
            {              
                try
                {               
                    this.inputlistenerthrread.checkinputqueue();                                   
                }
                catch(Exception e)
                {
                    e.printStackTrace(System.err);
                }

                try
                {
                    this.outputlistenerthread.checkoutputqueue();
                }
                catch(Exception e)
                {
                    e.printStackTrace(System.err);
                }     
            }            
            
            this.sleepmillis(500l);
        }
        catch(Exception e)
        {
            e.printStackTrace(System.err);
        }
    } 
    
    /**
     * 
     * @param millis
     * @throws Exception 
     */
    protected void sleepmillis(Long millis) throws Exception
    {        
        Thread.currentThread().sleep(millis);     
    }        
}
