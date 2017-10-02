package apml.system.functions;

import apml.system.work.Work;

public class DisplayWorkCompletedMessage extends Function
{
	public Work work = null;

	//

	public DisplayWorkCompletedMessage(Work work)
	{
		this.work = work;
	}

	public void run()
	{
		System.out.println("Work completed : " + work.toString());
	}
}
