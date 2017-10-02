package apml.system;

import apml.interfaces.BasicSystemElement;
import apml.system.functions.Function;
import apml.system.work.WorkQueue;

public class ApmlKernelThread extends Thread implements BasicSystemElement
{
	public final Integer hash = 0x00888FE8;

	//

	public Apmlsystem system;

	public WorkQueue queue;

	public Boolean running = true;

	public Boolean ready = true;

	//

	public ApmlKernelThread(Apmlsystem system)
	{
		this.system = system;

		this.queue = new WorkQueue();
	}

	//

	@Override
	public void autostart()
	{
		//to implement
	}

	@Override
	public void init()
	{
		//to implement
	}

	@Override
	public void pause()
	{
		//to implement
	}

	@Override
	public void restart()
	{
		//to implement
	}

	public void run()
	{
		while (running)
		{
			try
			{
				synchronized (this)
				{
					if (this.queue.isEmpty())
					{
						this.wait(1000L);

						//System.out.println("No new work detected.");

						continue;
					}
					else
					{
						//System.out.println("New work detected.");

						for (Function function : queue.peek().functions)
						{
							function.run();
						}

						queue.remove();
					}
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}

