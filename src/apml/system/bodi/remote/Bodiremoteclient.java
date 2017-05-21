package apml.system.bodi.remote;

import apml.system.bodi.Bodi;

import java.util.ArrayList;

/**
 * Client class to send Bodi requests (put, pull, trade, open, close, handshake) to retrieve Bodi objects from remote server(s)
 * 
 * @author Max Rupplin
 */
public class Bodiremoteclient extends Bodibaseclient implements Runnable
{
    public StringBuffer inputbuffer; //presume this is keyboard input for now
        
    public StringBuffer outputbuffer; //presume this request to bodi server        
    
    public ArrayList<Object> objectbuffer = new ArrayList(); //returned serialized Bodi objects [possibly json impl required]
    
    public Bodi bodi;
    
    //public Bodiconnection connection = new Bodiconnection(bodi);
    
    
    public Bodiremoteclient(String host, Integer port)
    {        
        super(host, port);                
        
        if(host==null || port==null) throw new SecurityException("//bodi/connect");
    }
    
    @Override
    public void run()
    {
        while(true)
        {
            try
            {
                this.read(inputbuffer); 
                
                this.write(outputbuffer);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
        
    public Object read(StringBuffer buffer) //should return an object via serialization process
    {
        synchronized(this.writelock)
        {
            try
            {
                this.writelock.wait(); //wait on baseclient to finish any writing into inputbuffer

                if(this.inputbuffer.length()>0)
                {
                    return this.inputbuffer;
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        
        this.inputbuffer.delete(0, this.inputbuffer.length());       
        
        return null;
    }
    
    //handshake returns 0x012312415156 
    public void write(StringBuffer buffer) //should write out a request for Bodi object
    {
        synchronized(this.readlock)
        {
            try
            {
                this.readlock.wait(); //wait on baseclient to finish any reading into outputbuffer

                if(this.outputbuffer.length()>0)
                {
                    this.writer.write(buffer.toString(), 0, buffer.toString().length());
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }        
    }
}
