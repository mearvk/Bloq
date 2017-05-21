package apml.system.bodi.remote;

/**
 *
 * @author Max Rupplin
 */
class Listenerthread extends Thread
{
    
    public Connection connection;
    
    public Boolean running = true;
    
    public Inputlistenerthread inputlistenerthrread;
    
    public Outputlistenerthread outputlistenerthread;
    
    
    public Listenerthread(Connection connection)
    {
        if(connection==null) throw new SecurityException("//bodi/connect");
        
        this.connection = connection;
                
        //
        
        this.setName("Listenerthread");
        
        //
        
        this.inputlistenerthrread = new Inputlistenerthread(this);
        
        this.inputlistenerthrread.start();        
                
        //
        
        this.outputlistenerthread = new Outputlistenerthread(this);                
        
        this.outputlistenerthread.start();
    }
    
    @Override
    public void run()
    {
        //System.out.println(">   Server main thread started...");
        
        try
        {
            while(running)            
            {
                //System.err.println("Listener thread now looping...");

                //check for socket disconnect here please
                
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
            e.printStackTrace();
        }
        
        //System.out.println("Basicserver listener thread exiting...");
    } 
    
    protected void sleepmillis(Long millis) throws Exception
    {        
        Thread.currentThread().sleep(millis);     
    }        
}
