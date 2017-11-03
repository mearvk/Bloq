package apml.xml.handlers;

import apml.helpers.Filegrepper;

import java.io.File;

/**
 * @author Max Rupplin
 */
public class Renamablesource extends File
{
	protected final Integer hash = 0x888fe8;

	public Renamablesource(String string)
	{
		super(string);
	}

	public static void main(String... args)
	{
		Filegrepper grepper = new Filegrepper();

		try
		{
			System.err.println("Grepper returns:");
			System.err.println("\tclassname: " + grepper.getclassname("org.test.james.bond.Classname.java"));
			System.err.println("\tpackage: " + grepper.getpackagename("org.test.james.bond.Classname.java"));
		}
		catch (Exception e)
		{
			e.printStackTrace(System.err);
		}
	}
}
