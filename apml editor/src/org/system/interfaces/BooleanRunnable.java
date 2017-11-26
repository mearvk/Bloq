package org.system.interfaces;

public interface BooleanRunnable
{
	public Boolean run();

	public Boolean run(Object object);

	public Boolean ex_run() throws Exception;

	public Boolean ex_run(Object object) throws Exception;
}
