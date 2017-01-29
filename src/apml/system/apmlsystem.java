/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apml.system;

import apml.subscribers.stdapmlsubscriber;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author oem
 */
public class apmlsystem 
{
    public static Map map = null;
    
    public static Object notify(stdapmlsubscriber subscriber, ActionEvent ae)
    {
        return null;
    }
    
    public static Object notifyAll(ArrayList<stdapmlsubscriber> subscriber, ActionEvent ae)
    {
        return null;
    }     
    
    public static Object notify(stdapmlsubscriber subscriber, String string)
    {
        return null;
    }
    
    public static Object notifyAll(ArrayList<stdapmlsubscriber> subscriber, String string)
    {
        return null;
    }    
    
    public static Object mountListener()
    {
        return null;
    }
    
    public static Object mountListeners()
    {
        return null;
    }    
    
    public static Object unmountListener()
    {
        return null;
    }    
   
    public static Object unmountListeners()
    {
        return null;
    }  
    
    public static Object mountSubscriber()
    {
        return null;
    }
    
    public static Object mountSubscribers()
    {
        return null;
    }    
    
    public static Object unmountSubscriber()
    {
        return null;
    }    
   
    public static Object unmountSubscribers()
    {
        return null;
    }    
    
    public static Object getListener()
    {
        return null;
    }
    
    public static Object getListeners()
    {
        return null;
    }    
    
    public static Object getSubscriber()
    {
        return null;
    }    
   
    public static ArrayList<stdapmlsubscriber> getSubscribers(String unique)
    {
        return null;
    }    
    
    public static Object doInstantiation(Class c)
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
    
    public static void doPutElementsOnStartup(Map map)
    {
        apmlsystem.map.putAll(map);        
    }
    
    public static void doPutElementsOnStartup(String[] names, Object[] objects)
    {
        for(int i=0;i<names.length;i++)
        {
            map.put(names[i],objects[i]);
        }
    }    
    
    public static Map doGetElements()
    {
        return apmlsystem.map;
    }    
    
    public static Object doGetElement(Object o)
    {
        return apmlsystem.map.get(o);
    }
}
