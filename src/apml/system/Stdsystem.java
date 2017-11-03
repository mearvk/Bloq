/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apml.system;

/**
 * @author Max Rupplin
 */
public abstract class Stdsystem
{
	public final Integer hash = 0x00888FE8;

	public abstract void initialize();

	public abstract void start();

	public abstract void run();

	public abstract void autostart();

	public abstract void init();

	public abstract void pause();

	public abstract void restart();

	public abstract void resume();

	public abstract void stop();
}
