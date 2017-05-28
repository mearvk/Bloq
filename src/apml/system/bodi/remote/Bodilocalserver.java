package apml.system.bodi.remote;

import apml.system.bodi.Bodi;

import java.util.ArrayList;

/**
 *
 * @author Max Rupplin
 */
public class Bodilocalserver extends Thread
{
    public Integer hash = 0x008808ef;
    
    public Bodiprotocolhandler protocolhandler = new Bodiprotocolhandler();
    
    public Boolean running = true; 
    
    public Bodi bodi;      
    
    public ArrayList<String> inputqueue = new ArrayList<String>();
    
    public Integer sessionid;
    
    /**
     * 
     */
    public Bodilocalserver()
    {
        
    }
    
    /**
     * 
     */
    @Override
    public void start()
    {
        this.go();
    }
    
    /**
     * 
     * @param input
     * @return 
     */
    public Bodiresponse addrequest(String input)
    {
        Bodiresponse response = new Bodiresponse();
        
        //process protocol
        response = this.processprotocol(input, response);
        
        //process request
        response = this.processrequest(input, response);
        
        //process response
        response = this.processreponse(input, response);
        
        return response;
    }
    
    /**
     * 
     * @param input
     * @param response
     * @return 
     */
    private Bodiresponse processprotocol(String input, Bodiresponse response)
    {
        if(input==null) throw new SecurityException("//bodi/connect");        
        
        String protocoltoken = Protocolstripper.stripforprotocoltoken(input);
        
        switch(protocoltoken)
        {
            case "//close": return response;
            
            case "//handshake": return response;
            
            case "//list": return response;
            
            case "//open": return response;
            
            case "//pull": return response;
            
            case "//put": return response;
            
            case "//trade": return response;
            
            default: break;            
        }
        
        response.result = "failure";
        
        response.cause = "no matching protocol token";
        
        return response;
    }
    
    /**
     * 
     * @param input
     * @param response
     * @return 
     */
    private Bodiresponse processrequest(String input, Bodiresponse response)
    {
        if(input==null) throw new SecurityException("//bodi/connect");        
        
        String protocoltoken = Protocolstripper.stripforprotocoltoken(input);
        
        switch(protocoltoken)
        {
            case "//close": return this.close(input);
            
            case "//handshake": return this.handshake(input);
            
            case "//list": return this.list(input);
            
            case "//open": return this.open(input);
            
            case "//pull": return this.pull(input);
            
            case "//put": return this.put(input);
            
            case "//trade": return this.trade(input);
            
            default: break;            
        }
        
        response.result = "failure";
        
        response.cause = "no matching protocol token";
        
        return response;
    }
    
    private Bodiresponse processreponse(String input, Bodiresponse response)
    {      
        return response;
    }
    
    /**
     * 
     * @param input 
     * @return 
     */
    protected synchronized Bodiresponse close(String input)
    {
        String context = Protocolstripper.stripforcontext(input);     
        
        Bodiresponse response = new Bodiresponse();
        
        if(Bodi.hascontextat(context))
        {
            try
            {
                Bodi.removecontext(context);
                
                response.result = "success";
          
                response.message = "context already set";
            }
            catch(Exception e)
            {
                e.printStackTrace(System.err);
            }            
        }                
        else 
        {
            Bodi.setcontext(input);
            
            response.result = "failure";
            
            response.cause = "no context set";
        }          
        
        return response;
    }

    /**
     * 
     * @param input 
     * @return 
     */    
    protected synchronized Bodiresponse handshake(String input)
    {
        String protocol = Protocolstripper.stripforprotocoltoken(input);     
        
        Bodiresponse response = new Bodiresponse();
        
        if(protocol.startsWith("//handshake"))
        {
            response.result = "success";
            
            response.message = "Bodi says Hello.";
        }                
        else 
        {            
            response.result = "failure";
            
            response.message = "unknown protocol token";
        }          
        
        return response;
    }
    
    /**
     * 
     * @param input 
     * @return 
     */    
    protected synchronized Bodiresponse list(String input)
    {
        String context = Protocolstripper.stripforcontext(input);     
        
        Bodiresponse response = new Bodiresponse();
        
        ArrayList<String> array = Bodi.listcontexts(context);
        
        if(array==null || array.size()==0)
        {                       
            response.result = "failure";
            
            response.cause = "no matching contexts";
        }                
        else 
        {            
            response.result = "success";
            
            response.message = "matching contexts included";
            
            response.values = array;
        }          
        
        return response;
    }
    
    /**
     * 
     * @param input 
     * @return  
     */    
    public synchronized Bodiresponse open(String input)
    {
        String context = Protocolstripper.stripforcontext(input);     
        
        Bodiresponse response = new Bodiresponse();
        
        if(Bodi.hascontextat(context))
        {
            response.result = "failure";
            
            response.cause = "context already set";
        }                
        else 
        {
            Bodi.setcontext(input);
            
            response.result = "success";
            
            response.message = "context set";
        }          
        
        return response;
    }
    
    /**
     * 
     * @param input 
     * @return 
     */    
    public synchronized Bodiresponse pull(String input)
    {
        String context = Protocolstripper.stripforcontext(input);
        
        String key = Protocolstripper.stripforkey(input);
        
        Bodiresponse response = new Bodiresponse();
        
        if(Bodi.hascontextat(context))
        {
            Object object = Bodi.context(context).pull(key);
            
            if(object==null) 
            {
                response.result = "failure";
            
                response.cause = "no object found";                
            }
            else
            {
                response.result = "success";
                
                response.message = "object included";
                
                response.value = object;
            }            
        }                
        else 
        {            
            response.result = "failure";
            
            response.message = "no existing context";
        }          
        
        return response;
    }
    
    /**
     * 
     * @param input 
     * @return 
     */    
    public synchronized Bodiresponse put(String input)
    {
        String context = Protocolstripper.stripforcontext(input); 
        
        String key = Protocolstripper.stripforkey(input);
        
        String value = Protocolstripper.stripforvalue(input);
        
        Bodiresponse response = new Bodiresponse();
        
        if( Bodi.hascontextat(context) )
        {
            try
            {
                Bodi.context(context).put(key, value);
                
                response.result = "success";
            
                response.message = "context and key/value pair set";  
                
                return response;
            }
            catch(Exception e)
            {
                e.printStackTrace(System.err);
            }            
        }                
        else 
        {          
            Bodi.setcontext(context);
            
            try
            {
                Bodi.context(context).put(key, value);
                
                response.result = "success";
            
                response.message = "context and key/value pair set";
                
                return response;
            }
            catch(Exception e)
            {
                e.printStackTrace(System.err);
            }            
        }          
        
        response.result = "failure";
        
        response.cause = "unable to set key/value pair";
        
        return response;
    }
    
    /**
     * 
     * @param input
     * @return 
     */
    public synchronized Bodiresponse trade(String input)
    {
        return new Bodiresponse();
    }
    
     /**
     * 
     */
    public void go() 
    {                                  
        while(running)
        {                
            try
            {
                
            }
            catch(Exception e)
            {
                
            }
            finally
            {
                this.sleepmillis(500L);
            }                        
        }
    }
    
    public void sleepmillis(Long millis)
    {
        try
        {
            Thread.sleep(millis);
        }
        catch(Exception e)
        {
            e.printStackTrace(System.err);
        }
    }
}
