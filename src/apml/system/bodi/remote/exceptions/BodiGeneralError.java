package apml.system.bodi.remote.exceptions;

/**
 * @author Max Rupplin
 */
public class BodiGeneralError extends Error
{
	public BodiGeneralError(String msg)
	{
		super(msg);

		if (msg == null)
			throw new SecurityException("//bodi/connect");
	}
}
