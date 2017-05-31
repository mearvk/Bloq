package apml.system;

import apml.compilers.java.codemodel.Bloqcompiler;

import apml.drivers.Stdbloqdriver;

import apml.drivers.Stddriver;

import apml.helpers.Fileloader;

import apml.listeners.Apmllistener;

import apml.modeling.Apmlsubscriber;

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
    public final Integer hash = 0x00888FE8;
    
    /*-------------------------------------------------------------------------*/

    public static ArrayList<Stdsystem> systems = new ArrayList();    
    
    public static ArrayList<Apmllistener> listeners = new ArrayList();
    
    public static ArrayList<Apmlsubscriber> subscribers = new ArrayList();
    
    /*-------------------------------------------------------------------------*/
    
    public Map properties = new HashMap();  
    
    /*-------------------------------------------------------------------------*/
    
    public Stddriver driver;    
    
    /*-------------------------------------------------------------------------*/
    
    public static Boolean loadbndi = true;
    
    public static Boolean loadsubsystems = true;
    
    public static Boolean displayloading = true;        
    
    /*-------------------------------------------------------------------------*/        
    
    public String basedir;
    
    public String apmlfile;
    
    /*-------------------------------------------------------------------------*/          
    
    public ArrayList<String> classnames = new ArrayList();
    
    public ArrayList<Class> classes = new ArrayList();
    
    /*-------------------------------------------------------------------------*/
    
    public static Map map = null;
    
    /*-------------------------------------------------------------------------*/
    
    public static short STATE;
    
    public static final short DO_SYSTEM_BOOTUP = 0x00000;
    
    public static final short DO_SYSTEM_INITIALIZE = 0x00001;
    
    public static final short DO_SYSTEM_RUN = 0x00002;
    
    public static final short DO_CLASSFILES_LOAD = 0x00003;
    
    public static final short DO_BODI_SETUP = 0x00004;
    
    
    /**
     * 
     * @param args 
     */
    public static void main(String...args)
    {
        Apmlsystem system = new Apmlsystem("/home/oem/Desktop/apml.xml", "/home/oem/Desktop/apml", new Stdbloqdriver());                
        
        system.execute(DO_BODI_SETUP);
        
        system.execute(DO_CLASSFILES_LOAD);              
        
        system.execute(DO_SYSTEM_BOOTUP);
        
        system.execute(DO_SYSTEM_INITIALIZE);
        
        system.execute(DO_SYSTEM_RUN);               
    }
    
    /**
     * 
     * @param apmlfile
     * @param basedir
     * @param driver 
     */
    public Apmlsystem(String apmlfile, String basedir, Stddriver driver)
    {
        this.setapmlfile(apmlfile);
        
        this.setbasedir(basedir);
        
        this.setdriver(driver);         
    }
    
    /**
     * 
     * @param apmlfile 
     */
    public final void setapmlfile(String apmlfile)
    {
        this.apmlfile = apmlfile;
    }
    
    /**
     * 
     * @param basedir 
     */
    public final void setbasedir(String basedir)
    {
        this.basedir = basedir;
    }
    
    /**
     * 
     * @param driver 
     */
    public final void setdriver(Stddriver driver)
    {
        this.driver = driver;
    }    
    
    /**
     * 
     * @param command 
     */
    public void execute(final short command)
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
            
            case DO_SYSTEM_BOOTUP: 
                
                Apmlsystem.STATE = DO_SYSTEM_BOOTUP; 
                
                this.start();                                 
                
                break;
                    
            case DO_SYSTEM_INITIALIZE: 
                
                Apmlsystem.STATE = DO_SYSTEM_INITIALIZE;  
                
                this.initialize();                                 
                
                break;
            
            case DO_SYSTEM_RUN: 
                
                Apmlsystem.STATE = DO_SYSTEM_RUN;  
                
                this.run(); 
                                                
                break;                        
            
            default: 
                
                return;
        }
    }
    
    /**
     * 
     */
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
        
        try
        {
            Bodi.context("//Apmlsystem").put("Apmlsystem", this);

            Bodi.context("//Apmlsystem/systems").put("Apmlsystem.systems", this.systems);

            Bodi.context("//Apmlsystem/classes").put("Apmlsystem.classes", this.classes);

            Bodi.context("//Apmlsystem/state").put("Apmlsystem.state", this.STATE);

            Bodi.context("//Apmlsystem/listeners").put("Apmlsystem.listeners", this.listeners);

            Bodi.context("//Apmlsystem.subscribers").put("Apmlsystem.subscribers", this.subscribers);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * 
     */
    public void start()
    {                      
        for(Stdsystem system: this.systems)
        {
            system.start();
        }                                 
    }
    
    /**
     * 
     */
    public void initialize()
    {
        for(Stdsystem system: this.systems)
        {
            system.initialize();
        }                             
    }
    
    /**
     * 
     */
    public void run()
    {
        for(Stdsystem system: this.systems)
        {
            system.run();
        }   
    }     
    
    /**
     * 
     * @param key
     * @param value 
     */
    public void put(Object key, Object value)
    {
        this.properties.put(key, value);
    }    
    
    /**
     * 
     * @param basedir 
     */
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
    
    /**
     * 
     * @param basedirs 
     */
    public void loadclasses(String[] basedirs)
    {       
        for(String basedir : basedirs)
        {
            this.loadclasses(basedir);
        }
    }
    
    /**
     * 
     * @param object
     * @param state
     * @throws Exception 
     */
    public void setproperty(Object object, Object state) throws Exception
    {
        Object certain = this.getproperty(object);
        
        if(certain==null) throw new Exception("System property ["+object+"] was not found; returning");
        
        this.properties.put(object, state);
    }
    
    /**
     * 
     * @param object
     * @return 
     */
    public Object getproperty(Object object)
    {
        return this.properties.get(object);
    }    
    
    /**
     * 
     * @param subscriber
     * @param ae
     * @return 
     */
    public static Object notify(Apmlsubscriber subscriber, ActionEvent ae)
    {
        subscriber.update(null, ae);
        
        return "success";
    }
    
    /**
     * 
     * @param subscribers
     * @param ae
     * @return 
     */
    public static Object notifyall(ArrayList<Apmlsubscriber> subscribers, ActionEvent ae)
    {
        for(Apmlsubscriber subscriber : subscribers)
        {
            subscriber.update(null, ae);
        }
        
        return "success";
    }     
    
    /**
     * 
     * @param subscriber
     * @param string
     * @return 
     */
    public static Object notify(Apmlsubscriber subscriber, String string)
    {
        subscriber.update(null, null);
        
        return "success";
    }
    
    /**
     * 
     * @param subscribers
     * @param string
     * @return 
     */
    public static Object notifyall(ArrayList<Apmlsubscriber> subscribers, String string)
    {
        for(Apmlsubscriber subscriber : subscribers)
        {
            subscriber.update(null, null);
        }
        
        return "success";        
    }    
    
    /**
     * 
     * @param listener
     * @return 
     */
    public Object mountlistener(Apmllistener listener)
    {
        this.listeners.add(listener);
        
        return "success";
    }
    
    /**
     * 
     * @param listeners
     * @return 
     */
    public Object mountlisteners(ArrayList<Apmllistener> listeners)
    {
        for(Apmllistener listener : listeners)
        {
            this.listeners.add(listener);
        }
        
        return "success";
    }    
    
    /**
     * 
     * @param listener
     * @return 
     */
    public Object unmountlistener(Apmllistener listener)
    {
        this.listeners.remove(listener);
        
        return "success";
    }    
   
    /**
     * 
     * @param listeners
     * @return 
     */
    public Object unmountlisteners(ArrayList<Apmllistener> listeners)
    {
        this.listeners.removeAll(listeners);
        
        return "success";
    }  
    
    /**
     * 
     * @param subscriber
     * @return 
     */
    public Object mountsubscriber(Apmlsubscriber subscriber)
    {
        this.subscribers.add(subscriber);
        
        return "success";
    }
    
    /**
     * 
     * @param subscribers
     * @return 
     */
    public Object mountsubscribers(ArrayList<Apmlsubscriber> subscribers)
    {
        for(Apmlsubscriber subscriber : subscribers)
        {
            this.subscribers.addAll(subscribers);
        }
        
        return "success";
    }    
    
    /**
     * 
     * @param subscriber
     * @return 
     */
    public Object unmountsubscriber(Apmlsubscriber subscriber)
    {
        this.subscribers.remove(subscriber);
        
        return "success";
    }    
   
    /**
     * 
     * @param subscribers
     * @return 
     */
    public Object unmountsubscribers(ArrayList<Apmlsubscriber> subscribers)
    {
        this.subscribers.removeAll(subscribers);
        
        return "success";
    }    
    
    /**
     * 
     * @param hashcode
     * @return 
     */
    public Object getlistener(Integer hashcode)
    {
        Apmllistener listener = (Apmllistener)Bodi.context("//Apmlsystem/listeners").pull(hashcode);
        
        return listener;        
    }
    
    /**
     * 
     * @param bodistring
     * @return 
     */
    public Object getlisteners(String bodistring)
    {
        Apmllistener listeners = (Apmllistener)Bodi.context("//Apmlsystem/listeners").pull(bodistring);
        
        return listeners; 
    }    
    
    /**
     * 
     * @param bodistring
     * @return 
     */
    public Object getsubscriber(String bodistring)
    {
        Apmllistener subscriber = (Apmllistener)Bodi.context("//Apmlsystem/subscribers").pull(bodistring);
        
        return subscriber;        
    }    
   
    /**
     * 
     * @param unique
     * @return 
     */
    public ArrayList<Apmlsubscriber> getsubscribers(String unique)
    {
        ArrayList<Apmlsubscriber> subscribers = (ArrayList<Apmlsubscriber>)Bodi.context("//Apmlsystem/subscribers").pull("Apmlsystem.subscribers");
        
        return subscribers; 
    }    
    
    /**
     * 
     * @param c
     * @return 
     */
    public static Object doinstantiation(Class c)
    {
        try
        {
            return c.newInstance();
        }
        catch(InstantiationException | IllegalAccessException ex)
        {
            Logger.getLogger(Bloqcompiler.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        
        return null;
    }
    
    /**
     * 
     * @param map 
     */
    public static void doputelementsonstartup(Map map)
    {
        Apmlsystem.map.putAll(map);        
    }
    
    /**
     * 
     * @param names
     * @param objects 
     */
    public static void doputelementsonstartup(String[] names, Object[] objects)
    {
        for(int i=0;i<names.length;i++)
        {
            map.put(names[i],objects[i]);
        }
    }    
    
    /**
     * 
     * @return 
     */
    public static Map dogetelements()
    {
        return Apmlsystem.map;
    }    
    
    /**
     * 
     * @param o
     * @return 
     */
    public static Object dogetelement(Object o)
    {
        return Apmlsystem.map.get(o);
    }
}
