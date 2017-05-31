package apml.system.bodi.remote;

import apml.system.bodi.Bodi;

import java.util.ArrayList;

import java.util.Random;

/**
 * Performs Bodi function(s) locally on same JVM as other process(es) /mr /ok /ss
 * 
 * @author Max Rupplin
 */
public class Bodilocalserver extends Thread
{
    public Integer hash = 0x008808EF;
    
    public Bodiprotocolhandler protocolhandler = new Bodiprotocolhandler();
    
    public Boolean running = true; 
    
    public Bodi bodi;      
    
    public Bodilag lag = new Bodilag();
    
    public static Bodilag _lag = new Bodilag();
    
    public ArrayList<String> inputqueue = new ArrayList();
    
    public Integer sessionid;

    //
    
    public static void main(String...args)
    {
        try
        {
            String value = "";
            
            Bodilocalserver localserver = new Bodilocalserver();

            localserver.go();                        
            
            //
            
            localserver.addrequest("//put //context=//test //key=key //value=valuexxx");
            
            value = (String)localserver.addrequest("//pull //context=//test //key=key").value;      
            
            System.out.println(value);            
            
            //
            
            localserver.addrequest("//put //context=//test //key=key //value=valueyyy");
            
            value = (String)localserver.addrequest("//pull //context=//test //key=key").value;             
            
            System.out.println(value);        
            
            //
            
            localserver.halt();
        }
        catch(Exception e)
        {
            e.printStackTrace(System.err);
        }
    }
    
    /**
     * Constructor: fill values passed as parameters.
     * 
     * @param context Context to set server against [e.g. //bodi/systems]
     * @param key Key to store the server in the Context context [e,g, localserver]
     * 
     * @throws Exception SecurityException against unaccepted parameters
     */
    public Bodilocalserver(String context, String key) throws Exception
    {
        if(context==null) throw new SecurityException("//bodi/connect");
        
        if(key==null) throw new SecurityException("//bodi/connect");
        
        if(Bodi.hascontextat(context)) throw new SecurityException("//bodi/connect");
        
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
     * Constructor; fill values from Bodiset.xml.
     * 
     * @throws Exception 
     */
    public Bodilocalserver() throws Exception
    {
        if(Bodi.hascontextat("//system/bodi")) throw new SecurityException("//bodi/connect");
        
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
     * Simply procreates a time lag for processing requests; ok /mr /ok /ss
     * 
     */
    @Override
    public void run()
    {
        while(running)
        {       
            this.tryformdiscretionarylag();            
            
            try
            {
                this.sleepmillis(500L);                     
            }
            catch(InterruptedException ie)
            {
                this.running = false;
            }
            catch(Exception e)
            {
                e.printStackTrace(System.err);
            }
        }
    }

    /**
     * 
     * @param input
     * @return 
     */
    public static Bodiresponse _addrequest(String input)
    {
        if(input==null) throw new SecurityException("//bodi/connect");
        
        try
        {
            Bodilocalserver._sleepmillis(Bodilocalserver._lag.lagmillis); //
        }
        catch(Exception e)
        {
            return null;
        }
        
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
        
        try
        {
            this.sleepmillis(this.lag.lagmillis); //
        }
        catch(Exception e)
        {
            return null;
        }
        
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
        
        if(response==null) throw new SecurityException("//bodi/connect");
        
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
        
        if( response==null ) throw new SecurityException("//bodi/connect");
        
        if( response.result!=null && response.result.equalsIgnoreCase("failure") ) throw new SecurityException("//bodi/connect");
        
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
    
    /**
     * 
     * @param input
     * @param response
     * @return 
     */
    private Bodiresponse processresponse(String input, Bodiresponse response)
    {      
        //check for too many connections etc from here 
        
        if(input==null) throw new SecurityException("//bodi/connect");
        
        if(response==null) throw new SecurityException("//bodi/connect");
        
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
        
        if(response==null) throw new SecurityException("//bodi/connect");
        
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
        
        if( response.result!=null && response.result.equalsIgnoreCase("failure") ) throw new SecurityException("//bodi/connect");
        
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
    
    /**
     * 
     * @param input
     * @param response
     * @return 
     */
    private static Bodiresponse _processresponse(String input, Bodiresponse response)
    {      
        //check for too many connections etc from here 
        
        if(input==null) throw new SecurityException("//bodi/connect");     
        
        if(response==null) throw new SecurityException("//bodi/connect");        
        
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
        
        if(array==null || array.isEmpty())
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
        
        Bodilocalserver._lag.lagmillis = Math.abs(random.nextLong()*random.nextLong()) % 450L;
        
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
        
        this.lag.lagmillis = Math.abs(random.nextLong()*random.nextLong()) % 450L;
        
        return this.lag.lagmillis;
    }
    
    /**
     * 
     * @param input
     * @param response
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
        this.start();        
    }

    /**
     * 
     */
    public void halt()
    {
       this.interrupt();
    }
    
    /**
     * 
     * @param millis
     * @throws Exception 
     */
    public static void _sleepmillis(Long millis) throws Exception
    {
        Thread.sleep(millis);
    }
    
    /**
     * 
     * @param millis
     * @throws Exception 
     */
    public void sleepmillis(long millis) throws Exception
    {
        Thread.sleep(millis);
    }
}
