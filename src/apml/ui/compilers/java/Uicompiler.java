package apml.ui.compilers.java;

import apml.system.bodi.Bodi;
import com.sun.codemodel.JCodeModel;
import java.util.ArrayList;

public class Uicompiler
{
    protected final Integer hash = 0x00888fe8;
    
    public Uifileguardian fileguardian = new Uifileguardian();
    
    public Uiinputmanager inputmanager = new Uiinputmanager(this); 
    
    public Uioutputmanager outputmanager = new Uioutputmanager(this);                
    
    public Uicompiler()
    {
        Bodi.setcontext("//bodi/version");
        
        Bodi.context("//bodi/version").put("version", "1.0");
        
        Bodi.context("//bodi/version").put("since", "04.18.2017");
    }
    
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