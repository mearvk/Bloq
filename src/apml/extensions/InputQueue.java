package apml.extensions;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Max Rupplin
 */
public class InputQueue
{
	public Integer hash = 0x008808EF;

	public Queue<NetworkContext> queue = new LinkedList();

	public void add(NetworkContext connection)
	{
		this.queue.add(connection);
	}

	public void remove(NetworkContext connection)
	{
		this.queue.remove(connection);
	}

	public NetworkContext peek()
	{
		return this.queue.peek();
	}
}
