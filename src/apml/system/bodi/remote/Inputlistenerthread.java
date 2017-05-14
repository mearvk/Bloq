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
                if(this.parent.connection.reader.ready() && this.parent.connection.server.doread)
                {                                           
                    //please use single line reads only for now
                    String line = this.parent.connection.reader.readLine();
                    
                    Connection connection = this.parent.connection;                    
                    
                    connection.appendline(line);
                    
                    connection.server.inputqueue.add(connection);
                    
                    connection.hasreadready = true;
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
                        
                        this.parent.connection.server.doread = true;
                    }
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    //sleep 400ms
                    this.sleep(400l);
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
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
}