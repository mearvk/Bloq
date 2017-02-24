/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apml.compilers;

import com.sun.codemodel.JCodeModel;
import java.util.ArrayList;

/**
 *
 * @author max rupplin
 */
public abstract class Bloqabstractoutputmanager 
{
    public ArrayList<JCodeModel> apmlmodels;  
    
    public ArrayList<JCodeModel> definitionmodels;
    
    public ArrayList<JCodeModel> dynamiclistenermodels;
    
    public ArrayList<JCodeModel> listenermodels;
    
    public ArrayList<JCodeModel> objectmodels;
    
    public ArrayList<JCodeModel> subscribermodels;
    
    public ArrayList<JCodeModel> systemmodels;    
}
