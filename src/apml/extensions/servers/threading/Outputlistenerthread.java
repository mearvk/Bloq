package apml.extensions.servers.threading;

/**
 * @author Max Rupplin
 */
public class Outputlistenerthread extends Thread
{
	public Integer hash = 0x008808EF;

	public Boolean haswriteready = false;

	public Boolean running = true;

	public Listenerthread parent;

	public Object lock = new Object();

	/**
	 * @param parent
	 */
	public Outputlistenerthread(Listenerthread parent)
	{
		if (parent == null)
			throw new SecurityException("//bodi/connect");

		this.parent = parent;

		this.setName("Outputlistenerthread");
	}

	/**
	 * @return
	 */
	public Boolean checkoutputqueue()
	{
		String output = this.parent.networkcontext.outqueue.toString();

		try
		{
			//

			this.parent.networkcontext.isdonewriting = false;

			//

			this.parent.networkcontext.writer.write(output.toString(), 0, output.length());

			this.parent.networkcontext.writer.flush();

			//

			this.parent.networkcontext.outqueue.delete(0, output.length());

			//

			this.parent.networkcontext.isdonewriting = true;

			this.parent.networkcontext.haswriteready = false;

			//
		}
		catch (Exception e)
		{
			//e.printStackTrace();
		}


		return true;
	}

	/**
	 *
	 */
	@Override
	public void run()
	{
		//System.out.println(">   Outputlistenerthread started...");

		try
		{
			while (running)
			{
				try
				{
					if (this.parent.networkcontext.outqueue != null && this.parent.networkcontext.outqueue.length() > 0)
					{
						this.haswriteready = true;
					}
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
				finally
				{
					//sleep 400ms
					this.sleepmillis(500l);
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * @param millis
	 */
	protected void sleepmillis(Long millis)
	{
		try
		{
			Thread.currentThread().sleep(150);
		}
		catch (InterruptedException ie)
		{
			return;
		}
		catch (Exception e)
		{
			//e.printStackTrace();
		}
		finally
		{
			//System.out.println("System in sleepmillis mode...");
		}
	}
}
