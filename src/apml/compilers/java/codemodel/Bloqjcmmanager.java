/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apml.compilers.java.codemodel;

import com.sun.codemodel.JCodeModel;
import java.util.ArrayList;

/**
 *
 * @author Max Rupplin
 */
public class Bloqjcmmanager
{
    public ArrayList<JCodeModel> apml;  
    
    public ArrayList<JCodeModel> definitions;
    
    public ArrayList<JCodeModel> dynamiclisteners;
    
    public ArrayList<JCodeModel> listeners;
    
    public ArrayList<JCodeModel> objects;
    
    public ArrayList<JCodeModel> subscribers;
    
    public ArrayList<JCodeModel> systems;
}
