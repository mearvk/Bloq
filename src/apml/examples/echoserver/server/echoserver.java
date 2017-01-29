package apml.examples.echoserver.server;

import apml.annotations.ApmlListener;
import apml.objects.networking.ServerSocketImpl;
import apml.subscribers.stdapmlsubscriber;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import apml.interfaces.startable;
import apml.interfaces.actionlistener;
import apml.interfaces.runnable;

/**
 *
 * @author Max Rupplin
 */
public class echoserver extends ServerSocketImpl implements runnable, startable
{   
    public ServerSocket serversocket;
    public Boolean running = true;
    public Integer port = 80;
    public Socket socket;        
    public String line;
    
    public echoserver()
    {
        
    }
    
    @Override
    public void start()
    {
        this.run();
    }   
    
    @Override
    public void stop()
    {
        this.running = false;
        
        try
        {
            this.serversocket.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    @Override
    @ApmlListener(listener="EchoServerOnReadListener") //now care to inform all subscribers
    public void read()
    {        
        try
        {
            BufferedReader reader = null;

            reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));                            

            for(line="";line!=null;line+=reader.readLine()+"\n"){}

            reader.close();            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    @Override
    @ApmlListener(listener="EchoServerOnDispatchListener") //now care to inform all subscribers
    public void write()
    {
        try
        {
            BufferedWriter writer;

            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            writer.write(line);

            writer.flush();

            writer.close(); 
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    @ApmlListener(listener="EchoServerOnConnectListener") //now care to inform all subscribers
    public Socket connect(ServerSocket serversocket) throws Exception
    {
        return serversocket.accept();
    }
    
    public void run()
    {
        while(running) 
        {
            try
            {                          
                this.serversocket = new ServerSocket(80);
                this.socket = connect(this.serversocket);
                
                new Thread()                                                    
                {                
                    @Override
                    public void run()
                    {                        
                        String line="";  
                            
                        try
                        {                
                            read();
                            
                            write();
                        }
                        catch(Exception ioe)
                        {
                            ;
                        }
                    }
                }.start();
            }
            catch(IOException ioe)
            {
                ;
            }
            catch(Exception e)
            {
                ;
            }
            finally
            {
                System.out.println("Ok.");
            }
        }
    }
}

class EchoServerOnConnectListener implements ActionListener
{
   @Override
    public void actionPerformed(ActionEvent ae) 
    {
        ArrayList<stdapmlsubscriber> subscribers = apml.system.apmlsystem.getSubscribers("echoserver/onconnect");
        
        for(stdapmlsubscriber s : subscribers)
        {
            apml.system.apmlsystem.notify(s,new ActionEvent(this, 0, "echoserver/commands/onconnect/notify"));
        }
    }   
}

class EchoServerOnExitListener implements actionlistener, ActionListener
{
    @Override
    public void actionEvent()
    {
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        ArrayList<stdapmlsubscriber> subscribers = apml.system.apmlsystem.getSubscribers("echoserver/onexit");
        
        for(stdapmlsubscriber s : subscribers)
        {
            apml.system.apmlsystem.notify(s,new ActionEvent(this, 0, "echoserver/commands/onconnect/notify"));
        }        
        
    }      
}

class EchoServerOnReadListener implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        ArrayList<stdapmlsubscriber> subscribers = apml.system.apmlsystem.getSubscribers("echoserver/onread");
        
        for(stdapmlsubscriber s : subscribers)
        {
            apml.system.apmlsystem.notify(s,new ActionEvent(this, 0, "echoserver/commands/onread/notify"));
        }        
    }      
}

class EchoServerOnReceiptListener implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        ArrayList<stdapmlsubscriber> subscribers = apml.system.apmlsystem.getSubscribers("echoserver/onreceipt");
        
        for(stdapmlsubscriber s : subscribers)
        {
            apml.system.apmlsystem.notify(s,new ActionEvent(this, 0, "echoserver/commands/onreceipt/notify"));
        }        
    }      
}

class EchoServerOnDispatchListener implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        ArrayList<stdapmlsubscriber> subscribers = apml.system.apmlsystem.getSubscribers("echoserver/ondispatch");
        
        for(stdapmlsubscriber s : subscribers)
        {
            apml.system.apmlsystem.notify(s,new ActionEvent(this, 0, "echoserver/commands/ondispatch/notify"));
        }        
    }      
}     
        