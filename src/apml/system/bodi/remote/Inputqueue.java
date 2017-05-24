package apml.system.bodi.remote;

import java.util.LinkedList;

import java.util.Queue;

/**
 *
 * @author Max Rupplin
 */
class Inputqueue 
{
    public Queue<Networkconnectioncontext> queue = new LinkedList();
    
    public void add(Networkconnectioncontext connection)
    {
        this.queue.add(connection);
    }
    
    public void remove(Networkconnectioncontext connection)
    {
        this.queue.remove(connection);
    }
    
    public Networkconnectioncontext peek()
    {
        return this.queue.peek();
    }
}
