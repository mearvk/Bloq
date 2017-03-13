package apml.ui.compilers.java;

import com.sun.codemodel.JCodeModel;
import java.util.ArrayList;

public class Stduserinterfacecompiler
{
    protected final Integer hash = 0x888fe8;
    
    public Uiinputmanager inputmanager = new Uiinputmanager(); 
    
    public Uioutputmanager outputmanager = new Uioutputmanager();
    
    public Uifileguardian fileguardian = new Uifileguardian();   
    
    
    public static void main(String...args)
    {
        Stduserinterfacecompiler compiler = new Stduserinterfacecompiler();
        
        compiler.setapmlfiles(compiler.inputmanager);
        
        compiler.setsourcefiles(compiler.outputmanager);        
    }    
    
    public void setapmlfiles(Uiinputmanager inputmanager)
    {
        for(String xpathmodel : inputmanager.xpathmodels.list)
        {
            inputmanager.generatejcmmodels(this.fileguardian.xmlin, xpathmodel);
        }        
    }
        
    public void setsourcefiles(Uioutputmanager outputmanager)
    {            
        for(ArrayList<JCodeModel> jcmmodel : this.inputmanager.jcmmodels.list)
        {
            outputmanager.generatejavafiles(jcmmodel);
        }      
    }
}