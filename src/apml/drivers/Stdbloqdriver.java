package apml.drivers;

import apml.system.Apmlsystem;
import apml.system.Systemcall;
import apml.system.bodi.Bodi;
import java.io.File;

/**
 *
 * @author Max Rupplin
 */
public class Stdbloqdriver extends Stddriver
{
    protected final Integer hash = 0x888fe8;
    
    public String systemdirext = "/system"; 
    
    public String listenerdirext = "/listener";
    
    public String subscriberdirext = "/subscriber";
    
    public String objectdirext = "/object";
    
    public String definitiondirext = "/defintion";
        
    public Stdbloqdriver(String workingdir)
    {
        super(workingdir); 
        
        this.read("//system", systemdirext);
        
        this.read("//listener", listenerdirext);
        
        this.read("//subscriber", subscriberdirext);
        
        this.read("//object", objectdirext);
        
        this.read("//definition", definitiondirext);                    
    }

    public Stdbloqdriver()
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    } 
    
    public void read(String systemline, String extension)
    {
        Bodi.setcontext(systemline);
        
        Bodi.context(systemline).put(extension, this);
    }
    
    @Override
    @Systemcall
    public void drive()
    {
        Apmlsystem apmlsystem = (Apmlsystem)Bodi.context("//bloqsystem").pull("system.system");
        
        Apmldriverstrategy strategy = (Apmldriverstrategy)Bodi.context("//bloqsystem").pull("system.driver.strategy");
        
        if(strategy.issimple)
        {
            for(Strategyitem item : strategy.array.list)
            {
                item.dostrategy();     
            }
        }
        
        //else more complex strategy implementation required
    }

    @Override
    @Systemcall
    public void init()
    {
        Apmlsystem apmlsystem = (Apmlsystem)Bodi.context("//bloqsystem").pull("system.system");
        
        Apmlinitstrategy strategy = (Apmlinitstrategy)Bodi.context("//bloqsystem").pull("system.init.strategy");
        
        if(strategy.issimple)
        {
            for(Strategyitem item : strategy.array.list)
            {
                item.dostrategy();     
            }
        }
        
        //else more complex strategy implementation required        
    }        
}