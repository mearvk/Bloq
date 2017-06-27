package apml.modeling;

import java.util.ArrayList;

/**
 *
 * @author Max Rupplin
 * @since 03.29.2017
 */
public class Apmlmodelfile 
{   
    protected final Integer hash = 0x888fe8;
        
    public ArrayList<String> children = new ArrayList();
    
    public ArrayList<String> subscribers = new ArrayList();
    
    public ArrayList<String> localinterfaces = new ArrayList();
    
    public ArrayList<String> localclasses = new ArrayList();
    
    public ArrayList<String> nestedclasses = new ArrayList();
    
    /* ------------------------------------------------------------------------*/
    
    public ArrayList<Apmlimplement> apmlimplements = new ArrayList();
    
    public ArrayList<Apmllistener> apmllisteners = new ArrayList();
    
    public ArrayList<Apmlobject> apmlobjects = new ArrayList();
    
    public ArrayList<Apmlstdinterface> apmlstdinterfaces = new ArrayList();
    
    public ArrayList<Apmlsubscriber> apmlsubscribers = new ArrayList();
    
    public ArrayList<Apmlsystem> apmlsystems = new ArrayList();
    
    public ArrayList<String> apmltaginterfaces = new ArrayList();     
    
    /* ------------------------------------------------------------------------*/
    
    public String bodi;   
    
    public String classname;        
    
    public String id;    
    
    public String packagename;
    
    public String parent;
    
    public String superclass;    
    
    /* ------------------------------------------------------------------------*/
    
    public String builddir;
    
    public String defaultdir;
    
    public String sourcedir;
    
    /* ------------------------------------------------------------------------*/
    
    public String autostart;    
    
    public String init;            
    
    public String run;
    
    public String start;
    
    public String tagname;        
    
    /* ------------------------------------------------------------------------*/
    
    public String[] implementors = new String[1];
    
    public String[] listeners = new String[1];
    
    public String[] objects = new String[1];
    
    public String[] stdinterfaces = new String[1];
    
    public String[] taginterfaces = new String[1];                  
}