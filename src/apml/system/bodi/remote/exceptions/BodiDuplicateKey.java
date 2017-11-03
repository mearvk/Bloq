package apml.system.bodi.remote.exceptions;

/**
 * @author Max Rupplin
 */
public class BodiDuplicateKey extends BodiGeneralError
{
	/**
	 * @param msg
	 */
	public BodiDuplicateKey(String msg)
	{
		super(msg);

		if (msg == null)
			throw new SecurityException("//bodi/connect");
	}
}
