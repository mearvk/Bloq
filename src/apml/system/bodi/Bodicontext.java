
package apml.system.bodi;

import apml.system.bodi.remote.BodiError;

import java.io.File;

import java.util.Collection;

import java.util.HashMap;

import java.util.Map;

import java.util.Map.Entry;

import java.util.Set;

import org.w3c.dom.Element;

import org.w3c.dom.Node;

/**
 *
 * @author Max Rupplin
 */
public class Bodicontext 
{
    protected final Integer hash = 0x00888fe8;
    
    private final Map<String, Object> namemap = new HashMap();
    
    private final Map<Integer, Object> hashmap = new HashMap();
    
    private final Map<Object, Object> objectmap = new HashMap();    
    
    public String contextname;
    
    public File file;
    
    public Bodicontext(String contextname)
    {       
        this.contextname = contextname;
    }

    public Bodicontext(File file)
    {
        this.file = file;
    }
    
    public Class getclass(String name)
    {
        return this.pull(name).getClass();
    }
    
    public void put(Integer key, Object value) throws Exception
    {       
        if(key==null) throw new BodiError("Bodicontext.put :: Key value was null.");
        
        if(value==null) throw new BodiError("Bodicontext.put :: Value value was null.");
        
        this.hashmap.put(key, value);
    }

    public void put(Object key, Object value) throws Exception
    {           
        if(key==null) throw new BodiError("Bodicontext.put :: Key value was null.");
        
        if(value==null) throw new BodiError("Bodicontext.put :: Value value was null.");
        
        this.objectmap.put(key, value);                     
    }
    
    public void put(String key, Object value) throws Exception
    {        
        if(key==null) throw new BodiError("Bodicontext.put :: Key value was null.");
        
        if(value==null) throw new BodiError("Bodicontext.put :: Value value was null.");
        
        this.namemap.put(key, value);            
    }                   
    
    /**
     * Can equality produce errant result early such that we care? /ok /mr /ss
     * 
     * @param object
     * @return 
     */
    public Object softpull(Object object)
    {
        Map<Object, Object> map = this.objectmap;
        
        for(Entry entry : map.entrySet())
        {                     
            Object key = entry.getKey();
            
            Object value = entry.getValue();
            
            if(object instanceof Node && key instanceof Node)
            {
                Element n1 = (Element)object;
                
                Element n2 = (Element)key;                
                
                if(n1.isEqualNode(n2)) 
                {
                    return value;
                }                
            }
        }
        
        return null;
    }
    
    public Object pull(Integer hashcode)
    {
        try
        {
            return hashmap.get(hashcode);            
        }
        catch(Exception e)
        {
            System.err.println(e);
        }
        
        return null;
    }    

    public Object pull(Object object)
    {
        try
        {            
            return objectmap.get(object);
        }
        catch(Exception e)
        {
            System.err.println(e);
        }
        
        return null;
    }    
    
    public Object pull(String name)
    {
        try
        {
            return namemap.get(name);            
        }
        catch(Exception e)
        {
            System.err.println(e);
        }
        
        return null;
    }       
    
    public Map<Object,Object> pullall()
    {
        HashMap<Object, Object> allvals = new HashMap<Object, Object>();
    
        allvals.putAll(namemap);
        
        allvals.putAll(objectmap);
        
        allvals.putAll(hashmap);
        
        return allvals;
    }
    
    public Set<Object> keys()
    {
        Map<Object, Object> allvals = this.pullall();
        
        return allvals.keySet();
    }
    
    public Collection<Object> values()
    {
        Map<Object, Object> allvals = this.pullall();
        
        return allvals.values();        
    }
    
    public Collection<Object> lists(String basecontext, Integer depth)
    {
        return null; 
    }    
    
    public Collection<Object> pullallstringkeyedvalues()
    {
        return namemap.values();
    }    
    
    public Set<String> pullallstringkeys()
    {
        return namemap.keySet();
    }
    
    
    public Collection<Object> pullallobjectkeyedvalues()
    {
        return objectmap.values();
    }    
    
    public Set<Object> pullallobjectkeys()
    {
        return objectmap.keySet();
    }

    
    public Collection<Object> pullallhashcodekeyedvalues()
    {
        return hashmap.values();
    }    
    
    public Set<Integer> pullAllKeys()
    {
        return hashmap.keySet();
    }    
}
