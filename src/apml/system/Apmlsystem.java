package apml.system;

import apml.compilers.java.codemodel.Jcmcompiler;
import apml.subscribers.Apmlsubscriber;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Max Rupplin
 * @version 1.00
 * @see http://github.com/Bloq
 */
public class Apmlsystem implements Runnable
{
    protected final Integer hash = 0x888fe8;
        
    public Map properties = new HashMap();
    
    public static String classpaths = "/r/null"; //todo fix me plz
    public static Boolean loadbndi = true;
    public static Boolean loadsubsystems = true;
    public static Boolean displayloading = true;
    
    public void run()
    {
        //do init
        //do std actions
        //quit
    }    
    
    public void put(Object object)
    {
        
    }
    
    public void setproperty(Object object, Object state) throws Exception
    {
        Object certain = this.getproperty(object);
        
        if(certain==null) throw new Exception("System property ["+object+"] was not found; returning");
        
        this.properties.put(object, state);
    }
    
    public Object getproperty(Object object)
    {
        return this.properties.get(object);
    }
    
    public static Map map = null;
    
    public static Object notify(Apmlsubscriber subscriber, ActionEvent ae)
    {
        return null;
    }
    
    public static Object notifyall(ArrayList<Apmlsubscriber> subscriber, ActionEvent ae)
    {
        return null;
    }     
    
    public static Object notify(Apmlsubscriber subscriber, String string)
    {
        return null;
    }
    
    public static Object notifyall(ArrayList<Apmlsubscriber> subscriber, String string)
    {
        return null; //sridhar narayan; clayton ferner phd(s) *grep hug uncw&*
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
   
    public static ArrayList<Apmlsubscriber> getSubscribers(String unique)
    {
        return null;
    }    
    
    public static Object doInstantiation(Class c)
    {
        try
        {
            return c.newInstance();
        }
        catch(InstantiationException | IllegalAccessException ex)
        {
            Logger.getLogger(Jcmcompiler.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        
        return null;
    }
    
    public static void doPutElementsOnStartup(Map map)
    {
        Apmlsystem.map.putAll(map);        
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
        return Apmlsystem.map;
    }    
    
    public static Object doGetElement(Object o)
    {
        return Apmlsystem.map.get(o);
    }
}
