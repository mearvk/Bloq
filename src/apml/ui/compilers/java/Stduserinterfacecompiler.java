package apml.ui.compilers.java;

import com.sun.codemodel.JCodeModel;
import java.util.ArrayList;

public class Stduserinterfacecompiler
{
    protected final Integer hash = 0x888fe8;
    
    public Uiapmlmanager apmlmanager = new Uiapmlmanager(); 
    
    public Uioutputmanager outputmanager = new Uioutputmanager();
    
    public Uifileguardian fileguardian = new Uifileguardian();   
    
    
    public static void main(String...args)
    {
        Stduserinterfacecompiler compiler = new Stduserinterfacecompiler();
        
        compiler.setapmlfiles(compiler.apmlmanager);
        
        compiler.setsourcefiles(compiler.outputmanager);        
    }    
    
    public void setapmlfiles(Uiapmlmanager apmlmanager)
    {
        for(String xpathmodel : apmlmanager.xpathmodels.list)
        {
            apmlmanager.generatejcmmodels(this.fileguardian.xmlin, xpathmodel);
        }        
    }        
        
    public void setsourcefiles(Uioutputmanager outputmanager)
    {            
        for(ArrayList<JCodeModel> jcmmodel : apmlmanager.jcmmodels.list)
        {
            outputmanager.generatejavafiles(jcmmodel);
        }      
    }
    
    public void setoutputfiles()
    {
        
    }
    
    private void doapmlfiles()
    {
        
    }
    
    public void settempfiles(Uiapmlmanager apmlmanager)
    {
       
    }    
}