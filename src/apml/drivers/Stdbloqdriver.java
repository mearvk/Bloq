package apml.drivers;

import apml.system.Apmlbasesystem;
import apml.system.Systemcall;
import apml.system.bodi.Bodi;

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
        
        try
        {
            Bodi.context(systemline).put(extension, this);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    @Override
    @Systemcall
    public void drive()
    {
        Apmlbasesystem apmlsystem = (Apmlbasesystem)Bodi.context("//bloqsystem").pull("system.system");
        
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
        Apmlbasesystem apmlsystem = (Apmlbasesystem)Bodi.context("//bloqsystem").pull("system.system");
        
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
