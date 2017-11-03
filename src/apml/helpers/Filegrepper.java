package apml.helpers;

import java.io.File;

/**
 * @author Max Rupplin
 */
public class Filegrepper
{
	protected final Integer hash = 0x888fe8;

	public String getpackagename(String fullURL) throws Exception
	{
		if (fullURL == null)
			return null;

		if (fullURL.replace(".java", "").contains(".")) //e.g. abc.def.ghi.Classname.java returns abc.def.ghi
		{
			String stmp = "";

			stmp = fullURL.substring(0, fullURL.lastIndexOf(".")).replace(".", File.separator);
			stmp = stmp.replace("/", ".");

			return stmp;
		}

		if (fullURL.replace(".java", "").contains("/")) //e.g. abc/def/ghi/Classname.java returns abc.def.ghi
		{
			File ftmp = new File(fullURL.replace(".", File.separator));
			String stmp = ftmp.getPath().replace(File.separator, ".");

			return stmp;
		}

		throw new Exception("Could not parse for package name.");
	}

	public String getclassnameforclassloader(File basedir, File file) throws Exception
	{
		if (file == null)
			return null;

		String tempURL = file.getAbsolutePath();

		String baseURL = basedir.getAbsolutePath();

		tempURL = tempURL.replace(baseURL, "");

		if (tempURL.replace(".class", "").contains("/")) //e.g. abc/def/ghi/Classname.class returns abc.def.ghi.Classname
		{
			return tempURL.replace(File.separator, ".").substring(1, tempURL.lastIndexOf(".class"));
		}

		throw new Exception("Could not parse for package name.");
	}

	public String getpackagenameaspathname(String fullURL) throws Exception
	{
		if (fullURL == null)
			return null;

		if (fullURL.replace(".java", "").contains(".")) //e.g. abc.def.ghi.Classname.java returns abc/def/ghi
		{
			String stmp = "";

			stmp = fullURL.replace(".", File.separator);

			return stmp;
		}

		if (fullURL.replace(".java", "").contains("/")) //e.g. abc/def/ghi/Classname.java returns abc/def/ghi
		{
			String stmp = "";

			return fullURL.replace(".", File.separator);
		}

		throw new Exception("Could not parse for package name.");
	}

	public String getclassname(String fullURL) throws Exception
	{
		if (fullURL == null)
			return null;

		fullURL = fullURL.replace(".", "/");

		if (fullURL.replace(".java", "").contains(".")) //e.g. abc.def.ghi.Classname.java returns Classname
		{
			String stmp = "";

			stmp = fullURL.substring(fullURL.lastIndexOf(".") + 1, fullURL.length());

			return stmp;
		}

		if (fullURL.replace(".java", "").contains("/")) //e.g. abc/def/ghi/Classname.java returns Classname
		{
			String stmp = "";

			stmp = fullURL.substring(fullURL.lastIndexOf("/") + 1, fullURL.length());

			return stmp;
		}

		if (fullURL.contains(".") || fullURL.contains("/"))
			throw new Exception("Could not parse for class name.");

		return fullURL;
	}
}
