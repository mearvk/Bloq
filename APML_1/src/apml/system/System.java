/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apml.system;

import java.util.Map;

/**
 *
 * @author oem
 */
public class System 
{
    public static Map map = null;
    
    public Object mountListener()
    {
        return null;
    }
    
    public Object mountListeners()
    {
        return null;
    }    
    
    public Object unmountListener()
    {
        return null;
    }    
   
    public Object unmountListeners()
    {
        return null;
    }   
    
    public Object doInstantiation(Class c)
    {
        try
        {
            return c.newInstance();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            return null;
        }
    }
    
    public void doChainElements(Map map)
    {
        System.map.putAll(map);        
    }
    
    public void doChainElements(String[] names, Object[] objects)
    {
        for(int i=0;i<names.length;i++)
        {
            map.put(names[i],objects[i]);
        }
    }    
    
    public Map doGetElements()
    {
        return System.map;
    }    
    
    public Object doGetElement(Object o)
    {
        return System.map.get(o);
    }
}
