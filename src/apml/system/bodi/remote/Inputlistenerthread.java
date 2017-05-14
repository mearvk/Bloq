package apml.system.bodi.remote;

import java.util.ArrayList;
import java.util.stream.Stream;
import javax.swing.SwingUtilities;

/**
 *
 * @author Max Rupplin
 */
class Inputlistenerthread extends Thread
{
    public Boolean hasreadready = false;
    
    public Boolean isnotchecking = true;
    
    public Boolean running = true;
    
    public Listenerthread parent;
    
    public String line = null;
    
    public Object readreadylock = new Object();
    
    public Inputlistenerthread(Listenerthread parent)
    {
        this.parent = parent;
        
        this.setName("Inputlistenerthread");                
    }
    
    public Boolean checkinputqueue()
    {                
        synchronized(this.parent.inputlock)
        {                 
            try
            {                
                if(this.parent.connection.reader.ready())
                {                                           
                    //readLine does not find \r or \n while using Telnet (at this time) so only use the single read please                    
                    String line;
                    
                    line = this.parent.connection.reader.readLine();
                    
                    this.parent.connection.appendline(line);     
                    
                    this.parent.connection.server.inputqueue.add(this.parent.connection);
                    
                    this.parent.connection.hasreadready = true;
                    
                    this.parent.connection.server.doread = true;
                }             
            }
            catch(Error e)
            {
                e.printStackTrace();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                //
            }           
        
            System.out.println("Call to checkinputqueue exiting...");
            
            return true;            
        }
    }  
    
    @Override
    public void run()
    {
        System.out.println(">   Inputlistenerthread started...");
        
        try
        {
            while(running)
            {                
                try
                {
                    if(this.parent.connection.reader.ready())
                    {                        
                        this.hasreadready = true;
                    }
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
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}