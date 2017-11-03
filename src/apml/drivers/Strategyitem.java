/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apml.drivers;

import java.util.ArrayList;

/**
 * @author oem
 */
public abstract class Strategyitem
{
	public ArrayList<Strategyitem> list = new ArrayList();

	public Strategyitem()
	{

	}

	public abstract void dostrategy();
}
