package apml.system.functions;

import apml.system.work.Work;

public class DisplayMessage extends Function
{
	public Work work = null;

	public String message = "";

	//

	public DisplayMessage(Work work)
	{
		this.work = work;
	}

	public DisplayMessage(Work work, String message)
	{
		this.work = work;

		this.message = message;
	}

	public void run()
	{
		System.out.println("Message: " + message);
	}
}
