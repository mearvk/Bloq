/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apml.modeling;

import apml.system.Apmlbasesystem;

import java.util.Observable;
import java.util.Observer;

/**
 * @author Max Rupplin
 */
public class Apmldriver implements Observer
{
	protected final Integer hash = 0x888fe8;

	public Apmlbasesystem monitor;

	//

	public Boolean autostartable;

	public Boolean startable;

	public String id;

	public String alias;

	public String classname;

	public String extension;

	public String packagename;

	//

	public void initialize()
	{
	}

	public void start()
	{
	}

	public void run()
	{
	}

	public void autostart()
	{
	}

	public void init()
	{
	}

	public void pause()
	{
	}

	public void restart()
	{
	}

	public void resume()
	{
	}

	public void stop()
	{
	}

	//

	@Override
	public void update(Observable o, Object o1)
	{
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}
