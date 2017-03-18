package apml.system;

import apml.compilers.java.codemodel.Codemodelcompiler;
import apml.drivers.Stdbloqdriver;
import apml.drivers.Stddriver;
import apml.helpers.Filegrepper;
import apml.helpers.Fileloader;
import apml.subscribers.Apmlsubscriber;
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
    
    public static ArrayList<String> classnames = new ArrayList();
    
    public static ArrayList<Class> classes = new ArrayList();
    
    public static Boolean loadbndi = true;
    
    public static Boolean loadsubsystems = true;
    
    public static Boolean displayloading = true;
    
    public static Map map = null;
    
    public Stddriver driver;    
    
    public String basedir;
    
    public String apmlfile;
    
    
    public static void main(String...args)
    {
        Apmlsystem system = new Apmlsystem("/home/oem/Desktop/apml.xml", "/home/oem/Desktop/apml", new Stdbloqdriver());                
        
        system.loadclasses("/home/oem/Desktop/UI/org/widgets");
        
        system.start();                
        
        system.initialize();                
        
        system.run();                
    }
    
    public Apmlsystem(String apmlfile, String basedir, Stddriver driver)
    {
        this.setapmlfile(apmlfile);
        
        this.setbasedir(basedir);
        
        this.setdriver(driver);         
    }
    
    public void setapmlfile(String apmlfile)
    {
        this.apmlfile = apmlfile;
    }
    
    public void setbasedir(String basedir)
    {
        this.basedir = basedir;
    }
    
    public void setdriver(Stddriver driver)
    {
        this.driver = driver;
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
            this.classnames = new Fileloader().loadclasses(new File(basedir), null, ".class", new ArrayList<String>());             
            
            for(String eachclass : this.classnames)
            {
                Apmlsystem.classes.add(Class.forName(eachclass));
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
