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
public class apmlmodelfile 
{   
    public String apml_autostart;    
    public String apml_classname;
    public String apml_extends;
    public String apml_idname;
    public String apml_initializable;        
    public String apml_packagename;
    public String apml_parent_node;
    public String apml_start;
    public String apml_run;
    public String apml_tagname;
        
    public String[] apml_implements;
    public String[] apml_listeners;
    public String[] apml_stdinterfaces;
    public String[] apml_taginterfaces;
    
    public ArrayList<String> children;
    public ArrayList<String> subscribers;
    public ArrayList<String> listeners;
    public ArrayList<String> localinterfaces;
    public ArrayList<String> localclasses;
    public ArrayList<String> nestedclasses;        
}
