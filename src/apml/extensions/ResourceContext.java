package apml.extensions;

import apml.system.bodi.Bodi;

import java.io.Serializable;

import java.util.ArrayList;

/**
 *
 * @author Max Rupplin
 */
public class ResourceContext
{
    public Integer hash = 0x008808EF;
    
    /*-------------------------------------------------------------------------*/
    
    public ArrayList<String> lines = new ArrayList();
    
    public Bodi bodi;
    
    public AbstractResourceServer server;
    
    public Boolean verified = true;       
    
    public Boolean islive = true;
            
    public NetworkContext connection;            
    
    public Integer sessionid;
    
    public Integer userid;
    
    public Long ttl = 60*1000*100L;
    
    public Long day;       
    
    public Serializable object;
    
    public String host;                
    
    public String result;
    
    public String operation = ""; 
    
    public String cause;
    
    public String error = "";        
    
    public String context;
    
    public String tradecontext;
    
    public String key;    
    
    public String message;
    
    public String value;   
    
    public ArrayList<String> values;
    
   
    /**
     * 
     */
    public ResourceContext()
    {
        this.sessionid = this.hashCode();
        
        this.day = System.currentTimeMillis();
    }
    
    /**
     * 
     * @param server
     * @param connection 
     */
    public ResourceContext(AbstractResourceServer server, NetworkContext connection)
    {                
        if(server==null || connection==null) throw new SecurityException("//bodi/connect/exceptions");
        
        this.connection = connection;
        
        this.day = System.currentTimeMillis();
    }
         
    /**
     * 
     * @param connectioncontext
     * @return 
     */
    public Boolean processrequest(ServerContext connectioncontext)
    {                
        return false;
    }
    
    /**
     * 
     * @param connectioncontext
     * @return 
     */
    private Boolean checkconnection(ServerContext connectioncontext)            
    {
        if(connectioncontext.resourcecontext==null) return false;
        
        if(connectioncontext.resourcecontext.ttl<=0) return false;
        
        if(this.server.isvalidsession(connectioncontext.resourcecontext)==null) return false;            
        
        return true;
    }    
    
    /**
     * 
     * @return 
     */
    protected String getresult()
    {
        return this.result = "";
    }    
       
    /**
     * 
     * @return 
     */
    protected Integer getsessionid() 
    {
        if(this.sessionid==null || this.sessionid==0)
        {
            return this.hashCode();
        }
        
        return this.sessionid;
    }
    
    /**
     * 
     * @return 
     */
    protected Long gettimetolive()
    {
        Long now = System.currentTimeMillis();        
        
        Long day = this.day;
        
        Long ttl = this.ttl;
                
        //
        
        if( (this.ttl = ttl-(now-day))<0 )
        {
            return -1L;
        }      
        
        return this.ttl;
    }        
    
    /**
     * 
     * @return 
     */
    @Override
    public String toString()
    {       
        return this.value;
    }        
}
