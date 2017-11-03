package apml.examples.echoserver.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

/**
 * @author Max Rupplin
 */
public class Echoclient
{
	protected final Integer hash = 0x888fe8;

	public Socket socket;
}

class EchoServerClientSocketListener implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent ae)
	{

	}
}