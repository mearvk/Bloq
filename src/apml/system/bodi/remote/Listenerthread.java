/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apml.system.bodi.remote;

/**
 *
 * @author Max Rupplin
 */
class Listenerthread extends Thread
{
    public Connection connection;
    
    public Boolean running = true;
    
    public Object inputlock = new Object();
    
    public Object outputlock = new Object();
    
    
    //public Basicserver server;
    
    public Inputlistenerthread inputlistenerthrread;
    
    public Outputlistenerthread outputlistenerthread;
    
    public Listenerthread(Connection connection)
    {
        this.connection = connection;
                
        /*---------------------------------------------------------------------*/
        
        this.setName("Listenerthread");
        
        /*---------------------------------------------------------------------*/
        
        this.inputlistenerthrread = new Inputlistenerthread(this);
        
        this.inputlistenerthrread.start();        
                
        /*---------------------------------------------------------------------*/
        
        this.outputlistenerthread = new Outputlistenerthread(this);                
        
        this.outputlistenerthread.start();
    }
    
    @Override
    public void run()
    {
        System.out.println(">   Server main thread started...");
        
        while(running)            
        {
            System.err.println("Listener thread now looping...");
            
            try
            {               
                this.inputlistenerthrread.checkinputqueue();                                   
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                //sleep 400 ms
            }
            
            try
            {
                this.outputlistenerthread.checkoutputqueue();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                //sleep 400ms
            }
        }
        
        System.out.println("Basicserver listener thread exiting...");
    }       
}
