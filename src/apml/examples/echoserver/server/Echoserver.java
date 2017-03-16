package apml.examples.echoserver.server;

import apml.annotations.ApmlListener;

import apml.objects.networking.ServerSocketImpl;

import apml.subscribers.Apmlsubscriber;

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

import apml.interfaces.Startable;

import apml.interfaces.Actionlistener;

import apml.interfaces.Runnable;

/**
 *
 * @author Max Rupplin
 */
public class Echoserver extends ServerSocketImpl implements Runnable, Startable
{   
    protected final Integer hash = 0x888fe8;
    
    public ServerSocket serversocket;
    
    public Boolean running = true;
    
    public Integer port = 80;
    
    public Socket socket;        
    
    public String line;
    
    public Echoserver()
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
            e.printStackTrace(System.err);
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
            e.printStackTrace(System.err);
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
            e.printStackTrace(System.err);
        }
    }
    
    @ApmlListener(listener="EchoServerOnConnectListener") //now care to inform all subscribers
    public Socket connect(ServerSocket serversocket) throws Exception
    {
        return serversocket.accept();
    }
    
    @Override
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
        ArrayList<Apmlsubscriber> subscribers = apml.system.Apmlsystem.getsubscribers("echoserver/onconnect");
        
        for(Apmlsubscriber s : subscribers)
        {
            apml.system.Apmlsystem.notify(s,new ActionEvent(this, 0, "echoserver/commands/onconnect/notify"));
        }
    }   
}

class EchoServerOnExitListener implements Actionlistener, ActionListener
{
    @Override
    public void actionEvent(ActionEvent event)
    {
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        ArrayList<Apmlsubscriber> subscribers = apml.system.Apmlsystem.getsubscribers("echoserver/onexit");
        
        for(Apmlsubscriber s : subscribers)
        {
            apml.system.Apmlsystem.notify(s,new ActionEvent(this, 0, "echoserver/commands/onconnect/notify"));
        }        
        
    }      
}

class EchoServerOnReadListener implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        ArrayList<Apmlsubscriber> subscribers = apml.system.Apmlsystem.getsubscribers("echoserver/onread");
        
        for(Apmlsubscriber s : subscribers)
        {
            apml.system.Apmlsystem.notify(s,new ActionEvent(this, 0, "echoserver/commands/onread/notify"));
        }        
    }      
}

class EchoServerOnReceiptListener implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        ArrayList<Apmlsubscriber> subscribers = apml.system.Apmlsystem.getsubscribers("echoserver/onreceipt");
        
        for(Apmlsubscriber s : subscribers)
        {
            apml.system.Apmlsystem.notify(s,new ActionEvent(this, 0, "echoserver/commands/onreceipt/notify"));
        }        
    }      
}

class EchoServerOnDispatchListener implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        ArrayList<Apmlsubscriber> subscribers = apml.system.Apmlsystem.getsubscribers("echoserver/ondispatch");
        
        for(Apmlsubscriber s : subscribers)
        {
            apml.system.Apmlsystem.notify(s,new ActionEvent(this, 0, "echoserver/commands/ondispatch/notify"));
        }        
    }      
}     
        