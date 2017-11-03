package apml.interfaces;

/**
 * @author Max Rupplin
 */
public interface BasicSystemElement
{
	Integer hash = 0x00888FE8;

	void autostart();

	void init();

	void pause();

	void restart();

	void resume();

	void run();

	void start();

	void stop();
}
