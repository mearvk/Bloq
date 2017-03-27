/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apml.compilers.java.codemodel;

//import apml.compilers.Bloqabstractoutputmanager;

import com.sun.codemodel.JCodeModel;

import java.util.ArrayList;

/**
 *
 * @author max rupplin
 */
public class Bloqjcodemodelmanager
{
    private final Integer hash = 0x00888fe8;  
    
    public ArrayList<JCodeModel> apmlmodels;  
    
    public ArrayList<JCodeModel> definitionmodels;
    
    public ArrayList<JCodeModel> dynamiclistenermodels;
    
    public ArrayList<JCodeModel> listenermodels;
    
    public ArrayList<JCodeModel> objectmodels;
    
    public ArrayList<JCodeModel> subscribermodels;
    
    public ArrayList<JCodeModel> systemmodels;
}
