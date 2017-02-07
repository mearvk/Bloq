/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apml.system.bndi;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author oem
 */
public class Bndicontext 
{
    private final Map<String, Object> namemap = new HashMap();
    private final Map<Integer, Object> hashmap = new HashMap();
    private final Map<Object, Integer> objectmap = new HashMap();    
    
    public String contextname;
    public File file;
    
    public Bndicontext(String contextname)
    {
        this.contextname = contextname;
    }

    public Bndicontext(File file)
    {
        this.file = file;
    }
    
    public void put(String name, Object object)
    {
        try{this.hashmap.put(object.hashCode(), object);}           catch(Exception e){}
        try{this.namemap.put(name, object);}                        catch(Exception e){}            
        try{this.objectmap.put(object, object.hashCode());}         catch(Exception e){}
    }        
    
    public Object pull(Integer hashcode)
    {
        return hashmap.get(hashcode);
    }

    public Object pull(Object object)
    {
        return objectmap.get(object);
    }    
    
    public Object pull(String name)
    {
        return namemap.get(name);
    }            
}
