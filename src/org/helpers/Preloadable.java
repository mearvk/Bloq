package org.helpers;

public class Preloadable extends Thread
{
	public Class c = null;		/*class*/

	public Boolean r = null;	/*register*/

	public Long pre = 0l; 		/*predelay*/

	public Long post = 0l;		/*postdelay*/

	public Preloadable(Class c, Boolean r, Long pre, Long post)
	{
		this.c = c;

		this.r = r;

		this.pre = pre;

		this.post = post;
	}

	@Override
	public void run()
	{
		try
		{
			c.newInstance();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}