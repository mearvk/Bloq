package apml.system;

import apml.compilers.java.codemodel.Codemodelcompiler;
import apml.drivers.Stddriver;
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
 * @see http://github.com/mearvk/Bloq
 */
public class Apmlsystem implements Runnable
{
    protected final Integer hash = 0x888fe8;
        
    public Map properties = new HashMap();
    
    public ArrayList<Stdsystem> systems;
    public Stddriver driver;    
    
    public static String[] classes = {"/r/null"}; //todo fix me plz
    
    public static Boolean loadbndi = true;
    
    public static Boolean loadsubsystems = true;
    
    public static Boolean displayloading = true;
    
    public static Map map = null;
    
    public void run()
    {
        for(Stdsystem system: this.systems)
        {
            system.run();
        }   
    }  
    
    public void start()
    {
        this.init();        
        
        for(Stdsystem system: this.systems)
        {
            system.init();
        }
                
        
        this.start();        
        
        for(Stdsystem system: this.systems)
        {
            system.start();
        }       
    }
    
    public void put(Object object)
    {
        
    }
    
    public void init()
    {
        
        //read properties file
        
        //read classes in from classpath
        
        //wire classes into system
        
        //wire classes into each other [where appropriate]
        
        //call driver.init
        
        //driver calls to init on subsystem(s)
        
        //driver calls to start on subsystems(s)
        
        //driver calls run on Apmlsystem
    }
    
    public void loadclasses(String classes)
    {
        
    }
    
    public void loadclasses()
    {       
        try
        {
            ClassLoader.getSystemClassLoader().loadClass("org.test.fixmeplz.Classname");
        }        
        catch(Exception exception)
        {
            
        }         
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
    
    public static Object mountlistener()
    {
        return null;
    }
    
    public static Object mountlisteners()
    {
        return null;
    }    
    
    public static Object unmountlistener()
    {
        return null;
    }    
   
    public static Object unmountlisteners()
    {
        return null;
    }  
    
    public static Object mountsubscriber()
    {
        return null;
    }
    
    public static Object mountsubscribers()
    {
        return null;
    }    
    
    public static Object unmountsubscriber()
    {
        return null;
    }    
   
    public static Object unmountsubscribers()
    {
        return null;
    }    
    
    public static Object getlistener()
    {
        return null;
    }
    
    public static Object getlisteners()
    {
        return null;
    }    
    
    public static Object getsubscriber()
    {
        return null;
    }    
   
    public static ArrayList<Apmlsubscriber> getsubscribers(String unique)
    {
        return null;
    }    
    
    public static Object doinstantiation(Class c)
    {
        try
        {
            return c.newInstance();
        }
        catch(InstantiationException | IllegalAccessException ex)
        {
            Logger.getLogger(Codemodelcompiler.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        
        return null;
    }
    
    public static void doputelementsonstartup(Map map)
    {
        Apmlsystem.map.putAll(map);        
    }
    
    public static void doputelementsonstartup(String[] names, Object[] objects)
    {
        for(int i=0;i<names.length;i++)
        {
            map.put(names[i],objects[i]);
        }
    }    
    
    public static Map dogetelements()
    {
        return Apmlsystem.map;
    }    
    
    public static Object dogetelement(Object o)
    {
        return Apmlsystem.map.get(o);
    }
}
