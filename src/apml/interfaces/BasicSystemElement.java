package apml.interfaces;

/**
 *
 * @author Max Rupplin 
 */
public interface BasicSystemElement
{
    public void autostart();
    
    public void init();
    
    public void pausable();
    
    public void restart();
    
    public void run();
    
    public void start();
    
    public void stop();
}
