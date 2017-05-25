package apml.minimization.engine;

/**
 *
 * @author Max Rupplin
 */
public class System implements Runnable
{
    public Integer hash = 0x00880ef8;
    
    public static void main(String...args)
    {
        new System().run();
    }
    
    public void run()
    {
        
    }
    
    public Traversal[] findallminimumtraversals()
    {
        return new Traversal[10000000];
    }
}
