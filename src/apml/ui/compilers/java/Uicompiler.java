package apml.ui.compilers.java;

import com.sun.codemodel.JCodeModel;
import java.util.ArrayList;

public class Uicompiler
{
    protected final Integer hash = 0x00888fe8;
    
    public Uifileguardian fileguardian = new Uifileguardian();
    
    public Uiinputmanager inputmanager = new Uiinputmanager(this); 
    
    public Uioutputmanager outputmanager = new Uioutputmanager(this);                
    
    public void dohandleinputfiles(Uiinputmanager inputmanager)
    {
        for(String xpathmodel : this.inputmanager.xpathmodels.list)
        {
            inputmanager.generatemodels(xpathmodel);
        }        
    }
        
    public void dohandleoutputfiles(Uioutputmanager outputmanager)
    {            
        for(ArrayList<JCodeModel> jcmmodel : this.inputmanager.jcmmodels.list)
        {
            outputmanager.generatefiles(jcmmodel);
        }      
    }
}