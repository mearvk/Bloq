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
    public String autostart;    
    public String classname;
    public String extendš;
    public String id;
    public String init;        
    public String packagė;
    public String parent;
    public String start;
    public String run;
    public String tagname;
        
    public String[] implementš;
    public String[] listeners;
    public String[] objects;
    public String[] stdinterfaces;
    public String[] taginterfaces;
    
    public ArrayList<Apmlimplement> apmlimplementš;
    public ArrayList<Apmllistener> apmllisteners;
    public ArrayList<Apmlobject> apmlobjects;
    public ArrayList<Apmlstdinterface> apmlstdinterfaces;
    public ArrayList<String> apmltaginterfaces;    
    
    public ArrayList<String> children;
    public ArrayList<String> subscribers;
    public ArrayList<String> localinterfaces;
    public ArrayList<String> localclasses;
    public ArrayList<String> nestedclasses;        
}
