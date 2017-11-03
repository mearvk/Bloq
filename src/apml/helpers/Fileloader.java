package apml.helpers;

import java.io.File;
import java.util.ArrayList;

/**
 * @author Max Rupplin
 */
public class Fileloader
{
	Filegrepper grepper = new Filegrepper();

	ArrayList<String> classes = new ArrayList();

	public static void main(String... args)
	{
		Fileloader loader = new Fileloader();

		ArrayList<String> classnames = loader.loadclasses(new File("/home/oem/Desktop/UI/org/widgets"), null, ".class", loader.classes);

		for (String name : classnames)
		{
			System.err.println(name);
		}
	}

	public ArrayList<String> loadclasses(File basedir, File current, String extension, ArrayList<String> classes)
	{
		if (current == null)
			current = basedir;

		try
		{
			for (File file : current.listFiles())
			{
				if (file.isDirectory())
				{
					loadclasses(basedir, file, extension, classes);
				}
				else if (file.getName().endsWith(extension))
				{
					classes.add(grepper.getclassnameforclassloader(basedir, file));
				}
			}
		}
		catch (Exception exception)
		{
			//
		}

		return classes;
	}
}
