package apml.system;

import apml.compilers.java.codemodel.Codemodelcompiler;
import apml.drivers.Stdbloqdriver;
import apml.drivers.Stddriver;
import apml.helpers.Filegrepper;
import apml.helpers.Fileloader;
import apml.listeners.Apmllistener;
import apml.subscribers.Apmlsubscriber;
import apml.system.bodi.Bodi;
import java.awt.event.ActionEvent;
import java.io.File;
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
    
    public ArrayList<Stdsystem> systems = new ArrayList();
                   
    public Map properties = new HashMap();                
    
    public static  ArrayList<Apmllistener> listeners = new ArrayList();
    
    public static ArrayList<Apmlsubscriber> subscribers = new ArrayList();
    
    public ArrayList<String> classnames = new ArrayList();
    
    public ArrayList<Class> classes = new ArrayList();
    
    public static Boolean loadbndi = true;
    
    public static Boolean loadsubsystems = true;
    
    public static Boolean displayloading = true;
    
    public static Map map = null;
    
    public Stddriver driver;    
    
    public String basedir;
    
    public String apmlfile;
    
    public static final short DO_SYS_BOOTUP = 0;
    
    public static final short DO_SYS_INITIALIZE = 1;
    
    public static final short DO_SYS_RUN = 2;
    
    public static final short DO_CLASSFILES_LOAD = 3;
    
    public static final short DO_BODI_SETUP = 4;
    
    public static short STATE;
    
    public static void main(String...args)
    {
        Apmlsystem system = new Apmlsystem("/home/oem/Desktop/apml.xml", "/home/oem/Desktop/apml", new Stdbloqdriver());                
        
        system.moveorexecute(DO_BODI_SETUP); //@healo @^christina applegate $^ //mr //ss //ok
        
        system.moveorexecute(DO_CLASSFILES_LOAD);              
        
        system.moveorexecute(DO_SYS_BOOTUP);
        
        system.moveorexecute(DO_SYS_INITIALIZE);
        
        system.moveorexecute(DO_SYS_RUN);               
    }
    
    public Apmlsystem(String apmlfile, String basedir, Stddriver driver)
    {
        this.setapmlfile(apmlfile);
        
        this.setbasedir(basedir);
        
        this.setdriver(driver);         
    }
    
    public final void setapmlfile(String apmlfile)
    {
        this.apmlfile = apmlfile;
    }
    
    public final void setbasedir(String basedir)
    {
        this.basedir = basedir;
    }
    
    public final void setdriver(Stddriver driver)
    {
        this.driver = driver;
    }    
    
    public void moveorexecute(final short command)
    {
        switch(command)
        {
            case DO_BODI_SETUP: 
            
                Apmlsystem.STATE = DO_BODI_SETUP; 
                
                this.loadbodi();                                
                
                break;
            
            case DO_CLASSFILES_LOAD:
                
                Apmlsystem.STATE = DO_CLASSFILES_LOAD;  
                
                this.loadclasses(basedir);                                 
                
                break;
            
            case DO_SYS_BOOTUP: 
                
                Apmlsystem.STATE = DO_SYS_BOOTUP; 
                
                this.start();                                 
                
                break;
                    
            case DO_SYS_INITIALIZE: 
                
                Apmlsystem.STATE = DO_SYS_INITIALIZE;  
                
                this.initialize();                                 
                
                break;
            
            case DO_SYS_RUN: 
                
                Apmlsystem.STATE = DO_SYS_RUN;  
                
                this.run(); 
                                                
                break;                        
            
            default: 
                
                return;
        }
    }
    
    public void loadbodi()
    {
        /* ------------------------ set contexts -------------------------------*/
        
        Bodi.setcontext("//Apmlsystem");
        
        Bodi.setcontext("//Apmlsystem/systems");
        
        Bodi.setcontext("//Apmlsystem/classes");
        
        Bodi.setcontext("//Apmlsystem/state");
        
        Bodi.setcontext("//Apmlsystem/listeners");
        
        Bodi.setcontext("//Apmlsystem/subscribers");
        
        /* ------------------------ set instances -------------------------------*/
        
        Bodi.context("//Apmlsystem").put("Apmlsystem", this);
        
        Bodi.context("//Apmlsystem/systems").put("Apmlsystem.systems", this.systems);
        
        Bodi.context("//Apmlsystem/classes").put("Apmlsystem.classes", this.classes);
        
        Bodi.context("//Apmlsystem/state").put("Apmlsystem.state", Apmlsystem.STATE);
        
        Bodi.context("//Apmlsystem/listeners").put("Apmlsystem.listeners", Apmlsystem.listeners);
        
        Bodi.context("//Apmlsystem.subscribers").put("Apmlsystem.subscribers", Apmlsystem.subscribers);
    }
    
    public void start()
    {                      
        for(Stdsystem system: this.systems)
        {
            system.start();
        }                                 
    }
    
    public void initialize()
    {
        for(Stdsystem system: this.systems)
        {
            system.initialize();
        }                             
    }
    
    public void run()
    {
        for(Stdsystem system: this.systems)
        {
            system.run();
        }   
    }     
    
    public void put(Object key, Object value)
    {
        this.properties.put(key, value);
    }    
    
    public void loadclasses(String basedir)
    {
        try
        {                    
            classnames = new Fileloader().loadclasses(new File(basedir), null, ".class", new ArrayList());             
            
            for(String eachclass : classnames)
            {
                this.classes.add(Class.forName(eachclass));
            }
        }
        catch(Exception exception)
        {
            System.out.println(exception);
        }
    }
    
    public void loadclasses(String[] basedirs)
    {       
        for(String basedir : basedirs)
        {
            this.loadclasses(basedir);
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
        subscriber.update(null, ae);
        
        return "success";
    }
    
    public static Object notifyall(ArrayList<Apmlsubscriber> subscribers, ActionEvent ae)
    {
        for(Apmlsubscriber subscriber : subscribers)
        {
            subscriber.update(null, ae);
        }
        
        return "success";
    }     
    
    public static Object notify(Apmlsubscriber subscriber, String string)
    {
        subscriber.update(null, null);
        
        return "success";
    }
    
    public static Object notifyall(ArrayList<Apmlsubscriber> subscribers, String string)
    {
        for(Apmlsubscriber subscriber : subscribers)
        {
            subscriber.update(null, null);
        }
        
        return "success";        
    }    
    
    public Object mountlistener(Apmllistener listener)
    {
        this.listeners.add(listener);
        
        return "success";
    }
    
    public Object mountlisteners(ArrayList<Apmllistener> listeners)
    {
        for(Apmllistener listener : listeners)
        {
            this.listeners.add(listener);
        }
        
        return "success";
    }    
    
    public Object unmountlistener(Apmllistener listener)
    {
        this.listeners.remove(listener);
        
        return "success";
    }    
   
    public Object unmountlisteners(ArrayList<Apmllistener> listeners)
    {
        this.listeners.removeAll(listeners);
        
        return "success";
    }  
    
    public Object mountsubscriber(Apmlsubscriber subscriber)
    {
        this.subscribers.add(subscriber);
        
        return "success";
    }
    
    public Object mountsubscribers(ArrayList<Apmlsubscriber> subscribers)
    {
        for(Apmlsubscriber subscriber : subscribers)
        {
            this.subscribers.addAll(subscribers);
        }
        
        return "success";
    }    
    
    public Object unmountsubscriber(Apmlsubscriber subscriber)
    {
        this.subscribers.remove(subscriber);
        
        return "success";
    }    
   
    public Object unmountsubscribers(ArrayList<Apmlsubscriber> subscribers)
    {
        this.subscribers.removeAll(subscribers);
        
        return "success";
    }    
    
    public Object getlistener(Integer hashcode)
    {
        Apmllistener listener = (Apmllistener)Bodi.context("//Apmlsystem/listeners").pull(hashcode);
        
        return listener;        
    }
    
    public Object getlisteners(String bodistring)
    {
        Apmllistener listeners = (Apmllistener)Bodi.context("//Apmlsystem/listeners").pull(bodistring);
        
        return listeners; 
    }    
    
    public Object getsubscriber(String bodistring)
    {
        Apmllistener subscriber = (Apmllistener)Bodi.context("//Apmlsystem/subscribers").pull(bodistring);
        
        return subscriber;        
    }    
   
    public ArrayList<Apmlsubscriber> getsubscribers(String unique)
    {
        ArrayList<Apmlsubscriber> subscribers = (ArrayList<Apmlsubscriber>)Bodi.context("//Apmlsystem/subscribers").pull("Apmlsystem.subscribers");
        
        return subscribers; 
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
