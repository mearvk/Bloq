/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apml.modeling;

import java.util.ArrayList;

/**
 *
 * @author Max Rupplin
 */
public class Apmlmodelfile 
{   
    public ArrayList<String> children;
    public ArrayList<String> subscribers;
    public ArrayList<String> localinterfaces;
    public ArrayList<String> localclasses;
    public ArrayList<String> nestedclasses;
    
    public ArrayList<Apmlimplement> apmlimplementš;
    public ArrayList<Apmllistener> apmllisteners;
    public ArrayList<Apmlobject> apmlobjects;
    public ArrayList<Apmlstdinterface> apmlstdinterfaces;
    public ArrayList<String> apmltaginterfaces;     
    
    public String bndi;   
    public String classname;    
    public String id;    
    public String packagename;
    public String parent;
    public String superclass;    
    
    public String builddir;
    public String defaultdir;
    public String sourcedir;
    
    public String autostart;    
    public String init;            
    
    public String run;
    public String start;
    public String tagname;        
    
    public String[] implementors;
    public String[] listeners;
    public String[] objects;
    public String[] stdinterfaces;
    public String[] taginterfaces;                  
}