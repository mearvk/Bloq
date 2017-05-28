package apml.system.bodi.remote;

import apml.system.bodi.Bodi;

import java.util.ArrayList;

import java.util.Random;

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
    
    public Bodilag lag = new Bodilag();
    
    public static Bodilag _lag = new Bodilag();
    
    public ArrayList<String> inputqueue = new ArrayList();
    
    public Integer sessionid;

    /**
     * 
     * @param context
     * @param key 
     */
    public Bodilocalserver(String context, String key)
    {
        Bodi.setcontext(context);
        
        try
        {
            Bodi.context(context).put(key, this);
        }
        catch(Exception e)
        {
            e.printStackTrace(System.err);
        }
    }
    
    /**
     * 
     */
    public Bodilocalserver()
    {
        Bodi.setcontext("//system/bodi");
        
        try
        {
            Bodi.context("//system/bodi").put("localserver", this);
        }
        catch(Exception e)
        {
            e.printStackTrace(System.err);
        }
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
    public static Bodiresponse _addrequest(String input)
    {
        if(input==null) throw new SecurityException("//bodi/connect");
        
        Bodilocalserver._sleepmillis(Bodilocalserver._tryformdiscretionarylag());
        
        Bodiresponse response = new Bodiresponse();
        
        //process protocol
        response = Bodilocalserver._processprotocol(input, response);
        
        //process request
        response = Bodilocalserver._processrequest(input, response);
        
        //process response
        response = Bodilocalserver._processresponse(input, response);
        
        return response;
    }
    
    /**
     * 
     * @param input
     * @return 
     */
    public Bodiresponse addrequest(String input)
    {
        if(input==null) throw new SecurityException("//bodi/connect");
        
        this.sleepmillis(this.lag.lagmillis);
        
        Bodiresponse response = new Bodiresponse();
        
        //process protocol
        response = this.processprotocol(input, response);
        
        //process request
        response = this.processrequest(input, response);
        
        //process response
        response = this.processresponse(input, response);
        
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
        
        String protocoltoken = Protocolstripper.stripforprotocoltoken(input).trim().toLowerCase();
        
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
        if( input==null ) throw new SecurityException("//bodi/connect");        
        
        if( response.result.equalsIgnoreCase("failure") ) throw new SecurityException("//bodi/connect");
        
        String protocoltoken = Protocolstripper.stripforprotocoltoken(input).trim().toLowerCase();
        
        switch(protocoltoken)
        {
            case "//close": return this.close(input, response);
            
            case "//handshake": return this.handshake(input, response);
            
            case "//list": return this.list(input, response);
            
            case "//open": return this.open(input, response);
            
            case "//pull": return this.pull(input, response);
            
            case "//put": return this.put(input, response);
            
            case "//trade": return this.trade(input, response);
            
            default: break;            
        }
        
        response.result = "failure";
        
        response.cause = "no matching protocol token";
        
        return response;
    }
    
    private Bodiresponse processresponse(String input, Bodiresponse response)
    {      
        //check for too many connections etc from here 
        
        return response;
    }
    
/**
     * 
     * @param input
     * @param response
     * @return 
     */
    private static Bodiresponse _processprotocol(String input, Bodiresponse response)
    {
        if(input==null) throw new SecurityException("//bodi/connect");        
        
        String protocoltoken = Protocolstripper.stripforprotocoltoken(input).trim().toLowerCase();
        
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
    private static Bodiresponse _processrequest(String input, Bodiresponse response)
    {
        if( input==null ) throw new SecurityException("//bodi/connect");        
        
        if( response.result.equalsIgnoreCase("failure") ) throw new SecurityException("//bodi/connect");
        
        String protocoltoken = Protocolstripper.stripforprotocoltoken(input).trim().toLowerCase();
        
        switch(protocoltoken)
        {
            case "//close": return Bodilocalserver._close(input, response);
            
            case "//handshake": return Bodilocalserver._handshake(input, response);
            
            case "//list": return Bodilocalserver._list(input, response);
            
            case "//open": return Bodilocalserver._open(input, response);
            
            case "//pull": return Bodilocalserver._pull(input, response);
            
            case "//put": return Bodilocalserver._put(input, response);
            
            case "//trade": return Bodilocalserver._trade(input, response);
            
            default: break;            
        }
        
        response.result = "failure";
        
        response.cause = "no matching protocol token";
        
        return response;
    }
    
    private static Bodiresponse _processresponse(String input, Bodiresponse response)
    {      
        //check for too many connections etc from here 
        
        return response;
    }    
    
    /**
     * 
     * @param input 
     * @param response
     * @return 
     */
    protected synchronized Bodiresponse close(String input, Bodiresponse response)
    {
        if(input==null || response==null) throw new SecurityException("//bodi/connect");
        
        String context = Protocolstripper.stripforcontext(input);                     
        
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
     * @param response
     * @return 
     */    
    protected synchronized Bodiresponse handshake(String input, Bodiresponse response)
    {
        if(input==null || response==null) throw new SecurityException("//bodi/connect");
        
        String protocol = Protocolstripper.stripforprotocoltoken(input);             
        
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
     * @param response
     * @return 
     */    
    protected synchronized Bodiresponse list(String input, Bodiresponse response)
    {
        if(input==null || response==null) throw new SecurityException("//bodi/connect");        
        
        String context = Protocolstripper.stripforcontext(input);             
        
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
     * @param response
     * @return  
     */    
    public synchronized Bodiresponse open(String input, Bodiresponse response)
    {
        if(input==null || response==null) throw new SecurityException("//bodi/connect");
        
        String context = Protocolstripper.stripforcontext(input);             
        
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
     * @param response
     * @return 
     */    
    public synchronized Bodiresponse pull(String input, Bodiresponse response)
    {
        if(input==null || response==null) throw new SecurityException("//bodi/connect");

        String context = Protocolstripper.stripforcontext(input);
        
        String key = Protocolstripper.stripforkey(input);        
        
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
     * @param response
     * @return 
     */    
    public synchronized Bodiresponse put(String input, Bodiresponse response)
    {
        if(input==null || response==null) throw new SecurityException("//bodi/connect");

        String context = Protocolstripper.stripforcontext(input); 
        
        String key = Protocolstripper.stripforkey(input);
        
        String value = Protocolstripper.stripforvalue(input);        
        
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
     * @param response
     * @return 
     */
    public synchronized Bodiresponse trade(String input, Bodiresponse response)
    {
        return new Bodiresponse();
    }
    
/**
     * 
     * @param input 
     * @param response
     * @return 
     */
    protected static synchronized Bodiresponse _close(String input, Bodiresponse response)
    {
        if(input==null || response==null) throw new SecurityException("//bodi/connect");

        String context = Protocolstripper.stripforcontext(input);             
        
        
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
     * @param response
     * @return 
     */    
    protected static synchronized Bodiresponse _handshake(String input, Bodiresponse response)
    {
        if(input==null || response==null) throw new SecurityException("//bodi/connect");

        String protocol = Protocolstripper.stripforprotocoltoken(input);             
        
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
     * @param response
     * @return 
     */    
    protected static synchronized Bodiresponse _list(String input, Bodiresponse response)
    {
        if(input==null || response==null) throw new SecurityException("//bodi/connect");

        String context = Protocolstripper.stripforcontext(input);             
        
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
     * @param response
     * @return  
     */    
    public static synchronized Bodiresponse _open(String input, Bodiresponse response)
    {
        if(input==null || response==null) throw new SecurityException("//bodi/connect");

        String context = Protocolstripper.stripforcontext(input);             
        
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
     * @param response
     * @return 
     */    
    public static synchronized Bodiresponse _pull(String input, Bodiresponse response)
    {
        if(input==null || response==null) throw new SecurityException("//bodi/connect");

        String context = Protocolstripper.stripforcontext(input);
        
        String key = Protocolstripper.stripforkey(input);        
        
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
     * @param response
     * @return 
     */    
    public static synchronized Bodiresponse _put(String input, Bodiresponse response)
    {
        if(input==null || response==null) throw new SecurityException("//bodi/connect");

        String context = Protocolstripper.stripforcontext(input); 
        
        String key = Protocolstripper.stripforkey(input);
        
        String value = Protocolstripper.stripforvalue(input);        
        
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
     * @return 
     */
    private static Long _tryformdiscretionarylag()
    {
        Random random;
        
        random = new Random();        
        
        Bodilocalserver._lag.lagmillis = (random.nextLong()*random.nextLong()) % 450L;
        
        return Bodilocalserver._lag.lagmillis;
    }
    
    /**
     * 
     * @return 
     */
    private Long tryformdiscretionarylag()
    {
        Random random;
        
        random = new Random();        
        
        this.lag.lagmillis = (random.nextLong()*random.nextLong()) % 450L;
        
        return this.lag.lagmillis;
    }
    
    /**
     * 
     * @param input
     * @return 
     */
    public static synchronized Bodiresponse _trade(String input, Bodiresponse response)
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
                this.tryformdiscretionarylag();
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

    public static void _sleepmillis(Long millis)
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
